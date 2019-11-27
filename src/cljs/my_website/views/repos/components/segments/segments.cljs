(ns my-website.views.repos.components.segments.segments
  (:require [my-website.components.semantic-ui :refer [component]]
            [my-website.views.repos.components.segments.styles :refer [segment-group-class]]
            [reagent.core :as r]))

(defonce segment (component "Segment"))
(defonce segment-group (component "Segment" "Group"))

(defn make-segment-group [repos]
  (reduce
    #(let [name (:Repositories/name %2)
           stars (:Repositories/stars %2)]
       (conj % [:> segment {:className "semantic-ui-segment"}
                [:div.my-segment {:on-click (fn [_ &] (println "hi!"))}
                 [:span name]
                 [:span.stars stars]]]))
    [:> segment-group {:className (segment-group-class)}]
    repos))

