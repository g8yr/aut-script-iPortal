@SetAutoPayiPortal
Feature: Login in iPortal and set Autopay 

Background:
	Given I visit the login page of the iPortal application using id

	
Scenario: Set Auto Pay Browser:Chrome
	When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage VASS900037187
	When I click iPortal AutoPay Settings link
	And  I enter my CreditCard details for iPortal AutoPay : name LeeCanton cardnumber 5454545454545454 expirydate 12/2020 zip 85254 email evalueoffshore+9038@gmail.com
	Then I see the iPortal text:You have successfully set up AutoPay.
				 	 
	

	