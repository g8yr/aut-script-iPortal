@PolicyDetailsProposedStatus @PolicyDetails @iPortalRegression
Feature: Login in iPortal and validate customer overview page for proposed policy 

Background:
	Given I visit the login page of the iPortal application using id

Scenario Outline: Validate customer overview page for proposed policy:Chrome
	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	Then I see Customer Overview page
	
	Examples:
	|Iteration	| 
	|1|
	
Scenario Outline: Validate customer overview page for proposed policy:InternetExplorer
	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	Then I see Customer Overview page
	
	Examples:
	|Iteration	| 
	|2|
	
Scenario Outline: Validate customer overview page for proposed policy:Firefox
	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	Then I see Customer Overview page
	
	Examples:
	|Iteration	| 
	|3|
	  
	
	

