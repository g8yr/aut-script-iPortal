@sprint25
Feature: Verify the Add Vehicle Flow, Interview Question, Question Summary View Page and Continue Button  
	
Background:
Given I visit the login page of the application
	
@completed
Scenario Outline: Check the AddVehicle Flow Browser:Chrome   
    Given I should be navigate to the Vehicles page <Iteration>
    And   I click ADDVehicle button and enter PurchaseDate and VIN <Iteration>
    When  I click NEXT button and VIN validation from PAS is passed
    Then  I see the vehicle Make Model year in AddVehicle action block title
    And   I see the AddVehicle action block description
    And   I see the Add Vehicle block collapse button changed to cancel
    And   I see the AddVehicle back button
    And   I do not see the NEXT button visible
    And   I see the Vehicle Ownership Type questions with options
    And   I click on the Ownership Type
    And   I see the Vehicle Usage Type question
    And   I click option for Vehicle Usage Type question 
    And   I see the Vehicle Located question
    And   I click option for Vehicle Located question 
    And   I see the Vehicle Salvaged question
    And   I click option for Vehicle Salvaged question 
    And   I see the All Registered Owners question
    And   I click option for all Registered Owners question 
    And   I see the AntiTheft question
    And   I click option for AntiTheft question 
    And   I see the Add/Remove Additional vehicles question
    And   I click option for Add/Remove Additional vehicles question 
    And   I see the Vehicle Summary screen
    And   I click AddContinue or Add button on the summary view
    And   I see vehicle being added is on top of the page with pending status
    And   I see Continue button in the page 
    
    Examples:
	|Iteration	|
	|1|

@completed
Scenario Outline: Check the AddVehicle Flow Browser:InternetExplorer   
    Given I should be navigate to the Vehicles page <Iteration>
    And   I click ADDVehicle button and enter PurchaseDate and VIN <Iteration>
    When  I click NEXT button and VIN validation from PAS is passed
    Then  I see the vehicle Make Model year in AddVehicle action block title
    And   I see the AddVehicle action block description
    And   I see the Add Vehicle block collapse button changed to cancel
    And   I see the AddVehicle back button
    And   I do not see the NEXT button visible
    And   I see the Vehicle Ownership Type questions with options
    And   I click on the Ownership Type
    And   I see the Vehicle Usage Type question
    And   I click option for Vehicle Usage Type question 
    And   I see the Vehicle Located question
    And   I click option for Vehicle Located question 
    And   I see the Vehicle Salvaged question
    And   I click option for Vehicle Salvaged question 
    And   I see the All Registered Owners question
    And   I click option for all Registered Owners question 
    And   I see the AntiTheft question
    And   I click option for AntiTheft question 
    And   I see the Add/Remove Additional vehicles question
    And   I click option for Add/Remove Additional vehicles question 
    And   I see the Vehicle Summary screen
    And   I click AddContinue or Add button on the summary view
    And   I see vehicle being added is on top of the page with pending status
    And   I see Continue button in the page 
    
    Examples:
	|Iteration	|
	|2|

@completed
Scenario Outline: Check the AddVehicle Flow Browser:Firefox   
    Given I should be navigate to the Vehicles page <Iteration>
    And   I click ADDVehicle button and enter PurchaseDate and VIN <Iteration>
    When  I click NEXT button and VIN validation from PAS is passed
    Then  I see the vehicle Make Model year in AddVehicle action block title
    And   I see the AddVehicle action block description
    And   I see the Add Vehicle block collapse button changed to cancel
    And   I see the AddVehicle back button
    And   I do not see the NEXT button visible
    And   I see the Vehicle Ownership Type questions with options
    And   I click on the Ownership Type
    And   I see the Vehicle Usage Type question
    And   I click option for Vehicle Usage Type question 
    And   I see the Vehicle Located question
    And   I click option for Vehicle Located question 
    And   I see the Vehicle Salvaged question
    And   I click option for Vehicle Salvaged question 
    And   I see the All Registered Owners question
    And   I click option for all Registered Owners question 
    And   I see the AntiTheft question
    And   I click option for AntiTheft question 
    And   I see the Add/Remove Additional vehicles question
    And   I click option for Add/Remove Additional vehicles question 
    And   I see the Vehicle Summary screen
    And   I click AddContinue or Add button on the summary view
    And   I see vehicle being added is on top of the page with pending status
    And   I see Continue button in the page 
    
    Examples:
	|Iteration	|
	|3|
	
	@completed
Scenario Outline: Check the AddVehicle Flow Browser:Safari   
    Given I should be navigate to the Vehicles page <Iteration>
    And   I click ADDVehicle button and enter PurchaseDate and VIN <Iteration>
    When  I click NEXT button and VIN validation from PAS is passed
    Then  I see the vehicle Make Model year in AddVehicle action block title
    And   I see the AddVehicle action block description
    And   I see the Add Vehicle block collapse button changed to cancel
    And   I see the AddVehicle back button
    And   I do not see the NEXT button visible
    And   I see the Vehicle Ownership Type questions with options
    And   I click on the Ownership Type
    And   I see the Vehicle Usage Type question
    And   I click option for Vehicle Usage Type question 
    And   I see the Vehicle Located question
    And   I click option for Vehicle Located question 
    And   I see the Vehicle Salvaged question
    And   I click option for Vehicle Salvaged question 
    And   I see the All Registered Owners question
    And   I click option for all Registered Owners question 
    And   I see the AntiTheft question
    And   I click option for AntiTheft question 
    And   I see the Add/Remove Additional vehicles question
    And   I click option for Add/Remove Additional vehicles question 
    And   I see the Vehicle Summary screen
    And   I click AddContinue or Add button on the summary view
    And   I see vehicle being added is on top of the page with pending status
    And   I see Continue button in the page 
    
    Examples:
	|Iteration	|
	|4|