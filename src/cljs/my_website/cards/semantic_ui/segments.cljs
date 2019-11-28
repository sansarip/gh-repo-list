(ns my-website.cards.semantic_ui.segments
  (:require [devcards.core :refer-macros (defcard)]
            [my-website.components.semantic-ui :refer [component]]
            [sablono.core :as sab]
            [reagent.core :as r]))

(defcard
  simple-segment-group
  "Simple demonstration of semantic-ui segment group"
  (sab/html
    (r/as-element
      (r/with-let [segment (component "Segment")
                   segment-group (component "Segment" "Group")]
                  [:> segment-group
                   [:> segment [:p "A"]]
                   [:> segment [:p "B"]]
                   [:> segment [:p "C"]]]))))

