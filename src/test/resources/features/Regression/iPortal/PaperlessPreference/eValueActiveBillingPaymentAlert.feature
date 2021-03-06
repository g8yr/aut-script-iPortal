@eValueActiveBillingPaymentAlert @PaperlessPreference @iPortalRegression
Feature: Login in iPortal and validate Active eValue Policy Document Alert message 

Background:
	Given I visit the login page of the iPortal application using id

Scenario Outline: eValue Policy Document Alert message:Chrome
	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	When I click edit button from Paperless Preferences
	Then I select Policy Documents as EMAIL and Policy billing as USMAIL
	Then I see the Text : If you unenroll the customer from paperless notifications, their eValue discount will be removed.
	And  I click Save in iPortal
	Then I check the Evalue NoneValue Icon Status
	
Examples:
|Iteration|
|1|	

Scenario Outline: eValue Policy Document Alert message:InternetExplorer
	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	When I click edit button from Paperless Preferences
	Then I select Policy Documents as EMAIL and Policy billing as USMAIL	
	Then I see the Text : If you unenroll the customer from paperless notifications, their eValue discount will be removed.
	And  I click Save in iPortal
	Then I check the Evalue NoneValue Icon Status
Examples:
|Iteration|
|2|	
	
Scenario Outline: eValue Policy Document Alert message:Firefox
	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	When I click edit button from Paperless Preferences
	Then I select Policy Documents as EMAIL and Policy billing as USMAIL
	Then I see the Text : If you unenroll the customer from paperless notifications, their eValue discount will be removed.
	And  I click Save in iPortal
	Then I check the Evalue NoneValue Icon Status

Examples:
|Iteration|
|3|	

