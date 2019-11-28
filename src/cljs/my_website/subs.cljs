(ns my-website.subs
  (:require
    [re-frame.core :as re-frame]))

(re-frame/reg-sub
  ::active-panel
  (fn [db _]
      (:active-panel db)))

(re-frame/reg-sub
  ::state
  (fn [db _]
    (:state db)))
