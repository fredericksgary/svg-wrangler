(ns com.gfredericks.svg-wrangler
  (:require [clojure.string :as s]))

(defn css
  [m]
  (s/join (for [[k v] m]
            (format "%s:%s;" (name k) (str v)))))

(defn svg*
  [[minx miny user-width user-height :as dims] width height contents]
  [:svg {:xmlns "http://www.w3.org/2000/svg" :version "1.1"
         :viewBox (apply format "%f %f %f %f" (map double dims))
         :style (format "width:%dpx;height:%dpx;" width height)}
   contents])

(defn elem
  [tagname attrs]
  [tagname (update-in attrs [:style] css)])

(defn circle
  [cx cy r attrs]
  (elem :circle (assoc attrs :cx cy :cy cy :r r)))