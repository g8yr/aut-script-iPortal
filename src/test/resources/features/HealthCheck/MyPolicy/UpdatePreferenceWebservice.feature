@UpdatePreferenceWebservice
Feature: To Verify Update Preference Webservice Response


@completed
Scenario Outline: Verify Update Preference Webservice Response
	When  I set Request for the Update Preference Webservice <Iteration>
	Then  I Call and validate the Update Preference Webservice Response

	Examples:
	|Iteration	| 
	|1|