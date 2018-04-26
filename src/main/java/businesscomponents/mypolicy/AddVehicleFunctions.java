package businesscomponents.mypolicy;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import services.rest.apiclients.ValidatePendingEndorsementVehicle;
import services.rest.apiclients.ValidateVIN;
import services.rest.apiclients.ViewAssignmentsGet;
import services.rest.pojo.validateVehiclesList.validateVehiclesResponse.ValidateVehiclesResponse;
import services.rest.pojo.validateVehiclesList.validateVehiclesResponse.Vehicle;
import stepDefinitions.common.MasterStepDefs;
import webelementrepository.MyPolicy.AddVehiclePage;
import webelementrepository.MyPolicy.MakePolicyChangesPage;
import MyPolicyContentSpec.VehiclesPageContent;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.StaticValue;
import com.Cucumber.supportLibraries.Util;
import com.Cucumber.supportLibraries.Webaction;
import com.Cucumber.supportLibraries.custom.ReadCSVFileFunctions;

public class AddVehicleFunctions extends MasterStepDefs {
	Webaction action = new Webaction();
	AddVehiclePage addvehiclepage = new AddVehiclePage();
	ValidateVIN validateVIN = new ValidateVIN();
	MakePolicyChangesFunctions makepolicychangesfunctions = new MakePolicyChangesFunctions();
	VehiclesPageContent vehiclespagecontent = new VehiclesPageContent();
	MakePolicyChangesPage makepolicychangespage = new MakePolicyChangesPage();
	GeneralFunctions GenFns = new GeneralFunctions();
	ReadCSVFileFunctions readCSVfilefunctions = new ReadCSVFileFunctions();
	ValidatePendingEndorsementVehicle validatependingendorsementvehicle = new ValidatePendingEndorsementVehicle();
	ViewAssignmentsGet viewAssignmentsGet = new ViewAssignmentsGet();
	public String VehicleVIN = StaticValue.Empty;
	public String VehicleOwnerShipType = StaticValue.Empty;
	public String VehicleUsageTypeAsBusiness = StaticValue.Empty;
	public String VehicleLocatedAtYourResidence = StaticValue.Empty;
	public String VehicleSalvaged = StaticValue.Empty;
	public String AllRegisteredOwnersInPolicy = StaticValue.Empty;
	public String AntiTheftDevice = StaticValue.Empty;
	public String AddRemoveAdditionalVehicle = StaticValue.Empty;
	public String csvData = StaticValue.Empty;
	public String policynumber = StaticValue.Empty;
	public static int totalTransactions;
	public static HashMap<String, Integer> VehicleDataMap = new HashMap<String, Integer>();

	public String Year;
	public String Model;
	public String vehoid;
	private String VehModelYear;
	private String Vehmanufacturer;
	private String Vehseries;
	private String Vehmodel;
	private String VehbodyStyle;
	private String VehIdentificationNo;
	private String VehvehicleStatus;
	public String Ownership;
	public String Usage;
	public String Salvaged;
	public String GaragingDifferent;
	public String AntiTheft;
	public String RegisteredOwner;

	String VINUI;
	String Vehicledetails;

	public String VehicleOifForDriverAssignements = StaticValue.Empty;

	WebDriver driver = DriverManager.getWebDriver();

	public void ValidateAddVehicleActionBlockTitle() {
		// action.waitToGetElementFluent(addvehiclepage.AddVehicleActionBlockTitle,
		// StaticValue.AddvehicleActionBlockTitle);
		action.waitForElement(addvehiclepage.AddVehicleActionBlockTitle, StaticValue.AddvehicleActionBlockTitle);
		action.focusonElementTakeScreenshot(addvehiclepage.AddVehicleActionBlockTitle);
		ValidateVehicleMakeModelYearFromAddVehicleActionBlockTitle();
	}

	// function to validate the Vehicle make model and year from Action block title
	public void ValidateVehicleMakeModelYearFromAddVehicleActionBlockTitle() {
		String AddVehicleActionBlockTitleUI = StaticValue.Empty;
		String[] SplitAddVehicleActionBlockTitleUI = null;
		AddVehicleActionBlockTitleUI = action.GetText(addvehiclepage.AddVehicleActionBlockTitle);
		AddVehicleActionBlockTitleUI = AddVehicleActionBlockTitleUI.trim();
		SplitAddVehicleActionBlockTitleUI = AddVehicleActionBlockTitleUI.split(" ");
		String VehicleYearAsString = new Integer(validateVIN.vehicleYEAR).toString();
		GenFns.chkTheContent(SplitAddVehicleActionBlockTitleUI[0], VehicleYearAsString,
				StaticValue.AddvehicleActionBlockTitleVehicleYear);
		GenFns.chkTheContent(SplitAddVehicleActionBlockTitleUI[1], validateVIN.vehicleMAKE,
				StaticValue.AddvehicleActionBlockTitleVehicleMake);
		GenFns.chkTheContent(SplitAddVehicleActionBlockTitleUI[2], validateVIN.vehicleMODEL,
				StaticValue.AddvehicleActionBlockTitleVehicleModel);
	}

	// function to validate the Add Vehicle Action Block Sub Title
	public void ValidateAddVehicleActionBlockSubTitle() {
		action.waitForElement(addvehiclepage.AddVehicleActionBlockSubTitle,
				StaticValue.VehiclePageAddvehicleactionblock);
		String AddVehicleActionBlockSubTitleUI = StaticValue.Empty;
		AddVehicleActionBlockSubTitleUI = action.GetText(addvehiclepage.AddVehicleActionBlockSubTitle);
		//action.focusonElementTakeScreenshot(addvehiclepage.AddVehicleActionBlockSubTitle);
		GenFns.chkTheContent(AddVehicleActionBlockSubTitleUI, vehiclespagecontent.AddVehicleActionBlockSubTitle,
				StaticValue.AddvehicleActionBlockSubTitle);

	}

	// function to validate the Add Vehicle Action Block Collapse button changed to
	// Cancel
	public void ValidateAddVehicleBlockCollapseButtonChangedToCancel() {
		if (action.IsObjectPresentWithoutWait(makepolicychangespage.AddVehicleCollapseArrow,
				StaticValue.AddVehicleCollapseArrowLink)) {
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			fail("<<<<  " + StaticValue.AddVehicleCollapseArrowLink
					+ " is available. And this is not as expected >>>>");
		}
		GenFns.ChkUILabelAndValueIsAsExpected(addvehiclepage.AddVehicleCancelButtonVisible,
				vehiclespagecontent.AddVehicleActionBlockCancel, StaticValue.AddvehicleActionBlockCancelButton);

	}

	// function to validate the Add Vehicle back button displayed
	public void ValidateAddVehicleBackButtonVisible() {
		if (!action.IsObjectPresentWithoutWait(addvehiclepage.AddVehicleBackButtonVisible,
				StaticValue.VehiclePageBACKButton)) {
			if (!action.isDisplayed(addvehiclepage.AddVehicleBackButtonVisible, StaticValue.VehiclePageBACKButton)) {
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				fail("<<<<  " + StaticValue.VehiclePageBACKButton
						+ " is not displayed. And this is not as expected >>>>");
			} else {
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				fail("<<<<  " + StaticValue.VehiclePageBACKButton
						+ " is not available. And this is not as expected >>>>");
			}
		}
		//action.focusonElementTakeScreenshot(addvehiclepage.AddVehicleBackButtonVisible);
		String backButtonCaption = StaticValue.Empty;
		backButtonCaption = action.GetAttribute(addvehiclepage.AddVehicleBackButtonVisible, "value");
		GenFns.chkTheContent(backButtonCaption, vehiclespagecontent.AddVehicleActionBlockBACK,
				StaticValue.VehiclePageBACKButton);

	}

	// function to validate the Add Vehicle Action Block NEXT button not displayed
	public void ValidateAddVehicleActionBlockNextButtonNotVisible() {
		if (action.IsObjectPresentWithoutWait(makepolicychangespage.AddVehiclePageNEXTButton,
				StaticValue.VehiclePageNEXTButton)) {
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			fail("<<<<  " + StaticValue.VehiclePageNEXTButton + " is displayed. And this is not as expected >>>>");
		}
	}

	// function to validate the Add Vehicle Action Block Vehicle MAKE MODEL
	// YEAR/Desc/ CANCEL button Visible/NEXT button not displayed
	public void ValidateAddVehicleActionBlock() {
		ValidateAddVehicleActionBlockTitle();
		ValidateAddVehicleActionBlockSubTitle();
		ValidateAddVehicleBlockCollapseButtonChangedToCancel();
		ValidateAddVehicleBackButtonVisible();
		ValidateAddVehicleActionBlockNextButtonNotVisible();
	}

	public void ValidateVehicleOwnership() {
		ValidateVehicleOwnershipQuestion();
		ValidateVehicleOwnershipOptions();
		ValidateAddVehicleActionBlock();

	}

	// function to validate the OwnershipQuestion Text
	public void ValidateVehicleOwnershipQuestion() {
		//action.focusonElementTakeScreenshot(addvehiclepage.AddVehicleDrawerViewText);
		currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		GenFns.ChkUILabelAndValueIsAsExpected(addvehiclepage.AddVehicleDrawerViewText, "My ", validateVIN.vehicleMAKE,
				" is:", StaticValue.AddvehicleActionBlockOwnershipTitle);
		ValidateAddVehicleActionBlock();

	}

	// function to validate the OwnershipQuestion Options Text
	public void ValidateVehicleOwnershipOptions() {

		GenFns.ChkUILabelAndValueIsAsExpected(addvehiclepage.AddVehicleOwnershipTypeQuestionOptionsOwned,
				vehiclespagecontent.AddVehicleownerShipTypeOwned, StaticValue.AddvehicleActionBlockOwnershipOwned);

		GenFns.ChkUILabelAndValueIsAsExpected(addvehiclepage.AddVehicleOwnershipTypeQuestionOptionsFinanced,
				vehiclespagecontent.AddVehicleownerShipTypeFinanced,
				StaticValue.AddvehicleActionBlockOwnershipFinanced);

		GenFns.ChkUILabelAndValueIsAsExpected(addvehiclepage.AddVehicleOwnershipTypeQuestionOptionsLeased,
				vehiclespagecontent.AddVehicleownerShipTypeLeased, StaticValue.AddvehicleActionBlockOwnershipLeased);
		
	}

	// function to Click the given OwnershipTypeQuestion Options Text
	public void ClickTheGivenVehicleOwnershipType() {
		// VehicleOwnerShipType =
		// readCSVfilefunctions.SingleVehicleData.get(StaticValue.VehicleOwnerShipType);
		VehicleOwnerShipType = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(),
				StaticValue.VehicleOwnerShipType);
		switch (VehicleOwnerShipType) {
		case "OWNED":
			action.isEnabled(addvehiclepage.AddVehicleOwnershipTypeQuestionOptionsOwned,
					StaticValue.AddvehicleActionBlockOwnershipOwned);
			action.ClickJSElement(addvehiclepage.AddVehicleOwnershipTypeQuestionOptionsOwned,
					StaticValue.AddvehicleActionBlockOwnershipOwned);
			//currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			break;
		case "FINANCED":
			action.isEnabled(addvehiclepage.AddVehicleOwnershipTypeQuestionOptionsFinanced,
					StaticValue.AddvehicleActionBlockOwnershipFinanced);
			action.ClickJSElement(addvehiclepage.AddVehicleOwnershipTypeQuestionOptionsFinanced,
					StaticValue.AddvehicleActionBlockOwnershipFinanced);
			//currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			break;
		case "LEASED":
			action.isEnabled(addvehiclepage.AddVehicleOwnershipTypeQuestionOptionsLeased,
					StaticValue.AddvehicleActionBlockOwnershipLeased);
			action.ClickJSElement(addvehiclepage.AddVehicleOwnershipTypeQuestionOptionsLeased,
					StaticValue.AddvehicleActionBlockOwnershipLeased);
			//currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			break;
		default:
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			fail("<<<<  '" + VehicleOwnerShipType + "' is not a valid Vehicle Ownership Type. >>>>");
			break;

		}

	}

	public void ValidateVehicleUsageTypequestion() {
		GenFns.ChkUILabelAndValueIsAsExpected(addvehiclepage.AddVehicleDrawerViewText,
				vehiclespagecontent.AddVehicleUsageQuestion, StaticValue.AddvehicleUsageTypeQuestion);
		currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		ValidateAddVehicleActionBlock();
	}

	public void ValidateYesNoOptions() {

		// checking YES option
		GenFns.ChkUILabelAndValueIsAsExpected(addvehiclepage.AddVehicleDrawerViewYesText,
				vehiclespagecontent.AddVehicleDrawerViewYES, StaticValue.AddvehicleDrawerViewYesButton);
		// checking NO option
		GenFns.ChkUILabelAndValueIsAsExpected(addvehiclepage.AddVehicleDrawerViewNoText,
				vehiclespagecontent.AddVehicleDrawerViewNO, StaticValue.AddvehicleDrawerViewNoButton);
		//currentScenario.embed(Util.takeScreenshot(driver), "image/png");

	}

	public void ClickTheGivenYesNoOption(String csvfieldName, String QuestionName) {
		csvData = StaticValue.Empty;
		csvData = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), csvfieldName);
		switch (csvData) {
		case StaticValue.YES:
			action.isEnabled(addvehiclepage.AddVehicleDrawerViewYesText, StaticValue.AddvehicleDrawerViewYesButton);
			action.ClickJSElement(addvehiclepage.AddVehicleDrawerViewYesText,
					StaticValue.AddvehicleDrawerViewYesButton);
			break;
		case StaticValue.NO:
			action.isEnabled(addvehiclepage.AddVehicleDrawerViewNoText, StaticValue.AddvehicleDrawerViewNoButton);
			action.ClickJSElement(addvehiclepage.AddVehicleDrawerViewNoText, StaticValue.AddvehicleDrawerViewNoButton);
			break;
		default:
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			fail("<<<<  '" + csvData + "' is not a valid option to select the " + QuestionName + " . >>>>");
			break;

		}

	}

	public void ValidateVehicleLocatedquestion() {
		GenFns.ChkUILabelAndValueIsAsExpected(addvehiclepage.AddVehicleDrawerViewText, "Is your ",
				validateVIN.vehicleMAKE, " located at your residence?",
				StaticValue.AddvehicleLocatedAtResidenceQuestion);
		currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		ValidateAddVehicleActionBlock();
	}

	public void ValidateVehicleSalvagedquestion() {
		GenFns.ChkUILabelAndValueIsAsExpected(addvehiclepage.AddVehicleDrawerViewText, "Has your ",
				validateVIN.vehicleMAKE, " been salvaged?", StaticValue.AddvehicleSalvageQuestion);
		currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		ValidateAddVehicleActionBlock();
	}

	public void ValidateAllRegisteredOwnersquestion() {
		GenFns.ChkUILabelAndValueIsAsExpected(addvehiclepage.AddVehicleDrawerViewText,
				"Are all registered owners of your ", validateVIN.vehicleMAKE,
				" on this policy? Registered owners are listed on your car registration.",
				StaticValue.AddvehicleAllRegisteredOwnersQuestion);
		currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		ValidateAddVehicleActionBlock();
	}

	public void ValidateAntiTheftquestion() {
		GenFns.ChkUILabelAndValueIsAsExpected(addvehiclepage.AddVehicleDrawerViewText, "Does your ",
				validateVIN.vehicleMAKE,
				" currently have an active vehicle recovery system, such as GM's OnStar, LoJack, or Teletrac?",
				StaticValue.AddvehicleAntiTheftDeviceQuestion);
		currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		ValidateAddVehicleActionBlock();

	}

	public void ValidateAddAdditionalvehiclesquestion() {
		GenFns.ChkUILabelAndValueIsAsExpected(addvehiclepage.AddVehicleDrawerViewText,
				vehiclespagecontent.AddVehicleADDREMOVEVehicleQuestion, StaticValue.AddvehicleADDREMOVEVehicleQuestion);
		currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		ValidateAddVehicleActionBlock();

	}

	// Functions to validate the Vehicle question summary page
	public void ValidateVehicleSummaryScreenPage() {
		ValidateAddVehicleActionBlockAndVin();
		ValidateVehicleQuestionSummaryOwnershipType();
		ValidateVehicleQuestionSummaryUsageType();
		ValidateVehicleQuestionSummaryLocated();
		ValidateVehicleQuestionSummarySalvaged();
		ValidateVehicleQuestionSummaryAllRegisteredOwners();
		action.focusonElementTakeScreenshot(addvehiclepage.AddVehicleQuestionSummaryAddContinueButton);
		ValidateVehicleQuestionSummaryAntiTheft();
		ValidateVehicleQuestionSummaryAddContinueButton();

	}

	// function to validate the Add Vehicle Action Block Vehicle MAKE MODEL YEAR/VIN
	// as Desc/ CANCEL button Visible/NEXT button not displayed
	public void ValidateAddVehicleActionBlockAndVin() {
		ValidateAddVehicleActionBlockTitleInSummarySection();
		ValidateVinINAddVehicleActionBlockSubTitleInSummaryQuestion();
		ValidateAddVehicleBackButtonVisibleInSummarypage();
		ValidateAddVehicleBlockCollapseCancelInSummarypage();
		ValidateAddVehicleActionBlockNextButtonNotVisible();
	}

	// // function to validate the Add Vehicle Action Block Title in Summary
	// // Question
	// public void ValidateAddVehicleActionBlockTitleInSummarySection() {
	// VehicleVIN = StaticValue.Empty;
	// VehicleVIN =
	// readCSVfilefunctions.SingleVehicleData.get(StaticValue.VehicleVIN);
	// GenFns.ChkUILabelAndValueIsAsExpected(addvehiclepage.AddVehicleSummaryPageActionBlockTitle,
	// VehicleVIN,
	// StaticValue.AddvehicleQuestionSummaryTitle);
	//
	// }

	// function to validate the Add Vehicle Action Block Title in Summary
	// Question
	public void ValidateAddVehicleActionBlockTitleInSummarySection() {
		String AddVehicleActionBlockTitleUI = StaticValue.Empty;
		String[] SplitAddVehicleActionBlockTitleUI = null;
		AddVehicleActionBlockTitleUI = action.GetText(addvehiclepage.AddVehicleSummaryPageActionBlockTitle);
		AddVehicleActionBlockTitleUI = AddVehicleActionBlockTitleUI.trim();
		SplitAddVehicleActionBlockTitleUI = AddVehicleActionBlockTitleUI.split(" ");
		String VehicleYearAsString = new Integer(validateVIN.vehicleYEAR).toString();
		GenFns.chkTheContent(SplitAddVehicleActionBlockTitleUI[0], VehicleYearAsString,
				StaticValue.AddvehicleActionBlockTitle);
		GenFns.chkTheContent(SplitAddVehicleActionBlockTitleUI[1], validateVIN.vehicleMAKE,
				StaticValue.AddvehicleActionBlockTitle);
		GenFns.chkTheContent(SplitAddVehicleActionBlockTitleUI[2], validateVIN.vehicleMODEL,
				StaticValue.AddvehicleActionBlockTitle);
		currentScenario.embed(Util.takeScreenshot(driver), "image/png");

	}

	// function to validate the Add Vehicle Action Block Sub Title in Summary
	// Question
	public void ValidateVinINAddVehicleActionBlockSubTitleInSummaryQuestion() {
		VehicleVIN = StaticValue.Empty;
		VehicleVIN = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), StaticValue.VehicleVIN);
		GenFns.ChkUILabelAndValueIsAsExpected(addvehiclepage.AddVehicleSummaryPageActionBlockSubTitle, "VIN: ",
				VehicleVIN, "", StaticValue.AddvehicleQuestionSummarySubTitle);

	}

	// function to validate the Add Vehicle Action Block has Cancel button in
	// summary page
	public void ValidateAddVehicleBlockCollapseCancelInSummarypage() {
		GenFns.ChkUILabelAndValueIsAsExpected(addvehiclepage.AddVehicleSummarypageCancelButtonVisible,
				vehiclespagecontent.AddVehicleActionBlockCancel, StaticValue.AddvehicleQuestionSummaryCancelButton);

	}

	// function to validate the Add Vehicle back button displayed
	public void ValidateAddVehicleBackButtonVisibleInSummarypage() {
		if (!action.IsObjectPresentWithoutWait(addvehiclepage.AddVehicleSummarypageBackButtonVisible,
				StaticValue.AddvehicleQuestionSummaryBackButton)) {
			if (!action.isDisplayed(addvehiclepage.AddVehicleSummarypageBackButtonVisible,
					StaticValue.AddvehicleQuestionSummaryBackButton)) {
				fail("<<<<  " + StaticValue.AddvehicleQuestionSummaryBackButton
						+ " is not displayed. And this is not as expected >>>>");
			} else {
				fail("<<<<  " + StaticValue.AddvehicleQuestionSummaryBackButton
						+ " is not available. And this is not as expected >>>>");
			}
		}
		String backButtonCaption = StaticValue.Empty;
		backButtonCaption = action.GetAttribute(addvehiclepage.AddVehicleSummarypageBackButtonVisible, "value");
		GenFns.chkTheContent(backButtonCaption, vehiclespagecontent.AddVehicleActionBlockBACK,
				StaticValue.AddvehicleQuestionSummaryBackButton);
	}

	public void ValidateVehicleQuestionSummaryOwnershipType() {
		GenFns.ChkUILabelAndValueIsAsExpected(addvehiclepage.AddVehicleQuestionSummaryOwnershipTypeLabel,
				vehiclespagecontent.AddVehicleQuestionSummaryOwnerShipTypeLabel,
				StaticValue.AddvehicleQuestionSummaryOwnershipType);

		VehicleOwnerShipType = StaticValue.Empty;
		// VehicleOwnerShipType =
		// readCSVfilefunctions.SingleVehicleData.get(StaticValue.VehicleOwnerShipType);
		VehicleOwnerShipType = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(),
				StaticValue.VehicleOwnerShipType);
		GenFns.ChkUILabelAndValueIsAsExpected(addvehiclepage.AddVehicleQuestionSummaryOwnershipTypeValue,
				VehicleOwnerShipType, StaticValue.AddvehicleQuestionSummaryOwnershipType);
	}

	public void ValidateVehicleQuestionSummaryUsageType() {
		GenFns.ChkUILabelAndValueIsAsExpected(addvehiclepage.AddVehicleQuestionSummaryUsageTypeLabel,
				vehiclespagecontent.AddVehicleQuestionSummaryUsageTypeLabel,
				StaticValue.AddvehicleQuestionSummaryUsuageType);

		VehicleUsageTypeAsBusiness = StaticValue.Empty;
		// VehicleUsageTypeAsBusiness =
		// readCSVfilefunctions.SingleVehicleData.get(StaticValue.VehicleUsageTypeAsBusiness);
		VehicleUsageTypeAsBusiness = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(),
				StaticValue.VehicleUsageTypeAsBusiness);
		GenFns.ChkUILabelAndValueIsAsExpected(addvehiclepage.AddVehicleQuestionSummaryUsageTypeValue,
				VehicleUsageTypeAsBusiness, StaticValue.AddvehicleQuestionSummaryUsuageType);

	}

	public void ValidateVehicleQuestionSummaryLocated() {
		GenFns.ChkUILabelAndValueIsAsExpected(addvehiclepage.AddVehicleQuestionSummaryLocatedLabel,
				vehiclespagecontent.AddVehicleQuestionSummaryLocatedLabel,
				StaticValue.AddvehicleQuestionSummaryLocated);

		VehicleLocatedAtYourResidence = "N/A";
		GenFns.ChkUILabelAndValueIsAsExpected(addvehiclepage.AddVehicleQuestionSummaryLocatedValue,
				VehicleLocatedAtYourResidence, StaticValue.AddvehicleQuestionSummaryLocated);

	}

	public void ValidateVehicleQuestionSummarySalvaged() {
		GenFns.ChkUILabelAndValueIsAsExpected(addvehiclepage.AddVehicleQuestionSummarySalvagedLabel,
				vehiclespagecontent.AddVehicleQuestionSummarySalvagedLabel,
				StaticValue.AddvehicleQuestionSummarySalvaged);

		VehicleSalvaged = StaticValue.Empty;
		VehicleSalvaged = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(),
				StaticValue.VehicleSalvaged);
		GenFns.ChkUILabelAndValueIsAsExpected(addvehiclepage.AddVehicleQuestionSummarySalvagedValue, VehicleSalvaged,
				StaticValue.AddvehicleQuestionSummarySalvaged);

	}

	public void ValidateVehicleQuestionSummaryAllRegisteredOwners() {
		GenFns.ChkUILabelAndValueIsAsExpected(addvehiclepage.AddVehicleQuestionSummaryAllRegisteredOwnersLabel,
				vehiclespagecontent.AddVehicleQuestionSummaryAllOwnersLabel,
				StaticValue.AddvehicleQuestionSummaryAllRegisteredOwners);

		AllRegisteredOwnersInPolicy = StaticValue.Empty;
		AllRegisteredOwnersInPolicy = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(),
				StaticValue.AllRegisteredOwnersInPolicy);
		GenFns.ChkUILabelAndValueIsAsExpected(addvehiclepage.AddVehicleQuestionSummaryAllRegisteredOwnersValue,
				AllRegisteredOwnersInPolicy, StaticValue.AddvehicleQuestionSummaryAllRegisteredOwners);

	}

	public void ValidateVehicleQuestionSummaryAntiTheft() {
		GenFns.ChkUILabelAndValueIsAsExpected(addvehiclepage.AddVehicleQuestionSummaryAntiTheftLabel,
				vehiclespagecontent.AddVehicleQuestionSummaryAntiTheftLabel,
				StaticValue.AddvehicleQuestionSummaryAntiTheft);

		AntiTheftDevice = StaticValue.Empty;
		// AntiTheftDevice =
		// readCSVfilefunctions.SingleVehicleData.get(StaticValue.AntiTheftDevice);
		AntiTheftDevice = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(),
				StaticValue.AntiTheftDevice);
		GenFns.ChkUILabelAndValueIsAsExpected(addvehiclepage.AddVehicleQuestionSummaryAntiTheftValue, AntiTheftDevice,
				StaticValue.AddvehicleQuestionSummaryAntiTheft);

	}

	public void ValidateVehicleQuestionSummaryAddContinueButton() {
		// AddRemoveAdditionalVehicle = StaticValue.Empty;
		// AddRemoveAdditionalVehicle =
		// readCSVfilefunctions.SingleVehicleData.get(StaticValue.AddRemoveAdditionalVehicle);

		if (DriverManager.properties.getProperty("MobileExecutionType").equalsIgnoreCase("Desktop_Responsive")) {
			GenFns.ChkUILabelAndValueIsAsExpected(addvehiclepage.AddVehicleQuestionSummaryAddContinueButton,
					vehiclespagecontent.AddVehicleQuestionSummaryAddLabel,
					StaticValue.AddvehicleQuestionSummaryAddButton);
		} else {
			GenFns.ChkUILabelAndValueIsAsExpected(addvehiclepage.AddVehicleQuestionSummaryAddContinueButton,
					vehiclespagecontent.AddVehicleQuestionSummaryAddContinueLabel,
					StaticValue.AddvehicleQuestionSummaryAddContinueButton);
		}
	}

	public void ClickVehcicleQuestionSummaryAddContinuebutton() {
		action.ClickJSElement(addvehiclepage.AddVehicleQuestionSummaryAddContinueButton,
				StaticValue.AddvehicleQuestionSummaryAddContinueButton);
	}

	public void ValidateAllVehicleListed() {
		policynumber = StaticValue.Empty;
		policynumber = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), StaticValue.PolicyNumber);
		makepolicychangesfunctions.ValidateTheVehcileListAsPerSpec(policynumber);
		ValidateVehicleListFromPAS(policynumber);

	}

	public void ValidateContinuebutton() {
		GenFns.ChkUILabelAndValueIsAsExpected(addvehiclepage.AddVehicleContinueButton,
				vehiclespagecontent.AddVehicleContinueLabel, StaticValue.AddvehicleContinueButton);
		action.focusonElementTakeScreenshot(addvehiclepage.AddVehicleContinueButton);
	}

	// Function to validate the Pending endorsement Vehicle List from PAS
	public void ValidateVehicleListFromPAS(String PolicyNumber) {
		try {
			ValidateVehiclesResponse pendingserviceResponse;
			pendingserviceResponse = validatependingendorsementvehicle.rest_getResponse(PolicyNumber);
			System.out.println(" <<<< PAS Pending Endorsement Vehicle Service call is sucess When ADD/Remove Vehicle option is YES >>>> ");
			ValidatePASServiceResponseFromUI(pendingserviceResponse);
		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException | org.apache.http.ParseException
				| URISyntaxException | IOException e) {
			e.printStackTrace();
		}

	}

	public void ValidatePASServiceResponseFromUI(ValidateVehiclesResponse pendingServiceResponse) {
		List<Vehicle> resDetails;

		resDetails = pendingServiceResponse.getVehicles();
		System.out.println("resDetails:" + resDetails);
		totalTransactions = pendingServiceResponse.getVehicles().size();
		String VinForTheVehicleBeingAdded = StaticValue.Empty;
		String AddRemoveAdditonalVehicleOptionFromInput = StaticValue.Empty;
		VinForTheVehicleBeingAdded = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(),
				StaticValue.VehicleVIN);
		AddRemoveAdditonalVehicleOptionFromInput = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(),
				StaticValue.AddRemoveAdditionalVehicle);
		VehicleDataMap.put(StaticValue.totalTransactions, totalTransactions);
		for (int i = 0; i < resDetails.size(); i++) {
			vehoid = StaticValue.Empty;
			VehModelYear = StaticValue.Empty;
			Vehmanufacturer = StaticValue.Empty;
			Vehseries = StaticValue.Empty;
			Vehmodel = StaticValue.Empty;
			VehbodyStyle = StaticValue.Empty;
			VehIdentificationNo = StaticValue.Empty;
			VehvehicleStatus = StaticValue.Empty;
			Ownership = StaticValue.Empty;
			Usage = StaticValue.Empty;
			Salvaged = StaticValue.Empty;
			GaragingDifferent = StaticValue.Empty;
			AntiTheft = StaticValue.Empty;
			RegisteredOwner = StaticValue.Empty;

			vehoid = resDetails.get(i).getOid();
			VehModelYear = resDetails.get(i).getModelYear();
			Vehmanufacturer = resDetails.get(i).getManufacturer();
			Vehseries = resDetails.get(i).getSeries();
			Vehmodel = resDetails.get(i).getModel();
			VehbodyStyle = resDetails.get(i).getBodyStyle();
			VehIdentificationNo = resDetails.get(i).getVehIdentificationNo();
			VehvehicleStatus = resDetails.get(i).getVehicleStatus();
			Ownership = resDetails.get(i).getOwnership();
			Usage = resDetails.get(i).getUsage();
			Salvaged = resDetails.get(i).getSalvaged();
			GaragingDifferent = resDetails.get(i).getGaragingDifferent();
			AntiTheft = resDetails.get(i).getAntiTheft();
			RegisteredOwner = resDetails.get(i).getRegisteredOwner();

			int iterator = StaticValue.zero;
			iterator = i + 1;
			String ActualVinWebElm = StaticValue.Empty;
			String ActualDriverNameDescWebElm = StaticValue.Empty;
			String DriverDescFromService = StaticValue.Empty;
			String DriverDescFromUI = StaticValue.Empty;

			// VIN validation
			ActualVinWebElm = makepolicychangespage.VechicleBlockGroup + "[" + iterator + "]"
					+ makepolicychangespage.VechicleVIN;
			VINUI = action.GetText(By.xpath(String.format(ActualVinWebElm)));
			VINUI = VINUI.trim();
			String[] vinUISplit = VINUI.split(" ");
			GenFns.chkTheContent(vinUISplit[0], vehiclespagecontent.VehiclesPageVINCaption,
					StaticValue.VehiclePageVINCaption);
			GenFns.chkTheContent(vinUISplit[1], VehIdentificationNo, StaticValue.VehiclePageVINNumber);

			// this code to check if the Vehicle being added has status pending
			if (VinForTheVehicleBeingAdded.equals(VehIdentificationNo)) {
				String ActualEndorsementPendingElm = StaticValue.Empty;
				ActualEndorsementPendingElm = makepolicychangespage.VechicleBlockGroup + "[" + iterator + "]"
						+ addvehiclepage.EndorsementPendingElm;
				GenFns.ChkUILabelAndValueIsAsExpected(By.xpath(String.format(ActualEndorsementPendingElm)),
						vehiclespagecontent.VehicleListPending, StaticValue.AddvehicleEndorsementPending);
				if (i != 0) {
					fail("<<<< Vehicle being added is displayed with pending tag. But not displayed on top  >>>>");
				}
				
				// Function to Validate the Interview Q&A Response From PAS
				ValidateInterviewQAResponseFromPAS();

			}

			// Model, Model year and Manufacturer validations
			ActualVinWebElm = StaticValue.Empty;
			ActualVinWebElm = makepolicychangespage.VechicleBlockGroup + "[" + iterator + "]"
					+ makepolicychangespage.VechicleModel;
			Vehicledetails = action.GetText(By.xpath(String.format(ActualVinWebElm)));
			String[] VehicledetailsSplit = Vehicledetails.split(" ");
			GenFns.chkTheContent(VehicledetailsSplit[0], VehModelYear, StaticValue.VehiclePageYear);
			GenFns.chkTheContent(VehicledetailsSplit[1], Vehmanufacturer, StaticValue.VehiclePageManufacturer);
			GenFns.chkTheContent(VehicledetailsSplit[2], Vehmodel, StaticValue.VehiclePageModel);
		}
		ValidateAddVehicleLink(totalTransactions);

	}

	// Function to Validate the AddVehicleHyperlinkText
	public void ValidateInterviewQAResponseFromPAS() {
		ValidatePASOwnerShipResponse();
		ValidatePASUsageResponse();
		ValidatePASsalvagedResponse();
		ValidatePASGaragingDifferentResponse();
		ValidatePASAntiTheftResponse();
		ValidatePASRegisteredOwnerResponse();

	}

	// Function to Validate the AddVehicleHyperlinkText
	public void ValidatePASOwnerShipResponse() {
		// String ownershipFromFile =
		// readCSVfilefunctions.SingleVehicleData.get(StaticValue.VehicleOwnerShipType);
		String ownershipFromFile = StaticValue.Empty;
		ownershipFromFile = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(),
				StaticValue.VehicleOwnerShipType);
		switch (ownershipFromFile) {
		case "OWNED":
			GenFns.chkTheContent(Ownership, "OWN", StaticValue.AddvehicleQuestionSummaryOwnershipTypePAS);
			break;
		case "LEASED":
			GenFns.chkTheContent(Ownership, "LSD", StaticValue.AddvehicleQuestionSummaryOwnershipTypePAS);
			break;
		case "FINANCED":
			GenFns.chkTheContent(Ownership, "FNC", StaticValue.AddvehicleQuestionSummaryOwnershipTypePAS);
			break;
		default:
			fail("<<<<  '" + ownershipFromFile + "' is not a valid option to select the "
					+ StaticValue.VehicleOwnerShipType + " . >>>>");
			break;

		}

	}

	// Function to Validate the AddVehicleHyperlinkText
	public void ValidatePASUsageResponse() {
		// String usuageFromFile =
		// readCSVfilefunctions.SingleVehicleData.get(StaticValue.VehicleUsageTypeAsBusiness);
		String usuageFromFile = StaticValue.Empty;
		usuageFromFile = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(),
				StaticValue.VehicleUsageTypeAsBusiness);
		switch (usuageFromFile) {
		case "NO":
			GenFns.chkTheContent(Usage, "Pleasure", StaticValue.AddvehicleQuestionSummaryUsuageTypePAS);
			break;
		case "YES":
			GenFns.chkTheContent(Usage, "Business", StaticValue.AddvehicleQuestionSummaryOwnershipTypePAS);
			break;
		default:
			fail("<<<<  '" + usuageFromFile + "' is not a valid option to select the "
					+ StaticValue.VehicleUsageTypeAsBusiness + " . >>>>");
			break;

		}

	}

	// Function to Validate the AddVehicleHyperlinkText
	public void ValidatePASsalvagedResponse() {
		// String salvageFromFile =
		// readCSVfilefunctions.SingleVehicleData.get(StaticValue.VehicleSalvaged);
		String salvageFromFile = StaticValue.Empty;
		salvageFromFile = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(),
				StaticValue.VehicleSalvaged);
		switch (salvageFromFile) {
		case "NO":
			GenFns.chkTheContent(Salvaged, "false", StaticValue.AddvehicleQuestionSummarySalvagedPAS);
			break;
		case "YES":
			GenFns.chkTheContent(Salvaged, "true", StaticValue.AddvehicleQuestionSummarySalvagedPAS);
			break;
		default:
			fail("<<<<  '" + salvageFromFile + "' is not a valid option to select the " + StaticValue.VehicleSalvaged
					+ " . >>>>");
			break;

		}
	}

	// Function to Validate the AddVehicleHyperlinkText
	public void ValidatePASGaragingDifferentResponse() {
		// String VehicleLocatedAtYourResidenceFromFile =
		// readCSVfilefunctions.SingleVehicleData.get(StaticValue.VehicleLocatedAtYourResidence);
		String VehicleLocatedAtYourResidenceFromFile = StaticValue.Empty;
		VehicleLocatedAtYourResidenceFromFile = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(),
				StaticValue.VehicleLocatedAtYourResidence);
		switch (VehicleLocatedAtYourResidenceFromFile) {
		case "NO":
			GenFns.chkTheContent(GaragingDifferent, "true", StaticValue.AddvehicleQuestionSummaryLocatedPAS);
			break;
		case "YES":
			GenFns.chkTheContent(GaragingDifferent, "false", StaticValue.AddvehicleQuestionSummaryLocatedPAS);
			break;
		default:
			fail("<<<<  '" + VehicleLocatedAtYourResidenceFromFile + "' is not a valid option to select the "
					+ StaticValue.VehicleLocatedAtYourResidence + " . >>>>");
			break;

		}
	}

	// Function to Validate the AddVehicleHyperlinkText
	public void ValidatePASAntiTheftResponse() {
		// String AntiTheftFromFile =
		// readCSVfilefunctions.SingleVehicleData.get(StaticValue.AntiTheftDevice);
		String AntiTheftFromFile = StaticValue.Empty;
		AntiTheftFromFile = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(),
				StaticValue.AntiTheftDevice);
		// GenFns.chkTheContentWithOutCaseSensitive(AntiTheft, AntiTheftFromFile,
		// StaticValue.AddvehicleQuestionSummaryAntiTheftPAS);
		switch (AntiTheftFromFile) {
		case "NO":
			GenFns.chkTheContent(AntiTheft, "NONE", StaticValue.AddvehicleQuestionSummaryAntiTheftPAS);
			break;
		case "YES":
			GenFns.chkTheContent(AntiTheft, "STD", StaticValue.AddvehicleQuestionSummaryAntiTheftPAS);
			break;
		default:
			fail("<<<<  '" + AntiTheftFromFile + "' is not a valid option to select the " + StaticValue.AntiTheftDevice
					+ " . >>>>");
			break;

		}

	}

	// Function to Validate the AddVehicleHyperlinkText
	public void ValidatePASRegisteredOwnerResponse() {
		// String RegisteredOwnerFromFile =
		// readCSVfilefunctions.SingleVehicleData.get(StaticValue.AllRegisteredOwnersInPolicy);
		String RegisteredOwnerFromFile = StaticValue.Empty;
		RegisteredOwnerFromFile = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(),
				StaticValue.AllRegisteredOwnersInPolicy);
		// GenFns.chkTheContentWithOutCaseSensitive(RegisteredOwner,
		// RegisteredOwnerFromFile,
		// StaticValue.AddvehicleQuestionSummaryAllRegisteredOwnersPAS);
		switch (RegisteredOwnerFromFile) {
		case "NO":
			GenFns.chkTheContent(RegisteredOwner, "false", StaticValue.AddvehicleQuestionSummaryAllRegisteredOwnersPAS);
			break;
		case "YES":
			GenFns.chkTheContent(RegisteredOwner, "true", StaticValue.AddvehicleQuestionSummaryAllRegisteredOwnersPAS);
			break;
		default:
			fail("<<<<  '" + RegisteredOwnerFromFile + "' is not a valid option to select the "
					+ StaticValue.AllRegisteredOwnersInPolicy + " . >>>>");
			break;

		}

	}

	// Function to Validate the AddVehicleHyperlinkText
	public void ValidateAddVehicleLink(int VehicleCountPAS) {
		int VechicleCountUI = StaticValue.zero;
		String AddVehicleDescription, AddVehicleCaption = StaticValue.Empty;
		VechicleCountUI = action.fnExistOfElm(makepolicychangespage.VehicleCount, 3);
		System.out.println("VechicleCountUI:" + VechicleCountUI + ":");
		if ((VehicleCountPAS <= StaticValue.MaximumVehicleDisplayCount) && (VehicleCountPAS == VechicleCountUI)
				&& !(VehicleCountPAS == StaticValue.zero)) {
			if ((VehicleCountPAS < StaticValue.MaximumVehicleDisplayCount)
					&& (action.IsObjectPresent(makepolicychangespage.AddVehicleHyperlink, "AddVehicleHyperlink"))) {
				System.out.println("Chk add vechicle text");

				AddVehicleDescription = action.GetText(makepolicychangespage.AddVehicleHyperlinktext);
				GenFns.chkTheContent(AddVehicleDescription, vehiclespagecontent.AddVehicleLinkText,
						StaticValue.VehiclePageAddVehicleDescription);

				AddVehicleCaption = action.GetText(makepolicychangespage.AddVehicleHyperlink);
				GenFns.chkTheContent(AddVehicleCaption, vehiclespagecontent.AddVehicleLinkCaption,
						StaticValue.VehiclePageAddVehicleCaption);

			}
			// Throws a fail statement while Policy have 7 Vehicle List 'Add Vehicle' Link
			// should not get displayed
			else if ((VehicleCountPAS == StaticValue.MaximumVehicleDisplayCount)
					&& (action.IsObjectPresent(makepolicychangespage.AddVehicleHyperlink, "AddVehicleHyperlink"))) {
				fail(" <<<< 'ADD VEHICLE' Link is displayed for the Policy though it reached the allowed maximum Vehicle Count "
						+ StaticValue.MaximumVehicleDisplayCount + "  >>>> ");
			}
		}

		// Throws a fail statement while Policy have Zero Vehicle List
		else if ((VehicleCountPAS == 0) && (VechicleCountUI == 0)) {
			fail(" <<<< PAS Pending Endorsement Vechicles Service is not added with any Vehicle. Issue in PAS service call >>>> ");
		}

		// Throws a fail statement while vehicles diaplyed in UI after PAS Vehicle List
		// Service with no vehicle
		else if ((VehicleCountPAS == 0) && (VechicleCountUI == 1)) {
			fail(" <<<< PAS Pending Endorsement Vechicles Service is not added with any Vehicle. However vehicles are displayed in My Policy Portal. Issue in MyPolicy portal itself >>>> ");
		}

		// Throws a fail statement while No vehicles in UI after success PAS Vehicle
		// List Service
		else if ((VehicleCountPAS == 1) && (VechicleCountUI == 0)) {
			fail(" <<<< PAS Pending Endorsement Vechicles Service is added with Vehicle. However some vehicles are not displayed in My Policy Portal. Issue in MyPolicy portal itself >>>> ");
		}

		// Throws a fail statement while Number of vehicles in UI is more than PAS
		// Vehicle List Service
		else if ((VechicleCountUI > VehicleCountPAS) && !(VehicleCountPAS > StaticValue.MaximumVehicleDisplayCount)) {
			fail(" <<<< PAS Pending Endorsement Vechicles Service is added with Vehicle. HoweverThere is a mismatch in the count of vehicles displayed in MyPolicy Portal. Issue in MyPolicy portal itself >>>> ");
		}
		// Throws a fail statement while Number of vehicles in UI is less than PAS
		// Vehicle List Service
		else if ((VechicleCountUI < VehicleCountPAS) && !(VehicleCountPAS > StaticValue.MaximumVehicleDisplayCount)) {
			fail(" <<<< PAS Pending Endorsement Vechicles Service is added with Vehicle. HoweverThere is a mismatch in the count of vehicles displayed in MyPolicy Portal. Issue in MyPolicy portal itself >>>> ");
		}
		// Throws a fail statement while Policy have more than 7 Vehicle List
		else {
			fail(" <<<< PAS Pending Endorsement Vechicles Service retrieves Vehicle List more than the allowed maximum Vehicle Count "
					+ StaticValue.MaximumVehicleDisplayCount + "  >>>> ");
		}

	}

}