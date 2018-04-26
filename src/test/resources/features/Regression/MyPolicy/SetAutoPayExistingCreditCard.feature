@SetAutoPayExistingCreditCard
Feature: Login in My Policy and set Autopay with Existing Credit Card Account

Background:
	Given I visit the login page of the application using id

Scenario Outline: Set Auto Pay using Existing Credit Card Browser:Chrome
	When   I login using the valid username digitalservices952@gmail.com and the valid password Password1
	And   I click payments from the left nav menu
	Given I view the payment details for my policy <Iteration>
	When  I click AutoPay Settings link
	And   I selected existing Credit Card details and click Save button
	Then I see the text:You have successfully set up AutoPay.
	
	Examples:
	|Iteration	| 
	|1|

	
	
Scenario Outline: Set Auto Pay using Existing Credit Card Browser:InternetExplorer
	When   I login using the valid username digitalservices952@gmail.com and the valid password Password1
	And   I click payments from the left nav menu
	Given I view the payment details for my policy <Iteration>
	When  I click AutoPay Settings link
	And   I selected existing Credit Card details and click Save button
	Then I see the text:You have successfully set up AutoPay.
	
	Examples:
	|Iteration	| 
	|2|

	
Scenario Outline: Set Auto Pay using Existing Credit Card Browser:Firefox
	When   I login using the valid username digitalservices952@gmail.com and the valid password Password1
	And   I click payments from the left nav menu
	Given I view the payment details for my policy <Iteration>
	When  I click AutoPay Settings link
	And   I selected existing Credit Card details and click Save button
	Then I see the text:You have successfully set up AutoPay.
	
	Examples:
	|Iteration	| 
	|3|

	