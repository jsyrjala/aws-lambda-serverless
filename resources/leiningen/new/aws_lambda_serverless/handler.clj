(ns {{name}}.handler
  "FIXME AWS Lambda to do XXX"
  (:require [clojure.tools.logging :as log])
  (:import [java.io InputStream OutputStream]
           [java.util UUID]
           [java.util.jar Manifest])
  (:gen-class
    ;; Need to define Lambda handler method signature here so that AWS can find the method
    :methods [^:static [handler [java.io.InputStream java.io.OutputStream] void]])
  )

(defn lambda-execute
  "FIXME Business happens here"
  [^InputStream input ^OutputStream output]
  ;; FIXME Add lambda implementation here
  (.write output (.getBytes "TODO implement me\n" "UTF-8"))
  )

;;;;;;;;;;;;;;;;;;;;;;

(defn manifest-version
  "Fetch project version from META-INF/MANIFEST.MF. Works only on uberjar."
  []
  (let [class-loader (.getClassLoader (class manifest-version))
        url (.getResource class-loader "META-INF/MANIFEST.MF")
        manifest (when url (new Manifest (.openStream url)))
        attributes (when manifest (.getMainAttributes manifest))]
    (when attributes
      (.getValue attributes "Implementation-Version"))))

;; Counter to track how many times this process has been invoked.
(def start-counter (atom 0))
;; Unique id for this process
(def instance-id (.toString (UUID/randomUUID)))

(defn lambda-handler
  "Business happens here"
  [execute-fn ^InputStream input ^OutputStream output]
  (try
    (log/info "Starting {{name}}" (or (manifest-version) ""))
    (swap! start-counter inc)
    (log/info "Lambda instance" instance-id "has been invoked" @start-counter "times including current invocation.")

    (lambda-execute input output)

    (log/info "Lambda successful")
    (catch Exception ex
      (log/error "Lambda failed:" (.getMessage ex))
      (throw ex))
    (finally
      (log/info "Lambda finishing."))) )

(defn -handler
  "AWS Lambda entry point"
  [^InputStream input ^OutputStream output]
  (lambda-handler lambda-execute input output))

(defn -main
  "Java main method. For testing. Reads stdin.
    e.g java -jar lambda.jar < file"
  []
  (try
    (lambda-handler lambda-execute System/in System/out)
    (finally
      (shutdown-agents))))
