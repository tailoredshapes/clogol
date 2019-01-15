(ns connect.core
  (:require [clojure.data.json :as json])
  (:gen-class
   :methods [^:static [handler [java.util.Map] String]]))


(defprotocol ConvertibleToClojure
  (->cljmap [o]))

(extend-protocol ConvertibleToClojure
  java.util.Map
  (->cljmap [o] (let [entries (.entrySet o)]
                (reduce (fn [m [^String k v]]
                          (assoc m (keyword k) (->cljmap v)))
                        {} entries)))

  java.util.List
  (->cljmap [o] (vec (map ->cljmap o)))

  java.lang.Object
  (->cljmap [o] o)

  nil
  (->cljmap [_] nil))

(def response-template {
                        :isBase64Encoded false
                        :statusCode 200
                        :headers {}
                        :body "OK"
                        })


(defn -handler [s]
  (let [event (->cljmap s)]
    (println (str "event: " event))
    (println (str "Response" (json/write-str response-template)))
    (println (str "ConnectionId:" (-> event
                                   :requestContext
                                   :connectionId)))
    (json/write-str response-template)))
