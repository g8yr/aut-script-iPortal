@ClickADDVehicleButton @sprint24
Feature: Amanda clicks on Add Vehicle button on the Vehicle page
	As a Valid user and met eligibility criteria to ADD new vehicle, 
	I can able to Click the ADDVEHICLE button 

Background:
	Given I visit the login page of the application
		

	
@completed1
Scenario Outline: Click Add Vehicle Button Browser:InternetExplorer
	Given I should be navigate to the Vehicles page <Iteration>
    When  I clicks on the 'Add Vehicle' action block which is in collapsed view
    Then  I should see the Add vehicle action block expanded with a drawer
    And   I can see the Purchase date and VIN fields displayed in the drawer view
    And   I see the the Next button disabled on the Add Vehicle drawer view
    And   I can see collapse arrow is displayed on the Add Vehicle action block

	Examples:
	|Iteration|
	|2|
	