@OptInPendingEnrollmentlValidation @PaperlessPreference @iPortalRegression
Feature: Login in iPortal and validate OptInpending Policy message in the preference card 

Background:
	Given I visit the login page of the iPortal application using id

	
Scenario Outline: Paperless Preference OptInpending Policy message in the preference card:Chrome
	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	Then I see the Text : The customer must confirm enrollment via the email sent.
	When I click edit button from Paperless Preferences	
	Then I validate the policy documents and billings are pointed towards USMAIL with EValue Pending
Examples:
|Iteration|
|1|

Scenario Outline: Paperless Preference OptInpending Policy message in the preference card:InternetExplorer
	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	Then I see the Text : The customer must confirm enrollment via the email sent.
	When I click edit button from Paperless Preferences	
	Then I validate the policy documents and billings are pointed towards USMAIL with EValue Pending

Examples:
|Iteration|
|2|
Scenario Outline: Paperless Preference OptInpending Policy message in the preference card:Firefox
	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	Then I see the Text : The customer must confirm enrollment via the email sent.
	When I click edit button from Paperless Preferences	
	Then I validate the policy documents and billings are pointed towards USMAIL with EValue Pending
	
	Examples:
|Iteration|
|3|