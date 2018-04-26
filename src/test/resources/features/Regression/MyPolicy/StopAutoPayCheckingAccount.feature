@StopAutoPayCheckingAccount
Feature: Login in My Policy and Stop AutoPay for Policy with Checking Account

Background:
	Given I visit the login page of the application using id

	
#Scenario Outline: Stop Auto Pay Checking Account Browser:Chrome
	#When   I login using the valid username digitalservices952@gmail.com and the valid password Password1
	#Then I can see DashBoard page
	#And   I click payments from the left nav menu
	#Then I view the payment details for my policy <Iteration>
	#When  I click AutoPay Settings link
	#Then  I view the Autopay popup window
	#And I click Autopay On link
	#Then I view Stop Autopay link
	#And   I Click Stop Autopay link
	#Then I see the text:You have successfully unenrolled from AutoPay.
	
	#Examples:
	#|Iteration	| 
	#|1|
	
#Scenario Outline: Stop Auto Pay Checking Account Browser:InternetExplorer
	#When   I login using the valid username digitalservices952@gmail.com and the valid password Password1
	#Then I can see DashBoard page
	#And   I click payments from the left nav menu
	#Then I view the payment details for my policy <Iteration>
	#When  I click AutoPay Settings link
	#Then  I view the Autopay popup window
	#And I click Autopay On link
	#Then I view Stop Autopay link
	#And   I Click Stop Autopay link
	#Then I see the text:You have successfully unenrolled from AutoPay.
	
	#Examples:
	#|Iteration	| 
	#|2|
	
Scenario Outline: Stop Auto Pay Checking Account Browser:Firefox
	When   I login using the valid username digitalservices952@gmail.com and the valid password Password1
	Then I can see DashBoard page
	And   I click payments from the left nav menu
	Then I view the payment details for my policy <Iteration>
	When  I click AutoPay Settings link
	Then  I view the Autopay popup window
	And I click Autopay On link
	Then I view Stop Autopay link
	And   I Click Stop Autopay link
	Then I see the text:You have successfully unenrolled from AutoPay.

	Examples:
	|Iteration	| 
	|3|