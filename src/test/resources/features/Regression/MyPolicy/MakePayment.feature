@MakePayment
Feature: Login in My Policy and submit payment using Credit Card 

Background:
	Given I visit the login page of the application using id

	
Scenario: Make Payment using Credit Card Browser:Chrome
	When   I login using the valid username digitalservices952@gmail.com and the valid password Password1
	And   I click payments from the left nav menu
	Given I note the full amount due for my policy VASS900029501
	When  I enter a payment amount of one cent
	And   I enter my CreditCard details: name shank cardnumber 5454545454545454 expirydate 12/2020 zip 85254 nickname shankvisa 
	And   I click continue
	And   I click pay again to submit the payment
	Then  I see the text:You successfully submitted a payment.

	