(ns my-website.utilities
  (:require [cljstache.core :refer [render]]
            [clojure.string :as string]))

(defn lower-case-fq-key [k]
  (if (keyword? k)
    (-> "{{namespace}}/{{name}}"
        (render {:namespace (string/lower-case (namespace k))
                 :name      (string/lower-case (name k))})
        keyword)))

(defn lower-case-fq-keys [coll]
  (reduce-kv (fn [c k v] (assoc c (lower-case-fq-key k) v)) {} coll))
