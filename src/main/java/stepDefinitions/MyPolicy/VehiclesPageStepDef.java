		package stepDefinitions.MyPolicy;

import java.io.IOException;

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

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import services.rest.apiclients.OAuthTokenPOST;;

public class VehiclesPageStepDef extends MasterStepDefs {
	
	
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
	public String VehiclePurchaseDate;
	private String VehicleVIN;
	String policynumber = StaticValue.Empty;


	
	@And("^I should be navigate to the Vehicles page (.*)$")
	public void ValidateMakePolicyChangeButton(String Iteration)
	{
	
		readCSVfilefunctions.readCsvFile(StaticValue.PolicyNumber, Iteration );
		//policynumber = readCSVfilefunctions.SingleVehicleData.get(StaticValue.PolicyNumber);
		policynumber = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), StaticValue.PolicyNumber);
		String PolicyDate = readCSVfilefunctions.readCsvFile(StaticValue.PolicyDate, Iteration );
		readCSVfilefunctions.readCsvFile(StaticValue.VehiclePurchaseDate, Iteration );
		readCSVfilefunctions.readCsvFile(StaticValue.VehicleVIN, Iteration);
	
		
		dashboardfunction.validateDashboardPage();
		dashboardfunction.clickPolicySection();
		dashboardfunction.validatePolicySection(policynumber);	
		policypagefunction.validatePolicyDetails(policynumber);
		policypagefunction.ValidateEndorsementFromPAS(policynumber,PolicyDate,StaticValue.Pass);
		policypagefunction.chkMakePolicyChangeButton(StaticValue.IsPresent);	
		policypagefunction.clickMakePolicyChangeButton();
		
		policypagefunction.chkEndorsementPageLoaded();		    
		action.waitForElement(makepolicychangespage.AddRemoveVechicleElm);	
		makepolicychangesfunctions.ValidateTheEnorsementPageAsPerSpec();
		action.ClickElement(makepolicychangespage.AddRemoveVechicleElm);		 
		makepolicychangesfunctions.ValidateTheVehiclePageLanded();		 
		makepolicychangesfunctions.ValidateTheVehcileListAsPerSpec(policynumber);		 
		policypagefunction.ValidateVehicleListFromPAS(policynumber);
}

	
	@And("^I click ADDVehicle button and enter PurchaseDate and VIN (.*)$")
	public void ValidateAddRemoveVehicleButton(String Iteration){
		readCSVfilefunctions.readCsvFile(StaticValue.VehicleOwnerShipType, Iteration );
		readCSVfilefunctions.readCsvFile(StaticValue.VehicleLocatedAtYourResidence, Iteration );
		readCSVfilefunctions.readCsvFile(StaticValue.VehicleUsageTypeAsBusiness, Iteration );
		readCSVfilefunctions.readCsvFile(StaticValue.AllRegisteredOwnersInPolicy, Iteration );
		readCSVfilefunctions.readCsvFile(StaticValue.VehicleSalvaged, Iteration );
		readCSVfilefunctions.readCsvFile(StaticValue.AntiTheftDevice, Iteration );
		readCSVfilefunctions.readCsvFile(StaticValue.AddRemoveAdditionalVehicle, Iteration );
		
		ClickAddVehiclelink();
		vehiclespagefunctions.ValidateAddVehicleDrawreViewText(StaticValue.IsNotCollapsed);
		ValidateVehiclePurchaseDateVINField();
		ValidateVehicleNextButtonDisabled();
		ValidateCollapseArrowLink();
		EnterPurchaseDate();
		ValidatePurchaseDate();
		EnterVIN();
		vehiclespagefunctions.ValidateVINNumber(StaticValue.ValidVIN);
		ClickAddVehiclelinkToCollapse();
		vehiclespagefunctions.ValidateAddVehicleDrawreViewText(StaticValue.IsCollapsed);
		ClickAddVehiclelink();
		vehiclespagefunctions.ValidateAddVehicleDrawreViewText(StaticValue.IsNotCollapsed);
		ValidateVINDatePrepopulated();
		vehiclespagefunctions.ValidateVehicleNextButton(StaticValue.IsEnabled);
			
	}
	
	
	@When("^I click NEXT button and VIN validation from PAS is passed$")
	public void ClickNEXTbuttonAndPASVinValidation()
	{
		ClickVehicleNextButton();
		ValidatePASVINFailed();	
	}
	
	
	@When("^I clicks on the 'Add Vehicle' action block which is in collapsed view$")
	public void ClickAddVehiclelink()
	{
		vehiclespagefunctions.clickAddVehiclelink();
	}
	
	@When("^I clicks on the 'Add Vehicle' action block which is in expanded view$")
	public void ClickAddVehiclelinkToCollapse()
	{
		vehiclespagefunctions.clickAddVehiclelink();
	}
	
	
	@Then("^I should see the Add vehicle action block expanded with a drawer$")
	public void ValidateAddVehicleDrwareViewText()
	{
	vehiclespagefunctions.ValidateAddVehicleDrawreViewText(StaticValue.IsNotCollapsed);
	}
	@Then("^I sees 'Add Vehicle' action block is collapsed$")
	public void ValidateAddVehicleSectionCollapsed()
	{
	vehiclespagefunctions.ValidateAddVehicleDrawreViewText(StaticValue.IsCollapsed);
	}
	
	@And("^I can see the Purchase date and VIN fields displayed in the drawer view$")
	public void ValidateVehiclePurchaseDateVINField()
	{
	vehiclespagefunctions.ValidateVehiclePurchaseDateField();
	vehiclespagefunctions.ValidateVehicleVINField();
	}
	
	@And("^I see the the Next button disabled on the Add Vehicle drawer view$")
	public void ValidateVehicleNextButtonDisabled()
	{
	vehiclespagefunctions.ValidateVehicleNextButton(StaticValue.IsDisabled);	
	}
	
	@And("^I see the the Next button enabled on the Add Vehicle drawer view$")
	public void ValidateVehicleNextButtonEnabled()
	{
	vehiclespagefunctions.ValidateVehicleNextButton(StaticValue.IsEnabled);	
	}
	
	@And("^I can see collapse arrow is displayed on the Add Vehicle action block")
	public void ValidateCollapseArrowLink()
	{
		vehiclespagefunctions.ValidateCollapseArrowLink();
	}
	
	@When("^I am  entering the Purchase date in Date field")
	public void EnterPurchaseDate()
	{
		VehiclePurchaseDate=GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(),StaticValue.VehiclePurchaseDate);
		vehiclespagefunctions.EnterPurchaseDate(VehiclePurchaseDate);
	}
	
	@Then("^I should see the inline error message to enter a valid date")
	public void ValidateInvalidPurchaseDate()
	{
		VehiclePurchaseDate=GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(),StaticValue.VehiclePurchaseDate);
		vehiclespagefunctions.ValidatePurchaseDate(VehiclePurchaseDate,StaticValue.InvalidPurchaseDate);
	}
	
	@Then("^I should see the inline error message for entering the future date")
	public void ValidateFuturePurchaseDate()
	{
		VehiclePurchaseDate=GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(),StaticValue.VehiclePurchaseDate);
		vehiclespagefunctions.ValidatePurchaseDate(VehiclePurchaseDate,StaticValue.FuturePurchaseDate);
	}
	
	@Then ("^I can enter the date only in MMDDYYYY format$")
	public void ValidatePurchaseDate()
	{
		VehiclePurchaseDate=GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(),StaticValue.VehiclePurchaseDate);
		vehiclespagefunctions.ValidatePurchaseDate(VehiclePurchaseDate,StaticValue.ValidPuchaseDate);
	}
	
	@When("^I can enter only maximum of 17 characters in the VIN field$")
	public void EnterVIN()
	{
		VehicleVIN=GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(),StaticValue.VehicleVIN);
		vehiclespagefunctions.EnterVIN(VehicleVIN);
	}
	
	@Then("^I enter VIN number in correct format")
	public void ValidateVIN()
	{
		vehiclespagefunctions.ValidateVINNumber(StaticValue.ValidVIN);
	}
	
	@Then("^I click of next in the add vehicle drawer view, I see an inline error message to enter a valid VIN number if it is not valid$")
	public void ValidateInvalidVIN()
	{
		vehiclespagefunctions.ValidateVINNumber(StaticValue.InvalidVIN);
	}
	
	
	@Then("^I see the pre populated VIN and purchase Date")
	public void ValidateVINDatePrepopulated()
	{
		vehiclespagefunctions.ValidateVINDatePrepopulated();
	}


	@And("^I see an inline error message if PAS VIN Validation Fails")
	public void ValidatePASVINFailed() 
	{
		vehiclespagefunctions.ValidateVINService();		
	}
	
	@And("^I click the Next button on the Add Vehicle drawer view")
	public void ClickVehicleNextButton() 
	{
		vehiclespagefunctions.ClickAddVehicleNextButton();
	}
	
}