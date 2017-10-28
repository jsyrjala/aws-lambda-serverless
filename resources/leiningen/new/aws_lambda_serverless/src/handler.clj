(ns {{name}}.handler
  "FIXME AWS Lambda to do XXX"
  (:require [{{name}}.lambda-util :as lambda-util])
  (:import [java.io InputStream OutputStream])
  (:gen-class
    ;; Define handler method signature here so that AWS can find the method
    :methods [^:static [handler [java.io.InputStream java.io.OutputStream] void]])
  )

(defn lambda-execute
  "FIXME Business happens here"
  [^InputStream input ^OutputStream output]
  ;; FIXME Add lambda implementation here
  (.write output (.getBytes "TODO implement me\n" "UTF-8"))
  )

(defn -handler
  "AWS Lambda entry point"
  [^InputStream input ^OutputStream output]
  (lambda-util/lambda-handler lambda-execute input output))

(defn -main
  "Java main method. For testing. Reads stdin.
    e.g java -jar lambda.jar < file"
  []
  (try
    (lambda-util/lambda-handler lambda-execute System/in System/out)
    (finally
      (shutdown-agents))))
