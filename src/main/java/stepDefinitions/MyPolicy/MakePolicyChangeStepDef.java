package stepDefinitions.MyPolicy;

import org.openqa.selenium.WebDriver;

import stepDefinitions.common.MasterStepDefs;
import webelementrepository.MyPolicy.MakePolicyChangesPage;
import webelementrepository.MyPolicy.PoliciesPage;
import businesscomponents.mypolicy.DashboardFunctions;
import businesscomponents.mypolicy.GeneralFunctions;
import businesscomponents.mypolicy.MakePolicyChangesFunctions;
import businesscomponents.mypolicy.PolicyPageFunctions;
import businesscomponents.mypolicy.VehiclesPageFunctions;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.StaticValue;
import com.Cucumber.supportLibraries.Webaction;
import com.Cucumber.supportLibraries.custom.ReadCSVFileFunctions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MakePolicyChangeStepDef extends MasterStepDefs {
		
	public String PolicyNumber;
	PoliciesPage policypage = new PoliciesPage();
	Webaction action  = new Webaction();
	MakePolicyChangesPage makepolicychangespage = new MakePolicyChangesPage();
	
	WebDriver driver = DriverManager.getWebDriver();
	PolicyPageFunctions policypagefunction= new PolicyPageFunctions();
	DashboardFunctions dashboardfunction= new DashboardFunctions();
	MakePolicyChangesFunctions makepolicychangesfunctions = new MakePolicyChangesFunctions();
	ReadCSVFileFunctions readCSVfilefunctions = new ReadCSVFileFunctions();
	VehiclesPageFunctions vehiclespagefunctions = new VehiclesPageFunctions();
	GeneralFunctions GenFns = new GeneralFunctions();
	

	@Then("^I can see Add/Remove vehicle option on the Endorsement Menu page$")
	public void ICanSeeAddRemoveVehicleOptionOnTheEndorsementMenuPage()  {
		action.waitForElement(makepolicychangespage.AddRemoveVechicleElm);
	}

//	@Then("^I can also see the policy number that is being edited at the top of the page$")
//	public void ICanAlsoSeeThePolicyNumberThatIs_BeingEditedAtTheTopOfThePage()  {
//		System.out.println(" No Policy Number visible .. Checked with bavina..it has to be out user story");
//
//	}
	
	@Then("^I should see the 'Endorsement Menu Page' as per specifications$")
	public void IShouldSeeTheEndorsementMenuPageAsPerSpecifications()  {
		makepolicychangesfunctions.ValidateTheEnorsementPageAsPerSpec();

	}
	
	@When("^I click on the 'Add/Remove Vehicles' button$")
	public void IClickOnAddRemoveVehiclesButton()  {	    
		//action.ClickJSElement(makepolicychangespage.AddRemoveVechicleElm, StaticValue.AddRemoveVehicle);
		action.ClickElement(makepolicychangespage.AddRemoveVechicleElm);
	}
	
	@Then("^I should be navigated to the Vehicles Page$")
	public void IShouldBeNavigatedToTheVehiclesPage()  {
		makepolicychangesfunctions.ValidateTheVehiclePageLanded();
	}
	    	
	@Given("^The PAS validation for Make Policy Vehicle List is passed for the Policy (.*)$")
	public void PASValidationForVehicleList(String PolicyNumber)  {
		policypagefunction.ValidateVehicleListFromPAS(PolicyNumber);
		
	}
	
	//@Then("^I should be able to see all the current vehicles on the auto policy displayed as separate 'Action Blocks with drawers' in the collapsed view Policy (.*)$")
	@Then("^I should be able to see all the vehicles page header, Action Block header and description as per Spec with the policy number$")
	public void ValidateVechileListPageContent()  {
		PolicyNumber = StaticValue.Empty;
		//PolicyNumber =readCSVfilefunctions.SingleVehicleData.get(StaticValue.PolicyNumber);
		PolicyNumber =GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(),StaticValue.PolicyNumber);
		makepolicychangesfunctions.ValidateTheVehcileListAsPerSpec(PolicyNumber);
	}

	//@Then("^I can see the Year, Make, Model of the vehicle displayed in the action block title Policy (.*)$")
	@Then("^I can see the Year, Make, Model of the vehicle displayed in the action block title for Policy$")
	public void ValidateVechileListPageMakeModelYearVIN() {
	//	makepolicychangesfunctions.ValidateVehcleVINMake();
		System.out.println("MakePolicyChangeStepDef.PASValidationForVehicleList()");
		PolicyNumber = StaticValue.Empty;
		//PolicyNumber =readCSVfilefunctions.SingleVehicleData.get(StaticValue.PolicyNumber);
		PolicyNumber =GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(),StaticValue.PolicyNumber);
		policypagefunction.ValidateVehicleListFromPAS(PolicyNumber);
	}
	
	
	
	
	
	
	
}