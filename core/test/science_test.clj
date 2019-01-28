(ns science-test
  (:require [science :refer :all]
            [gol :as g]
            [string-util :as su]
            [clojure.test :refer :all]
            [clojure.spec.test.alpha :as stest]))

(stest/instrument)

(def t1
  "0,0,0,0,0
   0,X,X,X,0
   0,0,0,0,0")

(deftest subsets
  (testing "should return a subset of the world"
    (let [game (su/parse-world t1)]
      (is (= #{[1 1]} (sample game [1 0] [1 2])) "slice through")
      (is (= #{[1 1] [2 1] [3 1]} (sample game [1 1] [4 1])) "take everything"))))

(deftest size
  (testing "should find the area of all living cells"
    (is (= [[0 0] [0 0]] (measure #{})))
    (is (= [[1 1] [3 1]] (measure (su/parse-world t1)))))
  (testing "should find the area of the world"
    (is (= [[0 0] [0 0]] (dimensions #{})))
    (is (= [[0 0] [3 1]] (dimensions (su/parse-world t1))))))
