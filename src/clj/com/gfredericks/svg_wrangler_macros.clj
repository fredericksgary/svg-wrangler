(ns com.gfredericks.svg-wrangler-macros)

(defmacro assoc-locals
  [x & names]
  `(assoc ~x ~@(mapcat (juxt keyword identity) names)))
