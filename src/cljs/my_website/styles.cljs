(ns my-website.styles
  (:require [spade.core :refer [defglobal]]))

(defglobal global-class []
           ["body" {:display "flex"
                    :justify-content "center"
                    :align-items "center"
                    :font-family "\"Work Sans\", sans-serif"
                    :color "#131F33"}])
