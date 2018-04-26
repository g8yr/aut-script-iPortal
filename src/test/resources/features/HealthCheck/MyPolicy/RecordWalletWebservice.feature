@RecordWalletWebservice
Feature: To Verify Record Wallet Webservice Response


@completed
Scenario Outline: Verify Record Wallet Webservice Response
	When  I set Request for the Record Wallet Webservice <Iteration>
	Then  I Call and validate the Record Wallet Webservice response

	Examples:
	|Iteration	| 
	|1|