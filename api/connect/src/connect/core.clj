(ns connect.core
  (:require [clojure.data.json :as json])
  (:gen-class
   :methods [^:static [handler [java.util.Map] java.util.Map]])
  (:import (com.amazonaws.services.dynamodbv2 AmazonDynamoDBClientBuilder)
           (com.amazonaws.services.dynamodbv2.document Item DynamoDB)))


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
                        "isBase64Encoded" false
                        "statusCode" 200
                        "headers" {"Content-Type" "text/plain"}
                        "body" "OK"})

(defn -handler [s]
  (let [event (->cljmap s)
        connectionId (-> event
                         :requestContext
                         :connectionId)
        client (.build (AmazonDynamoDBClientBuilder/standard))
        db (DynamoDB. client) table (.getTable db "clogol-views")]
    (doto (Item.) (.withPrimaryKey "connectionId" connectionId))
    response-template))
