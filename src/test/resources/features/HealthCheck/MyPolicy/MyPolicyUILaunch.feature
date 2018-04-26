@MyPolicyUILaunch
Feature: MyPolicy HealthCheck UI Validations
Background:
	Given I visit the login page of the application using id

Scenario: Verify launch of MyPolicy Application
	When   I login using the valid username digitalservices952@gmail.com and the valid password Password1
	And   I can see DashBoard page
	