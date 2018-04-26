@SetAutoPayNewSavingsAccount
Feature: Login in My Policy and set Autopay with New Savings Account

Background:
	Given I visit the login page of the application using id

	
Scenario Outline: Set Auto Pay using New Savings Account Browser:Chrome
	When I login using the valid username digitalservices952@gmail.com and the valid password Password1
	Then I can see DashBoard page
	And   I click payments from the left nav menu
	Then I view the payment details for my policy <Iteration>
	When  I click AutoPay Settings link
	Then  I view the Autopay popup window
	And I click Autopay Off link
	Then I view the payment option dropdown
	And  I enter my Saving Account details for AutoPay
	When I click Save button in Autopay popup window
	Then I see the text:You have successfully set up AutoPay.
	
	
	Examples:
	|Iteration	| 
	|1|
	
	
#Scenario Outline: Set Auto Pay using New Savings Account Browser:InternetExplorer
	#When I login using the valid username digitalservices952@gmail.com and the valid password Password1
	#Then I can see DashBoard page
	#And   I click payments from the left nav menu
	#Then I view the payment details for my policy <Iteration>
	#When  I click AutoPay Settings link
	#Then  I view the Autopay popup window
	#And I click Autopay Off link
	#Then I view the payment option dropdown
	#And  I enter my Saving Account details for AutoPay
	#When I click Save button in Autopay popup window
	#Then I see the text:You have successfully set up AutoPay.
	
	
	#Examples:
	#|Iteration	| 
	#|2|
	
#Scenario Outline: Set Auto Pay using New Savings Account Browser:Firefox
	#When I login using the valid username digitalservices952@gmail.com and the valid password Password1
	#Then I can see DashBoard page
	#And   I click payments from the left nav menu
	#Then I view the payment details for my policy <Iteration>
	#When  I click AutoPay Settings link
	#Then  I view the Autopay popup window
	#And I click Autopay Off link
	#Then I view the payment option dropdown
	#And  I enter my Saving Account details for AutoPay
	#When I click Save button in Autopay popup window
	#Then I see the text:You have successfully set up AutoPay.
	
	
	#Examples:
	#|Iteration	| 
	#|3|

	