@eValueActiveChangePreferenceValidation @PaperlessPreference @iPortalRegression
Feature: Login in iPortal and validate eValue Active Policy Preference Alert message 

Background:
	Given I visit the login page of the iPortal application using id

Scenario Outline: eValue Active Policy Preference Alert message:Chrome
	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	When I click edit button from Paperless Preferences
	Then I select Policy Documents as USMAIL and Policy billing as USMAIL	
	Then I see the Text : If you unenroll the customer from paperless notifications, their eValue discount will be removed.
	And  I click Cancel button from Preference Window
Examples:
|Iteration|
|1|		
	
Scenario Outline: eValue Active Policy Preference Alert message:InternetExplorer
	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	When I click edit button from Paperless Preferences
	Then I select Policy Documents as USMAIL and Policy billing as USMAIL	
	Then I see the Text : If you unenroll the customer from paperless notifications, their eValue discount will be removed.
	And  I click Cancel button from Preference Window
Examples:
|Iteration|
|2|		
	
Scenario Outline: eValue Active Policy Preference Alert message:Firefox
	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	When I click edit button from Paperless Preferences
	Then I select Policy Documents as USMAIL and Policy billing as USMAIL	
	Then I see the Text : If you unenroll the customer from paperless notifications, their eValue discount will be removed.
	And  I click Cancel button from Preference Window

	Examples:
|Iteration|
|3|	