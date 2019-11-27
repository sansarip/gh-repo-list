(ns my-website.views.repos.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
  ::repos
  (fn [db _]
    (-> db :repos/db :repos)))