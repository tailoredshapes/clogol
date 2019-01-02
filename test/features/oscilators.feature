Feature: Oscillating patterns
  Scenario: Blinker
    Given we have a game:
      """
      0,0,0,0,0
      0,0,X,0,0
      0,0,X,0,0
      0,0,X,0,0
      0,0,0,0,0
      """
    When we advance the game
    Then the game state is:
      """
      0,0,0,0,0
      0,0,0,0,0
      0,X,X,X,0
      0,0,0,0,0
      0,0,0,0,0
      """

    Given we have a game:
      """
      0,0,0,0,0
      0,0,0,0,0
      0,X,X,X,0
      0,0,0,0,0
      0,0,0,0,0
      """
    When we advance the game
    Then the game state is:
      """
      0,0,0,0,0
      0,0,X,0,0
      0,0,X,0,0
      0,0,X,0,0
      0,0,0,0,0
      """

  Scenario: Blinker
    Given we have a game:
      """
      0,0,0,0,0,0
      0,0,0,X,0,0
      0,X,0,0,X,0
      0,X,0,0,X,0
      0,0,X,0,0,0
      0,0,0,0,0,0
      """
    When we advance the game
    Then the game state is:
      """
      0,0,0,0,0,0
      0,0,0,0,0,0
      0,0,X,X,X,0
      0,X,X,X,0,0
      0,0,0,0,0,0
      0,0,0,0,0,0
      """

    Given we have a game:
      """
      0,0,0,0,0,0
      0,0,0,0,0,0
      0,0,X,X,X,0
      0,X,X,X,0,0
      0,0,0,0,0,0
      0,0,0,0,0,0
      """
    When we advance the game
    Then the game state is:
      """
      0,0,0,0,0,0
      0,0,0,X,0,0
      0,X,0,0,X,0
      0,X,0,0,X,0
      0,0,X,0,0,0
      0,0,0,0,0,0
      """

  Scenario: Beacon
    Given we have a game:
      """
      0,0,0,0,0,0
      0,X,X,0,0,0
      0,X,0,0,0,0
      0,0,0,0,X,0
      0,0,0,X,X,0
      0,0,0,0,0,0
      """
    When we advance the game
    Then the game state is:
      """
      0,0,0,0,0,0
      0,X,X,0,0,0
      0,X,X,0,0,0
      0,0,0,X,X,0
      0,0,0,X,X,0
      0,0,0,0,0,0
      """

    Given we have a game:
      """
      0,0,0,0,0,0
      0,X,X,0,0,0
      0,X,X,0,0,0
      0,0,0,X,X,0
      0,0,0,X,X,0
      0,0,0,0,0,0
      """
    When we advance the game
    Then the game state is:
      """
      0,0,0,0,0,0
      0,X,X,0,0,0
      0,X,0,0,0,0
      0,0,0,0,X,0
      0,0,0,X,X,0
      0,0,0,0,0,0
      """