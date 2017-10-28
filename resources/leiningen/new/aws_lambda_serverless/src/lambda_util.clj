(ns {{name}}.lambda-util
  (:require [clojure.tools.logging :as log]
    )
  (:import [java.io InputStream OutputStream]
           [java.util UUID]
           [java.util.jar Manifest])
  )

(defn manifest-version
  "Fetch project version from META-INF/MANIFEST.MF. Works only on uberjar."
  []
  (let [^java.lang.ClassLoader class-loader (.getClassLoader (class manifest-version))
        ;; this causes reflection warning, but works
        ^java.net.URL url (.findResource class-loader "META-INF/MANIFEST.MF")
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

    (execute-fn input output)

    (log/info "Lambda successful")
    (catch Exception ex
      (log/error "Lambda failed:" (.getMessage ex))
      (throw ex))
    (finally
      (log/info "Lambda finishing."))) )