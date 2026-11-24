@login
Feature: Login

  @login
  Scenario Outline: Login to Ndosi Automation Website
    Given I am on home page
    And I click login button
    And I enter username <username>
    And I enter password <password>
    When I click submit button
    Then I should be logged in successfully

    Examples:
      | username | password |
      | titi@gmail.com    | tlou@97LT    |

#  Scenario Outline: Verify Menu button exist
#    Given I am on home page
#    And I click login button
#    And I enter username <username>
#    And I enter password <password>
#    When I click submit button
#    Then I should be logged in successfully
#    And Verify Menu button exist
#
#    Examples:
#      | username | password |
#      | titi@gmail.com    | tlou@97LT   |
