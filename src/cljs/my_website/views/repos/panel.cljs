(ns my-website.views.repos.panel
  (:require [my-website.components.semantic-ui :refer [component]]
            [my-website.views.repos.styles :refer [container-class]]
            [my-website.views.repos.subs :as subs]
            [my-website.views.repos.events :as events]
            [my-website.views.repos.components.segments.segments :refer [make-segment-group]]
            [my-website.views.repos.components.filter.filter :refer [make-filter do-filter]]
            [reagent.core :as r]
            [re-frame.core :refer [subscribe dispatch]]))

(def header (component "Header"))
(def button (component "Button"))
(def tab (component "Tab"))

(defn repos-panel []
  (let [template @(subscribe [::subs/template])
        title (or (:Template/title template) "Title Not Found")
        subtitle (or (:Template/subtitle template) "Subtitle Not Found")
        repos @(subscribe [::subs/repos])
        selected @(subscribe [::subs/selected])]
    [:div {:className (container-class)}
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
     (make-segment-group (do-filter repos selected))]))

