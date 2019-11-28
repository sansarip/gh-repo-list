(ns my-website.views.repos.components.segments.styles
  (:require [spade.core :refer [defclass]]))

(defclass segment-group-class []
          {:grid-area "repos"}
          [".semantic-ui-segment" {:transition       "background-color .25s ease-in-out"
                                   :background-color "rgba(0, 0, 0, .2) !important"}]
          [".border-top" {:border-top ".8px solid rgba(255, 255, 255, .6) !important"}]
          [".semantic-ui-segment:hover" {:background-color "rgba(0, 0, 0, .14) !important"
                                         :cursor           "pointer"}]
          [".semantic-ui-segment:active" {:background-color "rgba(0, 0, 0, .08) !important"}]
          [".my-segment" {:width           "100%"
                          :display         "flex"
                          :justify-content "space-between"
                          :align-items     "center"
                          :padding         ".75em"}]
          [".stars" {:color "rgba(255, 255, 255, .7)"}]
          [".star-icon" {:margin-left ".125em"}])
