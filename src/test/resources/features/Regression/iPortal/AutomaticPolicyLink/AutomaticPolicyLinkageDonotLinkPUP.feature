@AutomaticPolicyLinkageDonotLinkPUP @AutomaticPolicyLink @iPortalRegression
Feature: Login in iPortal and Automatic policy linkage and donnot link PUP policy

Background:
       Given I visit the login page of the iPortal application using id

       
Scenario Outline: Automatic policy linkage donot link PUP Browser:Chrome
    When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	Then I see Customer Overview page
	Then I see the searched policy and the newly created Auto VAH3900035624 Home VAH4900034214 PUP VAPU900034215 policy in the Dashboard
       
Examples:
|Iteration|
|1|

Scenario Outline: Automatic policy linkage donot link PUP Browser:InternetExplorer
    When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	Then I see Customer Overview page
	Then I see the searched policy and the newly created Auto VAH3900035624 Home VAH4900034214 PUP VAPU900034215 policy in the Dashboard
       
Examples:
|Iteration|
|2|

Scenario Outline: Automatic policy linkage donot link PUP Browser:Firefox
    When I login iPortal application using the valid username wms_test_11@mailinator.com and the valid password Passw5rd
	And  I search and navigate To Iportal Policy OverviewPage <Iteration>
	Then I see Customer Overview page
	Then I see the searched policy and the newly created Auto VAH3900035624 Home VAH4900034214 PUP VAPU900034215 policy in the Dashboard
       
Examples:
|Iteration|
|3|




