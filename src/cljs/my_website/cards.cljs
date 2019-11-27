(ns my-website.cards
  (:require [devcards.core :as dc]
            [my-website.cards.semantic_ui.segments]))

(enable-console-print!)

(defn ^:export init []
  (dc/start-devcard-ui!))
