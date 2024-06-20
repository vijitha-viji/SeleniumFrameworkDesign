
@tag
Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature file

Background:
Given I landed on Ecommerce page

  @tag1
  Scenario: Positive test of submitting the order
  
    Given Logged in with username <username> and password <password>
    When I add product <productName> to cart
    And Checkout <prouctName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmationPage   

    Examples: 
      | name                      | password | productName  |
      | vijithaavasudev@gmail.com | Venba@26 | ZARA COAT 3 |
      
