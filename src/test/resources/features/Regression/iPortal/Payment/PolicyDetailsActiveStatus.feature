@PaymentWithCreditCard @Payment @iPortalRegression
Feature: Login in iPortal and Make payment with Credit card

Background:
       Given I visit the login page of the iPortal application using id

       
Scenario Outline: Make payment with Credit card:Chrome
   	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	Then I see Customer Overview page
	When I click iPortal Pay Now
	And  I enter my Credit Card details for iPortal AutoPay <Iteration>
	Then I see the iPortal text:You have successfully set up AutoPay. 
	    
Examples:
|Iteration|
|1|