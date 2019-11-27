(ns my-website.views.repos.events
  (:require
    [ajax.core :as ajax]
    [clojure.string :as string]
    [day8.re-frame.http-fx]
    [day8.re-frame.tracing :refer-macros [fn-traced]]
    [my-website.events :as events]
    [my-website.config :refer [api-spec]]
    [re-frame.core :refer [reg-event-db reg-event-fx]]))

(reg-event-fx
  ::fetch-templates-and-repos
  (fn-traced [{:keys [db]} _]
             (let [uri (string/join "/" [(:uri api-spec)
                                         (-> api-spec :endpoints :template :path)])]
               {:db         db
                :http-xhrio {:uri             uri
                             :method          :get
                             :timeout         6000
                             :response-format (ajax/json-response-format {:keywords? true})
                             :on-success      [::fetch-template-success]
                             :on-failure      [::fetch-template-failure]}})))

(reg-event-fx
  ::fetch-template-success
  (fn-traced [{:keys [db]} [_ response]]
             {:db         db
              :dispatch-n (list
                            [::assoc-repos-db :template response]
                            [::events/transition-state :succeed]
                            [::fetch-repos])}))

(reg-event-fx
  ::fetch-template-failure
  (fn-traced [{:keys [db]} [_ response]]
             {:db         db
              :dispatch-n (list
                            [::events/transition-state :fail]
                            [::fetch-repos])}))

(reg-event-fx
  ::fetch-repos
  (fn-traced [{:keys [db]} _]
             (let [uri (string/join "/" [(:uri api-spec)
                                         (-> api-spec :endpoints :repos :path)])]
               {:db         db
                :http-xhrio {:uri             uri
                             :method          :get
                             :timeout         6000
                             :response-format (ajax/json-response-format {:keywords? true})
                             :on-success      [::fetch-repos-success]
                             :on-failure      [::fetch-repos-failure]}})))

(reg-event-fx
  ::fetch-repos-success
  (fn-traced [{:keys [db]} [_ response]]
             {:db         db
              :dispatch-n (list
                            [::assoc-repos-db :repos response]
                            [::events/transition-state :succeed])}))

(reg-event-fx
  ::fetch-repos-failure
  (fn-traced [{:keys [db]} [_ response]]
             {:db       db
              :dispatch [::events/transition-state :fail]}))

(reg-event-db
  ::assoc-repos-db
  (fn-traced [db [_ k v]]
             (assoc-in db [:repos/db k] v)))
