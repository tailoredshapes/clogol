(ns string-util
  (:require [clojure.spec.alpha :as s]
            [clojure.string :as st]
            [specs :as p]
            [gol :as g]
            [science :as sci]))

(s/def ::row (s/coll-of string?))
(s/def ::csv (s/coll-of ::row))

(s/fdef csv
  :args (s/cat :s string?)
  :ret ::csv)

(defn csv [s]
  (map #(st/split % #"")
       (map st/trim (st/split-lines s))))


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
                                                    (if (= v "O")
                                                      [x y])) line)) t))))

(s/fdef parse-world
  :args (s/cat :w string?)
  :ret :gol/world)
(defn parse-world [w]
  (disj (into #{}
              (find-xs (csv w)))
        nil))

(defn line-world [w [[mnx mny] [mxx mxy] :as dim]]
  (for [y (range mny (inc mxy))
        x (range mnx (inc mxx))]
    (if (g/alive? w [x y]) "O" ".")))

(s/fdef print-world
  :args (s/cat :w :gol/world)
  :ret string?)
(defn print-world [w]
  (let [[[mnx mny] [mxx mxy] :as dim] (sci/dimensions w)]
    (st/join "\n" (map (partial apply str)
                       (partition (inc (- mxx mnx))
                                  (line-world w dim))))))

(s/fdef print-sample
  :args (s/cat :w :gol/world :dims (s/coll-of :gol/coord :size 2))
  :ret string?)
(defn print-sample [w [[mnx mny] [mxx mxy] :as dim]]
  (st/join "\n" (map (partial apply str)
                     (partition (inc (- mxx mnx))
                                (line-world w dim)))))
