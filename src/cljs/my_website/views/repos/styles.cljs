(ns my-website.views.repos.styles
  (:require [spade.core :refer [defclass]]))

(defclass container-class []
          {:display               "grid"
           :grid-template-columns "2fr 1fr 1fr 1fr 1fr 1fr 1fr 2fr"
           :grid-auto-rows        "minmax(2.12em, auto)"
           :grid-row-gap          ".15em"
           :grid-column-gap       ".5em"
           :grid-template-areas   (str
                                    "\". . . . . . . .\""
                                    "\". . header header header header . .\""
                                    "\". tab . . . filter new .\""
                                    "\". repos repos repos repos repos repos .\"")}
          [".my-header" {:text-align "center"
                         :grid-area  "header"}]
          [".my-tab" {:grid-area "tab"}]
          [".active.item" {:color        "white !important"
                           :border-color "rgba(0, 0, 255, .6) !important"}]
          [".new-action" {:grid-area        "new"
                          :border           "1px solid white !important"
                          :background-color "rgb(19, 112, 251) !important"
                          :transition       "background-color .25s ease-in-out"}]
          [".new-action:hover" {:background-color "rgb(29, 122, 261) !important"}]
          [".new-action:active" {:background-color "rgb(29, 132, 271) !important"}])
