Feature: Error Validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Error Validation for login
    Given I landed on Ecommerce page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | name             | password | 
      | shetty@gmail.com | Iamking@0 | 
