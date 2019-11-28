(ns my-website.views.repos.components.error-modal.error-modal
  (:require [my-website.components.semantic-ui :refer [component]]))

(def header (component "Header"))
(def icon (component "Icon"))
(def button (component "Button"))
(def modal (component "Modal"))
(def modal-content (component "Modal" "Content"))
(def modal-actions (component "Modal" "Actions"))

(defn make-error-modal [& {:keys [state
                                  on-rety
                                  on-no]}]
  (let [is-fetch-t-r-failure (= state 'fetch-t-r-failure)
        is-fetch-r-failure (= state 'fetch-r-failure)]
    [:> modal {:basic true
               :open  (or is-fetch-t-r-failure is-fetch-r-failure)}
     [:> header {:content "Error" :icon "warning"}]
     [:> modal-content
      [:p (str (cond is-fetch-t-r-failure "Unable to receive template or repositories. "
                     is-fetch-r-failure "Unable to receive repositories. ") "Retry?")]]
     [:> modal-actions
      [:> button {:basic    true
                  :color    "red"
                  :onClick  on-no
                  :inverted "true"}
       [:> icon {:name "remove"}] "No"]
      [:> button {:color    "blue"
                  :onClick  on-rety
                  :inverted "true"}
       [:> icon {:name "refresh"}] "Retry"]]]))
