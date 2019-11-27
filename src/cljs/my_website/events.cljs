(ns my-website.events
  (:require
    [re-frame.core :refer [reg-event-db]]
    [my-website.db :as db]
    [my-website.state :refer [next-state]]
    [day8.re-frame.tracing :refer-macros [fn-traced]]))

(reg-event-db
  ::initialize-db
  (fn-traced [_ _]
             db/default-db))

(reg-event-db
  ::set-state
  (fn-traced [db [_ state]]
             (assoc db :state state)))

(reg-event-db
  ::set-fsm
  (fn-traced [db [_ fsm]]
             (assoc db :fsm fsm)))

(reg-event-db
  ::set-active-panel
  (fn-traced [db [_ active-panel]]
             (assoc db :active-panel active-panel)))

(reg-event-db
  ::transition-state
  (fn-traced [db [_ transition]]
             (next-state (:fsm db) db transition)))
