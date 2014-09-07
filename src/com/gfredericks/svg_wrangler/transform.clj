(ns com.gfredericks.svg-wrangler.transform
  "Functions for creating transform strings."
  (:refer-clojure :exclude [comp]))

(defn ^:private call
  [function-name & nums]
  (str function-name
       \(
       (->> nums
            (map double)
            (clojure.string/join " "))
       \)))

(defn matrix
  [a b c d e f]
  (call "matrix" a b c d e f))

(defn translate
  ([x] (call "translate" x))
  ([x y] (call "translate" x y)))

(defn scale
  ([x] (call "scale" x))
  ([x y] (call "scale" x y)))

(defn rotate
  ([a] (call "rotate" a))
  ([a x y] (call "rotate" a x y)))

(defn skewX
  [a]
  (call "skewX" a))

(defn skewY
  [a]
  (call "skewY" a))

(defn comp
  [& strs]
  ;; I think this is the correct order.
  (clojure.string/join " " strs))
