@ValidVINFormat @sprint24
Feature: Amanda can enter only maximum of 17 characters for VIN field
	As a Valid user and met eligibility criteria and enter Purchase date and VIN, 
	I should enter only 17 characters in VIN field
	
Background:
Given I visit the login page of the application
	
@completed
Scenario Outline: Enter Vehicle VIN Number Browser:Chrome 
  Given I should be navigate to the Vehicles page <Iteration>   
  And   I clicks on the 'Add Vehicle' action block which is in collapsed view
  And   I am  entering the Purchase date in Date field
  When  I can enter only maximum of 17 characters in the VIN field
	
	Examples:
	|Iteration	|
	|1|
	
#@completed
#Scenario Outline: Enter Vehicle VIN Number Browser:InternetExplorer   
#  Given I should be navigate to the Vehicles page <Iteration>   
#  And   I clicks on the 'Add Vehicle' action block which is in collapsed view
#  And   I am  entering the Purchase date in Date field
#  When  I can enter only maximum of 17 characters in the VIN field
	
#	Examples:
#	|Iteration	|
#	|2|
	
@completed
Scenario Outline: Enter Vehicle VIN Number Browser:Firefox   
  Given I should be navigate to the Vehicles page <Iteration>   
  And   I clicks on the 'Add Vehicle' action block which is in collapsed view
  And   I am  entering the Purchase date in Date field
  When  I can enter only maximum of 17 characters in the VIN field
	
	Examples:
	|Iteration	|
	|3|