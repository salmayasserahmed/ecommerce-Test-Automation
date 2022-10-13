@Regression
@Tag
Feature: user could select different tags
  Scenario: user chooses tag from a subcategory
    When user clicks "Desktops" from "Computers" and redirected correctly
    And chooses "camera" from popular tags
    Then gets redirected to "camera" page

  Scenario: user chooses tag from a category
    When user clicks "Books" category
    And chooses "camera" from popular tags
    Then gets redirected to "camera" page