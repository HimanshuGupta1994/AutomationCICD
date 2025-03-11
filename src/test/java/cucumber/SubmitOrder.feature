Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature file

  Background:
    Given I landed on Ecommerce page

  @Regression
  Scenario Outline: Positive Test of Submitting the order

    Given Logged in with username <username> and password <password>
    When I add product <productname> to cart
    And checkout <productname> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page

    Examples:
      | username         | password    | productname |
      | shetty@gmail.com | Iamking@000 | ZARA COAT 3 |
      | shetty@gmail.com | Iamking@000 | ZARA COAT 3 |
      | shetty@gmail.com | Iamking@000 | ZARA COAT 3 |
      
