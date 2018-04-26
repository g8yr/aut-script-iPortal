package stepDefinitions.MyPolicy;

import org.openqa.selenium.WebDriver;

import stepDefinitions.common.MasterStepDefs;
import businesscomponents.mypolicy.GeneralFunctions;
import businesscomponents.mypolicy.PoliciesFunctions;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.StaticValue;
import com.Cucumber.supportLibraries.Webaction;
import com.Cucumber.supportLibraries.custom.ReadCSVFileFunctions;

import cucumber.api.java.en.Then;

public class PoliciesPageStepDef extends MasterStepDefs {
	
//	public PolicyStepDef (HashMap<String, String> SingleVehicleData) {
//		this.SingleVehicleData = SingleVehicleData;
//		
//	}
	
	public String PolicyNumber;
	public String PolicyDate;
	Webaction action  = new Webaction();

	WebDriver driver = DriverManager.getWebDriver();
	PoliciesFunctions policiesfunction= new PoliciesFunctions();
	ReadCSVFileFunctions readCSVfilefunctions = new ReadCSVFileFunctions();
	GeneralFunctions GenFns = new GeneralFunctions();

	
	@Then("^I view the policy details for my policy (.*)$")
	public void verifyPolciesSection(String Iteration)  
	{
		readCSVfilefunctions.readCsvFile(StaticValue.PolicyNumber, Iteration );
		//policynumber = readCSVfilefunctions.SingleVehicleData.get(StaticValue.PolicyNumber);
		PolicyNumber = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), StaticValue.PolicyNumber);
		readCSVfilefunctions.readCsvFile(StaticValue.eValueDiscountApply, Iteration );
		policiesfunction.validatePolicySection(PolicyNumber);
	}

	@Then("^I view eValue icon for eValue Policy$")
	public void verifyeValueDiscount()  
		{
			policiesfunction.validateDicount();
		}
	@Then("^I dont view eValue icon for Non eValue Policy$")
	public void verifyNoneValueDiscount()  
		{
			policiesfunction.validateDicount();
		}
		
}