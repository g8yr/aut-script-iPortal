@MakePolicyChangeButtonNotVisible @sprint23
Feature: Do not display Make Policy Changes button in the MyPolicy details page
	As a user with valid credentials and not met eligibility criteria, 
	I should not able to see the 'Make Policy Change' button.
	
Background:
	Given I visit the login page of the application
#	And   I login using the valid UserName digital3test3@yahoo.com and the valid password Password1
	
@completed
Scenario Outline: Make Policy Changes button not visible Browser:Chrome
	Given I can see DashBoard page						
	And   I click policies from the left navigation menu
    And   I click the policy <Iteration>
	When   I see my policy in policy details page
	Then  The PAS validation for Make Policy Changes button is failed <Iteration>
	And  I see the 'Make Policy Change' button not displayed on the policy summary card
	
	Examples:
	|Iteration	| 
	|1|

@completed	
Scenario Outline: Make Policy Changes button not visible Browser:Firefox
	Given I can see DashBoard page						
	And   I click policies from the left navigation menu
    And   I click the policy <Iteration>
	When   I see my policy in policy details page
	Then  The PAS validation for Make Policy Changes button is failed <Iteration>
	And  I see the 'Make Policy Change' button not displayed on the policy summary card
	
	Examples:
	|Iteration	| 
	|3|