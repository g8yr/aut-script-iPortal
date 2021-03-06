@PaymentWithCheckingAccount @Payment @iPortalRegression
Feature: Login in iPortal and Make payment with Checking Account

Background:
       Given I visit the login page of the iPortal application using id

       
Scenario Outline: Make payment with Checking Account:Chrome
   	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	Then I see Customer Overview page
	When I click iPortal Pay Now
	And  I enter my Checking Account details for iPortal AutoPay <Iteration>
	When I close the payment pop up 
	Then I see the payment reflected in the page  
	   
Examples:
|Iteration|
|1|

Scenario Outline: Make payment with Checking Account:InternetExplorer
   	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	Then I see Customer Overview page
	When I click iPortal Pay Now
	And  I enter my Checking Account details for iPortal AutoPay <Iteration>
	When I close the payment pop up 
	Then I see the payment reflected in the page  
	   
Examples:
|Iteration|
|2|

Scenario Outline: Make payment with Checking Account:Firefox
   	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	Then I see Customer Overview page
	When I click iPortal Pay Now
	And  I enter my Checking Account details for iPortal AutoPay <Iteration>
	When I close the payment pop up 
	Then I see the payment reflected in the page  
	   
Examples:
|Iteration|
|3|