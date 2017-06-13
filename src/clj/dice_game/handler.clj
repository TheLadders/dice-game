(ns dice-game.handler
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :refer [resources]]
            [ring.util.response :refer [resource-response]]
            [ring.middleware.reload :refer [wrap-reload]]
            [dice-game.core :as c]))

(defroutes routes
  (GET "/" [] (resource-response "index.html" {:root "public"}))
  (GET "/gs" [] (c/game-state))
  (resources "/"))

(def dev-handler (-> #'routes wrap-reload))

(def handler routes)
