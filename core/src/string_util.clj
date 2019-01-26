(ns string-util
  (:require [clojure.spec.alpha :as s]
            [clojure.string :as st]
            [specs :as p]))

(s/def ::row (s/coll-of string?))
(s/def ::csv (s/coll-of ::row))

(s/fdef csv
  :args (s/cat :s string?)
  :ret ::csv)

(defn csv [s]
  (map (fn [s]
         (map st/trim
              (st/split s #",")))
       (st/split-lines s)))


(defn find-dimensions [s]
  (let [c (csv s)
        h (count c)
        w (count (first c))]
    [w h]))

(s/fdef find-xs
  :args (s/cat :t ::csv)
  :ret (s/coll-of :gol/coord))
(defn find-xs [t]
  (remove nil? (apply concat
                      (map-indexed (fn [y line]
                                     (map-indexed (fn [x v]
                                                    (if (= v "X")
                                                      [x y])) line)) t))))

(s/fdef parse-world
  :args (s/cat :w string?)
  :ret :gol/world)
(defn parse-world [w]
  (disj (into #{}
              (find-xs (csv w)))
        nil))
