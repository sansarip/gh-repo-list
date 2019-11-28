(ns my-website.views.repos.events
  (:require
    [ajax.core :as ajax]
    [clojure.string :as string]
    [day8.re-frame.http-fx]
    [day8.re-frame.tracing :refer-macros [fn-traced]]
    [my-website.events :as events]
    [my-website.config :refer [api-spec]]
    [my-website.utilities :refer [lower-case-fq-keys]]
    [re-frame.core :refer [reg-event-db reg-event-fx]]
    [cljstache.core :refer [render]]))

(reg-event-fx
  ::create-repo
  (fn-traced [{:keys [db]} [_ r-name stars]]
             (let [uri (-> (string/join "/" [(:uri api-spec)
                                             (-> api-spec :endpoints :new :path)])
                           (str (render "?name={{name}}&stars={{stars}}"
                                        {:name  r-name
                                         :stars stars})))]

               {:db         db
                :http-xhrio {:uri             uri
                             :method          :post
                             :timeout         6000
                             :format          (ajax/url-request-format)
                             :response-format (ajax/json-response-format {:keywords? true})
                             :on-success      [::create-repo-success]
                             :on-failure      [::create-repo-failure]}})))

(reg-event-fx
  ::create-repo-success
  (fn-traced [{:keys [db]} [_ _response]]
             {:db         db
              :dispatch-n (list
                            [::events/transition-state :succeed]
                            [::fetch-repos])}))

(reg-event-fx
  ::create-repo-failure
  (fn-traced [{:keys [db]} [_ _response]]
             {:db       db
              :dispatch [::events/transition-state :failure]}))

(reg-event-fx
  ::fetch-template-and-repos
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
                            [::assoc-repos-db :template (-> response
                                                            first
                                                            lower-case-fq-keys)]
                            [::events/transition-state :succeed]
                            [::fetch-repos])}))

(reg-event-fx
  ::fetch-template-failure
  (fn-traced [{:keys [db]} [_ _response]]
             {:db         db
              :dispatch-n (list
                            [::events/transition-state :fail])}))

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
                            [::assoc-repos-db :repos (vec (map lower-case-fq-keys response))]
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
(reg-event-db
  ::assoc-repos-db
  (fn-traced [db [_ k v]]
             (assoc-in db [:repos/db k] v)))

(reg-event-db
  ::assoc-repos-db
  (fn-traced [db [_ k v]]
             (assoc-in db [:repos/db k] v)))

(reg-event-db
  ::set-selected
  (fn-traced [db [_ val]]
             (assoc-in db [:repos/db :selected] val)))
