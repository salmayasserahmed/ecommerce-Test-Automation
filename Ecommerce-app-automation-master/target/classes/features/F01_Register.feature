@Regression
@Registeration
Feature: User registers new account

  Scenario: User registers with valid data
    When user enters "Mariam" in first name and "Khashab" in second name
    And user enters "mohamedosama@outlook.com" in email
    And user enters "123456" in password and "123456" in confirm password
    Then he can register successfully and "Your registeration completed" messege appears

  Scenario: User registers with invalid email or invalid password or mismatch password
    When user enters "Mariam" in first name and "Khashab" in second name
    And user enters "mariamelkhashab.com" in email
    And user enters "12345" in password and "123456" in confirm password
    Then error messeges for invalid fields appear

  Scenario: User registers with any empty data field
    When user enters "mariam" in first name and "" in second name
    And user enters "mariamelkhashab@outlook" in email
    And user enters "123456" in password and "" in confirm password
    Then error messeges for empty fields appear appear

  Scenario: User registers with an existing email
    When user enters "Mahmoud" in first name and "Ahmed" in second name
    And user enters "mohamedosama@outlook.com" in email
    And user enters "123456" in password and "123456" in confirm password
    Then error messeges for existing account appear
