@ViewAllVehiclesVisible @sprint23
Feature: Amanda should be able to see all her existing vehicles on the policy
	As a user with valid credentials and met eligibility criteria, 
	I want to be able to see all the  vehicles  on the Policy.
	
Background:
Given I visit the login page of the application
	
@completed
Scenario Outline: Visible all the vehicles in policy Browser:Chrome  
    #Given  I login using the valid credentials <Iteration>
    And     I can see DashBoard page				
	And     I click policies from the left navigation menu
	And     I click the given policy <Iteration>
	And     I see my policy in policy details page
	And     The PAS validation for Make Policy Changes button is passed <Iteration>
	Then    I see the 'Make Policy Change' button displayed on the policy summary card
	And     I click on the 'Make Policy Changes' button
	And     I should be navigated to the Endorsement Menu Page
    Given   I can see Add/Remove vehicle option on the Endorsement Menu page
	#And    I can also see the policy number that is being edited at the top of the page
	And     I click on the 'Add/Remove Vehicles' button
	When    I should be navigated to the Vehicles Page
	Then    I should be able to see all the vehicles page header, Action Block header and description as per Spec with the policy number
	And     I can see the Year, Make, Model of the vehicle displayed in the action block title for Policy
	
	Examples:
	|Iteration	|
	|1|

@completed
Scenario Outline: Visible all the vehicles in policy Browser:Firefox  
    #Given  I login using the valid credentials <Iteration>
    And     I can see DashBoard page				
	And     I click policies from the left navigation menu
	And     I click the given policy <Iteration>
	And     I see my policy in policy details page
	And     The PAS validation for Make Policy Changes button is passed <Iteration>
	Then    I see the 'Make Policy Change' button displayed on the policy summary card
	And     I click on the 'Make Policy Changes' button
	And     I should be navigated to the Endorsement Menu Page
    Given   I can see Add/Remove vehicle option on the Endorsement Menu page
	#And    I can also see the policy number that is being edited at the top of the page
	And     I click on the 'Add/Remove Vehicles' button
	When    I should be navigated to the Vehicles Page
	Then    I should be able to see all the vehicles page header, Action Block header and description as per Spec with the policy number
	And     I can see the Year, Make, Model of the vehicle displayed in the action block title for Policy
	
	Examples:
	|Iteration	|
	|3|