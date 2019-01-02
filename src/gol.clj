(ns gol
  (:require [clojure.spec.alpha :as s]
            [specs :as p]))


(def neighbours
  (filter #(not (= [0 0] %))
          (apply concat
                 (map #(map (partial conj [%])
                            (range -1 2))
                      (range -1 2)))))


(s/fdef add-vec
  :args (s/coll-of :gol/coord)
  :ret :gol/coord)
(defn add-vec
  "adds the members of the vectors together"
  [& vecs]
  (apply map + vecs))


(s/fdef bring-to-life
  :args (s/cat :game :gol/world :coords :gol/coord)
  :ret ::world)
(defn bring-to-life
  "Brings a cell in the game to life"
  [game coords]
  (conj game coords))


(s/fdef kill
  :args (s/cat :game :gol/world :coords :gol/coord)
  :ret ::world)
(defn kill
  "Kills a cell in the game"
  [game coords]
  (disj game coords))


(s/fdef alive?
  :args (s/cat :game :gol/world :coords :gol/coord)
  :ret boolean?)
(defn alive?
  "Checks the status of a cell in the game"
  [game coords]
  (contains? game coords))

(defn neg-vec? [[x y]]
  (or (< x 0) (< y 0)))

(s/fdef get-neighbours
  :arg (s/cat :coord :gol/coord)
  :ret (s/coll-of ::coord :type set?))
(defn get-neighbours
  "Lists all cells that are adjacent to the cell"
  [coord]
  (into #{} (remove neg-vec? (map (partial add-vec coord) neighbours))))


(s/fdef count-neighbours
  :arg (s/cat :game :gol/world :coords :gol/coord)
  :ret (s/and int? #(>= % 0) #(<= % 8)))
(defn count-neighbours
  "Counts the number of living neighbours of a cell"
  [game coords]
  (count (filter #(alive? game %)
                 (get-neighbours coords))))


(s/fdef interesting-cells
  :args (s/cat :game :gol/world)
  :ret :gol/world)
(defn interesting-cells
  "Lists all the cells that may influence the turn"
  [game]
  (reduce (fn [s cell]
            (conj (into s
                        (get-neighbours cell))
                  cell))
          #{}
          game))


(s/fdef survive?
  :args (s/cat :game :gol/world
               :coord :gol/coord
               :num-neighbours (s/and int? #(>= % 0) #(<= % 8)))
  :ret boolean?)
(defn survive?
  "Dictates if cell should survive"
  [game coord num-neighbours]
  (if (alive? game coord)
    (contains? #{2 3} num-neighbours)
    (= 3 num-neighbours)))


(s/fdef then
  :args (s/cat :game :gol/world)
  :ret :gol/world)
(defn then
  "Calculates the next state of the game"
  [game]
  (reduce (fn [s coord]
            (if (->> coord
                     (count-neighbours game)
                     (survive? game coord))
              (conj s coord)
              s))
          #{}
          (interesting-cells game)))
