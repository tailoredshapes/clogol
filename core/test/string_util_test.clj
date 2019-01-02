(ns string-util-test
  (:require [string-util :refer :all]
            [clojure.test :refer :all]
            [clojure.spec.test.alpha :as stest]))

(stest/instrument)

(def t1
  "0,0,0
   0,X,0
   0,0,0")

(deftest string-to-col
  (testing "we can take a string and get it into a collection"
    (is (= [["0" "0" "0"] ["0" "X" "0"] ["0" "0" "0"]] (csv t1)))))

(deftest finds-the-X
  (testing "lists the Xs in the world"
    (is (= [[1 1]] (find-xs (csv t1))))))

(deftest builds-a-world
  (testing "turns a string in a world"
    (is (= #{[1 1]} (parse-world t1)))))
