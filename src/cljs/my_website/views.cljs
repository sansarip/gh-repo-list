(ns my-website.views
  (:require
    [re-frame.core :refer [subscribe]]
    [my-website.subs :as subs]
    [my-website.styles]))

(defn main-panel []
  (let [active-panel @(subscribe [::subs/active-panel])]
    (active-panel)))
