Feature: Login

  Scenario Outline: Register at ndosi automation
    Given I am on home page
    And I click login button
    And I enter username <username>
    And I enter password <password>
    When I click submit button
    Then I should be logged in successfully

    Examples:
      | username | password |
      | titi@gmail.com | tlou@97LT    |
  #    | user2    | pass2    |
