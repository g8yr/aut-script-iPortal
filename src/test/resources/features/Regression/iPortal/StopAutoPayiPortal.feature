@StopAutoPayiPortal
Feature: Login in iPortal and Stop Autopay 

Background:
	Given I visit the login page of the iPortal application using id

	
Scenario: Stop Auto Pay Browser:Chrome
	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage VASS900037187
	When I Click Stop Iportal Autopay link
	Then I see the iPortal text:You have successfully set up AutoPay.
				 	 
	

	