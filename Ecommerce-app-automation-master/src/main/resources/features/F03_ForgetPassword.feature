@Regression
@ResetPass
  Feature: User forgets password and asks to reset it

    Scenario: user resets password with valid email
      When user asks to reset with email "mariamelkhashab@outlook.com"
      Then "Email with instructions has been sent to you" is displayed

    Scenario: user resets password with invalid email
      When user asks to reset with email "mariamelkhashab@outlook"
      Then "Wrong Email" is displayed

    Scenario: user resets password with non existing email
      When user asks to reset with email "mahmoud@outlook.com"
      Then "Email not found" is displayed


