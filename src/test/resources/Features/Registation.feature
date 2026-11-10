Feature: User registration

  Scenario: Register users with random details and groups
    Given I am on home page
    And I click login button
    When I register 3 users with random details
    Then the registered users should be saved to "test-output/registered-users.csv"