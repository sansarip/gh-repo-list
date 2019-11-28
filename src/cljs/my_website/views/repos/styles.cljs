(ns my-website.views.repos.styles
  (:require [spade.core :refer [defclass]]
            [my-website.styles :refer [screen-sizes]]))

(defclass container-class []
          (at-media {:screen    :only
                     :max-width (:tablet screen-sizes)}
                    [".sizer" {:width "30em"}])
          (at-media {:screen    :only
                     :max-width (:mobile-large screen-sizes)}
                    [".sizer" {:width "27em"}])
          (at-media {:screen    :only
                     :max-width (:mobile-medium screen-sizes)}
                    [".sizer" {:width "26em"}])
          (at-media {:screen    :only
                     :max-width (:mobile-small screen-sizes)}
                    [".sizer" {:width "21em"}]
                    [".grid" {:grid-template-areas (str
                                                     "\". . . .\""
                                                     "\"header header header header\""
                                                     "\"tab tab tab tab\""
                                                     "\"filter filter new new\""
                                                     "\"repos repos repos repos\" !important")}])
          {:display         "flex"
           :justify-content "center"}
          [".sizer" {:max-width "47em"}]
          [".grid" {:display               "grid"
                    :grid-template-columns "1fr 3fr 1fr 1fr"
                    :grid-auto-rows        "minmax(2.12em, auto)"
                    :grid-row-gap          ".2em"
                    :grid-column-gap       ".5em"
                    :grid-template-areas   (str
                                             "\". . . .\""
                                             "\"header header header header\""
                                             "\"tab . filter new\""
                                             "\"repos repos repos repos\"")}]
          [".my-header" {:text-align  "center"
                         :grid-area   "header"
                         :white-space "nowrap"}]
          [".my-tab" {:grid-area "tab"}]
          [".active.item" {:color        "white !important"
                           :border-color "rgba(0, 0, 255, .6) !important"}]
          [".new-action" {:grid-area        "new"
                          :justify-self     "end"
                          :border           "1px solid white !important"
                          :background-color "rgb(19, 112, 251) !important"
                          :transition       "background-color .25s ease-in-out"}]
          [".new-action:hover" {:background-color "rgb(29, 122, 261) !important"}]
          [".new-action:active" {:background-color "rgb(29, 132, 271) !important"}])
