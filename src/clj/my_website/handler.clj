(ns my-website.handler
  (:use compojure.core
        clostache.parser
        [hiccup.middleware :only (wrap-base-url)])
  (:require
    [com.stuartsierra.component :as component]
    [compojure.route :refer [resources]]
    [compojure.handler :as handler]
    [my-website.components.database :refer [new-database]]
    [my-website.config :as config]
    [next.jdbc :as jdbc]
    [next.jdbc.connection :as connection]
    [ring.middleware.json :refer [wrap-json-response]]
    [ring.util.response :refer [resource-response response]]
    [ring.middleware.reload :refer [wrap-reload]]))

(defonce db (try
              (component/start (new-database config/db-spec))
              (catch Exception _
                nil)))

(def error-response {:status "500"
                     :body   "Internal Server Error"})

(defroutes main-routes
           (GET "/" []
             (resource-response "index.html" {:root "public"}))
           (-> (GET "/repos" {{min :min max :max} :params}
                 (try
                   (-> (jdbc/execute!
                         (:datasource db)
                         [(render
                            "SELECT * FROM Repositories WHERE id BETWEEN {{min}} AND {{max}}"
                            {:min (. Integer parseInt (or min "0"))
                             :max (. Integer parseInt (or max "9"))})])
                       response)
                   (catch Exception _
                     (component/stop db)
                     error-response)))
               wrap-json-response)
           (-> (GET "/template" []
                 (try
                   (-> (jdbc/execute!
                         (:datasource db)
                         ["SELECT * FROM Template WHERE isActive=1 LIMIT 1"])
                       response)
                   (catch Exception _
                     (component/stop db)
                     error-response)))
               wrap-json-response)
           (resources "/"))

(def app
  (-> (handler/site main-routes)
      wrap-base-url))
