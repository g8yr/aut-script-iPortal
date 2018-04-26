@iPortalHealthCheck
Feature: iPortal HealthCheck validations

Scenario: iPortal HealthCheck:Chrome
	Given I generate the iPortal Generate OauthToken
	Then  I Validate RetrievePolicyDetails Auto PAS SOAP Service
	Then  I Validate Retrieve Policy Billing Summaries SIS SOAP Service
	Then  I Validate Retrieve Payment Transaction SOAP Service
	#Then  I Validate Customer Search Webservice
	#Then  I Validate Policy Search Webservice
	Then I Validate Retrieve Policy Summary SOAP Service
	Then I Validate Find Policy SOAP Service
	Then I Validate Retrieve Customer Details SOAP Service
	#Then I Validate_Retrieve Bill History SOAP Service
	#Then I Validate Retrieve Policy Transaction Details SOAP Service
	
	
	