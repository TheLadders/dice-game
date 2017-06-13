(ns dice-game.core
  (:require [cheshire.core :as json]))

(def gs (atom []))
(def claims (atom [[0 0]]))

(defn game-state []
  (json/generate-string (pr-str @gs)))

(defn roll-die []
  (inc (rand-int 6)))

(defn roll-dice [num]
  (vec (repeatedly num roll-die)))

(defn init-players [num-players]
  (swap! gs (fn [_] (mapv #(roll-dice %) (repeat num-players 5)))))

(defn re-roll []
  (swap! gs #(mapv (fn [od] (roll-dice (count od))) %)))

(defn loser [player]
  (swap! gs #(update % player pop))
  (re-roll))

(defn safe? [num die]
  (->> @gs
       (map #(count (filter (fn [cur] (or (= 1 cur) (= die cur))) %)))
       (reduce +)
       (<= num)))

(defn valid-claim? [num die]
  (let [lclaim (peek @claims)
        [lnum ldie] lclaim]
    (and (< 0 num)
         (< 0 die)
         (>= 6 die)
         (or (> num lnum)
             (and (= num lnum)
                  (> die ldie))))))

(defn make-claim [num die]
  (if (valid-claim? num die)
    (swap! claims #(conj % [num die]))
    "That is not a valid claim"))

(init-players 5)