Feature: Login

  Scenario Outline: Login to Ndosi Automation Website
    Given I am on home page page
    And I click login button
    And I enter username <username>
    And I enter password <password>
    When I click submit button
    Then I should be logged in successfully

    Examples:
      | username | password |
      | user1    | pass1    |
      | user2    | pass2    |
