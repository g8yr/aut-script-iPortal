package stepDefinitions.MyPolicy;

import webelementrepository.MyPolicy.LoginPage;
import webelementrepository.MyPolicy.MakePolicyChangesPage;
import businesscomponents.mypolicy.AddVehicleFunctions;
import businesscomponents.mypolicy.VehiclesPageFunctions;

import com.Cucumber.supportLibraries.StaticValue;
import com.Cucumber.supportLibraries.Webaction;
import com.Cucumber.supportLibraries.custom.ReadCSVFileFunctions;

import cucumber.api.java.en.When;

public class AddVehicleStepDef {

	Webaction action = new Webaction();
	LoginPage loginpage = new LoginPage();
	MakePolicyChangesPage makepolicychangespage = new MakePolicyChangesPage();
	AddVehicleFunctions addVehicleFunctions = new AddVehicleFunctions();
	ReadCSVFileFunctions readCSVfilefunctions = new ReadCSVFileFunctions();
	VehiclesPageStepDef vehiclesPageStepDef = new VehiclesPageStepDef();
	VehiclesPageFunctions vehiclespagefunctions = new VehiclesPageFunctions();

	@When("^I see the vehicle Make Model year in AddVehicle action block title$")
	public void ChkAddVehicleActionBlockTitle() {
		addVehicleFunctions.ValidateAddVehicleActionBlockTitle();

	}

	@When("^I see the AddVehicle action block description$")
	public void ChkAddVehicleActionBlockSubTitle() {
		addVehicleFunctions.ValidateAddVehicleActionBlockSubTitle();

	}

	@When("^I see the Add Vehicle block collapse button changed to cancel$")
	public void ChkAddVehicleBlockCollapseButtonChangedToCancel() {
		addVehicleFunctions.ValidateAddVehicleBlockCollapseButtonChangedToCancel();

	}

	@When("^I see the AddVehicle back button$")
	public void ChkAddVehicleBackButton() {
		addVehicleFunctions.ValidateAddVehicleBackButtonVisible();

	}

	@When("^I do not see the NEXT button visible$")
	public void ChkAddVehicleActionBlockNextButtonNotVisible() {
		addVehicleFunctions.ValidateAddVehicleActionBlockNextButtonNotVisible();

	}

	@When("^I see the Vehicle Ownership Type questions with options$")
	public void VehicleOwnership() {
		addVehicleFunctions.ValidateVehicleOwnership();
	}

	@When("^I click on the Ownership Type$")
	public void ClickVehicleOwnershipType() {
		addVehicleFunctions.ClickTheGivenVehicleOwnershipType();
	}

	@When("^I see the Vehicle Usage Type question$")
	public void ValidateVehicleUsageTypeQuestion() {
		addVehicleFunctions.ValidateVehicleUsageTypequestion();
	}

	@When("^I click option for Vehicle Usage Type question$")
	public void ValidateAndClickVehicleUsageTypeOptions() {
		addVehicleFunctions.ValidateYesNoOptions();
		addVehicleFunctions.ClickTheGivenYesNoOption(StaticValue.VehicleUsageTypeAsBusiness,
				StaticValue.AddvehicleUsageTypeQuestion);
	}

	@When("^I see the Vehicle Located question$")
	public void ValidateVehicleLocatedQuestion() {
		addVehicleFunctions.ValidateVehicleLocatedquestion();
	}

	@When("^I click option for Vehicle Located question$")
	public void ValidateAndClickVehicleLocatedOption() {
		addVehicleFunctions.ValidateYesNoOptions();
		addVehicleFunctions.ClickTheGivenYesNoOption(StaticValue.VehicleLocatedAtYourResidence,
				StaticValue.AddvehicleLocatedAtResidenceQuestion);
	}

	@When("^I see the Vehicle Salvaged question$")
	public void ValidateVehicleSalvagedQuestion() {
		addVehicleFunctions.ValidateVehicleSalvagedquestion();
	}

	@When("^I click option for Vehicle Salvaged question$")
	public void ValidateAndClickVehicleSalvagedOption() {
		addVehicleFunctions.ValidateYesNoOptions();
		addVehicleFunctions.ClickTheGivenYesNoOption(StaticValue.VehicleSalvaged,
				StaticValue.AddvehicleSalvageQuestion);
	}

	@When("^I see the All Registered Owners question$")
	public void ValidateAllRegisteredOwnersQuestion() {
		addVehicleFunctions.ValidateAllRegisteredOwnersquestion();
	}

	@When("^I click option for all Registered Owners question$")
	public void ValidateAndClickVehicleAllregisteredOwnersQuestionOption() {
		addVehicleFunctions.ValidateYesNoOptions();
		addVehicleFunctions.ClickTheGivenYesNoOption(StaticValue.AllRegisteredOwnersInPolicy,
				StaticValue.AddvehicleAllRegisteredOwnersQuestion);
	}

	@When("^I see the AntiTheft question$")
	public void ValidateAntiTheftQuestion() {
		addVehicleFunctions.ValidateAntiTheftquestion();
	}

	@When("^I click option for AntiTheft question$")
	public void ValidateAndClickVehicleAntiTheftQuestionOption() {
		addVehicleFunctions.ValidateYesNoOptions();
		addVehicleFunctions.ClickTheGivenYesNoOption(StaticValue.AntiTheftDevice,
				StaticValue.AddvehicleAntiTheftDeviceQuestion);
	}

	@When("^I see the Add/Remove Additional vehicles question$")
	public void ValidateAddAdditionalvehiclesQuestion() {
		addVehicleFunctions.ValidateAddAdditionalvehiclesquestion();
	}

	@When("^I click option for Add/Remove Additional vehicles question$")
	public void ValidateAndClickAddRemoveVehicleQuestionOption() {
		addVehicleFunctions.ValidateYesNoOptions();
		addVehicleFunctions.ClickTheGivenYesNoOption(StaticValue.AddRemoveAdditionalVehicle,
				StaticValue.AddvehicleADDREMOVEVehicleQuestion);
	}

	@When("^I see the Vehicle Summary screen$")
	public void ValidateVehicleSummaryScreen() {
		addVehicleFunctions.ValidateVehicleSummaryScreenPage();

	}

	@When("^I click AddContinue or Add button on the summary view$")
	public void ClickVehcicleQuestionSummaryAddContinueButton() {
		addVehicleFunctions.ClickVehcicleQuestionSummaryAddContinuebutton();
	}

	@When("^I see vehicle being added is on top of the page with pending status$")
	public void ValidateVehicleListHasPending() {
		addVehicleFunctions.ValidateAllVehicleListed();

	}

	@When("^I see Continue button in the page$")
	public void ValidateContinueButton() {
		addVehicleFunctions.ValidateContinuebutton();

	}
	
	@When("^I entered all the Vehicle Interview Questions$")
	public void FunctionToEnterInterviewQuestion() {
		vehiclespagefunctions.ClickAddVehicleNextButton();
		vehiclespagefunctions.ValidateVINService();	
		addVehicleFunctions.ValidateAddVehicleActionBlockTitle();
		addVehicleFunctions.ValidateAddVehicleActionBlockSubTitle();
		addVehicleFunctions.ValidateAddVehicleBlockCollapseButtonChangedToCancel();
		addVehicleFunctions.ValidateAddVehicleBackButtonVisible();
		addVehicleFunctions.ValidateAddVehicleActionBlockNextButtonNotVisible();
		addVehicleFunctions.ValidateVehicleOwnership();
		addVehicleFunctions.ClickTheGivenVehicleOwnershipType();
		addVehicleFunctions.ValidateVehicleUsageTypequestion();
		ValidateAndClickVehicleUsageTypeOptions();
		addVehicleFunctions.ValidateVehicleLocatedquestion();
		ValidateAndClickVehicleLocatedOption();
		addVehicleFunctions.ValidateVehicleSalvagedquestion();
		ValidateAndClickVehicleSalvagedOption();
		addVehicleFunctions.ValidateAllRegisteredOwnersquestion();
		ValidateAndClickVehicleAllregisteredOwnersQuestionOption();
		addVehicleFunctions.ValidateAntiTheftquestion();
		ValidateAndClickVehicleAntiTheftQuestionOption();
		addVehicleFunctions.ValidateAddAdditionalvehiclesquestion();
		

	}

}
