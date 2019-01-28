(ns science
  (:require [gol :as g]
            [clojure.spec.alpha :as s]))

(s/fdef sample
  :args (s/cat :game :gol/world
               :min :gol/coord
               :max :gol/coord)
  :ret :gol/world)
(defn sample [game min max]
  (let [grid (for [x (range (first min) (inc (first max)))
                   y (range (second min) (inc (second max)))]
               [x y])]
    (into #{}
          (filter (partial g/alive?
                           game)
                  grid))))

(s/fdef measure
  :args (s/cat :game :gol/world)
  :ret (s/coll-of :gol/coord))
(defn measure [[[x y] & gs :as w]]
  (if (empty? w)
    [[0 0][0 0]]
    (reduce (fn [[[mnx mny] [mxx mxy]]
                 [cx cy]]
              [[(min mnx cx)
                (min mny cy)]
               [(max mxx cx)
                (max mxy cy)]])
            [[x, y] [x, y]]
            gs)))

(s/fdef dimensions
  :args (s/cat :game :gol/world)
  :ret (s/coll-of :gol/coord))
(defn dimensions [w]
  (reduce (fn [[[mnx mny] [mxx mxy]]
               [cx cy]]
            [[(min mnx cx)
              (min mny cy)]
             [(max mxx cx)
              (max mxy cy)]])
          [[0 0] [0 0]]
          w))
