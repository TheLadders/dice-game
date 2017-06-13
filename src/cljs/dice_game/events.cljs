(ns dice-game.events
  (:require [re-frame.core :as re-frame]
            [ajax.core :as ajax]
            [dice-game.db :as db]
            [cljs.reader :as edn]
            [day8.re-frame.http-fx]
            [clojure.walk :as w]))

(re-frame/reg-event-db
  :initialize-db
  (fn [_ _]
    db/default-db))

(re-frame/reg-event-db
  :set-active-panel
  (fn [db [_ active-panel]]
    (assoc db :active-panel active-panel)))


(re-frame/reg-event-db
  :process-gs
  (fn [db [_ response]]
    (let [gs (-> response
                 str
                 edn/read-string)]
      (assoc db :gs gs))))

(re-frame/reg-event-fx
  :get-game-state
  (fn [{:keys [db]} _]
    {:http-xhrio {:method          :get
                  :uri             "/gs"
                  :params          []
                  :timeout         5000
                  :format          (ajax/json-request-format)
                  :response-format (ajax/json-response-format {:keywords? true})
                  :on-success      [:process-gs]
                  :on-failure      [:process-gs]}}))