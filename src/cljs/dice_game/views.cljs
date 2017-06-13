(ns dice-game.views
  (:require [re-frame.core :as re-frame]
            [re-com.core :as rc]))


;; home

(defn game-title []
  (fn []
    [rc/h-box :children [[rc/title
                          :label (str "Liars Dice")
                          :level :level1]
                         [rc/button :label "Start" :on-click #(re-frame/dispatch [:get-game-state])]]]))

(defn die-draw [num]
  (nth [#_one
        [[0 0 0]
         [0 1 0]
         [0 0 0]]
        #_two
        [[1 0 0]
         [0 0 0]
         [0 0 1]]
        #_three
        [[1 0 0]
         [0 1 0]
         [0 0 1]]
        #_four
        [[1 0 1]
         [0 0 0]
         [1 0 1]]
        #_five
        [[1 0 1]
         [0 1 0]
         [1 0 1]]
        #_six
        [[1 0 1]
         [1 0 1]
         [1 0 1]]] (dec num)))

(defn one? [num]
  (= 1 num))

(defn dot-or-blank [n]
  (fn []
    (if (one? n)
      [:div {:class "dot"}]
      [:div {:class "blank"}])))

(defn die-row [dr]
  (fn [] (into [:div {:class "row"}] (map #(vector dot-or-blank %) dr))))

(defn die [num]
  [rc/v-box
   :class (str "die " num "die")
   :children (mapv #(vector die-row %) (die-draw num))])

(defn dice [pdice]
  (fn []
    [rc/h-box :children (mapv die pdice)]))

(defn player-info [name pdice]
  (fn [] [rc/v-box
          :class "player"
          :children [[rc/label :class "title" :label (str "Player: " name)]
                     [dice pdice]]]))

(def players
  (map #(str "Player " (inc %)) (range)))

(defn player-list []
  (fn []
    (let [gs (re-frame/subscribe [:gs])]
      [rc/v-box
       :children (mapv (partial vector player-info) players @gs)])))

(defn game-panel []
  (fn []
    [rc/v-box
     :class "game"
     :children [[game-title]
                [:div {:class "divider"}]
                [player-list]]]))


;; about

(defn about-title []
  [rc/title
   :label "This is the About Page."
   :level :level1])

(defn link-to-home-page []
  [rc/hyperlink-href
   :label "go to Home Page"
   :href "#/"])

(defn about-panel []
  [rc/v-box
   :gap "1em"
   :children [[about-title] [link-to-home-page]]])


;; main

(defn- panels [panel-name]
  (case panel-name
    :game-panel [game-panel]
    :about-panel [about-panel]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      [rc/v-box
       :height "100%"
       :children [[panels @active-panel]]])))
