Feature: Big games
  It should be possible to process different neighbourhoods in parallel correctly.

  Scenario: Two stable shapes that don't interact
    Given we place this at (10,10):
      """
      ....
      .OO.
      .OO.
      ....
      """       

      And we place this at (50,50):
      """
      ....
      .OO.
      .OO.
      ....
      """       
    When we advance the game 10 times
    Then there should be this at (10,10):
      """
      ....
      .OO.
      .OO.
      ....
      """
     And there should be this at (50,50):
      """
      ....
      .OO.
      .OO.
      ....
      """       

  Scenario: Should identify neighbourhoods
      Given we have a game:
      """
      .......
      .OO.OO.
      .OO.OO.
      .......
      """
      
      When we request neighbourhoods
      Then then the 0 neighbourhood is:
      """
      .......
      .OO.OO.
      .OO.OO.
      .......
      """

Scenario: Should identify multiple neighbourhoods
    Given we have a game:
      """
      ....
      .OO.
      .OO.
      ....
      """       
      And we place this at (10,0):
      """
      ....
      .OO.
      .OO.
      ....
      """      
      When we request neighbourhoods
      Then then the 1 neighbourhood is:
      """
      ....
      .OO.
      .OO.
      ....
      """ 
       And then the 0 neighbourhood sampled at (10,0) is:
      """
      ....
      .OO.
      .OO.
      ....
      """ 

Scenario: Lets try counting neighbourhoods with a Gosper gun
  Given we have a game:
  """
  ........................O........... 
  ......................O.O........... 
  ............OO......OO............OO 
  ...........O...O....OO............OO 
  OO........O.....O...OO.............. 
  OO........O...O.OO....O.O........... 
  ..........O.....O.......O........... 
  ...........O...O.................... 
  ............OO......................
  """
  When we request neighbourhoods
  Then we have 4 neighbourhoods
