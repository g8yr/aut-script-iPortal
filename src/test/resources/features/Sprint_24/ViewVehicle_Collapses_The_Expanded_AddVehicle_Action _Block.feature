@CollapseTheExpandedAddVehicleButton @sprint24
Feature: Amanda collapses the expanded Add Vehicle action block
	As a Valid user and met eligibility criteria and clicking AddVehicle button, 
	I should able to see Add Vehicle button in collapsed view 
	
Background:
Given I visit the login page of the application
	
@completed
Scenario Outline: Collapse The Expanded AddVehicle Button Browser:Chrome     
   Given I should be navigate to the Vehicles page <Iteration>
   And   I clicks on the 'Add Vehicle' action block which is in collapsed view
   And   I should see the Add vehicle action block expanded with a drawer
   And   I can see the Purchase date and VIN fields displayed in the drawer view
   And   I see the the Next button disabled on the Add Vehicle drawer view
   And   I can see collapse arrow is displayed on the Add Vehicle action block
   When  I am  entering the Purchase date in Date field
   Then  I can enter the date only in MMDDYYYY format
   And   I see the the Next button disabled on the Add Vehicle drawer view
   And   I can enter only maximum of 17 characters in the VIN field
   When  I clicks on the 'Add Vehicle' action block which is in expanded view  
   And   I sees 'Add Vehicle' action block is collapsed
   
	Examples:
	|Iteration	|
	|2|
	

@completed
Scenario Outline: Collapse The Expanded AddVehicle Button Browser:Firefox     
   Given I should be navigate to the Vehicles page <Iteration>
   And   I clicks on the 'Add Vehicle' action block which is in collapsed view
   And   I should see the Add vehicle action block expanded with a drawer
   And   I can see the Purchase date and VIN fields displayed in the drawer view
   And   I see the the Next button disabled on the Add Vehicle drawer view
   And   I can see collapse arrow is displayed on the Add Vehicle action block
   When  I am  entering the Purchase date in Date field
   Then  I can enter the date only in MMDDYYYY format
   And   I see the the Next button disabled on the Add Vehicle drawer view
   And   I can enter only maximum of 17 characters in the VIN field
   When  I clicks on the 'Add Vehicle' action block which is in expanded view  
   And   I sees 'Add Vehicle' action block is collapsed
   
	Examples:
	|Iteration	|
	|3|