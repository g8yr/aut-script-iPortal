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
import services.rest.apiclients.ViewDriversGet;
import services.rest.pojo.validateVehiclesList.validateVehiclesResponse.ValidateVehiclesResponse;
import services.rest.pojo.validateVehiclesList.validateVehiclesResponse.Vehicle;
import stepDefinitions.common.MasterStepDefs;
import webelementrepository.MyPolicy.AddVehiclePage;
import webelementrepository.MyPolicy.DriverVehicleAssignmentPage;
import webelementrepository.MyPolicy.MakePolicyChangesPage;
import MyPolicyContentSpec.DriverVehicleAssignmentPageContent;
import MyPolicyContentSpec.VehiclesPageContent;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.StaticValue;
import com.Cucumber.supportLibraries.Util;
import com.Cucumber.supportLibraries.Webaction;
import com.Cucumber.supportLibraries.custom.ReadCSVFileFunctions;

public class DriverVehicleAssignmentFunctions extends MasterStepDefs {
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
	public String csvData = StaticValue.Empty;
	public String policynumber = StaticValue.Empty;
	public ValidateVehiclesResponse pendingserviceResponse;
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
	
	String Vehicledetails;

	public void chkTheGivenAddRemoveAdditionalVehicleQuestionOptionFromInputFile(String option) {
		action.waitForElement(addvehiclepage.AddVehicleActionBlockTitle, StaticValue.AddvehicleActionBlockTitle);
		action.focusonElementTakeScreenshot(addvehiclepage.AddVehicleActionBlockTitle);
		String AddremoveVehicleOptionFromFile = StaticValue.Empty;
		AddremoveVehicleOptionFromFile = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(),
				StaticValue.AddRemoveAdditionalVehicle);
		if (!AddremoveVehicleOptionFromFile.equals(option)) {
			fail("<<<< Expected  option '" + option + "' from input file for '" + StaticValue.AddRemoveAdditionalVehicle
					+ "', but found option '" + AddremoveVehicleOptionFromFile
					+ "' in the input file. And this is not a corect input data >>>>");
		}
	}

	public void ValidateDriverVehicleAssignmentSummaryPage() {
		String  policynumber = StaticValue.Empty;
		policynumber = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), StaticValue.PolicyNumber);
		action.HardDelay(3000);
		currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		makepolicychangesfunctions.ValidateThePageHeaderInEnorsementPage();
		makepolicychangesfunctions.ValidatePolicyNumberVehicleDetailsSection(policynumber);
		ValidateTextInDriverAssignmentSummarySection();
		ValidateSubTextInDriverAssignmentSummarySection();

	}

	// Function to Ensure the DescriptionText in Driver Vehicle Assignment  Page
	public void ValidateTextInDriverAssignmentSummarySection() {
		action.waitForElement(driverVehicleAssignmentPage.DriverVehicleAssignmentSummaryText);
		String DescriptionText = action.GetText(driverVehicleAssignmentPage.DriverVehicleAssignmentSummaryText);
		GenFns.chkTheContent(DescriptionText, driverVehicleAssignmentPageContent.DriverVehicleAssignmentSummaryText,
				StaticValue.DriverVehicleAssignmentSummaryText);
	}

	// Function to Ensure the DescriptionSubtextText in Driver Vehicle Assignment Page
	public void ValidateSubTextInDriverAssignmentSummarySection() {
		action.waitForElement(driverVehicleAssignmentPage.DriverVehicleAssignmentSummarySubText);
		String DescriptionText = action.GetText(driverVehicleAssignmentPage.DriverVehicleAssignmentSummarySubText);
		GenFns.chkTheContent(DescriptionText, driverVehicleAssignmentPageContent.DriverVehicleAssignmentSummarySubText,
				StaticValue.DriverVehicleAssignmentSummarySubText);
	}

	// Function to call PAS Vehicle Pending Service and validate the response for in Driver Vehicle Assignment Page
		public void ValidatePendingFlagBasedOnPASpendingVehicleService() {
			String policynumber = StaticValue.Empty;
			policynumber = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), StaticValue.PolicyNumber);
			try {
				pendingserviceResponse = validatependingendorsementvehicle.rest_getResponse(policynumber);
				System.out.println(" <<<< PAS Pending Endorsement Vehicle Service call is sucess When ADD/Remove Vehicle option is NO >>>> ");
				ValidatePendingFlagFromPASServiceResponseAndUI(pendingserviceResponse);
			} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException | ParseException
					| URISyntaxException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	
		}
		
		
		public void ValidatePendingFlagFromPASServiceResponseAndUI(ValidateVehiclesResponse pendingServiceResponse) {
			List<Vehicle> resDetails;
      		resDetails = pendingServiceResponse.getVehicles();
			System.out.println("resDetails:" + resDetails);
			totalTransactions = pendingServiceResponse.getVehicles().size();
			String VinForTheVehicleBeingAdded = StaticValue.Empty;
			VinForTheVehicleBeingAdded = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(),
					StaticValue.VehicleVIN);
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
				String ActualVehicleDetailsWebElm = StaticValue.Empty;
				String ActualDriverNameDescWebElm = StaticValue.Empty;
				String DriverDescFromService = StaticValue.Empty;
				String DriverDescFromUI = StaticValue.Empty;

				

				// this code to check if the Vehicle being added has status pending
				if (VinForTheVehicleBeingAdded.equals(VehIdentificationNo)) {
					String ActualEndorsementPendingElm = StaticValue.Empty;
					ActualEndorsementPendingElm = driverVehicleAssignmentPage.DriverVechicleAssignmentBlockGroup + "[" + iterator + "]"
							+ driverVehicleAssignmentPage.DriverVechicleAssignmentPendingTag;
					GenFns.ChkUILabelAndValueIsAsExpected(By.xpath(String.format(ActualEndorsementPendingElm)),
							driverVehicleAssignmentPageContent.DriverVehicleAssignmentPendingText, StaticValue.DriverVehicleAssignmentPagePending);
					
					if (i != 0) {
						fail("<<<< Vehicle being added is displayed with pending tag in Coverage Page. But not displayed on top  >>>>");
					}
					

					// Function to Validate the Interview Q&A Response From PAS
					ValidateInterviewQAResponseFromPAS();

				}

				// Model, Model year and Manufacturer validations
				ActualVehicleDetailsWebElm = StaticValue.Empty;
				ActualVehicleDetailsWebElm = driverVehicleAssignmentPage.DriverVechicleAssignmentBlockGroup + "[" + iterator + "]"
						+ driverVehicleAssignmentPage.DriverVechicleAssignmentModel;
				Vehicledetails = action.GetText(By.xpath(String.format(ActualVehicleDetailsWebElm)));
				Vehicledetails = Vehicledetails.trim();
				String[] VehicledetailsSplit = Vehicledetails.split(" ");
				GenFns.chkTheContent(VehicledetailsSplit[0], VehModelYear, StaticValue.DriverVehicleAssignmentPageYear);
				GenFns.chkTheContent(VehicledetailsSplit[1], Vehmanufacturer, StaticValue.DriverVehicleAssignmentPageMamufacturer);
				GenFns.chkTheContent(VehicledetailsSplit[2], Vehmodel, StaticValue.DriverVehicleAssignmentPageModel);
			}

		}
		
		
		public void ValidateDriverAssignedToVehicle() {
			validateTheDriverAssignedToVehicle(pendingserviceResponse);
			
		}
		
		
		public void validateTheDriverAssignedToVehicle(ValidateVehiclesResponse pendingServiceResponse) {
			List<Vehicle> resDetails2;

			resDetails2 = pendingServiceResponse.getVehicles();
			System.out.println("resDetails2:" + resDetails2);
			for (int i = 0; i < resDetails2.size(); i++) {
				vehoid = StaticValue.Empty;
				Vehmanufacturer = StaticValue.Empty;
				vehoid = resDetails2.get(i).getOid();
				Vehmanufacturer = resDetails2.get(i).getManufacturer();
				

				int iterator = StaticValue.zero;
				iterator = i + 1;
				String ActualDriverNameDescWebElm = StaticValue.Empty;
				String DriverDescFromService = StaticValue.Empty;
				String DriverDescFromUI = StaticValue.Empty;

				String driverOid = StaticValue.Empty;
				String driverFN = StaticValue.Empty;

				// Calling PAS View Assignment service to get the driver OID for the respective
				// Vehicle OID
				System.out.println("vehoid:"+vehoid);
				driverOid = viewAssignmentsGet.invokeWebApi(currentTestParameters.getScenarioName(), vehoid);
				System.out.println("driverOid found : " + driverOid );
				// Calling PAS View Driver service to get the driver's first name for the
				// respective Driver OID
				driverFN = viewDriversGet.invokeWebApi(currentTestParameters.getScenarioName(), driverOid);
				System.out.println("first name found :" + driverFN+":" );
				
				ActualDriverNameDescWebElm = driverVehicleAssignmentPage.DriverVechicleAssignmentBlockGroup + "[" + iterator + "]"
						+ driverVehicleAssignmentPage.DriverVechicleAssignmentDriverDescription;
				DriverDescFromUI = action.GetText(By.xpath(String.format(ActualDriverNameDescWebElm)));
				DriverDescFromService = driverFN + " drives the " + Vehmanufacturer + " most often";
				System.out.println("actual driver help text:" + DriverDescFromService + ":");
				GenFns.chkTheContentWithOutCaseSensitive(DriverDescFromUI, DriverDescFromService, StaticValue.DriverVehicleAssignmentFirstnameAsDriver);

		
				
		
			}
			
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
		

		
		public void ValidateContinueButtonInDriverAssignmentpage() {
			GenFns.ChkUILabelAndValueIsAsExpected(driverVehicleAssignmentPage.DriverVehicleAssignmentContinueButton,
					driverVehicleAssignmentPageContent.DriverVehicleAssignmentContinueText,
					StaticValue.DriverVehicleAssignmentContinueButton);
		    if(!action.isEnabled(driverVehicleAssignmentPage.DriverVehicleAssignmentContinueButton, StaticValue.DriverVehicleAssignmentContinueButton)) {
		    	fail(" <<<< Continue Button in Driver Vehicle Assignment Page  is not enabled  >>>>");
		    }

		}
		
		public void clickContinueButtonFromTheDriverVehicleAssignmentPage() {
		    action.ClickJSElement(driverVehicleAssignmentPage.DriverVehicleAssignmentContinueButton, StaticValue.DriverVehicleAssignmentContinueButton);

		}
		
		
		
		
		
}
