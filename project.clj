(defproject gol "0.0.1"
  :description "A demo of Cucumber with Clojure and Leiningen"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [info.cukes/cucumber-clojure "1.1.1"]]
  :plugins [[com.siili/lein-cucumber "1.0.7"]
            [jonase/eastwood "0.3.3"]]
  :test-paths ["acceptance/features" "acceptance/step_definitions" "test"]
  :cucumber-feature-paths ["acceptance/features"])
