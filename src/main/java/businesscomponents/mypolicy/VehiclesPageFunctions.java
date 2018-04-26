package businesscomponents.mypolicy;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import services.rest.apiclients.AddVehiclePOST;
import services.rest.apiclients.StartEndorsementPOST;
import services.rest.apiclients.ValidateEndorsement;
import services.rest.apiclients.ValidateVIN;
import services.rest.apiclients.ValidateVehicleList;
import stepDefinitions.common.MasterStepDefs;
import webelementrepository.MyPolicy.AddVehiclePage;
import webelementrepository.MyPolicy.MakePolicyChangesPage;
import webelementrepository.MyPolicy.PaymentPage;
import MyPolicyContentSpec.VehiclesPageContent;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.StaticValue;
import com.Cucumber.supportLibraries.Util;
import com.Cucumber.supportLibraries.Webaction;
import com.Cucumber.supportLibraries.custom.ReadCSVFileFunctions;

public class VehiclesPageFunctions extends MasterStepDefs {
	static Logger log = Logger.getLogger(PaymentPage.class);
	WebDriver driver = DriverManager.getWebDriver();
	Webaction action = new Webaction();
	PaymentPage paymentpage = new PaymentPage();
	ValidateEndorsement endorsementWebservice = new ValidateEndorsement();
	ValidateVehicleList vehicleWebservice = new ValidateVehicleList();
	ValidateVIN validateVIN = new ValidateVIN();
	MakePolicyChangesPage makepolicychangespage = new MakePolicyChangesPage();
	VehiclesPageContent vehiclespagecontent = new VehiclesPageContent();
	MakePolicyChangesFunctions makepolicychangesfunctions = new MakePolicyChangesFunctions();
	AddVehiclePage addvehiclepage = new AddVehiclePage();
	GeneralFunctions GenFns = new GeneralFunctions();
	ReadCSVFileFunctions readCSVfilefunctions = new ReadCSVFileFunctions();
	StartEndorsementPOST startendorsementPost = new StartEndorsementPOST();
	AddVehiclePOST addvehiclePOST = new AddVehiclePOST();
	
	int TotalNoOfPolicy = StaticValue.zero;
	int TotalNoOfPolicyWithMinimumDue = StaticValue.zero;
	int TotalNoOfPolicyPaidFull = StaticValue.zero;
	int CntOfPolicyWithMinimumDueForGivenInstance = StaticValue.zero;
	SoftAssert sa = new SoftAssert();
	int FutureDateFoundFlag = StaticValue.zero;
	String DisclaimerText2;
	private String PurchaseDateErrorUI;
	private String PurchaseDateUI;
	private String VINErrorUI;
	char c;
	int cint;
	private String VinPrepopulatedUI;
	private String DatePrepopulatedUI;
	private String VehiclePurchaseDate;
	private String VehicleVIN;
	String VinUI = StaticValue.Empty;

	// Function to validate the Add Vehicle button in vehicles page
	public void ValidateAddVehiclelink() {

		vehicleWebservice.ValidateAddVehicleLink(vehicleWebservice.VehicleDataMap.get(StaticValue.totalTransactions));
	}

	// Function to click Add Vehicle button in vehicles page
	public void clickAddVehiclelink() {
		action.ClickJSElement(makepolicychangespage.AddVehicleHyperlink, "AddVehicleHyperlink");
		currentScenario.embed(Util.takeScreenshot(driver), "image/png");

	}

	// Function to validate Add Vehicle drawer section available in vehicles
	// page
	public void ValidateAddVehicleDrawreViewText(String option) {
		if (option.equalsIgnoreCase(StaticValue.IsNotCollapsed)) {
			if (!action.IsObjectPresentWithoutWait(makepolicychangespage.AddVehicleDrawerViewSection,
					StaticValue.VehiclePageAddvehicleactionblock)) {
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				fail(StaticValue.VehiclePageAddvehicleactionblock + " is not expanded");
			}
			action.waitForElement(makepolicychangespage.AddVehicleDrawerViewText);
			//action.focusonElementTakeScreenshot(makepolicychangespage.AddVehicleDrawerViewText);
			String DescriptionText = action.GetText(makepolicychangespage.AddVehicleDrawerViewText);
			GenFns.chkTheContent(DescriptionText, vehiclespagecontent.AddVehicleDrawerViewText,
					StaticValue.VehiclePageDrawerViewDescription);
			
		} else {
			if (action.IsObjectPresentWithoutWait(makepolicychangespage.AddVehicleDrawerViewSection,
					StaticValue.VehiclePageAddvehicleactionblock)) {
				action.focusonElementTakeScreenshot(makepolicychangespage.AddVehicleDrawerViewSection);
				fail(StaticValue.VehiclePageAddvehicleactionblock + " is not expanded");
			}

		}
	}

	// Function to validate purchase date field display in Drawer view section
	// in vehicles page
	public void ValidateVehiclePurchaseDateField() {
		ChkVehiclePurchaseDateFieldavailability();
		ValidateVehiclePurchaseDateFieldCaption();
		ValidateVehiclePurchaseDateFormatCaption();
		ValidateVehiclePurchaseDateSubText();
	}

	// Function to check the purchase date field available in Drawer view
	// section
	// in vehicles page
	public void ChkVehiclePurchaseDateFieldavailability() {
		action.waitForElement(makepolicychangespage.PurchaseDateTextBox);
		if (!action.isEnabled(makepolicychangespage.PurchaseDateTextBox, StaticValue.VehiclePagePurchaseDateField)) {
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			fail(StaticValue.VehiclePagePurchaseDateField + " is not enabled");
		}
	}

	// Function to validate purchase date field caption in Drawer view section
	// in vehicles page
	public void ValidateVehiclePurchaseDateFieldCaption() {
		String PurchaseDateFieldCaption = action.GetText(makepolicychangespage.PurchaseDateCaption);
		GenFns.chkTheContent(PurchaseDateFieldCaption,
				vehiclespagecontent.AddVehiclePurchaseDateCaption, "PurchaseDate Caption");

	}

	// Function to validate purchase date field format caption in Drawer view
	// section in vehicles page
	public void ValidateVehiclePurchaseDateFormatCaption() {
		action.ClickElement(makepolicychangespage.PurchaseDateTextBox);
		String PurchaseDateFieldFormatCaption = action.GetText(makepolicychangespage.PurchaseDateFormat);
		GenFns.chkTheContent(PurchaseDateFieldFormatCaption,
				vehiclespagecontent.AddVehiclePurchaseDateFormat, "PurchaseDateFormat Caption");
	}

	// Function to validate purchase date subtext in Drawer view
	// section in vehicles page
	public void ValidateVehiclePurchaseDateSubText() {
		String PurchaseDateFieldSubText = action.GetText(makepolicychangespage.PurchaseDateSubText);
		GenFns.chkTheContent(PurchaseDateFieldSubText,
				vehiclespagecontent.AddVehiclePurchaseDateSubText, "PurchaseDate SubText");
	}

	// Function to validate VIN field display in Drawer view section in vehicles
	// page
	public void ValidateVehicleVINField() {
		ChkVINFieldavailability();
		ValidateVehicleVINFieldCaption();
		ValidateVehicleVINSubText();
	}

	// Function to check the VIN field available in Drawer view section
	// in vehicles page
	public void ChkVINFieldavailability() {
		action.waitForElement(makepolicychangespage.VINTextBox);
		if (!action.isEnabled(makepolicychangespage.VINTextBox, StaticValue.VehiclePageVINField)) {
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			fail(StaticValue.VehiclePageVINField + " is not enabled");
		}
	}

	// Function to validate VIN field caption in Drawer view section
	// in vehicles page
	public void ValidateVehicleVINFieldCaption() {
		String VINFieldCaption = action.GetText(makepolicychangespage.VINCaption);
		GenFns.chkTheContent(VINFieldCaption, vehiclespagecontent.AddVehicleVINCaption,
				"PurchaseDate Caption");

	}

	// Function to validate VIN subtext in Drawer view
	// section in vehicles page
	public void ValidateVehicleVINSubText() {
		String VINFieldSubText = action.GetText(makepolicychangespage.VINSubText);
		//action.focusonElementTakeScreenshot(makepolicychangespage.VINSubText);
		GenFns.chkTheContent(VINFieldSubText, vehiclespagecontent.AddVehicleVINSubText,
				"PurchaseDate SubText");
	}

	// Function to validate NEXT Button display in Drawer view section in
	// vehicles
	// page
	public void ValidateVehicleNextButton(String option) {

		if (option.equals(StaticValue.IsEnabled)) {
			action.waitForElement(makepolicychangespage.AddVehiclePageNEXTButton);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();",
					driver.findElement(makepolicychangespage.AddVehiclePageNEXTButton));
			if (!action.isEnabled(makepolicychangespage.AddVehiclePageNEXTButtonEnabled,
					StaticValue.VehiclePageNEXTButton)) {
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				fail("<<<<  '" + StaticValue.VehiclePageNEXTButton + "' is disabled. And this is not as Expected >>>>");
			}
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");

		} else {
			action.waitForElement(makepolicychangespage.AddVehiclePageNEXTButton);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();",
					driver.findElement(makepolicychangespage.AddVehiclePageNEXTButton));
			if (action.isEnabled(makepolicychangespage.AddVehiclePageNEXTButtonDisabled,
					StaticValue.VehiclePageNEXTButton)) {
				fail("<<<<  '" + StaticValue.VehiclePageNEXTButton + "' is enabled. And this is not as Expected >>>>");
			}
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		}
	}

	// Function to validate CollapseArrow link display in Drawer view section in
	// vehicles
	// page
	public void ValidateCollapseArrowLink() {
		if (!action.IsObjectPresentWithoutWait(makepolicychangespage.AddVehicleCollapseArrow,
				StaticValue.AddVehicleCollapseArrowLink)) {
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			fail("<<<<  '" + StaticValue.AddVehicleCollapseArrowLink
					+ "' is not available. And this is not as expected >>>>");
		}
		if (!action.isDisplayed(makepolicychangespage.AddVehicleCollapseArrow,
				StaticValue.AddVehicleCollapseArrowLink)) {
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			fail("<<<<  '" + StaticValue.AddVehicleCollapseArrowLink
					+ "' is not displayed. And this is not as expected >>>>");
		}

	}

	// Function to enter date in Purchase Date in vehicles page
	public void EnterPurchaseDate(String vehiclePurchaseDate) {		
		action.waitForElement(makepolicychangespage.PurchaseDateTextBox);	
		action.ClickElement(makepolicychangespage.PurchaseDateTextBox);
		action.EnterText(makepolicychangespage.PurchaseDateTextBox, vehiclePurchaseDate);
		action.focusonElementTakeScreenshot(makepolicychangespage.PurchaseDateTextBox);
		action.pressTabkey();
		//currentScenario.embed(Util.takeScreenshot(driver), "image/png");

	}

	// Function to validate date in Purchase Date in vehicles page
	public void ValidatePurchaseDate(String vehiclePurchaseDate, String option) {
		//currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		action.waitForElement(makepolicychangespage.PurchaseDateTextBox);
		PurchaseDateUI = action.GetAttribute(makepolicychangespage.PurchaseDateTextBox, "value");
		// if (option.equals(StaticValue.ErrorPresent)) {
		Date current = new Date();
		String myFormatString = "MM/dd/yyyy";
		SimpleDateFormat df = new SimpleDateFormat(myFormatString);
		Date givenDate = null;

		if (isThisDateValid(PurchaseDateUI, "MM/dd/yyyy", option)) {
			if (option.equals(StaticValue.InvalidPurchaseDate)) {
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				fail(" <<<< Entered purchase date  '" + vehiclePurchaseDate
						+ "' is a valid date, Expected Invalid Date >>>>");
			}
			if (action.IsObjectPresentWithoutWait(makepolicychangespage.PurchaseDateErrorID,
					StaticValue.VehiclePagePurchaseDateField) && option.equals(StaticValue.ValidPuchaseDate)) {
				String DateErrorUI = action.GetText(makepolicychangespage.PurchaseDateErrorID);
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				fail("<<<< Entered Valid purchase date. '" + vehiclePurchaseDate + "' But got '" + DateErrorUI
						+ "' as a error messge which is not as expected. Entered date Could be future date  >>>>");
			}

		} else {
			if (!option.equals(StaticValue.InvalidPurchaseDate)) {
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				fail(" <<<< Entered puchase date  '" + vehiclePurchaseDate + "' is not a valid date >>>>");
			}
			if (!action.IsObjectPresentWithoutWait(makepolicychangespage.PurchaseDateErrorID,
					StaticValue.VehiclePagePurchaseDateField)) {
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				fail("<<<< Entered Invalid purchase date. '" + vehiclePurchaseDate + "' But did not get '"
						+ vehiclespagecontent.PurchaseDateFuturedateErrorMsg
						+ "' as a error messge which is not as expected. >>>>");
			}
			ValidatePurchaseDateError(makepolicychangespage.PurchaseDateErrorID,
					vehiclespagecontent.InvalidPurchaseDateErrorMsg, StaticValue.VehiclePagePurchaseDateField);

		}

		try {
			givenDate = df.parse(PurchaseDateUI);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Long l = givenDate.getTime();
		Date next = new Date(l);
		if (next.getTime() >= current.getTime()) {
			System.out.println("The date is future day");
			if (!action.IsObjectPresentWithoutWait(makepolicychangespage.PurchaseDateErrorID,
					StaticValue.VehiclePagePurchaseDateField) && option.equals(StaticValue.FuturePurchaseDate)) {
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				fail("<<<< Entered Future purchase date. '" + vehiclePurchaseDate + "' But did not get '"
						+ vehiclespagecontent.PurchaseDateFuturedateErrorMsg
						+ "' as a error messge which is not as expected. >>>>");
			}
			if (option.equals(StaticValue.FuturePurchaseDate)) {
				ValidatePurchaseDateError(makepolicychangespage.PurchaseDateErrorID,
						vehiclespagecontent.PurchaseDateFuturedateErrorMsg, StaticValue.VehiclePagePurchaseDateField);
		
				
			}
			// FutureDateFoundFlag = 1;
		} else {
			if (option.equals(StaticValue.FuturePurchaseDate)) {
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				fail(" <<<< Entered Valid purchase date  '" + vehiclePurchaseDate + "', Expected Future Date >>>>");
			}
		}
		currentScenario.embed(Util.takeScreenshot(driver), "image/png");
	}

	public void ValidatePurchaseDateError(By by, String GivenContentMessage, String Fieldname) {
		action.waitForElement(makepolicychangespage.PurchaseDateErrorID);
		PurchaseDateErrorUI = action.GetText(makepolicychangespage.PurchaseDateErrorID);
		GenFns.chkTheContent(PurchaseDateErrorUI, GivenContentMessage, Fieldname);
	}

	// Function to validate the year format
	public boolean isThisDateValid(String dateToValidate, String dateFromat, String option) {
		if (dateToValidate == null) {
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);

		try {

			// if not valid, it will throw ParseException
			Date date = sdf.parse(dateToValidate);
			ValidateYear(dateToValidate, option);
		} catch (ParseException e) {

			e.printStackTrace();
			return false;
		}

		return true;
	}

	// Function to validate Year from Purchase date is GT than 1980
	public void ValidateYear(String dateToValidate, String option) {
		String[] splitdate = dateToValidate.split("/");
		int result = Integer.parseInt(splitdate[2]);

		if (result > StaticValue.maxyear) {
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			fail("<<<<  Entered Purchase date '" + dateToValidate + "' has Invalid Year >>>>");

		}

		if (result < StaticValue.Onethousand) {
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			fail("<<<<  Entered Purchase date '" + dateToValidate + "' has Invalid Year >>>>");

		}

		if (result < StaticValue.NineteenEighty && !option.equals(StaticValue.InvalidPurchaseDate)) {
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			fail("<<<<  Entered Purchase date '" + dateToValidate + "' has year less than 1980 >>>>");

		}

	}

	// Function to enter VIN number in VIN text box in vehicles page
	public void EnterVIN(String vehicleVIN) {

		action.waitForElement(makepolicychangespage.VINTextBox);
		action.EnterText(makepolicychangespage.VINTextBox, vehicleVIN);
		action.pressTabkey();
		ValidateVehicleNextButton(StaticValue.IsEnabled);
		action.focusonElementTakeScreenshot(makepolicychangespage.VINTextBox);
		VinUI = action.GetAttribute(makepolicychangespage.VINTextBox, "value");
		if (VinUI.length() > StaticValue.AllowedVINLength) {
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			fail(" <<<< VIN field accepts VIN number more than 17 characters  '" + VinUI
					+ "' This is not as Expected >>>>");
		}
	}

	// Function to validate VIn Field in vehicles page
	public void ValidateVINNumber(String isthatvalidVin) {
		action.waitForElement(makepolicychangespage.VINTextBox);
		VinPrepopulatedUI = action.GetAttribute(makepolicychangespage.VINTextBox, "value");

		if (VinPrepopulatedUI.length() < StaticValue.AllowedVINLength) {
			ValidateVINError();
			if (isthatvalidVin.equals(StaticValue.ValidVIN)) {
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				fail(" <<<< the given VIN length '" + VinPrepopulatedUI + "' is less than 17 digits  >>>>");
			}			
		}

		if (VinPrepopulatedUI.matches("[0-9]+")) {
			ValidateVINError();
			if (isthatvalidVin.equals(StaticValue.ValidVIN)) {
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				fail(" <<<< the given VIN  '" + VinPrepopulatedUI + "' is not a alpha numeric value  >>>>");
			}
		}

		if (VinPrepopulatedUI.matches("[A-Za-z]+")) {
			ValidateVINError();
			if (isthatvalidVin.equals(StaticValue.ValidVIN)) {
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				fail(" <<<< the given VIN  '" + VinPrepopulatedUI + "' is not a alpha numeric value  >>>>");
			}
		}

		for (int n = 0; n < VinPrepopulatedUI.length(); n++) {
			c = VinPrepopulatedUI.charAt(n);
			cint = (int) c;
			if (cint < 48 || (cint > 57 && cint < 65) || (cint > 90 && cint < 97) || cint > 122) {
				ValidateVINError();
				if (isthatvalidVin.equals(StaticValue.ValidVIN)) {
					currentScenario.embed(Util.takeScreenshot(driver), "image/png");
					fail(" <<<< the given VIN  '" + VinPrepopulatedUI + "' has special characters  >>>>");
				}				
			}
		}

	}

	public void ValidateVINError() {
		action.waitForElement(makepolicychangespage.VINErrorID);
		VINErrorUI = action.GetText(makepolicychangespage.VINErrorID);
		action.focusonElementTakeScreenshot(makepolicychangespage.VINErrorID);
		GenFns.chkTheContent(VINErrorUI, vehiclespagecontent.VINInvalidErrorMsg,
				StaticValue.VehiclePageVINField);
		
	}

	// Function to click NEXT Button in
	// vehicles
	// page
	public void ClickAddVehicleNextButton() {
		action.waitForElement(makepolicychangespage.AddVehiclePageNEXTButtonEnabled, StaticValue.VehiclePageNEXTButton);
		action.focusonElementTakeScreenshot(makepolicychangespage.AddVehiclePageNEXTButtonEnabled);
		action.ClickJSElement(makepolicychangespage.AddVehiclePageNEXTButtonEnabled, StaticValue.VehiclePageNEXTButton);				
	}

	// Function to validate whether entered VIN and purchase date is cached in UI
	public void ValidateVINDatePrepopulated() {
		VinPrepopulatedUI = action.GetAttribute(makepolicychangespage.VINTextBox, "value");
		DatePrepopulatedUI = action.GetAttribute(makepolicychangespage.PurchaseDateTextBox, "value");
		VehiclePurchaseDate = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(),StaticValue.VehiclePurchaseDate);
		VehicleVIN = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(),StaticValue.VehicleVIN);
		if (!VinPrepopulatedUI.equalsIgnoreCase(VehicleVIN.substring(0, 17))) {
			action.focusonElementTakeScreenshot(makepolicychangespage.VINTextBox);
			fail("Value Displayed in" + StaticValue.VehicleVIN
					+ "doesn't match with already entered data if collapsed and expanded");
		}
		if (!DatePrepopulatedUI.equalsIgnoreCase(VehiclePurchaseDate)) {
			action.focusonElementTakeScreenshot(makepolicychangespage.PurchaseDateTextBox);
			fail("Date Displayed in" + StaticValue.ValidPuchaseDate
					+ "doesn't match with already entered data if collapsed and expanded");
		}
		action.focusonElementTakeScreenshot(makepolicychangespage.VINTextBox);
	}

	// public void ValidateVINService(String ExpectedStatus) throws
	// ClientProtocolException, IOException,
	// URISyntaxException, KeyManagementException, NoSuchAlgorithmException,
	// KeyStoreException {
	//
	// validateVIN.invokeWebApi();
	//
	// if (validateVIN.result.equals(ExpectedStatus) &&
	// ExpectedStatus.contains("Fail")) {
	// ValidateVINError();
	// } else {
	// if (action.IsObjectPresentWithoutWait(makepolicychangespage.VINErrorID,
	// StaticValue.VehiclePageVINFieldError)) {
	// fail("<<<< '" + vehiclespagecontent.VINInvalidErrorMsg + "' message found
	// which is not as expected");
	// }
	// }
	// }

	public void ValidateVINService()  {

		try {			
			String ScenarioName = StaticValue.Empty;
			ScenarioName =  currentTestParameters.getScenarioName();
			validateVIN.invokeWebApi(ScenarioName);
		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException | IOException
				| URISyntaxException e) {
			e.printStackTrace();
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			fail(" <<<< Issue While calling PAS VIN Validation Service >>>> ");
		}
		
		if (validateVIN.result.equals(StaticValue.Fail)) {
			action.focusonElementTakeScreenshot(makepolicychangespage.VINErrorID);
			ValidateVINError();		
		} else {
			System.out.println("<<<<< PAS VIN validation Success >>>>>");
			//currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			//ValidateStartEndorsementService();
			//System.out.println("<<<<< PAS StartEndorsement POST service Success  >>>>>");
			//ValidateAddVehiclePostService(validateVIN.vehicleYEAR);
			//System.out.println("<<<<< PAS AddVehicle POST service Success  >>>>>");
		}
	}
	
	
	
	public void ValidateStartEndorsementService()  {
		
			try {
				action.HardDelay(3000);
				String ScenarioName = StaticValue.Empty;
				ScenarioName =  currentTestParameters.getScenarioName();
				startendorsementPost.invokeWebApi(ScenarioName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				fail(" <<<< Issue While calling PAS StartEndorsement Service >>>> ");
			}
		
		
	}
	
	public void ValidateAddVehiclePostService(int year)  {
		
			try {
				String ScenarioName = StaticValue.Empty;
				ScenarioName =  currentTestParameters.getScenarioName();
				addvehiclePOST.invokeWebApi(year,ScenarioName);
			} catch (IOException e) {
				e.printStackTrace();
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				fail(" <<<< Issue While calling PAS ADDVehicle POST Service >>>> ");
			}
	
		
	}
	
	

}
