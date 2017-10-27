(ns {{name}}.util
  (:import [java.util.jar Manifest]))

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