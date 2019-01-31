(ns string-util-test
  (:require [string-util :refer :all]
            [clojure.test :refer :all]
            [clojure.spec.test.alpha :as stest]
            [clojure.string :as st]
            [gol :as g]
            [science :as sci]))

(stest/instrument)

(def t1
  "...
   .OO
   O..")

(def t2
  "...
.OO
O..")

(def big-t1
  "...............
...............
...............
............O..
...............
...............
.O.............
...............
...............
...............
...............
.............OO
............O..")

(def sample-t1
  "......
......
......
......
....OO
...O..")

(def sample-t2
  "......
.OO.OO
.OO.OO")

(deftest string-to-col
  (testing "we can take a string and get it into a collection"
    (is (= [["." "." "."] ["." "O" "O"] ["O" "." "."]] (csv t1)))))

(deftest finds-the-X
  (testing "lists the Os in the world"
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
           (print-world (parse-world big-t1)))))
  (testing "prints a sample"
    (is (= sample-t1 (print-sample (into #{} (g/translate-cells[10 10]
                                                        (parse-world t1)))
                                   [[7,7][12, 12]]))))

  (testing "prints a slightly more complicated sample"
    (is (= sample-t2 (print-world (parse-world sample-t2))))))
