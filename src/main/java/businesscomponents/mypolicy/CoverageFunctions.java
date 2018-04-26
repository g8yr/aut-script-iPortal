package businesscomponents.mypolicy;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

import org.apache.http.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import services.rest.apiclients.ValidatePendingEndorsementVehicle;
import services.rest.apiclients.ValidateVIN;
import services.rest.apiclients.ViewAssignmentsGet;
import services.rest.apiclients.ViewCoveragesGet;
import services.rest.apiclients.ViewDriversGet;
import services.rest.pojo.ViewCoveragesGetResponse.Coverage;
import services.rest.pojo.ViewCoveragesGetResponse.VehicleLevelCoverage;
import services.rest.pojo.ViewCoveragesGetResponse.ViewCoveragesGetResponse;
import services.rest.pojo.validateVehiclesList.validateVehiclesResponse.ValidateVehiclesResponse;
import services.rest.pojo.validateVehiclesList.validateVehiclesResponse.Vehicle;
import stepDefinitions.common.MasterStepDefs;
import webelementrepository.MyPolicy.AddVehiclePage;
import webelementrepository.MyPolicy.CoveragePage;
import webelementrepository.MyPolicy.DriverVehicleAssignmentPage;
import webelementrepository.MyPolicy.MakePolicyChangesPage;
import MyPolicyContentSpec.CoveragePageContent;
import MyPolicyContentSpec.DriverVehicleAssignmentPageContent;
import MyPolicyContentSpec.VehiclesPageContent;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.StaticValue;
import com.Cucumber.supportLibraries.Util;
import com.Cucumber.supportLibraries.Webaction;
import com.Cucumber.supportLibraries.custom.ReadCSVFileFunctions;

public class CoverageFunctions extends MasterStepDefs {
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
	DriverVehicleAssignmentPage driverVehicleAssignmentPage = new DriverVehicleAssignmentPage();
	DriverVehicleAssignmentPageContent driverVehicleAssignmentPageContent = new DriverVehicleAssignmentPageContent();
	ViewDriversGet viewDriversGet = new ViewDriversGet();
	AddVehicleFunctions addVehicleFunctions = new AddVehicleFunctions();

	DriverVehicleAssignmentFunctions driverVehicleAssignmentFns = new DriverVehicleAssignmentFunctions();
	CoveragePage coveragePage = new CoveragePage();
	CoveragePageContent coveragePageContent = new CoveragePageContent();
	ViewCoveragesGet viewCoveragesGet = new ViewCoveragesGet();
	public ViewCoveragesGetResponse viewCoveragesRespe;

	public String csvData = StaticValue.Empty;
	public String policynumber = StaticValue.Empty;
	public ValidateVehiclesResponse pendingserviceResponseForCoveragePageVerification;
	WebDriver driver = DriverManager.getWebDriver();

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
	public String CoverageCode = StaticValue.Empty;
	public String CoverageType = StaticValue.Empty;
	public String CovergareLimitDisplay = StaticValue.Empty;
	public boolean CoverageCustomerDisplayed;
	public String CoverageDescription = StaticValue.Empty;
	List<Coverage> individualVehicleCoverage;
	ValidateVehiclesResponse pendingserviceResponseForCoverageVerification;

	String VINUI;
	String Vehicledetails;

	public void VerifyCoveragePageLandedSuccesfully() {
		String policynumber = StaticValue.Empty;
		policynumber = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), StaticValue.PolicyNumber);
		action.HardDelay(3000);
		currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		makepolicychangesfunctions.ValidateThePageHeaderInEnorsementPage();
		makepolicychangesfunctions.ValidatePolicyNumberVehicleDetailsSection(policynumber);
		GenFns.ChkUILabelAndValueIsAsExpected(coveragePage.CoverageSummaryText, coveragePageContent.CoverageSummaryText,
				StaticValue.CoverageSummaryText);
		if (!action.isDisplayed(coveragePage.CoverageSummaryText, StaticValue.CoverageSummaryText)) {
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			fail(" <<<<  '" + StaticValue.CoverageSummaryText + " in the Coverage Page  is not displayed  >>>>");
		}

		GenFns.ChkUILabelAndValueIsAsExpected(coveragePage.CoverageSummarySubText,
				coveragePageContent.CoverageSummarySubText, StaticValue.CoverageSummarySubText);
		if (!action.isDisplayed(coveragePage.CoverageSummarySubText, StaticValue.CoverageSummarySubText)) {
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			fail(" <<<<  '" + StaticValue.CoverageSummarySubText + " in the Coverage Page  is not displayed  >>>>");
		}

	}

	public void validateVehicleLevelCoverageDisplayedAsPerPAS() {
		System.out.println("CoverageFunctions.validateVehicleLevelCoverageDisplayedAsPerPAS()");
		String policynumber = StaticValue.Empty;
		policynumber = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), StaticValue.PolicyNumber);
		try {
			pendingserviceResponseForCoverageVerification = validatependingendorsementvehicle
					.rest_getResponse(policynumber);
			System.out.println(
					" <<<< PAS Pending Endorsement Vehicle Service call is sucess to get the VehOID for Coverage >>>> ");
		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException | ParseException
				| URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		}

		System.out.println(
				"pendingserviceResponseForCoverageVerification:" + pendingserviceResponseForCoverageVerification);
		List<Vehicle> resDetails3;

		resDetails3 = pendingserviceResponseForCoverageVerification.getVehicles();
		System.out.println("resDetails3:" + resDetails3);
		String VinForTheVehicleBeingAdded = StaticValue.Empty;
		VinForTheVehicleBeingAdded = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(),
				StaticValue.VehicleVIN);
		for (int i = 0; i < resDetails3.size(); i++) {
			vehoid = StaticValue.Empty;
			VehIdentificationNo = StaticValue.Empty;
			VehvehicleStatus = StaticValue.Empty;

			vehoid = resDetails3.get(i).getOid();
			VehIdentificationNo = resDetails3.get(i).getVehIdentificationNo();
			VehvehicleStatus = resDetails3.get(i).getVehicleStatus();

			int iterator = StaticValue.zero;
			iterator = i + 1;
			String ActualVinWebElm = StaticValue.Empty;

			if (VinForTheVehicleBeingAdded.equals(VehIdentificationNo)) {
				System.out.println(" <<<< Identified Vehicle OID '" + vehoid + "'  for the VINno: '"
						+ VehIdentificationNo + "' >>>> ");
				individualVehicleCoverage = viewCoveragesGet.invokeWebApi(currentTestParameters.getScenarioName(),
						vehoid);
				System.out.println(
						" <<<< PAS  View Coverage Service call is sucess for the Vehicle OID '" + vehoid + "'  >>>> ");
				System.out.println("individualVehicleCoverage:" + individualVehicleCoverage);
				ValidatePASCoverageServiceResponseFromUI(individualVehicleCoverage);

			}

		}

	}

	public void validatePendingFlagYearMakeModelVINForTheNewlyAddedVehicle() {
		ValidateVehiclesResponse pendingserviceResponsePendingFlagYearMakeModelVINVerification = pendingserviceResponseForCoverageVerification;
		System.out.println("pendingserviceResponsePendingFlagYearMakeModelVINVerification:"
				+ pendingserviceResponsePendingFlagYearMakeModelVINVerification);
		List<Vehicle> resDetails4;

		resDetails4 = pendingserviceResponsePendingFlagYearMakeModelVINVerification.getVehicles();
		System.out.println("resDetails4:" + resDetails4);
		totalTransactions = pendingserviceResponsePendingFlagYearMakeModelVINVerification.getVehicles().size();
		System.out.println("totalTransactions:" + totalTransactions);
		String VinForTheVehicleBeingAdded = StaticValue.Empty;
		VinForTheVehicleBeingAdded = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(),
				StaticValue.VehicleVIN);
		System.out.println("VinForTheVehicleBeingAdded:" + VinForTheVehicleBeingAdded);
		for (int i = 0; i < resDetails4.size(); i++) {
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

			vehoid = resDetails4.get(i).getOid();
			VehModelYear = resDetails4.get(i).getModelYear();
			Vehmanufacturer = resDetails4.get(i).getManufacturer();
			Vehseries = resDetails4.get(i).getSeries();
			Vehmodel = resDetails4.get(i).getModel();
			VehbodyStyle = resDetails4.get(i).getBodyStyle();
			VehIdentificationNo = resDetails4.get(i).getVehIdentificationNo();
			VehvehicleStatus = resDetails4.get(i).getVehicleStatus();
			Ownership = resDetails4.get(i).getOwnership();
			Usage = resDetails4.get(i).getUsage();
			Salvaged = resDetails4.get(i).getSalvaged();
			GaragingDifferent = resDetails4.get(i).getGaragingDifferent();
			AntiTheft = resDetails4.get(i).getAntiTheft();
			RegisteredOwner = resDetails4.get(i).getRegisteredOwner();
			System.out.println("VehIdentificationNo:" + VehIdentificationNo);
			int iterator = StaticValue.zero;
			iterator = i + 1;
			String ActualVinWebElm = StaticValue.Empty;
			String ActualDriverNameDescWebElm = StaticValue.Empty;
			String DriverDescFromService = StaticValue.Empty;
			String DriverDescFromUI = StaticValue.Empty;

			if (VinForTheVehicleBeingAdded.equals(VehIdentificationNo)) {

				// this code to check if the Vehicle being added has status pending
				GenFns.ChkUILabelAndValueIsAsExpected(By.xpath(String.format(coveragePage.CoveragePendingTag)),
						coveragePageContent.CoveragePendingText, StaticValue.CoveragePendingVehiclesDetails);

				// VIN validation
				VINUI = action.GetText(By.xpath(String.format(coveragePage.CoverageVehicleVIN)));
				String[] vinUISplit = VINUI.split(" ");
				GenFns.chkTheContent(vinUISplit[0], vehiclespagecontent.VehiclesPageVINCaption,
						StaticValue.VehiclePageVINCaption);
				GenFns.chkTheContent(vinUISplit[1], VehIdentificationNo, StaticValue.CoveragePageVINNumber);

				// Model, Model year and Manufacturer validations
				Vehicledetails = action.GetText(By.xpath(String.format(coveragePage.CoverageVehicleModel)));
				String[] VehicledetailsSplit = Vehicledetails.split(" ");
				GenFns.chkTheContent(VehicledetailsSplit[0], VehModelYear, StaticValue.CoveragePageYear);
				GenFns.chkTheContent(VehicledetailsSplit[1], Vehmanufacturer, StaticValue.CoveragePageManufacturer);
				GenFns.chkTheContent(VehicledetailsSplit[2], Vehmodel, StaticValue.CoveragePageModel);

			}

		}

	}

	public void ValidatePASCoverageServiceResponseFromUI(List<Coverage> individualVehicleCoverage) {
		int CoverageIte = StaticValue.zero;
		for (int i = 0; i < individualVehicleCoverage.size(); i++) {
			String buildReturnValue = StaticValue.Empty;
			String[] buildReturnValueSplit = null;
			if (individualVehicleCoverage.get(i).getCustomerDisplayed()) {
				CoverageIte = i + 1;
				buildReturnValue = buildCoverageWebElement(CoverageIte, StaticValue.CoverageDescriptionBlock,
						individualVehicleCoverage.get(i).getCoverageCd());
				buildReturnValueSplit = buildReturnValue.split("##");
				GenFns.ChkUILabelAndValueIsAsExpected(By.xpath(String.format(buildReturnValueSplit[0])),
						individualVehicleCoverage.get(i).getCoverageDescription(), buildReturnValueSplit[2]);

				GenFns.ChkUILabelAndValueIsAsExpected(By.xpath(String.format(buildReturnValueSplit[1])),
						buildReturnValueSplit[4], buildReturnValueSplit[3]);
			} else {
				System.out.println("need to develop script for not to display element");
			}
		}

	}

	public String buildCoverageWebElement(int CoverageIte, String DescORValueBLock, String CoverageCode) {
		String CoverageBlkTitleElm = StaticValue.Empty;
		String CoverageBlkSubElm = StaticValue.Empty;
		String CoverageDesc1 = StaticValue.Empty;
		String CoverageDesc2 = StaticValue.Empty;
		String CoverageContent = StaticValue.Empty;
		String CoverageReturn = StaticValue.Empty;
		switch (CoverageCode) {
		case "COMPDED":
			if (DescORValueBLock.equals("DescriptionBlock")) {
				CoverageBlkTitleElm = coveragePage.CoverageBlockGroup + "[" + CoverageIte + "]"
						+ coveragePage.CoverageDescriptionBlockTitleText;
				CoverageBlkSubElm = coveragePage.CoverageBlockGroup + "[" + CoverageIte + "]"
						+ coveragePage.CoverageDescriptionBlockSubText;
				CoverageDesc1 = StaticValue.CoverageComprehensiveDescriptionText;
				CoverageDesc2 = StaticValue.CoverageComprehensiveHelpText;
				CoverageContent = coveragePageContent.CoverageComprehensiveHelpText;
				CoverageReturn = CoverageBlkTitleElm + "##" + CoverageBlkSubElm + "##" + CoverageDesc1 + "##"
						+ CoverageDesc2 + "##" + CoverageContent;
			} else {
				CoverageBlkTitleElm = coveragePage.CoverageBlockGroup + "[" + CoverageIte + "]"
						+ coveragePage.CoverageValueBlockTitleText;
				CoverageBlkSubElm = coveragePage.CoverageBlockGroup + "[" + CoverageIte + "]"
						+ coveragePage.CoverageValueBlockSubText;
				CoverageDesc1 = StaticValue.CoverageComprehensiveDisplayLimit;
				CoverageDesc2 = StaticValue.CoverageComprehensiveTypeText;
				CoverageReturn = CoverageBlkTitleElm + "##" + CoverageBlkSubElm + "##" + CoverageDesc1 + "##"
						+ CoverageDesc2 + "##" + CoverageContent;
			}
			break;

		case "COLLDED":
			if (DescORValueBLock.equals("DescriptionBlock")) {
				CoverageBlkTitleElm = coveragePage.CoverageBlockGroup + "[" + CoverageIte + "]"
						+ coveragePage.CoverageDescriptionBlockTitleText;
				CoverageBlkSubElm = coveragePage.CoverageBlockGroup + "[" + CoverageIte + "]"
						+ coveragePage.CoverageDescriptionBlockSubText;
				CoverageDesc1 = StaticValue.CoverageCollisionDescriptionText;
				CoverageDesc2 = StaticValue.CoverageCollisionHelpText;
				CoverageContent = coveragePageContent.CoverageCollisionHelpText;
				CoverageReturn = CoverageBlkTitleElm + "##" + CoverageBlkSubElm + "##" + CoverageDesc1 + "##"
						+ CoverageDesc2 + "##" + CoverageContent;
			} else {
				CoverageBlkTitleElm = coveragePage.CoverageBlockGroup + "[" + CoverageIte + "]"
						+ coveragePage.CoverageValueBlockTitleText;
				CoverageBlkSubElm = coveragePage.CoverageBlockGroup + "[" + CoverageIte + "]"
						+ coveragePage.CoverageValueBlockSubText;
				CoverageDesc1 = StaticValue.CoverageCollisionDisplayLimit;
				CoverageDesc2 = StaticValue.CoverageCollisionTypeText;
				CoverageContent = coveragePageContent.CoverageCollisionHelpText;
				CoverageReturn = CoverageBlkTitleElm + "##" + CoverageBlkSubElm + "##" + CoverageDesc1 + "##"
						+ CoverageDesc2 + "##" + CoverageContent;
			}
			break;

		case "TOWINGLABOR":
			if (DescORValueBLock.equals("DescriptionBlock")) {
				CoverageBlkTitleElm = coveragePage.CoverageBlockGroup + "[" + CoverageIte + "]"
						+ coveragePage.CoverageDescriptionBlockTitleText;
				CoverageBlkSubElm = coveragePage.CoverageBlockGroup + "[" + CoverageIte + "]"
						+ coveragePage.CoverageDescriptionBlockSubText;
				CoverageDesc1 = StaticValue.CoverageTowingAndLaborDescriptionText;
				CoverageDesc2 = StaticValue.CoverageTowingAndLaborHelpText;
				CoverageContent = coveragePageContent.CoverageTowingAndLaborHelpText;
				CoverageReturn = CoverageBlkTitleElm + "##" + CoverageBlkSubElm + "##" + CoverageDesc1 + "##"
						+ CoverageDesc2 + "##" + CoverageContent;
			} else {
				CoverageBlkTitleElm = coveragePage.CoverageBlockGroup + "[" + CoverageIte + "]"
						+ coveragePage.CoverageValueBlockTitleText;
				CoverageBlkSubElm = coveragePage.CoverageBlockGroup + "[" + CoverageIte + "]"
						+ coveragePage.CoverageValueBlockSubText;
				CoverageDesc1 = StaticValue.CoverageTowingAndLaborDisplayLimit;
				CoverageDesc2 = StaticValue.CoverageTowingAndLaborTypeText;
				CoverageContent = coveragePageContent.CoverageTowingAndLaborHelpText;
				CoverageReturn = CoverageBlkTitleElm + "##" + CoverageBlkSubElm + "##" + CoverageDesc1 + "##"
						+ CoverageDesc2 + "##" + CoverageContent;
			}
			break;

		case "LOAN":
			if (DescORValueBLock.equals("DescriptionBlock")) {
				CoverageBlkTitleElm = coveragePage.CoverageBlockGroup + "[" + CoverageIte + "]"
						+ coveragePage.CoverageDescriptionBlockTitleText;
				CoverageBlkSubElm = coveragePage.CoverageBlockGroup + "[" + CoverageIte + "]"
						+ coveragePage.CoverageDescriptionBlockSubText;
				CoverageDesc1 = StaticValue.CoverageLoanDescriptionText;
				CoverageDesc2 = StaticValue.CoverageLoanHelpText;
				CoverageContent = coveragePageContent.CoverageLoanHelpText;
				CoverageReturn = CoverageBlkTitleElm + "##" + CoverageBlkSubElm + "##" + CoverageDesc1 + "##"
						+ CoverageDesc2 + "##" + CoverageContent;
			} else {
				CoverageBlkTitleElm = coveragePage.CoverageBlockGroup + "[" + CoverageIte + "]"
						+ coveragePage.CoverageValueBlockTitleText;
				CoverageBlkSubElm = coveragePage.CoverageBlockGroup + "[" + CoverageIte + "]"
						+ coveragePage.CoverageValueBlockSubText;
				CoverageDesc1 = StaticValue.CoverageLoanDisplayLimit;
				CoverageDesc2 = StaticValue.CoverageLoanTypeText;
				CoverageContent = coveragePageContent.CoverageLoanHelpText;
				CoverageReturn = CoverageBlkTitleElm + "##" + CoverageBlkSubElm + "##" + CoverageDesc1 + "##"
						+ CoverageDesc2 + "##" + CoverageContent;
			}
			break;

		case "RREIM":
			if (DescORValueBLock.equals("DescriptionBlock")) {
				CoverageBlkTitleElm = coveragePage.CoverageBlockGroup + "[" + CoverageIte + "]"
						+ coveragePage.CoverageDescriptionBlockTitleText;
				CoverageBlkSubElm = coveragePage.CoverageBlockGroup + "[" + CoverageIte + "]"
						+ coveragePage.CoverageDescriptionBlockSubText;
				CoverageDesc1 = StaticValue.CoverageRentalCarReimburseDescriptionText;
				CoverageDesc2 = StaticValue.CoverageRentalCarReimburseHelpText;
				CoverageContent = coveragePageContent.CoverageRentalCarReimburseHelpText;
				CoverageReturn = CoverageBlkTitleElm + "##" + CoverageBlkSubElm + "##" + CoverageDesc1 + "##"
						+ CoverageDesc2 + "##" + CoverageContent;
			} else {
				CoverageBlkTitleElm = coveragePage.CoverageBlockGroup + "[" + CoverageIte + "]"
						+ coveragePage.CoverageValueBlockTitleText;
				CoverageBlkSubElm = coveragePage.CoverageBlockGroup + "[" + CoverageIte + "]"
						+ coveragePage.CoverageValueBlockSubText;
				CoverageDesc1 = StaticValue.CoverageRentalCarReimburseDisplayLimit;
				CoverageDesc2 = StaticValue.CoverageRentalCarReimburseTypeText;
				CoverageContent = coveragePageContent.CoverageRentalCarReimburseHelpText;
				CoverageReturn = CoverageBlkTitleElm + "##" + CoverageBlkSubElm + "##" + CoverageDesc1 + "##"
						+ CoverageDesc2 + "##" + CoverageContent;
			}
			break;
		default:
			fail(" <<<<   The CoverageCode '" + CoverageCode
					+ "' from PAS ViewCoverage service is not expected as VehicleLevelCoverage code to display in MyPolicy UI Coverage page  >>>>");
			break;

		}
		return CoverageReturn;
	}

	public void validateContinueButtonEnabledOnTheCoveragePage() {
		action.waitForElement(coveragePage.CoverageContinueButton,StaticValue.CoverageContinueButtonText);
		action.focusonElementTakeScreenshot(coveragePage.CoverageContinueButton);
		GenFns.ChkUILabelAndValueIsAsExpected(coveragePage.CoverageContinueButton,
				coveragePageContent.CoverageContinueButtonText, StaticValue.CoverageContinueButtonText);
		if (!action.isEnabled(coveragePage.CoverageContinueButton, StaticValue.CoverageContinueButtonText)) {
			fail(" <<<< Continue Button in Coverage Page  is not enabled  >>>>");
		}
		// action.ClickJSElement(coveragePage.CoverageContinueButton,
		// StaticValue.CoverageContinueButtonText);

	}

	public void ValidateComprehensionCollisionAmountDisplayedAsPerPASCoverageService() {
		int CoverageIte = StaticValue.zero;
		for (int i = 0; i < individualVehicleCoverage.size(); i++) {

			String buildReturnValue = StaticValue.Empty;
			String[] buildReturnValueSplit = null;
			if (individualVehicleCoverage.get(i).getCustomerDisplayed()) {
				if (individualVehicleCoverage.get(i).getCoverageCd().equals("COMPDED")
						|| individualVehicleCoverage.get(i).getCoverageCd().equals("COLLDED")) {
					CoverageIte = i + 1;
					buildReturnValue = buildCoverageWebElement(CoverageIte, StaticValue.CoverageValueBlock,
							individualVehicleCoverage.get(i).getCoverageCd());
					buildReturnValueSplit = buildReturnValue.split("##");
					CovergareLimitDisplay = StaticValue.Empty;
					if (individualVehicleCoverage.get(i).getCoverageLimitDisplay()
							.equals(coveragePageContent.NoCoverage)) {
						CovergareLimitDisplay = coveragePageContent.GetCoverage;
					} else {
						CovergareLimitDisplay = individualVehicleCoverage.get(i).getCoverageLimitDisplay();
					}
					GenFns.ChkUILabelAndValueIsAsExpected(By.xpath(String.format(buildReturnValueSplit[0])),
							CovergareLimitDisplay, buildReturnValueSplit[2]);

					if (!CovergareLimitDisplay.equals(coveragePageContent.GetCoverage)) {
						GenFns.ChkUILabelAndValueIsAsExpected(By.xpath(String.format(buildReturnValueSplit[1])),
								individualVehicleCoverage.get(i).getCoverageType(), buildReturnValueSplit[3]);
					}

				}
			} else {
				System.out.println("need to develop script for not to display element");
			}

		}

	}

	public void validateRentalCarCoverageAmountDisplayedAsPerPASCoverageService() {
		int CoverageIte = StaticValue.zero;
		for (int i = 0; i < individualVehicleCoverage.size(); i++) {
			String buildReturnValue = StaticValue.Empty;
			String[] buildReturnValueSplit = null;
			if (individualVehicleCoverage.get(i).getCustomerDisplayed()) {
				if (individualVehicleCoverage.get(i).getCoverageCd().equals("RREIM")) {
					CoverageIte = i + 1;
					buildReturnValue = buildCoverageWebElement(CoverageIte, StaticValue.CoverageValueBlock,
							individualVehicleCoverage.get(i).getCoverageCd());
					buildReturnValueSplit = buildReturnValue.split("##");
					CovergareLimitDisplay = StaticValue.Empty;
					if (individualVehicleCoverage.get(i).getCoverageLimitDisplay()
							.equals(coveragePageContent.NoCoverage)) {
						CovergareLimitDisplay = coveragePageContent.GetCoverage;
					} else {
						CovergareLimitDisplay = individualVehicleCoverage.get(i).getCoverageLimitDisplay();
					}
					action.focusonElementinMiddleTakeScreenshot(By.xpath(String.format(buildReturnValueSplit[0])));
					GenFns.ChkUILabelAndValueIsAsExpected(By.xpath(String.format(buildReturnValueSplit[0])),
							CovergareLimitDisplay, buildReturnValueSplit[2]);

					if (!CovergareLimitDisplay.equals(coveragePageContent.GetCoverage)) {
						GenFns.ChkUILabelAndValueIsAsExpected(By.xpath(String.format(buildReturnValueSplit[1])),
								individualVehicleCoverage.get(i).getCoverageType(), buildReturnValueSplit[3]);
					}

				}
			} else {
				System.out.println("need to develop script for not to display element");
			}

		}

	}

	public void validateTowingAndLaborCoverageAmountDisplayedAsPerPASCoverageService() {
		int CoverageIte = StaticValue.zero;
		for (int i = 0; i < individualVehicleCoverage.size(); i++) {

			String buildReturnValue = StaticValue.Empty;
			String[] buildReturnValueSplit = null;
			if (individualVehicleCoverage.get(i).getCustomerDisplayed()) {
				if (individualVehicleCoverage.get(i).getCoverageCd().equals("TOWINGLABOR")) {
					CoverageIte = i + 1;
					buildReturnValue = buildCoverageWebElement(CoverageIte, StaticValue.CoverageValueBlock,
							individualVehicleCoverage.get(i).getCoverageCd());
					buildReturnValueSplit = buildReturnValue.split("##");
					CovergareLimitDisplay = StaticValue.Empty;
					if (individualVehicleCoverage.get(i).getCoverageLimitDisplay()
							.equals(coveragePageContent.NoCoverage)) {
						CovergareLimitDisplay = coveragePageContent.GetCoverage;
					} else {
						CovergareLimitDisplay = individualVehicleCoverage.get(i).getCoverageLimitDisplay();
					}
					GenFns.ChkUILabelAndValueIsAsExpected(By.xpath(String.format(buildReturnValueSplit[0])),
							CovergareLimitDisplay, buildReturnValueSplit[2]);

					if (!CovergareLimitDisplay.equals(coveragePageContent.GetCoverage)) {
					GenFns.ChkUILabelAndValueIsAsExpected(By.xpath(String.format(buildReturnValueSplit[1])),
							individualVehicleCoverage.get(i).getCoverageType(), buildReturnValueSplit[3]);
					}
				}
			} else {
				System.out.println("need to develop script for not to display element");
			}

		}

	}

	public void validateExcessElectronicEquipmentCoverage() {

		for (int i = 0; i < individualVehicleCoverage.size(); i++) {

			switch (individualVehicleCoverage.get(i).getCoverageCd()) {
			case "SPECEQUIP":
				CovergareLimitDisplay = StaticValue.Empty;
				if (individualVehicleCoverage.get(i).getCustomerDisplayed()) {
					fail(" <<<<  '" + StaticValue.CoverageExcessElectronicEquipementText
							+ "  Customer Display is true in PAS response which is not as expected >>>>");

				} else {
					if (action.IsObjectPresent(coveragePage.CoverageExcessElectrionicEquipment,
							StaticValue.CoverageExcessElectronicEquipementText)) {
						fail(" <<<<  '" + StaticValue.CoverageExcessElectronicEquipementText
								+ " is present in the Coverage Page and this is not expected. >>>>");
					}
				}

				break;
			}

		}

	}

	public void validateNewCarAddedProtectionCoverageNotAvailable() {

		for (int i = 0; i < individualVehicleCoverage.size(); i++) {

			switch (individualVehicleCoverage.get(i).getCoverageCd()) {
			case "NEWCARADDPROT":
				CovergareLimitDisplay = StaticValue.Empty;
				if (individualVehicleCoverage.get(i).getCustomerDisplayed()) {
					fail(" <<<<  '" + StaticValue.CoverageNewCarAddedText
							+ "  Customer Display is true in PAS response which is not as expected >>>>");

				} else {
					if (action.IsObjectPresent(coveragePage.CoverageNewCarAdded, StaticValue.CoverageNewCarAddedText)) {
						fail(" <<<<  '" + StaticValue.CoverageNewCarAddedText
								+ " is present in the Coverage Page and this is not expected. >>>>");
					}
				}

				break;
			}

		}

	}

	public void validateCustomizedEquipmentCoverage() {

		for (int i = 0; i < individualVehicleCoverage.size(); i++) {

			switch (individualVehicleCoverage.get(i).getCoverageCd()) {
			case "CUSTEQUIP":
				CovergareLimitDisplay = StaticValue.Empty;
				if (individualVehicleCoverage.get(i).getCustomerDisplayed()) {
					fail(" <<<<  '" + StaticValue.CoverageCustomizedEquipmentText
							+ "  Customer Display is true in PAS response which is not as expected >>>>");

				} else {
					if (action.IsObjectPresent(coveragePage.CoverageCustomizedEquipment,
							StaticValue.CoverageCustomizedEquipmentText)) {
						fail(" <<<<  '" + StaticValue.CoverageCustomizedEquipmentText
								+ " is present in the Coverage Page and this is not expected. >>>>");
					}
				}

				break;
			}

		}

	}

}
