@eValueDiscountPolicy
Feature: Login in to My Policy and Verify eValue Discount

Background:
	Given I visit the login page of the application using id

	
#Scenario Outline: Verify eValue Discount in Discount Section Browser:Chrome
	#When   I login using the valid username digitalservices952@gmail.com and the valid password Password1
	#Then I can see DashBoard page
	#And   I click policies from the left navigation menu
	#Then I view the policy details for my policy <Iteration>
	#Then I view eValue icon for eValue Policy

	#Examples:
	#|Iteration	| 
	#|1|
	
#Scenario Outline: Verify eValue Discount in Discount Section Browser:InternetExplorer
	#When   I login using the valid username digitalservices952@gmail.com and the valid password Password1
	#Then I can see DashBoard page
	#And   I click policies from the left navigation menu
	#Then I view the policy details for my policy <Iteration>
	#Then I view eValue icon for eValue Policy

	#Examples:
	#|Iteration	| 
	#s|2|
	
#Scenario Outline: Verify eValue Discount in Discount Section Browser:Firefox
	#When   I login using the valid username digitalservices952@gmail.com and the valid password Password1
	#Then I can see DashBoard page
	#And   I click policies from the left navigation menu
	#Then I view the policy details for my policy <Iteration>
	#Then I view eValue icon for eValue Policy

	#Examples:
	#|Iteration	| 
	#|3|
	
Scenario Outline: Verify eValue Discount in Discount Section Browser:Safari
	When   I login using the valid username digitalservices952@gmail.com and the valid password Password1
	Then I can see DashBoard page
	And   I click policies from the left navigation menu
	Then I view the policy details for my policy <Iteration>
	Then I view eValue icon for eValue Policy

	Examples:
	|Iteration	| 
	|4|
	
