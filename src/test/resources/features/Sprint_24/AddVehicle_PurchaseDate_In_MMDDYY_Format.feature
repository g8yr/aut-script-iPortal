@ValidPurchaseDate @sprint24
Feature: Amanda should be able to enter purchase date only in MMDDYYYY format
	As a Valid user and met eligibility criteria to ADD new vehicle, 
	I can able to enter Vehicle purchase date only in MMDDYYYY format 
	
Background:
Given I visit the login page of the application
	
@completed
Scenario Outline: Enter Valid Vehicle purchase date Browser:Chrome
   Given I should be navigate to the Vehicles page <Iteration>
   And   I clicks on the 'Add Vehicle' action block which is in collapsed view
   And   I should see the Add vehicle action block expanded with a drawer
   And   I can see the Purchase date and VIN fields displayed in the drawer view
   And   I see the the Next button disabled on the Add Vehicle drawer view
   And   I can see collapse arrow is displayed on the Add Vehicle action block
   When  I am  entering the Purchase date in Date field
   Then  I can enter the date only in MMDDYYYY format
   And   I see the the Next button disabled on the Add Vehicle drawer view
   
    
	
	Examples:
	|Iteration	|
	|2|

@completed
Scenario Outline: Enter Valid Vehicle purchase date Browser:Firefox
   Given I should be navigate to the Vehicles page <Iteration>
   And   I clicks on the 'Add Vehicle' action block which is in collapsed view
   And   I should see the Add vehicle action block expanded with a drawer
   And   I can see the Purchase date and VIN fields displayed in the drawer view
   And   I see the the Next button disabled on the Add Vehicle drawer view
   And   I can see collapse arrow is displayed on the Add Vehicle action block
   When  I am  entering the Purchase date in Date field
   Then  I can enter the date only in MMDDYYYY format
   And   I see the the Next button disabled on the Add Vehicle drawer view
   
    
	
	Examples:
	|Iteration	|
	|3|