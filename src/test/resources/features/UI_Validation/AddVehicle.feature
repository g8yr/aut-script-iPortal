@MakePolicyChanges
Feature: Amanda clicks on Add Vehicle button on the Vehicle page
	As a Valid name and met eligibility criteria to ADD new vehicle, 
	I can able to Click the ADDVEHICLE button and add vehicle

Background:
	Given I visit the login page of the application
	

@completed1
Scenario Outline: Add vehicle link UI validation for make policy changes
	Given I should be navigate to the Vehicles page <Iteration>
    Then  I should validate the look and feel of add vehicle button

	Examples:
	|Iteration|
	|1| 


	





	
	
	
	
	