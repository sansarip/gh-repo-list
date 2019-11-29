(ns my-website.views.repos.components.new-action.new-action
  (:require [my-website.components.semantic-ui :refer [component]]
            [my-website.utilities :refer [valid-field?]]
            [reagent.core :as r]))

(def header (component "Header"))
(def icon (component "Icon"))
(def input (component "Input"))
(def form (component "Form"))
(def form-field (component "Form" "Field"))
(def button (component "Button"))
(def button-content (component "Button" "Content"))
(def modal (component "Modal"))
(def modal-content (component "Modal" "Content"))
(def modal-actions (component "Modal" "Actions"))

(defn get-input-value [o]
  (-> o
      (js->clj :keywordize-keys true)
      :value))

(defn make-new-action-modal [on-submit]
  (r/with-let [name-input (r/atom "")
               stars-input (r/atom "")
               is-open (r/atom false)]
              (let [invalid-name (->> @name-input
                                      (valid-field? :name)
                                      not)
                    invalid-stars (->> @stars-input
                                       (valid-field? :positive-int)
                                       not)]
                [:<>
                 [:> button {:className "new-action"
                             :onClick   #(reset! is-open true)
                             :primary   true}
                  "New Action"]
                 [:> modal {:open @is-open
                            :size "mini"}
                  [:> modal-content
                   [:> form
                    [:> form-field {:error invalid-name}
                     [:label "Repo Name"]
                     [:> input {:placeholder "repo name"
                                :onChange    #(reset! name-input (get-input-value %2))
                                :error       invalid-name}]]
                    [:> form-field {:error invalid-stars}
                     [:label "Stars"]
                     [:> input {:placeholder "stars"
                                :onChange    #(reset! stars-input (get-input-value %2))}]]]]
                  [:> modal-actions
                   [:> button {:animated true
                               :primary  true
                               :disabled (or invalid-name invalid-stars)
                               :onClick  #(do
                                            (reset! is-open false)
                                            (on-submit @name-input @stars-input)
                                            (reset! name-input "")
                                            (reset! stars-input ""))}
                    [:> button-content {:visible true}
                     "Submit"]
                    [:> button-content {:hidden true}
                     [:> icon {:name "send"}]]]]]])))

