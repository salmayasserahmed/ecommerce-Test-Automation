@Regression
@Comparelist
Feature: user could add different products to Comparelist
  Scenario: user adds a product to Comparelist
    When user navigates to "https://demo.nopcommerce.com/first-prize-pies" compare
    And clicks on add to compare list
    Then compare list success message appears