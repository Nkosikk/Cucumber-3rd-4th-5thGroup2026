@login
Feature: Login

  @ValidLogin
  Scenario Outline: Login to Ndosi Automation Website with valid username and password
    Given I am on home page
    And I click login button
    And I enter username <username>
    And I enter password <password>
    When I click submit button
    Then I should be logged in successfully

    Examples:
      | username | password |
      | njceles@gmail.com    | @12345678    |


  @InvalidLogin
  Scenario Outline: Login to Ndosi Automation Website with invalid username and password
    Given I am on home page
    And I click login button
    And I enter username <username>
    And I enter password <password>
    When I click submit button
    Then I should see an error message

    Examples:
      | username | password |
      | njceles@gmail.comxd    | @12345678    |

