(ns api.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(def world (atom #{}))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/world" [] @world)
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
