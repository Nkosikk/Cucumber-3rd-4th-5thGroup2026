Feature: Login

  Scenario Outline: Register at ndosi automtion
    Given I am on home page
    And I click login button
    And I enter username <username>
    And I enter password <password>
    When I click submit button
    Then I should be logged in successfully

    Examples:
      | username | password |
      | njceles@gmail.com    | @12345678    |
#      | user2    | pass2    |
