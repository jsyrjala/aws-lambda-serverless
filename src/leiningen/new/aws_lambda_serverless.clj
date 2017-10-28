(ns leiningen.new.aws-lambda-serverless
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "aws-lambda-serverless"))

(defn aws-lambda-serverless
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' aws-lambda-serverless project.")
    (->files data
             ["project.clj" (render "project.clj" data)]
             ["README.md" (render "README.md" data)]
             ["resources/logback.xml" (render "resources/logback.xml" data)]
             ["src/{{sanitized}}/handler.clj" (render "src/handler.clj" data)]
             ["src/{{sanitized}}/lambda_util.clj" (render "src/lambda_util.clj" data)]
             ["serverless.yml" (render "serverless.yml" data)]
             ["serverless/dev.yml" (render "serverless/dev.yml" data)]
             ["serverless/prod.yml" (render "serverless/prod.yml" data)]
             )))
