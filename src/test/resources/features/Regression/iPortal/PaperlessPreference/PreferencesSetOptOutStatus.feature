@PreferencesSetOptOutStatus @PaperlessPreference @iPortalRegression
Feature: Login in iPortal and validate OptOut Paperless Preferences Status 

Background:
	Given I visit the login page of the iPortal application using id

	
Scenario Outline: Paperless Preferences OptOut Status:Chrome
	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	When I click edit button from Paperless Preferences
	Then I validate the policy documents and billings are pointed towards USMAIL with EValue Opt Out 

Examples:
|Iteration|
|1|

Scenario Outline: Paperless Preferences OptOut Status:InternetExplorer
	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	When I click edit button from Paperless Preferences
	Then I validate the policy documents and billings are pointed towards USMAIL with EValue Opt Out 

Examples:
|Iteration|
|2|

Scenario Outline: Paperless Preferences OptOut Status:Firefox
	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	When I click edit button from Paperless Preferences
	Then I validate the policy documents and billings are pointed towards USMAIL with EValue Opt Out
	
	Examples:
|Iteration|
|3|