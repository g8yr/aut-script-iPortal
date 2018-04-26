@PASVINValidationFail @sprint24
Feature: Amanda sees an inline error message for VIN when PAS VIN validation fails
	As a Valid user and met eligibility criteria and PAS VIN Validation fails, 
	I should able to see   inline error message
	
Background:
Given I visit the login page of the application


	
@completed
Scenario Outline: PAS VIN Validation Fail Browser:InternetExplorer 
Given  I should be navigate to the Vehicles page <Iteration>   
  And  I clicks on the 'Add Vehicle' action block which is in collapsed view
  And  I am  entering the Purchase date in Date field
  And  I can enter the date only in MMDDYYYY format
  And  I can enter only maximum of 17 characters in the VIN field
  And  I enter VIN number in correct format
  When I click the Next button on the Add Vehicle drawer view
  Then I see an inline error message if PAS VIN Validation Fails  
 	
	Examples:
	|Iteration	|
	|2|
	
#@completed
#Scenario Outline: PAS VIN Validation Fail Browser:Firefox
#Given  I should be navigate to the Vehicles page <Iteration>   
#  And  I clicks on the 'Add Vehicle' action block which is in collapsed view
#  And  I am  entering the Purchase date in Date field
#  And  I can enter the date only in MMDDYYYY format
#  And  I can enter only maximum of 17 characters in the VIN field
#  And  I enter VIN number in correct format
#  When I click the Next button on the Add Vehicle drawer view
#  Then I see an inline error message if PAS VIN Validation Fails  
 	
#	Examples:
#	|Iteration	|
#	|3|

#@completed
#Scenario Outline: PAS VIN Validation Fail Browser:Chrome
#Given  I should be navigate to the Vehicles page <Iteration>   
#  And  I clicks on the 'Add Vehicle' action block which is in collapsed view
#  And  I am  entering the Purchase date in Date field
#  And  I can enter the date only in MMDDYYYY format
#  And  I can enter only maximum of 17 characters in the VIN field
#  And  I enter VIN number in correct format
#  When I click the Next button on the Add Vehicle drawer view
#  Then I see an inline error message if PAS VIN Validation Fails  
 	
#	Examples:
#	|Iteration	|
#	|1|
	
#@completed
#Scenario Outline: PAS VIN Validation Fail Browser:Safari
#Given  I should be navigate to the Vehicles page <Iteration>   
#  And  I clicks on the 'Add Vehicle' action block which is in collapsed view
#  And  I am  entering the Purchase date in Date field
#  And  I can enter the date only in MMDDYYYY format
#  And  I can enter only maximum of 17 characters in the VIN field
#  And  I enter VIN number in correct format
#  When I click the Next button on the Add Vehicle drawer view
#  Then I see an inline error message if PAS VIN Validation Fails  
 	
#	Examples:
#	|Iteration	|
#	|4|

