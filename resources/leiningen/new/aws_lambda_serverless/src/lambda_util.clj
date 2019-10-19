(ns {{namespace}}.lambda-util
  "Utility code for AWS Lambda"
  (:require
    [clojure.java.io :as jio]
    [clojure.main :refer [demunge]]
    [clojure.string :as string]
    [clojure.tools.logging :as log]
    [cheshire.core :as json]
    )
  (:import
    [java.io InputStream OutputStream]
    [java.util UUID]
    [java.util.jar Manifest])
  )

(defn manifest-version
  "Fetch project version from META-INF/MANIFEST.MF. Works only on uberjar."
  []
  (let [^java.lang.ClassLoader class-loader (.getClassLoader (class manifest-version))
        ;; this causes reflection warning, but works
        ^java.net.URL url (.getResource class-loader "META-INF/MANIFEST.MF")
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


(defn parse-json
  "Read input stream and parse it eagerly as JSON"
  [input-stream]
  (json/parse-stream-strict (jio/reader input-stream) true))


(defn write-json
  "Serialize output-data to JSON and write it to output-stream.
  Also logs output data."
  [output-data output-stream]
  (log/info "Result: " (json/generate-string output-data))
  (json/generate-stream output-data (jio/writer output-stream)))


(defn lambda-json-handler
  "Wrapper that parses input as JSON and writes output to JSON.
  Assumes that execute-fn takes one arg and returns response as Clojure datastructure
  that can be written as JSON."
  [execute-fn ^InputStream input-stream ^OutputStream output-stream]
  (try
    (let [execute-fn-name (fn-name execute-fn)
          version (or (manifest-version) "N/A")
          _ (swap! start-counter inc)
          _ (log/info (str "Starting function " execute-fn-name " version: " version
                           "  invoke count: " @start-counter
                           "  instanceId: " instance-id))
          output-data (execute-fn (parse-json input-stream))]

      (write-json output-data output-stream)

      (log/info "Lambda successful" output-data))
    (catch Exception ex
      (log/error ex "Lambda failed")
      (throw ex))))

(defn lambda-raw-handler
  "Wrapper that passes raw input and ouput streams to execute-fn"
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
      (log/error ex "Lambda failed:")
      (throw ex))))


(defn main-handler [handler-fns args]
  (when (< (count args) 1)
    (throw (new Exception (str "Wrong number of args. Use one of " (vec (map name (keys handler-fns)))))))
  (let [arg (nth args 0)
        handler-fn (handler-fns (keyword arg))]
    (when-not handler-fn
      (throw (new Exception (str "Wrong arg. Use one of " (vec (map name (keys handler-fns)))))))
    (try
      (handler-fn System/in System/out)
      (finally
        (shutdown-agents)))))
