@PreferencesSetValidation @PaperlessPreference @iPortalRegression
Feature: Login in iPortal and validate Preference Set

Background:
	Given I visit the login page of the iPortal application using id

	
Scenario Outline: OptInPending Policy Preference Set validation:Chrome
	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	When I click edit button from Paperless Preferences
	Then I select Policy Documents as EMAIL and Policy billing as EMAIL
	And I click Save in iPortal
	When I click edit button from Paperless Preferences
	Then I validate the policy documents and billings are pointed towards US MAIL with EValue Pending
Examples:
|Iteration|
|1|


Scenario Outline: OptInPending Policy Preference Set validation:InternetExplorer
	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	When I click edit button from Paperless Preferences
	Then I select Policy Documents as EMAIL and Policy billing as EMAIL
	And  I click Save in iPortal
	When I click edit button from Paperless Preferences
	Then I validate the policy documents and billings are pointed towards US MAIL with EValue Pending
	
Examples:
|Iteration|
|2|
	
Scenario Outline: OptInPending Policy Preference Set validation:Firefox
	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	When I click edit button from Paperless Preferences
	Then I select Policy Documents as EMAIL and Policy billing as EMAIL
	And  I click Save in iPortal
	When I click edit button from Paperless Preferences
	Then I validate the policy documents and billings are pointed towards US MAIL with EValue Pending	

Examples:
|Iteration|
|3|
	
	