(ns my-website.utilities-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [my-website.utilities :refer [valid-field?]]
            [clojure.string :as string]))

;; define tests
#_
(defspec test-gen-valid-names 100
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

(deftest fake-test
  (testing "fake description"
    (is (= 1 1))))

;; necessary boilerplate

(enable-console-print!)

(cljs.test/run-tests)
