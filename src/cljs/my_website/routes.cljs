(ns my-website.routes
  (:require-macros [secretary.core :refer [defroute]])
  (:import goog.History)
  (:require
    [secretary.core :as secretary]
    [goog.events :as gevents]
    [goog.history.EventType :as EventType]
    [re-frame.core :refer [dispatch dispatch-sync]]
    [my-website.events :as events]
    [my-website.views.repos.panel :refer [repos-panel]]
    [my-website.views.repos.state :as repos-state]
    [my-website.views.repos.events :as repos-events]))


(defn hook-browser-navigation! []
  (doto (History.)
    (gevents/listen
      EventType/NAVIGATE
      (fn [event]
        (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

(defn app-routes []
  (secretary/set-config! :prefix "#")

  (defroute "/" []
            (dispatch [::events/set-fsm repos-state/fsm])
            (dispatch [::events/set-state (:start repos-state/fsm)])
            (dispatch [::repos-events/fetch-template-and-repos])
            (dispatch [::events/set-active-panel repos-panel]))

  (hook-browser-navigation!))
