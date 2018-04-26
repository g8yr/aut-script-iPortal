@MyPolicyHealthCheck
Feature: MyPolicy HealthCheck WebService Validations

Background:
	Given I generate the OauthToken
	

@completed
Scenario: Verify Retrieve Policy Details Auto Webservice Response
	Then  I Validate RetrievePolicyDetails Auto PAS SOAP Service



@completed
Scenario: Verify Retrieve Payment Transaction Webservice Response
	Then I Validate Retrieve Payment Transaction SOAP Service

@completed
Scenario: Verify Retrieve Policy Summary Webservice Response
	Then I Validate Retrieve Policy Summary SOAP Service
	
@completed
Scenario: Verify Find Policy Webservice Response
	Then I Validate Find Policy SOAP Service
	
@completed
Scenario: Verify Retrieve Customer Details Webservice Response
	Then I Validate Retrieve Customer Details SOAP Service

@completed
Scenario: Verify Policy Billing Summaries Webservice Response	
	Then  I Validate Retrieve Policy Billing Summaries SIS SOAP Service


@completed
Scenario: Verify Validate_Retrieve Bill History SOAP Service Response
	Then I Validate Retrieve Bill History SOAP Service

@completed
Scenario: Verify Validate Retrieve Policy Transaction Details SOAP Service Response
	Then I Validate Retrieve Policy Transaction Details SOAP Service

@completed
Scenario: Verify Customer Search Webservice Response
	Then  I Validate Customer Search Webservice

@completed
Scenario: Verify Policy Search Webservice Response
	Then  I Validate Policy Search Webservice
	

@completed
Scenario Outline: Verify Update Preference Webservice Response
	When  I set Request for the Update Preference Webservice <Iteration>
	Then  I Call and validate the Update Preference Webservice Response

	Examples:
	|Iteration	| 
	|1|



@completed
Scenario Outline: Verify Get Policy Preference Webservice Response
	When  I set Request for the Get Policy Preference Webservice <Iteration>
	Then  I Call and validate the Get Policy Preference Webservice Response

	Examples:
	|Iteration	| 
	|1|
	
@completed
Scenario Outline: Verify Record Wallet Webservice Response
	When  I set Request for the Record Wallet Webservice <Iteration>
	Then  I Call and validate the Record Wallet Webservice response

	Examples:
	|Iteration	| 
	|1|
	
@completed
Scenario Outline: Verify Retrieve Wallet Details Webservice Response
	When  I set Request for the Retrieve Wallet Details Webservice <Iteration>
	Then  I Call and validate the Retrieve Wallet Details Webservice response

	Examples:
	|Iteration	| 
	|1|
	
