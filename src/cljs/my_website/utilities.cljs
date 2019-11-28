(ns my-website.utilities
  (:require [cljstache.core :refer [render]]
            [clojure.string :as string]))

(def common-regex {:name         #"^[a-zA-Z0-9\-_ ]{1,26}$"
                   :positive-int #"^[0-9]{1,6}$"})

(defn lower-case-fq-key [k]
  (if (keyword? k)
    (-> "{{namespace}}/{{name}}"
        (render {:namespace (string/lower-case (namespace k))
                 :name      (string/lower-case (name k))})
        keyword)))

(defn valid-field? [k field]
  (let [rx (common-regex k)]
    (if k
      (re-matches rx field))))

(defn lower-case-fq-keys [coll]
  (reduce-kv (fn [c k v] (assoc c (lower-case-fq-key k) v)) {} coll))
