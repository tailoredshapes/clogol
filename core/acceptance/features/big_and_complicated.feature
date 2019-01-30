Feature: Big games
  It should be possible to process different neighbourhoods in parallel correctly.

  Scenario: Two stable shapes that don't interact
    Given we place this at (10,10):
      """
      0,0,0,0
      0,X,X,0
      0,X,X,0
      0,0,0,0
      """
      And we place this at (100,100):
      """
      0,0,0,0
      0,X,X,0
      0,X,X,0
      0,0,0,0
      """
    When we advance the game 10 times
    Then there should be this at (10,10):
      """
      0,0,0,0
      0,X,X,0
      0,X,X,0
      0,0,0,0
      """
     And there should be this at (100,100):
      """
      0,0,0,0
      0,X,X,0
      0,X,X,0
      0,0,0,0
      """

  Scenario: Should identify neighbourhoods
    Given we place this at (0,0):
      """
      0,0,0,0
      0,X,X,0
      0,X,X,0
      0,0,0,0
      """
      And we place this at (3,0):
      """
      0,0,0,0
      0,X,X,0
      0,X,X,0
      0,0,0,0
      """
      When we request neighbourhoods
      Then then the 0 neighbourhood is:
      """
      0,0,0,0,0,0,0
      0,X,X,0,X,X,0
      0,X,X,0,X,X,0
      0,0,0,0,0,0,0
      """

Scenario: Should identify multiple neighbourhoods
    Given we place this at (0,0):
      """
      0,0,0,0
      0,X,X,0
      0,X,X,0
      0,0,0,0
      """
      And we place this at (10,0):
      """
      0,0,0,0
      0,X,X,0
      0,X,X,0
      0,0,0,0
      """
      When we request neighbourhoods
      Then then the 0 neighbourhood is:
      """
      0,0,0,0
      0,X,X,0
      0,X,X,0
      0,0,0,0
      """
       And then the 1 neighbourhood is:
      """
      0,0,0,0
      0,X,X,0
      0,X,X,0
      0,0,0,0
      """ 
