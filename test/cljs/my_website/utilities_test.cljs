(ns my-website.utilities-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [my-website.utilities :refer [valid-field?]]
            [clojure.string :as string]))

;; define tests
#_(defspec test-gen-valid-names 100
           (let [dash (gen/fmap
                        (fn [item] (string/join (if (> 5 (first item)) "-" "") (map (partial string/join "") item)))
                        (gen/tuple (gen/vector (gen/choose 0 9) 3)
                                   (gen/vector (gen/choose 0 9) 3)
                                   (gen/vector (gen/choose 0 9) 4)))
                 paren
                 (gen/fmap
                   (partial string/join "")
                   (gen/tuple
                     (gen/fmap
                       #(str "(" (string/join "" %) (if (> 5 (first %)) ")" ") "))
                       (gen/vector (gen/choose 0 9) 3))
                     (gen/fmap
                       (fn [item] (string/join "-" (map (partial string/join "") item)))
                       (gen/tuple (gen/vector (gen/choose 0 9) 3)
                                  (gen/vector (gen/choose 0 9) 4)))))]
             (prop/for-all [pnum (gen/frequency
                                   [[1 dash]
                                    [1 paren]])]
                           (valid-field? :name pnum))))

; all names must start with a letter
(defspec test-gen-names-with-starting-char 100
         (prop/for-all* [(gen/one-of [(gen/choose 0 9) (gen/elements ["-" "_" " "])])
                         gen/char-alpha]
                        (fn [bad good]
                          (and
                            (not (valid-field? :name (str bad)))
                            (valid-field? :name (str good))))))

; names may contain words separated by one of the following [-_ ]
(defspec test-gen-names-with-words 100
         (let [size (inc (rand-int 25))
               separator-elements ["-" "_" " "]
               next-to-sep? (fn [c k]
                              (let [prev (get c (dec k))
                                    next (get c (inc k))
                                    in-separators? (fn [v] (some #(= v %) separator-elements))]
                                (or (in-separators? prev) (in-separators? next))))
               to-string (fn [s v]
                           (reduce-kv (fn [c k v]
                                        (let [is-not-last-or-first (and (not= k 0) (< k (dec size)))
                                              is-not-next-to-sep (not (next-to-sep? c k))
                                              should-separate (and is-not-last-or-first
                                                                   is-not-next-to-sep
                                                                   (first (get s k)))]
                                          (str c (if should-separate
                                                   (second (get s k))
                                                   v))))
                                      ""
                                      v))]
           (prop/for-all [separators (gen/vector (gen/tuple
                                                   gen/boolean
                                                   (gen/elements separator-elements))
                                                 size)
                          string-vec (gen/vector gen/char-alphanumeric size)
                          beginning-char gen/char-alpha
                          end-char gen/char-alpha]
                         (if (not (valid-field? :name (to-string separators (-> string-vec
                                                                                (assoc 0 beginning-char)
                                                                                (assoc (dec size) end-char)))))
                           (println (to-string separators (-> string-vec
                                                              (assoc 0 beginning-char)
                                                              (assoc (dec size) end-char))))
                           true))))

; TODO:
; names cannot end with any of the following [-_ ]
; names can only contain alphanumeric chars and [-_ ]
; names must be 1-26 chars long

;; necessary boilerplate

(enable-console-print!)

(cljs.test/run-tests)
