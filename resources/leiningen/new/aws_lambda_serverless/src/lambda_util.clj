(ns {{name}}.lambda-util
  (:require [clojure.tools.logging :as log]
            [clojure.string :as string]
            [clojure.main :refer [demunge]]
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

;; https://stackoverflow.com/questions/22116257/how-to-get-functions-name-as-string-in-clojure
(defn fn-name
  [f]
  (as-> (str f) $
        (demunge $)
        (or (re-find #"(.+)--\d+@" $)
            (re-find #"(.+)@" $))
        (last $)))

(defn lambda-handler
  "Logging etc around lambda function"
  [execute-fn ^InputStream input ^OutputStream output]
  (try
    (let [execute-fn-name (fn-name execute-fn)
          version (or (manifest-version) "")]
      (log/info (str "Starting {{name}} function " execute-fn-name " " version))
      (swap! start-counter inc)
      (log/info "Lambda instance" instance-id "has been invoked" @start-counter "times including current invocation.")

      (execute-fn input output)

      (log/info "Lambda successful")
      )
    (catch Exception ex
      (log/error "Lambda failed:" (.getMessage ex))
      (throw ex))
    (finally
      (log/info "Lambda finishing."))) )

(defn main-handler [handler-fns args]
  (when (< (count args) 1)
    (throw (new Exception (str "Wrong number of args. Use one of " (vec (map name (keys handler-fns)))))))
  (let [arg (nth args 0)
        handler-fn (handler-fns (keyword arg))]
    (when-not handler-fn
      (throw (new Exception (str "Wrong arg. Use one of " (vec (map name (keys handler-fns)))))))
    (try
      (lambda-handler handler-fn System/in System/out)
      (finally
        (shutdown-agents)))))
