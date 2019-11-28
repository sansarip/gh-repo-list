(ns my-website.views.repos.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
  ::repos
  (fn [db _]
    (-> db :repos/db :repos)))

(reg-sub
  ::selected
  (fn [db _]
    (-> db :repos/db :selected)))

(reg-sub
  ::template
  (fn [db _]
    (-> db :repos/db :template)))

(reg-sub
  ::search-value
  (fn [db _]
    (-> db :repos/db :search-value)))
