@InvalidVIN  @sprint24
Feature: Amanda sees an error message for invalid VIN number
	As a Valid user and met eligibility criteria and enter Purchase date , 
	I should see a proper inline error message if I enter invalid VIN 
	
Background:
Given I visit the login page of the application

#@completed
#Scenario Outline: Enter Invalid VIN Browser:Chrome 
#  Given I should be navigate to the Vehicles page <Iteration>
#  And   I clicks on the 'Add Vehicle' action block which is in collapsed view
#  And   I am  entering the Purchase date in Date field
#  When  I can enter only maximum of 17 characters in the VIN field
#  Then  I click of next in the add vehicle drawer view, I see an inline error message to enter a valid VIN number if it is not valid
  		
#	Examples:
#	|Iteration	|
#	|1|



@completed
Scenario Outline: Enter Invalid VIN Browser:InternetExplorer
  Given I should be navigate to the Vehicles page <Iteration>
  And   I clicks on the 'Add Vehicle' action block which is in collapsed view
  And   I am  entering the Purchase date in Date field
  When  I can enter only maximum of 17 characters in the VIN field
  Then  I click of next in the add vehicle drawer view, I see an inline error message to enter a valid VIN number if it is not valid
  		
	Examples:
	|Iteration	|
	|2|
	

	

#@completed
#Scenario Outline: Enter Invalid VIN Browser:Firefox
#  Given I should be navigate to the Vehicles page <Iteration>
#  And   I clicks on the 'Add Vehicle' action block which is in collapsed view
#  And   I am  entering the Purchase date in Date field
#  When  I can enter only maximum of 17 characters in the VIN field
#  Then  I click of next in the add vehicle drawer view, I see an inline error message to enter a valid VIN number if it is not valid
#  		
#	Examples:
#	|Iteration	|
#	|3|


#@completed
#Scenario Outline: Enter Invalid VIN Browser:Safari
#  Given I should be navigate to the Vehicles page <Iteration>
#  And   I clicks on the 'Add Vehicle' action block which is in collapsed view
#  And   I am  entering the Purchase date in Date field
#  When  I can enter only maximum of 17 characters in the VIN field
#  Then  I click of next in the add vehicle drawer view, I see an inline error message to enter a valid VIN number if it is not valid
  		
#	Examples:
#	|Iteration	|
#	|4|