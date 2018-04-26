@MakePolicyChangesButton
Feature: I want to validate the UX requirements for the make policy changes button
	
Background:
	Given I visit the login page of the application
	
@completed
<<<<<<< HEAD
Scenario Outline: Make policy changes button Visible 	
=======
Scenario: 'Make policy changes' button Visible 	
>>>>>>> Committed the changes for MakePolicyChanges Spec
	And   I can see DashBoard page
	And   I click policies from the left navigation menu
	And   I click the given policy <Iteration>
	And   I see my policy in policy details page
<<<<<<< HEAD
	#Given  I see the Make Policy Change button displayed on the policy summary card
	Then  I should be able to validate the UX features for Make Policy Changes button 

	
=======
	And   The PAS validation for Make Policy Changes button is passed <Iteration> 	
	Given  I see the Make Policy Change button displayed on the policy summary card
	Then  I should be able to validate the UX features for Make Policy Changes button 

>>>>>>> Committed the changes for MakePolicyChanges Spec
	Examples:
	|Iteration|
	|1| 