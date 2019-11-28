(ns my-website.views.repos.panel
  (:require [my-website.components.semantic-ui :refer [component]]
            [my-website.views.repos.styles :refer [container-class]]
            [my-website.subs :as root-subs]
            [my-website.events :as root-events]
            [my-website.views.repos.subs :as subs]
            [my-website.views.repos.events :as events]
            [my-website.views.repos.components.segments.segments :refer [make-segment-group]]
            [my-website.views.repos.components.filter.filter :refer [make-filter do-filter]]
            [my-website.views.repos.components.error-modal.error-modal :refer [make-error-modal]]
            [my-website.views.repos.components.new-action.new-action :refer [make-new-action-modal]]
            [reagent.core :as r]
            [re-frame.core :refer [subscribe dispatch]]))

(def button (component "Button"))
(def dimmer (component "Dimmer"))
(def header (component "Header"))
(def icon (component "Icon"))
(def loader (component "Loader"))
(def tab (component "Tab"))

(defn repos-panel []
  (let [template @(subscribe [::subs/template])
        title (or (:template/title template) "Title Not Found")
        subtitle (or (:template/subtitle template) "Subtitle Not Found")
        repos @(subscribe [::subs/repos])
        selected @(subscribe [::subs/selected])
        state @(subscribe [::root-subs/state])
        is-fetching-t-r (= state 'fetching-t-r)
        is-fetching-r (= state 'fetching-r)
        is-creating-r (= state 'creating-r)]
    [:div {:className (container-class)}
     [:div.sizer
      [:div.grid
       (make-error-modal :state state
                         :on-no #(dispatch [::root-events/transition-state :ok])
                         :on-retry #(do (dispatch [::root-events/transition-state :retry])
                                        (dispatch [::events/fetch-template-and-repos])))
       (make-new-action-modal #(do (dispatch [::root-events/transition-state :create])
                                   (dispatch [::events/create-repo %1 %2])))
       [:> dimmer {:active (or is-fetching-t-r is-fetching-r is-creating-r)}
        [:> loader {:content  (cond is-fetching-t-r "Loading template"
                                    is-fetching-r "Loading repositories"
                                    is-creating-r "Creating new repository")
                    :active   (or is-fetching-t-r is-fetching-r)
                    :inverted "true"}]]
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
       (make-segment-group (do-filter repos selected))]]]))

