(ns my-website.styles
  (:require [spade.core :refer [defglobal]]
            [cljstache.core :refer [render]]))

(defglobal global-class []
           (let [bg-color "rgb(19, 112, 251)"
                 dot-color "#3882E8"
                 dot-size 2
                 dot-space 22]
             ["body" {:font-size       "16px"
                      :color           "#131F33"
                      :background      (render
                                         "linear-gradient(90deg, {{bg-color}} {{dot-diff}}, transparent 1%) center,
                                         linear-gradient({{bg-color}} {{dot-diff}}, transparent 1%) center,
                                         {{dot-color}}"
                                         {:bg-color  bg-color
                                          :dot-color dot-color
                                          :dot-diff  (str (- dot-space dot-size) "px")
                                          :dot-size  (str dot-size "px")
                                          :dot-space (str dot-space "px")})
                      :background-size (render "{{dot-space}} {{dot-space}}"
                                               {:dot-space (str dot-space "px")})}]))

