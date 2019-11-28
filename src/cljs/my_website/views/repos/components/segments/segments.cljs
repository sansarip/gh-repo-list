(ns my-website.views.repos.components.segments.segments
  (:require [my-website.components.semantic-ui :refer [component]]
            [my-website.views.repos.components.segments.styles :refer [segment-group-class]]
            [reagent.core :as r]
            [confetti]))

(def segment (component "Segment"))
(def segment-group (component "Segment" "Group"))
(def icon (component "Icon"))

(defn confetti-cannon [id]
  (confetti (.querySelector js/document (str "#" id))
            #js {:angle  45
                 :spread 90}))

(defn make-segment-group [repos]
  (reduce-kv
    (fn [coll k v]
      (let [name (:Repositories/name v)
            stars (:Repositories/stars v)
            uuid (str "id-" (random-uuid))]
        (conj coll [:> segment {:className (str "semantic-ui-segment" (if (> k 0) " border-top"))
                                :inverted  "true"
                                :on-click  #(confetti-cannon uuid)}
                    [:div.my-segment {:id uuid}
                     [:span name]
                     [:span.stars stars
                      [:> icon {:name      "star outline"
                                :className "star-icon"}]]]])))
    [:> segment-group {:className (segment-group-class)
                       :raised    true}]
    repos))

