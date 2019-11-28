(ns my-website.views.repos.components.filter.styles
  (:require [spade.core :refer [defclass]]
            [my-website.styles :refer [screen-sizes]]))

(defclass filter-class []
          (at-media {:screen    :only
                     :max-width (:mobile-large screen-sizes)}
                    [:& {:width "72%"}])
          (at-media {:screen    :only
                     :max-width (:mobile-small screen-sizes)}
                    [:& {:justify-self "start"
                         :width        "100%"}])
          {:grid-area    "filter"
           :justify-self "end"})
