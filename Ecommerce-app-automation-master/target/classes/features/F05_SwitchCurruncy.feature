@Regression
@Switch
  Feature: user switches between available currency from dropdown
    Scenario: user switches "US Dollar" from to "Euro"
      When current currency is "US Dollar"
      And user chooses to switch to "Euro"
      Then switch is done successfully and "Euro" is visible