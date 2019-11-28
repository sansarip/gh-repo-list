(ns my-website.views.repos.panel
  (:require [my-website.components.semantic-ui :refer [component]]
            [my-website.views.repos.styles :refer [container-class]]
            [my-website.subs :as root-subs]
            [my-website.events :as root-events]
            [my-website.views.repos.subs :as subs]
            [my-website.views.repos.events :as events]
            [my-website.views.repos.components.segments.segments :refer [make-segment-group]]
            [my-website.views.repos.components.filter.filter :refer [make-filter do-filter]]
            [reagent.core :as r]
            [re-frame.core :refer [subscribe dispatch]]))

(def button (component "Button"))
(def header (component "Header"))
(def icon (component "Icon"))
(def loader (component "Loader"))
(def modal (component "Modal"))
(def modal-content (component "Modal" "Content"))
(def modal-actions (component "Modal" "Actions"))
(def tab (component "Tab"))

(defn repos-panel []
  (let [template @(subscribe [::subs/template])
        title (or (:Template/title template) "Title Not Found")
        subtitle (or (:Template/subtitle template) "Subtitle Not Found")
        repos @(subscribe [::subs/repos])
        selected @(subscribe [::subs/selected])
        state @(subscribe [::root-subs/state])
        is-fetching-t-r (= state 'fetching-t-r)
        is-fetching-r (= state 'fetching-r)
        is-fetch-t-r-failure (= state 'fetch-t-r-failure)
        is-fetch-r-failure (= state 'fetch-r-failure)]
    [:div {:className (container-class)}
     [:div.sizer
      [:div.grid
       [:> modal {:basic true
                  :open  (or is-fetch-t-r-failure is-fetch-r-failure)}
        [:> modal-content
         [:p (str (cond is-fetch-t-r-failure "Unable to receive template or repositories. "
                        is-fetch-r-failure "Unable to receive repositories. ") "Retry?")]]
        [:> modal-actions
         [:> button {:basic    true
                     :color    "red"
                     :onClick  #(dispatch [::root-events/transition-state :ok])
                     :inverted "true"}
          [:> icon {:name "remove"}] "No"]
         [:> button {:color    "blue"
                     :onClick  #(do (dispatch [::root-events/transition-state :retry])
                                    (dispatch [::events/fetch-template-and-repos]))
                     :inverted "true"}
          [:> icon {:name "refresh"}] "Retry"]]]
       [:> loader {:content  (cond is-fetching-t-r "Loading template"
                                   is-fetching-r "Loading repositories")
                   :active   (or is-fetching-t-r is-fetching-r)
                   :inverted "true"}]
       [:> header {:as        :h1
                   :className "my-header"
                   :content   title
                   :subheader subtitle
                   :inverted  "true"}]
       [:> tab {:menu      {:secondary true
                            :pointing  true}
                :className "my-tab"
                :inverted  "true"
                :panes     [{:menuItem "Lorem Ipsum"}]}]
       (make-filter :repos repos
                    :on-search #(dispatch [::events/set-selected %]))
       [:> button {:className "new-action"
                   :primary   true}
        "New Action"]
       (make-segment-group (do-filter repos selected))]]]))

