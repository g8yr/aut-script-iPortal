@ClickMakePolicyChangeButton @sprint23
Feature: Amanda clicks on Make Policy Changes button to update her auto policy
	As a user with valid credentials and met eligibility criteria, 
	I want to be able to see and click the 'Make Policy Change' button.
	
Background:
	Given I visit the login page of the application
	
@completed
Scenario Outline: Click Make Policy Change Button Browser:InternetExplorer 
	Given I can see DashBoard page						
	And   I click policies from the left navigation menu
	And   I click the given policy <Iteration>
	And   I see my policy in policy details page
	And   The PAS validation for Make Policy Changes button is passed <Iteration> 
	And   I see the 'Make Policy Change' button displayed on the policy summary card
	When  I click on the 'Make Policy Changes' button
	Then  I should be navigated to the Endorsement Menu Page

	Examples:
	|Iteration	| 
	|2|