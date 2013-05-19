(ns com.gfredericks.svg-wrangler
  (:require [clojure.string :as s]))

(defmacro assoc-locals
  [x & names]
  `(assoc ~x ~@(mapcat (juxt keyword identity) names)))

(defn css
  [m]
  (s/join (for [[k v] m]
            (format "%s:%s;" (name k) (str v)))))

(defn set-style
  [attrs]
  (cond-> attrs
          (map? (:style attrs))
          (update-in [:style] css)))

(defn svg*
  [[minx miny user-width user-height :as dims] width height contents]
  [:svg {:xmlns "http://www.w3.org/2000/svg" :version "1.1"
         :viewBox (apply format "%f %f %f %f" (map double dims))
         :style (format "width:%dpx;height:%dpx;" width height)}
   contents])

(defn ^:private points-str
  [sep points]
  (s/join sep (for [[x y] points] (str (double x) \, (double y)))))

(defn elem
  ([tagname attrs]
     [tagname (set-style attrs)])
  ([tagname attrs contents]
     [tagname (set-style attrs) contents]))

(defn circle
  ([cx cy r] (circle cx cy r) {})
  ([cx cy r attrs]
     (elem :circle (assoc-locals attrs cx cy r))))

(defn line
  ([x1 y1 x2 y2] (line x1 y1 x2 y2 {}))
  ([x1 y1 x2 y2 attrs]
     (elem :line (assoc-locals attrs x1 y1 x2 y2))))

(defn polyline
  ([points] (polyline points {}))
  ([points attrs]
     (elem :polyline (assoc attrs :points (points-str " " points)))))

(defn rect
  ([x y width height] (rect x y width height {}))
  ([x y width height attrs]
     (elem :rect (assoc-locals attrs x y width height))))

(defn text
  ([x y val] (text x y val {}))
  ([x y val attrs]
     (elem :text (assoc-locals attrs x y) val)))
