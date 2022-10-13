@Regression
@CreateOrder
Feature: Logged in user checksout from card and places an order

  Scenario: un authenticated  user tries to checkout
    Then user clicks on checkout button from "https://demo.nopcommerce.com/htc-one-m8-android-l-50-lollipop" and accepts terms of service
    Then he is redirected to login page

  Scenario: authenticated  user tries to checkout
    When user logs in with "msm@outlook.com", "123456"
    Then user clicks on checkout button from "https://demo.nopcommerce.com/htc-one-m8-android-l-50-lollipop" and accepts terms of service
    And he is redirected to checkout page
    And enter "Mariam" "Osama" "Egypt" "Giza" "abc" "123" "011111"
    And Fills all the steps and clicks continue
    Then Your order has been successfully processed! appears


