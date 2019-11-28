(ns my-website.views.repos.components.filter.filter
  (:require [my-website.components.semantic-ui :refer [component]]
            [my-website.views.repos.components.filter.styles :refer [filter-class]]
            [reagent.core :as r]
            [clojure.string :as string]))

(def input (component "Input"))

(defn do-search [repos name]
  (reduce-kv
    (fn [coll k v]
      (if (re-find (-> name
                       string/lower-case
                       re-pattern)
                   (string/lower-case (str (:repositories/name v))))
        (conj coll k)
        coll))
    #{}
    repos))

(defn do-filter [repos selected]
  (reduce #(conj % (get repos %2)) [] (sort selected)))

(defn make-filter [& {:keys [repos
                             on-search]
                      :or   {repos nil}}]

  ;; get all initially
  #_(if (not (empty? repos))
      (r/with-let [_ (on-search (do-search repos ""))]))

  [:> input {:className   (filter-class)
             :onChange    #(on-search (-> %2
                                          (js->clj :keywordize-keys true)
                                          :value))
             :placeholder "Filter by name"}])
