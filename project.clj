(defproject org.clojars.frozenlock/svg-wranger "0.1.5-snapshot"
;com.gfredericks/svg-wrangler "0.1.5-snapshot"  <---- replace the original once tests are completed.
  :description "Helpers for doing SVG with hiccup/crate."
  :url "https://github.com/fredericksgary/svg-wrangler"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2156"]]
  :source-paths ["src/clj" "target/generated/clj" "target/generated/cljs"]
  :plugins [[com.keminglabs/cljx "0.3.2"]
            [lein-cljsbuild "1.0.2"]]
  :cljx {:builds [{:source-paths ["src/cljx"]
                   :output-path "target/generated/clj"
                   :rules :clj}
                  {:source-paths ["src/cljx"]
                   :output-path "target/generated/cljs"
                   :rules :cljs}]}
  :cljsbuild {
              :builds [{:source-paths ["target/generated/cljs" "src/clj"]
                        :compiler {
                                   :output-to "main.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}]
              :jar true}
  :hooks [cljx.hooks leiningen.cljsbuild])
