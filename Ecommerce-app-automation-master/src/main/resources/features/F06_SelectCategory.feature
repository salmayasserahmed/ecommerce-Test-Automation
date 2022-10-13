@Regression
@Category
Feature: user could select different categories and sub categories
  Scenario: user chooses a category with a sub category
    When user hoovers over "Computers"
    And user chooses "Desktops"
    And clicks on his choice
    Then he is redirected to "Desktops" page

  Scenario: user chooses a category without a sub category
    When user hoovers over "Books"
    And clicks on his choice
    Then he is redirected to "Books" page