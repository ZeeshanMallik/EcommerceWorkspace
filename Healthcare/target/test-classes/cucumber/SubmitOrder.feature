
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

 Background:
 Given I landed on Ecommerce page
  @tag2
  Scenario Outline: Positive Test of Submitting the order
  
    Given Logged in with Username <name> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name  									| password 			| productName  |
      | zishan.maalik@gmail.com | Zn@170990	    | ZARA COAT 3  |


      