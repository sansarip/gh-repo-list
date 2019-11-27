(ns my-website.views.repos.panel
  (:require [my-website.components.semantic-ui :refer [component]]
            [my-website.subs :as root-subs]
            [my-website.views.repos.styles :refer [container-class]]
            [my-website.views.repos.subs :as subs]
            [my-website.views.repos.components.segments.segments :refer [make-segment-group]]
            [reagent.core :as r]
            [re-frame.core :refer [subscribe]]))

(defn repos-panel []
  (r/with-let [container (component "Container")]
              [:> container
               (make-segment-group @(subscribe [::subs/repos]))]))

