@NoneValueIconValidation @EValue @iPortalRegression
Feature: Login in iPortal and Validate the Evalue NoneValue Icon Status

Background:
       Given I visit the login page of the iPortal application using id

       
Scenario Outline: Evalue NoneValue Icon Status Validation:Chrome
   	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	Then I see Customer Overview page
	Then I check the Evalue NoneValue Icon Status  
	   
Examples:
|Iteration|
|1|

Scenario Outline: Evalue NoneValue Icon Status Validation:InternetExplorer
   	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	Then I see Customer Overview page
	Then I check the Evalue NoneValue Icon Status  
	   
Examples:
|Iteration|
|2|

Scenario Outline: Evalue NoneValue Icon Status Validation:Firefox
   	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	Then I see Customer Overview page
	Then I check the Evalue NoneValue Icon Status  
	   
Examples:
|Iteration|
|3|