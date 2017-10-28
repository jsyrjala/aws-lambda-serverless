(ns {{name}}.handler
  "FIXME AWS Lambda to do XXX"
  (:require [clojure.tools.logging :as log]
            [{{name}}.lambda-util :as lambda-util])
  (:import [java.io InputStream OutputStream]
           [java.util UUID])
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

;; Counter to track how many times this process has been invoked.
(def start-counter (atom 0))
;; Unique id for this process
(def instance-id (.toString (UUID/randomUUID)))

(defn lambda-handler
  "Business happens here"
  [execute-fn ^InputStream input ^OutputStream output]
  (try
    (log/info "Starting {{name}}" (or (lambda-util/manifest-version) ""))
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
