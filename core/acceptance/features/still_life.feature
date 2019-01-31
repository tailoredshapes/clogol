Feature: Stable patterns
  Scenario: Block
    Given we have a game:
      """
      ....
      .OO.
      .OO.
      ....
      """

    When we advance the game
    Then the game state is:
      """
      ....
      .OO.
      .OO.
      ....
      """

  Scenario: Behive
    Given we have a game:
      """
      ......
      ..OO..
      .O..O.
      ..OO..
      ......
      """
    When we advance the game
    Then the game state is:
      """
      ......
      ..OO..
      .O..O.
      ..OO..
      ......
      """

  Scenario: Loaf
    Given we have a game:
      """
      ......
      ..OO..
      .O..O.
      ..O.O.
      ...O..
      """
    When we advance the game
    Then the game state is:
      """
      ......
      ..OO..
      .O..O.
      ..O.O.
      ...O..
      """

  Scenario: Tub
    Given we have a game:
      """
      .....
      ..O..
      .O.O.
      ..O..
      .....
      """
    When we advance the game
    Then the game state is:
    """
    .....
    ..O..
    .O.O.
    ..O..
    .....
    """
