@VisibleAddRemoveButton @sprint23
Feature: Amanda can see Add/Remove vehicles button on the Endorsement Menu page
	As a user with valid credentials and met eligibility criteria, 
	I want to be able to see  Add/Remove vehicles button on the Endorsement Menu page.
	
Background:
	Given I visit the login page of the application
	
@completed
Scenario: 'Add/Remove Vehicles' button Visible 	
	And   I can see DashBoard page
	And   I click policies from the left navigation menu
	And   I click the given policy <Iteration>
	And   I see my policy in policy details page
	And   The PAS validation for Make Policy Changes button is passed <Iteration> 	
	Given  I see the Make Policy Change button displayed on the policy summary card
	And   I click on the 'Make Policy Changes' button
	When  I should be navigated to the Endorsement Menu Page
	Then  I can see Add/Remove vehicle option on the Endorsement Menu page 
	#And   I can also see the policy number that is being edited at the top of the page
	And   I should see the 'Endorsement Menu Page' as per specifications
	
	Examples:
	|Iteration	| 
	|2|