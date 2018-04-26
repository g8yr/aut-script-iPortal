@GetPreferenceWebservice
Feature: To Verify Get Policy Preference Webservice Response


@completed
Scenario Outline: Verify Get Policy Preference Webservice Response
	When  I set Request for the Get Policy Preference Webservice <Iteration>
	Then  I Call and validate the Get Policy Preference Webservice Response

	Examples:
	|Iteration	| 
	|1|