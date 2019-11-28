(ns my-website.cards.semantic-ui.confetti
  (:require [devcards.core :refer-macros (defcard)]
            [my-website.components.semantic-ui :refer [component]]
            [sablono.core :as sab]
            [reagent.core :as r]
            [confetti]))

(defcard simple-confetti-button
         "Simple demonstration of confetti"
         (sab/html
           (r/as-element
             (let [id1 "button-a"
                   cannon #(confetti (.querySelector js/document (str "#" %)) #js {:spread       180
                                                                                   :elementCount 69})]
               [:button {:id       id1
                         :on-click #(cannon id1)}
                "A"]))))

