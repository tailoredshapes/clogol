(use 'clojure.test)
(use 'clojure.string)
(use 'string-util)
(use 'gol)

(def game (atom #{}))

(Given #"^we have a game:$" [g]
       (reset! game (parse-world g)))

(Given #"^we place this at \((\d+),(\d+)\):$" [x y life]
       (let [coord [(read-string x) (read-string y)]
             l (parse-world life)]
         (swap! game
                (fn [g]
                  (into g
                        (translate-cells coord l))))))

(When #"^we advance the game$" []
      (swap! game then))

(When #"^we advance the game (\d+) times$" [n]
      (let [t (read-string n)]
        (repeat t (swap! game then))))

(Then #"^the cell (\d+),(\d+) should be dead$" [x y]
      (let [coord [(read-string x) (read-string y)]]
        (assert (not (alive? @game coord)) (str "Game: "
                                                @game
                                                "\t should not contain "
                                                coord))))

(Then #"^the cell (\d+),(\d+) should be alive$" [x y]
      (let [coord [(read-string x) (read-string y)] ]
        (assert (alive? @game coord) (str "Game: "
                                          @game
                                          "\t should contain "
                                          coord ))))

(Then #"^the game state is:$" [expected]
      (let [exp (parse-world expected)]
        (assert (= exp @game) (str "What we had: \n"
                                @game
                                "\nWhat we wanted: \n"
                                exp))))

(Then #"^there should be this at \((\d+),(\d+)\):$" [xs ys life]
      (let [x (read-string xs)
            y (read-string ys)
            dims (find-dimensions life)]
        #_(throw (cucumber.api.PendingException.))
        (assert (= (parse-world life) (sample @game
                                              [x y]
                                              [(+ (first dims) x)
                                               (+ (second dims) y)])))))
