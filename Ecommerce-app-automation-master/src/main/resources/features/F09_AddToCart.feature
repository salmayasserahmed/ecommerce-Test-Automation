@Regression
@Cart
Feature: user could add different products to shopping cart
  Scenario: user adds a product to cart
    When user navigates to "https://demo.nopcommerce.com/first-prize-pies"
    And clicks on ADD TO CART
    Then Success message appears