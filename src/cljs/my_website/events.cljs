(ns my-website.events
  (:require
    [re-frame.core :as re-frame]
    [my-website.db :as db]
    [my-website.state :refer [next-state]]
    [day8.re-frame.tracing :refer-macros [fn-traced defn-traced]]))

(re-frame/reg-event-db
  ::initialize-db
  (fn-traced [_ _]
             db/default-db))

(re-frame/reg-event-db
  ::set-state
  (fn-traced [db [_ state]]
             (assoc db :state state)))

(re-frame/reg-event-db
  ::set-fsm
  (fn-traced [db [_ fsm]]
             (assoc db :fsm fsm)))

(re-frame/reg-event-db
  ::set-active-panel
  (fn-traced [db [_ active-panel]]
             (assoc db :active-panel active-panel)))

(re-frame/reg-event-db
  ::transition-state
  (fn-traced [db [_ transition]]
             (next-state (:state db) db transition)))
