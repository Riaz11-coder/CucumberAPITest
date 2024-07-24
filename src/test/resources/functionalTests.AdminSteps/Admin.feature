Feature: Administrative features

  @ListUsers
  Scenario: Retrieve a list of users
    When I request the list of users
    Then I should receive a successful response with user details
