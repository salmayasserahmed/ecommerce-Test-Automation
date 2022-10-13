@Regression
@Login
Feature: User logs into an existing account

  Scenario: User logs in with valid email and password
    When user enters "mariamelkhashab@outlook.com" in email login
    And user enters "123456" in password
    Then he gets redirected to home page as authenticated user

  Scenario: User logs in with invalid email or invalid password
    When user enters "mariamelkhashab@outlook.com" in email login
    And user enters "345678" in password
    Then he stays on the same page and error messeges for invalid login appear
