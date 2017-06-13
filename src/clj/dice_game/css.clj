(ns dice-game.css
  (:require [garden.def :refer [defstyles]]))

(def content-width "1200px")
(def teal "#3fb2bb")
(def teal-dark "#2f8c92")
(def white "#fff")
(def black "#231f20")
(def charcoal "#333333")
(def dark-grey "#4D4D4B")
(def light-grey "#eae8e3")
(def font "HelveticaNeue-thin")
(def font-bold "HelveticaNeue-light")
(def tan "#f7f5f0")
(def dark-tan "#F0EFEA")
(def error-red "#bc0505")

(defstyles
  screen
  [:body {:color       charcoal
          :background  tan
          :font-size  "20px"
          :font-family font}]
  [:.btn {:color         white
          :background    teal
          :border-radius "0"
          :border        "none"
          :align-self    "flex-start"
          :padding       "10px"}
   [:&:hover {:color      white
              :background teal-dark}]
   [:&:focus {:color      white
              :background teal-dark}]]
  [:.divider {:width "100%"
              :height "1px"
              :background white}]
  [:.game {:margin "0 auto"
           :padding "0 400px 0 0 "}
   [:.btn {:margin "25px 100px"
           :padding-left "40px"
           :padding-right "40px"
           :font-size "16px"}]
   [:.level1 {:color       charcoal
              :font-family font-bold}]
   [:.player {:background white
              :margin "15px 0"
              :width "450px"
              :font-size "20px"
              :padding "20px 20px 30px"
              :border-radius "10px 0 0 0"}
    [:.title {:margin "10px"
              :font-family font-bold}]]
   [:.die {:background    teal
           :width         "60px"
           :height        "60px"
           :border-radius "5px"
           :margin        "0 10px"}
    [:.row {:float  "left"
            :margin "0"}]
    [:.dot :.blank {:flex          "0 0 auto"
                    :border-radius "10px"
                    :width         "10px"
                    :height        "10px"
                    :margin        "5px"
                    :float         "left"}]
    [:.dot {:background white}]]])
