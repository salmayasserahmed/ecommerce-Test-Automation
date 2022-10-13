@Regression
@Color
Feature: user filters with color
  Scenario: user wants to buy a red shoes
    When user goes to "https://demo.nopcommerce.com/shoes"
    And chooses "" Red "" from color attributes
    Then adidas 80s running shoes appears in results
