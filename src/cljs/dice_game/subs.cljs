(ns dice-game.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :gs
 (fn [db]
   (:gs db [])))

(re-frame/reg-sub
 :active-panel
 (fn [db _]
   (:active-panel db)))

