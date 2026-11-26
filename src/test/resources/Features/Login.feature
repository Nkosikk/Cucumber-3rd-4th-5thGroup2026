@login
Feature: Login

  Background:
    Given I am on home page
    And I click login button
    And I enter username <username>
    And I enter password <password>
    When I click submit button




  @ValidLogin
  Scenario: Login to Ndosi Automation Website with valid username and password
    Then I should be logged in successfully



  @InvalidLogin
  Scenario: Login to Ndosi Automation Website with invalid username and password
    Then I should see an error message


