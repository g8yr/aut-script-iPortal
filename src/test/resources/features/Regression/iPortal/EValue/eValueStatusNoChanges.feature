@eValueStatusNoChanges @EValue @iPortalRegression
Feature: Login in iPortal and Validate the Evalue Status on saving preference without any changes

Background:
       Given I visit the login page of the iPortal application using id

       
Scenario Outline: Validate the Evalue Status on saving preference without any changes:Chrome
   	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	Then I see Customer Overview page
	Then I click edit button from Paperless Preferences 
	And I click Save in iPortal
	Then I check the Evalue Active Icon Status 
	
	   
Examples:
|Iteration|
|1|

Scenario Outline: Validate the Evalue Status on saving preference without any changes:InternetExplorer
   	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	Then I see Customer Overview page
	Then I click edit button from Paperless Preferences 
	And I click Save in iPortal
	Then I check the Evalue Active Icon Status 
	
	   
Examples:
|Iteration|
|2|

Scenario Outline: Validate the Evalue Status on saving preference without any changes:Firefox
   	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	Then I see Customer Overview page
	Then I click edit button from Paperless Preferences 
	And I click Save in iPortal
	Then I check the Evalue Active Icon Status 
	
	   
Examples:
|Iteration|
|3|