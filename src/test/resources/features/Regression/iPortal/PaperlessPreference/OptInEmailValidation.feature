@OptInEmailValidation @PaperlessPreference @iPortalRegression
Feature: Login in iPortal and validate PAS Auto OptIn Policy email address in the preference card 

Background:
	Given I visit the login page of the iPortal application using id

Scenario Outline: Paperless Preference OptIn Policy email address in the preference card:Chrome
	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	When I click edit button from Paperless Preferences
	Then I select Policy Documents as EMAIL and Policy billing as EMAIL
	And  I change the email address as evaluetestingdemo+77@gmail.com
	And  I click Save in iPortal
	Then I validate the email address evaluetestingdemo+77@gmail.com in Preference Card	
	When I click edit button from Paperless Preferences
	And  I change the email address as evaluetestingdemo+66@gmail.com
	And  I click Save in iPortal
Examples:
|Iteration|
|1|		
Scenario Outline: Paperless Preference OptIn Policy email address in the preference card:InternetExplorer
	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	When I click edit button from Paperless Preferences
	Then I select Policy Documents as EMAIL and Policy billing as EMAIL
	And  I change the email address as evaluetestingdemo+77@gmail.com
	And  I click Save in iPortal
	Then I validate the email address evaluetestingdemo+77@gmail.com in Preference Card	
	When I click edit button from Paperless Preferences
	And  I change the email address as evaluetestingdemo+66@gmail.com
	And  I click Save in iPortal
Examples:
|Iteration|
|2|	

Scenario Outline: Paperless Preference OptIn Policy email address in the preference card:Firefox
	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	When I click edit button from Paperless Preferences
	Then I select Policy Documents as EMAIL and Policy billing as EMAIL
	And  I change the email address as evaluetestingdemo+77@gmail.com
	And  I click Save in iPortal
	Then I validate the email address evaluetestingdemo+77@gmail.com in Preference Card	
	When I click edit button from Paperless Preferences
	And  I change the email address as evaluetestingdemo+66@gmail.com
	And  I click Save in iPortal

Examples:
|Iteration|
|3|	