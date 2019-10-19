(ns leiningen.new.aws-lambda-serverless
  (:require
    [leiningen.new.templates
     :refer [renderer name-to-path ->files project-name
             sanitize-ns]]
    [leiningen.core.main :as main]))

(def render (renderer "aws-lambda-serverless"))

(defn aws-lambda-serverless
  "FIXME: write documentation"
  [name]
  (let [main-ns (sanitize-ns name)
        data    {:raw-name  name
                 :name      (project-name name)
                 :namespace main-ns
                 :dirs      (name-to-path main-ns)
                 :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' aws-lambda-serverless project.")
    (->files data
             ["project.clj"    (render "project.clj" data)]
             ["README.md"      (render "README.md" data)]
             [".gitignore"     (render "gitignore" data)]
             [".hgignore"      (render "hgignore" data)]
             ["CHANGELOG.md"   (render "CHANGELOG.md" data)]
             ["serverless.yml" (render "serverless.yml" data)]
             ["dev.yml"        (render "dev.yml" data)]
             ["prod.yml"       (render "prod.yml" data)]

             ["resources/logback.xml" (render "resources/logback.xml" data)]

             ["src/{{dirs}}/handler.clj"     (render "src/handler.clj" data)]
             ["src/{{dirs}}/lambda_util.clj" (render "src/lambda_util.clj" data)]

             ["test/{{dirs}}/handler_test.clj" (render "test/handler_test.clj" data)]
             )))
