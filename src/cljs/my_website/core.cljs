(ns ^:figwheel-hooks my-website.core
  (:require
    [reagent.core :as r]
    [re-frame.core :refer [clear-subscription-cache! dispatch-sync]]
    [my-website.events :as events]
    [my-website.routes :as routes]
    [my-website.views :as views]))

(defn dev-setup []
  (when js/goog.DEBUG
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (clear-subscription-cache!)
  (r/render [views/main-panel]
            (.getElementById js/document "app")))

(defn ^:after-load re-render []
  (mount-root))

(defn ^:export init []
  (routes/app-routes)
  (dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
