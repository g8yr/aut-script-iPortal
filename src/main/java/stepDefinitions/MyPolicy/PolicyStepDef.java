package stepDefinitions.MyPolicy;

import org.openqa.selenium.WebDriver;

import stepDefinitions.common.MasterStepDefs;
import webelementrepository.MyPolicy.PoliciesPage;
import businesscomponents.mypolicy.DashboardFunctions;
import businesscomponents.mypolicy.GeneralFunctions;
import businesscomponents.mypolicy.PolicyPageFunctions;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.StaticValue;
import com.Cucumber.supportLibraries.Webaction;
import com.Cucumber.supportLibraries.custom.ReadCSVFileFunctions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class PolicyStepDef extends MasterStepDefs {
	
//	public PolicyStepDef (HashMap<String, String> SingleVehicleData) {
//		this.SingleVehicleData = SingleVehicleData;
//		
//	}
	
	public String PolicyNumber;
	public String PolicyDate;
	PoliciesPage policypage = new PoliciesPage();
	Webaction action  = new Webaction();

	WebDriver driver = DriverManager.getWebDriver();
	PolicyPageFunctions policypagefunction= new PolicyPageFunctions();
	DashboardFunctions dashboardfunction= new DashboardFunctions();
	ReadCSVFileFunctions readCSVfilefunctions = new ReadCSVFileFunctions();
	LoginStepDefs logindtepdefs2 = new LoginStepDefs();
	GeneralFunctions GenFns = new GeneralFunctions();

	@Given("^I see my policy in policy details page$")
	public void IseeTheGivenPolicy() throws Throwable {
		System.out.println("PolicyStepDef.IseeTheGivenPolicy()");
		String policynumber = StaticValue.Empty;
		//policynumber = readCSVfilefunctions.SingleVehicleData.get(StaticValue.PolicyNumber);
		policynumber = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(),StaticValue.PolicyNumber);
		policypagefunction.validatePolicyDetails(policynumber);
	}

	@And ("^I click the policy (.*)$")
	public void ValidatePolicySection(String Iteration){
		System.out.println("PolicyStepDef.ValidatePolicySection()");
		//policypagefunction.validatePolicyDetails(PolicyNumber);
		String policynumber = StaticValue.Empty;
		policynumber = readCSVfilefunctions.readCsvFile(StaticValue.PolicyNumber, Iteration );
		dashboardfunction.validatePolicySection(policynumber);
	}

	@And ("^I click the given policy (.*)$")
	public void ValidateGivenPolicySection(String Iteration){
		System.out.println("PolicyStepDef.ValidatePolicySection()");
		String policynumber = StaticValue.Empty;
		readCSVfilefunctions.readCsvFile(StaticValue.PolicyNumber, Iteration );
		//policynumber =readCSVfilefunctions.SingleVehicleData.get(StaticValue.PolicyNumber);
		policynumber = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), StaticValue.PolicyNumber);
		dashboardfunction.validatePolicySection(policynumber);
	}
	
	
	@Given("^The PAS validation for Make Policy Changes button is passed Policy: (.*) Date: (.*)$")
	public void PASValidationForButtonChkPass(String PolicyNumber, String EndorsementDate)  {
		policypagefunction.ValidateEndorsementFromPAS(PolicyNumber,EndorsementDate,StaticValue.Pass);
		
	}
	
	@Given("^The PAS validation for Make Policy Changes button is passed (.*)$")
	public void PASValidationForButtonChkPass(String Iteration)  {
		System.out.println("PolicyStepDef.PASValidationForButtonChkPass()");
		String policynumber = StaticValue.Empty;
		//policynumber =readCSVfilefunctions.SingleVehicleData.get(StaticValue.PolicyNumber);
		policynumber =GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(),StaticValue.PolicyNumber);
		PolicyDate = readCSVfilefunctions.readCsvFile(StaticValue.PolicyDate, Iteration );
		policypagefunction.ValidateEndorsementFromPAS(policynumber,PolicyDate,StaticValue.Pass);
		
	}

	@Given("^The PAS validation for Make Policy Changes button is failed (.*)$")
	public void PASValidationForButtonChkFail(String Iteration)  {
		System.out.println("PolicyStepDef.PASValidationForButtonChkFail()");
		String policynumber = StaticValue.Empty;
		//policynumber =readCSVfilefunctions.SingleVehicleData.get(StaticValue.PolicyNumber);
		policynumber =GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(),StaticValue.PolicyNumber);
		//PolicyDate = readCSVfilefunctions.SingleVehicleData.get(StaticValue.PolicyDate);
		PolicyDate = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(),StaticValue.PolicyDate);
		policypagefunction.ValidateEndorsementFromPAS(policynumber,PolicyDate,StaticValue.Fail);
		
	}
	
	
	@Then("^I see the 'Make Policy Change' button displayed on the policy summary card$")
	public void ChkMakePolicyChangeButtonDisplayedOnThePolicySummaryCard()   {
		policypagefunction.chkMakePolicyChangeButton(StaticValue.IsPresent);
		
	}
	
	@Given("^The PAS validation for Make Policy Changes button is failed Policy: (.*) Date: (.*)$")
	public void PASValidationForButtonChkFail(String PolicyNumber, String EndorsementDate)  {
		policypagefunction.ValidateEndorsementFromPAS(PolicyNumber,EndorsementDate,StaticValue.Fail);		
	}
	
	@Then("^I see the 'Make Policy Change' button not displayed on the policy summary card$")
	public void ChkMakePolicyChangeButtonNotDisplayedOnThePolicySummaryCard()   {
		System.out.println("PolicyStepDef.ChkMakePolicyChangeButtonNotDisplayedOnThePolicySummaryCard()");
		policypagefunction.chkMakePolicyChangeButton(StaticValue.IsNotPresent);
		
	}
	
	@Then("^I click on the 'Make Policy Changes' button$")
	public void ClickMakePolicyChangeButton()   {
		policypagefunction.clickMakePolicyChangeButton();
		//action.ClickJSElement(policypage.MakePolicyChangeVisibleElm(), StaticValue.MakePolicyChanges);		
	}
	
	
	@Then("^I should be navigated to the Endorsement Menu Page$")
	public void IshouldBeNavigatedToTheEndorsementMenuPage()  {
	    System.out.println("PolicyStepDef.IshouldBeNavigatedToTheEndorsementMenuPage()");
	    policypagefunction.chkEndorsementPageLoaded();
	}
	
}