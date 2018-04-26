package stepDefinitions.MyPolicy;

import webelementrepository.MyPolicy.LoginPage;
import webelementrepository.MyPolicy.MakePolicyChangesPage;
import businesscomponents.mypolicy.AddVehicleFunctions;
import businesscomponents.mypolicy.CoverageFunctions;
import businesscomponents.mypolicy.DriverVehicleAssignmentFunctions;
import businesscomponents.mypolicy.GeneralFunctions;
import businesscomponents.mypolicy.VehiclesPageFunctions;

import com.Cucumber.supportLibraries.StaticValue;
import com.Cucumber.supportLibraries.Webaction;
import com.Cucumber.supportLibraries.custom.ReadCSVFileFunctions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import stepDefinitions.common.MasterStepDefs;

public class CoverageStepDef extends MasterStepDefs{

	Webaction action = new Webaction();
	LoginPage loginpage = new LoginPage();
	MakePolicyChangesPage makepolicychangespage = new MakePolicyChangesPage();
	AddVehicleFunctions addVehicleFns = new AddVehicleFunctions();
	ReadCSVFileFunctions readCSVfilefns = new ReadCSVFileFunctions();
	VehiclesPageStepDef vehiclesPageStepDef = new VehiclesPageStepDef();
	VehiclesPageFunctions vehiclespagefns = new VehiclesPageFunctions();
	AddVehicleStepDef addVehicleStepDef = new AddVehicleStepDef();
	DriverVehicleAssignmentFunctions driverVehicleAssignmentFns =new DriverVehicleAssignmentFunctions();
	GeneralFunctions GenFns = new GeneralFunctions();
	CoverageFunctions CoverageFns = new CoverageFunctions();

	
	
	
	@Then("^I should be navigated to the coverage page$")
	public void ValidateCoverPageLanded() {
		CoverageFns.VerifyCoveragePageLandedSuccesfully();
	   
	}

	@Then("^I should be able to see vehicle level coverage displayed as per PAS system along with the help text$")
	public void ValidateVehicleLevelCoverageDisplayedAsPerPAS()  {
		CoverageFns.validateVehicleLevelCoverageDisplayedAsPerPAS();
	    
	    
	}

	@Then("^I see the  newly added vehicle with pending tag, vehicle year, make and model along with VIN#$")
	public void ValidatePendingFlagYearMakeModelVINForTheNewlyAddedVehicle() {
		CoverageFns.validatePendingFlagYearMakeModelVINForTheNewlyAddedVehicle();
	    
	}

	@Then("^I see the continue button enabled on the Coverage page$")
	public void ValidateContinueButtonEnabledOnTheCoveragePage()  {
		CoverageFns.validateContinueButtonEnabledOnTheCoveragePage();
	    
	}

	@Then("^I see Comprehension/Collision default amount with label is displayed as per PAS service$")
	public void ValidateComprehensionCollisionDefaultAmountWithLabelIsDisplayed()  {
		CoverageFns.ValidateComprehensionCollisionAmountDisplayedAsPerPASCoverageService();
	   
	}

	@Then("^I see Rental car coverage default amount with label is displayed$")
	public void ValidateRentalCarCoverageAmountDisplayedAsPerPASCoverageService() {
		CoverageFns.validateRentalCarCoverageAmountDisplayedAsPerPASCoverageService();
	   
	}

	@Then("^I see Towing and Labor coverage with default value as 'Get Coverage'$")
	public void i_see_Towing_and_Labor_coverage_with_default_value_as_Get_Coverage()  {
		CoverageFns.validateTowingAndLaborCoverageAmountDisplayedAsPerPASCoverageService();
	    
	}

	
	@Then("^I should not be able to see the New Car Added Protection coverage if available in PAS$")
	public void ValidateNewCarAddedProtectionCoverageNotAvailable() {
		CoverageFns.validateNewCarAddedProtectionCoverageNotAvailable();
	    
	}

	
	@Then("^I should not be able to see the Customized equipment coverage if available in PAS$")
	public void ValidateCustomizedEquipmentCoverage()  {
		CoverageFns.validateCustomizedEquipmentCoverage();
	  
	}

	
	@Then("^I should not be able to see the Excess Electronic Equipment coverage if available in PAS$")
	public void ValidateExcessElectronicEquipmentCoverage()  {
		CoverageFns.validateExcessElectronicEquipmentCoverage();
	   
	}
}
