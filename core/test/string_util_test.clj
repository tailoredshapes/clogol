(ns string-util-test
  (:require [string-util :refer :all]
            [clojure.test :refer :all]
            [clojure.spec.test.alpha :as stest]
            [clojure.string :as st]
            [gol :as g]))

(stest/instrument)

(def t1
  "0,0,0
   0,X,X
   X,0,0")

(def t2
  "0,0,0
0,X,X
X,0,0")

(def big-t1
  "0,0,0,0,0,0,0,0,0,0,0,0,0
0,0,0,0,0,0,0,0,0,0,0,0,0
0,0,0,0,0,0,0,0,0,0,0,0,0
0,0,0,0,0,0,0,0,0,0,0,0,0
0,0,0,0,0,0,0,0,0,0,0,0,0
0,0,0,0,0,0,0,0,0,0,0,0,0
0,0,0,0,0,0,0,0,0,0,0,0,0
0,0,0,0,0,0,0,0,0,0,0,0,0
0,0,0,0,0,0,0,0,0,0,0,0,0
0,0,0,0,0,0,0,0,0,0,0,0,0
0,0,0,0,0,0,0,0,0,0,0,0,0
0,0,0,0,0,0,0,0,0,0,0,X,X
0,0,0,0,0,0,0,0,0,0,X,0,0"  )

(deftest string-to-col
  (testing "we can take a string and get it into a collection"
    (is (= [["0" "0" "0"] ["0" "X" "X"] ["X" "0" "0"]] (csv t1)))))

(deftest finds-the-X
  (testing "lists the Xs in the world"
    (is (= [[1 1] [2 1] [0 2]] (find-xs (csv t1))))))

(deftest builds-a-world
  (testing "turns a string in a world"
    (is (= #{[1 1] [2 1] [0 2]} (parse-world t1)))))

(deftest prints-a-world
  (testing "can take a world and turn it back into a csv"
    (is (= t2
           (print-world (parse-world t1)))))
  (testing "prints a larger world"
    (is (= big-t1
           (print-world (into #{} (g/translate-cells[10 10]
                                                    (parse-world t1)))))))
  (testing "prints a sample"
    (is (= t1 (print-world (g/sample (into #{} (g/translate-cells[10 10]
                                                               (parse-world t1)))
                                     [7,7][12, 12]))))))
