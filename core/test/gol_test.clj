(ns gol-test
  (:require [gol :refer :all]
            [string-util :as su]
            [clojure.test :refer :all]
            [clojure.spec.test.alpha :as stest]))

(stest/instrument)

(def t1
  "0,0,0,0,0
   0,X,X,X,0
   0,0,0,0,0")

(def t1'
  "0,0,X,0,0
   0,0,X,0,0
   0,0,X,0,0")

(def neighbours-of-11
  "X,X,X
   X,0,X
   X,X,X")

(def neighbours-of-00
  "0,X,0
   X,X,0")


(deftest starts-dead
  (testing "any empty game is a dead game"
    (is (not (alive? #{} [0 0])))))

(deftest life
  (testing "can bring a cell to life"
    (is (alive? #{[0 0]} [0 0]))
    (is (not (alive? #{[0 0]} [0 1])))))

(deftest death
  (testing "can kill a cell"
    (let [game #{[0 0] [0 1]}
          game' (kill game [0 0])]
      (is (alive? game' [0 1]) )
      (is (not (alive? game' [0 0]))))))


(deftest add-vecs
  (testing "adding two vectors"
    (is (= [-1 -1] (add-vec [0 0] [-1 -1])))
    (is (= [0 -1] (add-vec [0 0] [0 -1])))
    (is (= [1 1] (add-vec [0 0] [1 1])))
    (is (= [4 -3] (add-vec [3 4] [1 -7])))))

(deftest neighbourhood
  (testing "the neighbours template"
    (is (= `([-1 -1] [-1 0] [-1 1] [0 -1] [0 1] [1 -1] [1 0] [1 1]) neighbours)))
  (testing "calculates the correct neighbours"
    (is (= (su/parse-world neighbours-of-00) (get-neighbours [0 0])))
    (is (= (su/parse-world neighbours-of-11) (get-neighbours [1 1]))))
  (testing "counts the living neighbours"
    (is (= 8 (count-neighbours (su/parse-world neighbours-of-11) [1 1])))))

(deftest survival
  (testing "a cell with two neighbours lives"
    (let [game (su/parse-world t1)
          expected (su/parse-world t1')]
      (is (= expected (then game))))))

(deftest super-sets
  (testing "should combine worlds that have overlapping values"
    (is (= [#{[0 0] [0 1] [20 20] [30 30]} #{[1 1] [40 40]}]
           (find-super-sets []
                            [#{[0 0] [0 1] [20 20]}
                             #{[0 0] [30 30]}
                             #{[1 1] [40 40]}])))))
