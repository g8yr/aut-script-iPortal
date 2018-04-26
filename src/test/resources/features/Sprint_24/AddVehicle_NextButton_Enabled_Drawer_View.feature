@CheckNextButtonEnabled @sprint24
Feature: Amanda sees Next button enabled on the add vehicle drawer view
	As a Valid user and met eligibility criteria and enter Purchase date and VIN and clicking AddVehicle button, 
	I should able to see Next button enabled on the Add Vehicle drawer view   
	
Background:
Given I visit the login page of the application
	
@completed
Scenario Outline: Check the Next Button Enabled Browser:Chrome 
Given I should be navigate to the Vehicles page <Iteration>   
  And   I clicks on the 'Add Vehicle' action block which is in collapsed view
  And   I am  entering the Purchase date in Date field
  And   I can enter the date only in MMDDYYYY format
  When  I can enter only maximum of 17 characters in the VIN field
  Then  I see the the Next button enabled on the Add Vehicle drawer view	 
    
	
	Examples:
	|Iteration	|
	|1|
	
#@completed
#Scenario Outline: Check the Next Button Enabled Browser:InternetExplorer  
#Given I should be navigate to the Vehicles page <Iteration>   
#  And   I clicks on the 'Add Vehicle' action block which is in collapsed view
#  And   I am  entering the Purchase date in Date field
#  And   I can enter the date only in MMDDYYYY format
#  When  I can enter only maximum of 17 characters in the VIN field
#  Then  I see the the Next button enabled on the Add Vehicle drawer view	 
    
	
#	Examples:
#	|Iteration	|
#	|2|

@completed
Scenario Outline: Check the Next Button Enabled Browser:Firefox
Given I should be navigate to the Vehicles page <Iteration>   
  And   I clicks on the 'Add Vehicle' action block which is in collapsed view
  And   I am  entering the Purchase date in Date field
  And   I can enter the date only in MMDDYYYY format
  When  I can enter only maximum of 17 characters in the VIN field
  Then  I see the the Next button enabled on the Add Vehicle drawer view	 
    
	
	Examples:
	|Iteration	|
	|3|


	
