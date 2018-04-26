@RetrieveWalletDetailsWebservice
Feature: To Verify Retrieve Wallet Details Webservice Response


@completed
Scenario Outline: Verify Retrieve Wallet Details Webservice Response
	When  I set Request for the Retrieve Wallet Details Webservice <Iteration>
	Then  I Call and validate the Retrieve Wallet Details Webservice response

	Examples:
	|Iteration	| 
	|1|