@ValidateVINAndPurchaseDatePrepopulated @sprint24
Feature: Amanda clicks on collapsed Add Vehicle action block - Purchase date and VIN pre-populated
	As a Valid user and met eligibility criteria and enter Purchase date and VIN and clicking AddVehicle button, 
	I should able to see the the Add Vehicle card expanded with pre populated value VIN and purchase date   
	
Background:
Given I visit the login page of the application
	
@completed
Scenario Outline: Check the VIN and Purchase Date Prepopulated Browser:Chrome       
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
   When  I clicks on the 'Add Vehicle' action block which is in collapsed view 
   Then  I see the pre populated VIN and purchase Date
   
	Examples:
	|Iteration	|
	|2|
	

@completed
Scenario Outline: Check the VIN and Purchase Date Prepopulated Browser:Firefox       
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
   When  I clicks on the 'Add Vehicle' action block which is in collapsed view 
   Then  I see the pre populated VIN and purchase Date
   
	Examples:
	|Iteration	|
	|3|