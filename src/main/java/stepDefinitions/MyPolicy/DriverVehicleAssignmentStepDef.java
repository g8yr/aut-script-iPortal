package stepDefinitions.MyPolicy;

import webelementrepository.MyPolicy.LoginPage;
import webelementrepository.MyPolicy.MakePolicyChangesPage;
import businesscomponents.mypolicy.AddVehicleFunctions;
import businesscomponents.mypolicy.DriverVehicleAssignmentFunctions;
import businesscomponents.mypolicy.GeneralFunctions;
import businesscomponents.mypolicy.VehiclesPageFunctions;

import com.Cucumber.supportLibraries.StaticValue;
import com.Cucumber.supportLibraries.Webaction;
import com.Cucumber.supportLibraries.custom.ReadCSVFileFunctions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import stepDefinitions.common.MasterStepDefs;

public class DriverVehicleAssignmentStepDef extends MasterStepDefs{

	Webaction action = new Webaction();
	LoginPage loginpage = new LoginPage();
	MakePolicyChangesPage makepolicychangespage = new MakePolicyChangesPage();
	AddVehicleFunctions addVehicleFunctions = new AddVehicleFunctions();
	ReadCSVFileFunctions readCSVfilefunctions = new ReadCSVFileFunctions();
	VehiclesPageStepDef vehiclesPageStepDef = new VehiclesPageStepDef();
	VehiclesPageFunctions vehiclespagefunctions = new VehiclesPageFunctions();
	AddVehicleStepDef addVehicleStepDef = new AddVehicleStepDef();
	DriverVehicleAssignmentFunctions driverVehicleAssignmentFunctions =new DriverVehicleAssignmentFunctions();
	GeneralFunctions GenFns = new GeneralFunctions();

	
	
	
	@When("^I choose No option for Add/Remove Additional vehicles question$")
	public void SelectNoOptionForAddRemoveAdditionalVehicleQuestion()  {
		driverVehicleAssignmentFunctions.chkTheGivenAddRemoveAdditionalVehicleQuestionOptionFromInputFile(StaticValue.NO);
		addVehicleStepDef.ValidateAndClickAddRemoveVehicleQuestionOption();
	}

	@When("^I choose Yes option for Add/Remove Additional vehicles question$")
	public void SelectYesOptionForAddRemoveAdditionalVehicleQuestion()  {
		driverVehicleAssignmentFunctions.chkTheGivenAddRemoveAdditionalVehicleQuestionOptionFromInputFile(StaticValue.YES);
		addVehicleStepDef.ValidateAndClickAddRemoveVehicleQuestionOption();
	}

	@Then("^I should be navigated to the Driver and Vehicle assignment page$")
	public void ValidateDriverVehicleAssignmentPage()  {
		driverVehicleAssignmentFunctions.ValidateDriverVehicleAssignmentSummaryPage();
	   
	}

	@Then("^I should be able to view pending flag for the newly added vehicle$")
	public void ValidatePendingFlagFromDriverVehicleAssignmentPage()  {
		driverVehicleAssignmentFunctions.ValidatePendingFlagBasedOnPASpendingVehicleService();
	   
	}
	
	@Then("^I should be able to view the newly added vehicle assigned to the existing driver$")
	public void ValidateDirverNameInDriverVehicleAssignmentpage()  {		
		driverVehicleAssignmentFunctions.ValidateDriverAssignedToVehicle();
	}
	


	@Then("^I should see the Continue button enabled in the Driver and Vehicle assignment page$")
	public void VerifyContinueButtonInDriverVehicleAssignmentPage()  {
		driverVehicleAssignmentFunctions.ValidateContinueButtonInDriverAssignmentpage();
	    
	}

	@Then("^I click on continue button from the Driver and Vehicle assignment page$")
	public void ClickContinueButtonFromTheDriverVehicleAssignmentPage() {
		driverVehicleAssignmentFunctions.clickContinueButtonFromTheDriverVehicleAssignmentPage();
	    
	}

	
}
