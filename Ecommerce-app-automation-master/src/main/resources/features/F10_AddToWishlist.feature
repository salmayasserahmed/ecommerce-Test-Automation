@Regression
@Wishlist
Feature: user could add different products to wishlist
  Scenario: user adds a product to wishlist
    When user navigates to "https://demo.nopcommerce.com/first-prize-pies" wish
    And clicks on add to wishlist
    Then wishlist success message appears