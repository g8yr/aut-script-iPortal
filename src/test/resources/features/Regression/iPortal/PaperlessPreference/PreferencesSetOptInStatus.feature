@PreferencesSetOptInStatus @PaperlessPreference @iPortalRegression
Feature: Login in iPortal and validate OptIn Paperless Preferences Status 

Background:
	Given I visit the login page of the iPortal application using id

	
Scenario Outline: Paperless Preferences OptIn Status:Chrome
	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	When I click edit button from Paperless Preferences
	Then I validate the policy documents and billings are pointed towards EMAIL with EValue Active 

Examples:
|Iteration|
|1|

Scenario Outline: Paperless Preferences OptIn Status:InternetExplorer
	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	When I click edit button from Paperless Preferences
	Then I validate the policy documents and billings are pointed towards EMAIL with EValue Active 

Examples:
|Iteration|
|2|

Scenario Outline: Paperless Preferences OptIn Status:Firefox
	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	When I click edit button from Paperless Preferences
	Then I validate the policy documents and billings are pointed towards EMAIL with EValue Active
	
	Examples:
|Iteration|
|3|