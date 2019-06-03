(defproject {{name}} "0.1.0-SNAPSHOT"
  :description "AWS Lambda to do FIXME"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure "1.9.0"]

                 [org.clojure/tools.logging "0.4.1"]
                 [ch.qos.logback/logback-classic "1.2.3"]
                 [org.slf4j/slf4j-api "1.7.26"]
                 [org.slf4j/log4j-over-slf4j "1.7.26"]
                 [org.slf4j/jcl-over-slf4j "1.7.26"]
                 ]
  :jvm-opts ["-Dclojure.compiler.elide-meta=[:doc]"
             "-Dclojure.compiler.direct-linking=true"]

  :manifest {"Implementation-Version" ~(fn [project] (:version project))}

  :profiles {:uberjar {:aot :all
                       :global-vars {*warn-on-reflection* true}
                       }
             }

  :main {{name}}.handler
)
