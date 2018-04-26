@FuturePurchaseDate @sprint24
Feature: Amanda sees an error message when the purchase date is a future date
	As a Valid user and met eligibility criteria, 
	I should see a proper inline error message if I enter future date MMDDYYYY 
	
Background:
Given I visit the login page of the application

#@completed
#Scenario Outline: Enter Future Vehicle purchase date Browser:Safari
#  Given I should be navigate to the Vehicles page <Iteration>   
#  And  I clicks on the 'Add Vehicle' action block which is in collapsed view
#  When  I am  entering the Purchase date in Date field
#  Then  I should see the inline error message for entering the future date
#  And  I see the the Next button disabled on the Add Vehicle drawer view
    
	
#	Examples:
#	|Iteration	|
#	|4|

#@completed
#Scenario Outline: Enter Future Vehicle purchase date Browser:Chrome
#  Given I should be navigate to the Vehicles page <Iteration>   
#  And  I clicks on the 'Add Vehicle' action block which is in collapsed view
#  When  I am  entering the Purchase date in Date field
#  Then  I should see the inline error message for entering the future date
#  And  I see the the Next button disabled on the Add Vehicle drawer view
    
	
#	Examples:
#	|Iteration	|
#	|1|

	
@completed
Scenario Outline: Enter Future Vehicle purchase date Browser:InternetExplorer
  Given I should be navigate to the Vehicles page <Iteration>   
  And  I clicks on the 'Add Vehicle' action block which is in collapsed view
  When  I am  entering the Purchase date in Date field
  Then  I should see the inline error message for entering the future date
  And  I see the the Next button disabled on the Add Vehicle drawer view
    
	
	Examples:
	|Iteration	|
	|2|

#@completed
#Scenario Outline: Enter Future Vehicle purchase date Browser:Firefox
#  Given I should be navigate to the Vehicles page <Iteration>   
#  And  I clicks on the 'Add Vehicle' action block which is in collapsed view
#  When  I am  entering the Purchase date in Date field
#  Then  I should see the inline error message for entering the future date
#  And  I see the the Next button disabled on the Add Vehicle drawer view
#    
	
#	Examples:
#	|Iteration	|
#	|3|

