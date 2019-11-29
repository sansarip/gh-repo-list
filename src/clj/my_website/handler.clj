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

(defn carefully-execute [database query error-response]
  (try
    (-> (jdbc/execute!
          (:datasource database)
          [query])
        response)
    (catch Exception _
      (component/stop db)
      error-response)))

(defroutes main-routes
           ;; TODO: Add input sanitation
           (GET "/" []
             (resource-response "index.html" {:root "public"}))
           (-> (GET "/repos" {{min :min max :max} :params}
                 (carefully-execute db (render
                                         "SELECT * FROM Repositories WHERE id BETWEEN {{min}} AND {{max}}"
                                         {:min (str (. Integer parseInt (or min "0")))
                                          :max (str (. Integer parseInt (or max "20")))})
                                    error-response))
               wrap-json-response)
           (-> (GET "/template" []
                 (carefully-execute db "SELECT * FROM Template WHERE isActive=1 LIMIT 1" error-response))
               wrap-json-response)
           (-> (POST "/new" {{r-name :name stars :stars} :params}
                 (carefully-execute db (render
                                         "INSERT INTO Repositories (name, stars) VALUES ('{{name}}', {{stars}})"
                                         {:name  r-name
                                          :stars stars})
                                    error-response))
               wrap-json-response)
           (resources "/"))

(def app
  (-> (handler/site main-routes)
      wrap-base-url))
