@Regression
@Search
Feature: user searches for a product

  Scenario: user searches for an available product
    When user enters "computer" in search bar
    Then product results appear

  Scenario: user searches for an unavailable product
    When user enters "sand" in search bar
    Then "No products were found" message is displayed