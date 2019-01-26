(use 'clojure.test)
(use 'clojure.string)
(use 'string-util)
(use 'gol)

(def game (atom #{}))

(Given #"^we have a game:$" [g]
       (reset! game (parse-world g)))

(When #"^we advance the game$" []
      (swap! game then))

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

(Given #"^we place this at \((\d+),(\d+)\):$" [x y life]
       (comment  Write code here that turns the phrase above into concrete actions  )
       (throw (cucumber.api.PendingException.)))

(When #"^we advance the game (\d+) times$" [n]
      (repeat n (swap! game then)))

(Then #"^there should be this at \((\d+),(\d+)\):$" [x y life]
      (comment  Write code here that turns the phrase above into concrete actions  )
      (throw (cucumber.api.PendingException.)))
