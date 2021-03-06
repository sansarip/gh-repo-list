(ns my-website.server
  (:require [my-website.handler :refer [app]]
            [config.core :refer [env]]
            [ring.adapter.jetty :refer [run-jetty]])
  (:gen-class))

(defn -main [& args]
  (let [port (Integer/parseInt (or (env :port) (or (System/getenv "JETTY_PORT") "3000")))]
    (run-jetty app {:port port :join? false})))
