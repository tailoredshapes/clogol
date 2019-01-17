(ns disconnect.core
  (:require [util.core :as util])
  (:gen-class
   :methods [^:static [handler [java.util.Map] java.util.Map]])
  (:import (com.amazonaws.services.dynamodbv2 AmazonDynamoDBClientBuilder)
           (com.amazonaws.services.dynamodbv2.document DynamoDB)
           (com.amazonaws.services.dynamodbv2.document.spec DeleteItemSpec)))


(def response-template {
                        "isBase64Encoded" false
                        "statusCode" 200
                        "headers" {}
                        "body" "OK"
                        })


(defn -handler [s]
  (let [event (util/->cljmap s)
        connectionId (-> event
                         :requestContext
                         :connectionId)
        client (.build (AmazonDynamoDBClientBuilder/standard))
        db (DynamoDB. client)
        table (.getTable db "clogol-views")]
    (.deleteItem table
                 (doto
                     (DeleteItemSpec.)
                   (.withPrimaryKey "connectionId" connectionId)))

    response-template))
