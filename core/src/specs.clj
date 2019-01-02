(ns specs
  (:require [clojure.spec.alpha :as s] ))

(s/def :gol/coord (s/coll-of nat-int? :count 2))
(s/def :gol/world (s/coll-of :gol/coord :kind set?))
