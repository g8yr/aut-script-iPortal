@MakePolicyChangeButtonVisible @sprint23
Feature: Amanda sees Make Policy Changes button to update her auto policy
	As a user with valid credentials and met eligibility criteria, 
	I want to be able to see the Make Policy Change button.
	
Background:
	Given I visit the login page of the application
	
@completed
Scenario Outline: Make Policy Changes button visible Browser:Chrome 
    #Given  I login using the valid credentials <Iteration>
    And     I can see DashBoard page				
	And     I click policies from the left navigation menu
	And     I click the given policy <Iteration>
	And     I see my policy in policy details page 
	And     The PAS validation for Make Policy Changes button is passed <Iteration>
	Then    I see the 'Make Policy Change' button displayed on the policy summary card
	
	Examples:
	|Iteration	| 
	|1|

#@completed
#Scenario Outline: Make Policy Changes button visible Browser:InternetExplorer 
#    #Given  I login using the valid credentials <Iteration>
#    And     I can see DashBoard page				
#	And     I click policies from the left navigation menu
#	And     I click the given policy <Iteration>
#	And     I see my policy in policy details page 
#	And     The PAS validation for Make Policy Changes button is passed <Iteration>
#	Then    I see the 'Make Policy Change' button displayed on the policy summary card
	
#	Examples:
#	|Iteration	| 
#	|2|
	
@completed
Scenario Outline: Make Policy Changes button visible Browser:Firefox 
    #Given  I login using the valid credentials <Iteration>
    And     I can see DashBoard page				
	And     I click policies from the left navigation menu
	And     I click the given policy <Iteration>
	And     I see my policy in policy details page 
	And     The PAS validation for Make Policy Changes button is passed <Iteration>
	Then    I see the 'Make Policy Change' button displayed on the policy summary card
	
	Examples:
	|Iteration	| 
	|3|
	
