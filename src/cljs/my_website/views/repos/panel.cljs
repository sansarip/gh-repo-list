(ns my-website.views.repos.panel
  (:require [my-website.components.semantic-ui :refer [component]]
            [my-website.subs :as root-subs]
            [my-website.views.repos.styles :refer [container-class]]
            [reagent.core :as r]
            [re-frame.core :as re-frame]))

(defn repos-panel []
  (r/with-let [container (component "Container")
               segment (component "Segment")
               segment-group (component "Segment" "Group")]
              [:> container
               [:> segment-group
                [:> segment
                 [:p "hi"]]
                [:> segment
                 [:p "bye"]]]]))
