(ns live.core
  (:require [util.core :as util])
  (:gen-class
   :methods [^:static [handler [java.util.Map] java.util.Map]]))


(defn -handler [s]
  (let [event (util/->cljmap s)]
    (println "Hello World!")))
