(ns my-website.views.repos.components.segments.styles
  (:require [spade.core :refer [defclass]]))

(defclass segment-group-class []
          [".semantic-ui-segment" {:transition "background-color .25s ease-in-out"}]
          [".semantic-ui-segment:hover" {:background-color "rgba(0, 0, 0, .06)"}]
          [".my-segment" {:width           "100%"
                          :display         "flex"
                          :justify-content "space-between"
                          :align-items     "center"
                          :padding         ".75em"}]
          [".my-segment:hover" {:cursor "pointer"}]
          [".stars" {:color "rgba(0, 0, 0, .3)"}])
