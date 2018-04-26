@sprint272
Feature: Add Driver and Coverage for the pending endorsement vehicles 
	
Background:
Given I visit the login page of the application
	
@completed
Scenario Outline: Add Driver and Coverage Positive Flow Browser:Safari   
    Given I should be navigate to the Vehicles page <Iteration>
    And   I click ADDVehicle button and enter PurchaseDate and VIN <Iteration>
    And   I entered all the Vehicle Interview Questions
    When  I choose No option for Add/Remove Additional vehicles question 
    And   I see the Vehicle Summary screen
    And   I click AddContinue or Add button on the summary view
    Then  I should be navigated to the Driver and Vehicle assignment page   
    And   I should be able to view pending flag for the newly added vehicle
    And   I should be able to view the newly added vehicle assigned to the existing driver
    And   I should see the Continue button enabled in the Driver and Vehicle assignment page
    And   I click on continue button from the Driver and Vehicle assignment page
    And   I should be navigated to the coverage page
    And   I should be able to see vehicle level coverage displayed as per PAS system along with the help text
    And   I see the  newly added vehicle with pending tag, vehicle year, make and model along with VIN#
    And   I see the continue button enabled on the Coverage page
    And   I see Comprehension/Collision default amount with label is displayed as per PAS service
    And   I see Rental car coverage default amount with label is displayed 
    And   I see Towing and Labor coverage with default value as 'Get Coverage'
    And   I should not be able to see the New Car Added Protection coverage if available in PAS 
    And   I should not be able to see the Customized equipment coverage if available in PAS
    And   I should not be able to see the Excess Electronic Equipment coverage if available in PAS
 
    
    Examples:
	|Iteration	|
	|4|

