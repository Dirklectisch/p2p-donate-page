(defproject p2p-donate-script "0.1.0-SNAPSHOT"
  :description "Payments functionality for p2p foundation donation page"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [domina "1.0.0"]]
  :plugins [[lein-cljsbuild "0.2.9"]]
  :cljsbuild {
    :builds [{:source-path "src-cljs"
              :compiler {:output-to "target/js/main-debug.js"
                         :output-dir "out-debug"
                         :optimizations :simple
                         :pretty-print true}}
             {:source-path "src-cljs"
                           :compiler {:output-to "target/js/main.js"
                                      :output-dir "out"
                                      :optimizations :advanced
                                      :pretty-print false}}]
   :repl-listen-port 9000
   :repl-launch-commands
     ; Launch command for connecting the page of choice to the REPL.
     ; Only works if the page at URL automatically connects to the REPL,
     ;     $ lein trampoline cljsbuild repl-launch default <URL>
     {"default" ["open"
                 :stdout ".repl-default-out"
                 :stderr ".repl-default-err"]}})