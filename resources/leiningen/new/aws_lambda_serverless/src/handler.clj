(ns {{name}}.handler
  "FIXME AWS Lambda to do XXX"
  (:require [{{name}}.lambda-util :as lambda-util])
  (:import [java.io InputStream OutputStream])
  (:gen-class
    ;; Define handler method signature here so that AWS can find the method
    :methods [^:static [func1 [java.io.InputStream java.io.OutputStream] void]
              ^:static [func2 [java.io.InputStream java.io.OutputStream] void]])
  )

(defn func1-execute
  "FIXME func1 does XXX"
  [^InputStream input ^OutputStream output]
  ;; FIXME Add lambda implementation here
  (.write output (.getBytes "TODO implement func1\n" "UTF-8"))
  )

(defn func2-execute
  "FIXME func2 does XXX"
  [^InputStream input ^OutputStream output]
  ;; FIXME Add lambda implementation here
  (.write output (.getBytes "TODO implement func2\n" "UTF-8"))
  )

(defn -func1
  "AWS Lambda entry point"
  [^InputStream input ^OutputStream output]
  (lambda-util/lambda-handler func1-execute input output))

(defn -func2
  "AWS Lambda entry point"
  [^InputStream input ^OutputStream output]
  (lambda-util/lambda-handler func2-execute input output))

(defn -main
  "Java main method. For testing. Reads stdin.
    e.g java -jar lambda.jar < file"
  [& args]
  (lambda-util/main-handler {:func1 func1-execute
                             :func2 func2-execute} args))
