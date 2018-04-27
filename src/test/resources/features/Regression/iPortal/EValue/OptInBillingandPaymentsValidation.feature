@OptInBillingandPaymentsValidation @EValue @iPortalRegression
Feature: Login in iPortal and Validate payment remainders and confirmations are turned On

Background:
       Given I visit the login page of the iPortal application using id

       
Scenario Outline: Validate payment remainders and confirmations are turned on:Chrome
   	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	Then I see Customer Overview page
	When I click edit button from Paperless Preferences
	Then I select Policy Documents as USMAIL and Policy billing as EMAIL
	And I check Payment Reminders and Payment Confirmations are automatically turned on	 
	   
Examples:
|Iteration|
|1|

Scenario Outline: Validate payment remainders and confirmations are turned on:InternetExplorer
   	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	Then I see Customer Overview page
	When I click edit button from Paperless Preferences
	Then I select Policy Documents as USMAIL and Policy billing as EMAIL
	And I check Payment Reminders and Payment Confirmations are automatically turned on	 
	   
Examples:
|Iteration|
|2|

Scenario Outline: Validate payment remainders and confirmations are turned on:Firefox
   	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	Then I see Customer Overview page
	When I click edit button from Paperless Preferences
	Then I select Policy Documents as USMAIL and Policy billing as EMAIL
	And I check Payment Reminders and Payment Confirmations are automatically turned on	 
	   
Examples:
|Iteration|
|3|
