Feature: userLogin
  As a registered user
  I want to login with valid credentials
  So that I can access my Amazon account

  Scenario: Successful login with valid credentials
    Given user is on the login page
    When user is logged in with emaiL "test.email" and passworD "test.password"
    Then user should be logged in successfully "test.ownerName"
