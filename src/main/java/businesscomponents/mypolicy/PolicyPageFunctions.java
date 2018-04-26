package businesscomponents.mypolicy;

import static org.junit.Assert.fail;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import services.rest.apiclients.ValidateEndorsement;
import services.rest.apiclients.ValidateVehicleList;
import webelementrepository.MyPolicy.DashboardPage;
import webelementrepository.MyPolicy.MakePolicyChangesPage;
import webelementrepository.MyPolicy.PoliciesPage;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.StaticValue;
import com.Cucumber.supportLibraries.Util;
import com.Cucumber.supportLibraries.Webaction;

public class PolicyPageFunctions extends Webaction implements DashboardPage {
	WebDriver driver = DriverManager.getWebDriver();
	int cnt, TotalNoOfPolicy = StaticValue.zero;
	PoliciesPage policypage = new PoliciesPage();
	PaymentsFunctions paymentsfunction = new PaymentsFunctions();
	Webaction action = new Webaction();
	ValidateEndorsement endorsementWebservice = new ValidateEndorsement();
	ValidateVehicleList vehicleWebservice = new ValidateVehicleList();
	MakePolicyChangesPage makepolicychangespage = new MakePolicyChangesPage();
	int TotalNoOfPolicyWithMinimumDue = StaticValue.zero;
	int TotalNoOfPolicyPaidFull = StaticValue.zero;
	int CntOfPolicyWithMinimumDueForGivenInstance = StaticValue.zero;
	SoftAssert sa = new SoftAssert();

	// Function to Ensure the Policy Details Loaded
	public void ChkDashboardPageLoaded() {

		currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		System.out.println("PoliciesFunctions.ChkDashboardPageLoaded() : " + driver.getTitle() + ":");
		assertTrue(driver.getCurrentUrl().contains("Dashboard"), "Dashboard not loaded sucessfully");
	}

	// Function to validate the Vehicle List from PAS
	public void ValidateVehicleListFromPAS(String PolicyNumber) {

		try {
			vehicleWebservice.rest_getResponse(PolicyNumber);
		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException | ParseException
				| URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Function to validate the Endorsement from PAS
	public void ValidateEndorsementFromPAS(String PolicyNumber, String EndorsementDate, String ExpectedStatus) {

		System.out.println("PoliciesFunctions.ValidateEndorsementFromPAS()");

		try {
			String ScenarioName = StaticValue.Empty;
			ScenarioName = currentTestParameters.getScenarioName();
			try {
				endorsementWebservice.rest_getResponse(PolicyNumber, EndorsementDate, ExpectedStatus);
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException | ParseException
				| IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Function to Ensure the EndorsementPage Loaded
	public void chkEndorsementPageLoaded() {

		action.waitForElement(makepolicychangespage.WhatWouldYouLikeToDoTodayElm);
        action.focusonElementTakeScreenshot(makepolicychangespage.WhatWouldYouLikeToDoTodayElm);
		assertTrue(driver.getCurrentUrl().contains("endorsement"), "endorsement not loaded sucessfully");

	}

	public void validatePolicyDetails(String PolicyNumber) {

		System.out.println("PolicyPageFunctions.validatePolicyDetails()");
		TotalNoOfPolicy = paymentsfunction.FindTheTotalNoOfAvailablePolicy();
		System.out.println("PolicyPageFunctions.validatePolicyDetails() :" + TotalNoOfPolicy + ":");
		String WebPolicyNumber = action.GetText(policypage.PolicyNumberElm());
		if (!WebPolicyNumber.equals(PolicyNumber)) {
			action.focusonElementinMiddleTakeScreenshot(policypage.PolicyNumberElm());
			fail(" The given policy number is not available");

		}

	}

	public void chkMakePolicyChangeButton(String option) {
		// if
		// (DriverManager.properties.getProperty("MobileExecutionType").equalsIgnoreCase("Desktop_Responsive"))
		// {
		// action.ClickJSElement(policypage.Mobile_PoliciesSectionArrow,
		// "Mobile_PoliciesSectionArrow");
		//
		// if (option.equals(StaticValue.IsPresent)) {
		// currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		// // action.waitForElement(policypage.MakePolicyChangeButtonVisibleElm);
		// isDisplayed(policypage.Mobile_MakePolicyChangeButtonVisibleElm,
		// StaticValue.MakePolicyChanges);
		// isEnabled(policypage.Mobile_MakePolicyChangeButtonVisibleElm,
		// StaticValue.MakePolicyChanges);
		// if (isDisplayed(policypage.MakePolicyChangeButtonVisibleElm,
		// StaticValue.MakePolicyChanges)
		// && isEnabled(policypage.MakePolicyChangeButtonVisibleElm,
		// StaticValue.MakePolicyChanges)) {
		// currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		// fail("<<< 'Make Policy Change ' Button with id of desktop is found which is
		// not as expected >>>");
		// }
		// } else {
		// if (IsObjectPresentWithoutWait(policypage.MakePolicyChangeNotVisibleElm,
		// StaticValue.MakePolicyChanges)
		// ||
		// IsObjectPresentWithoutWait(policypage.Mobile_MakePolicyChangeButtonVisibleElm,
		// StaticValue.MakePolicyChanges)) {
		// currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		// System.out.println("<<< 'Make Policy Change ' Button not visible as Expected
		// >>>");
		// } else {
		// currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		// fail("<<< 'Make Policy Change ' Button found which is not as expected >>>");
		// }
		// }
		//
		// } else {
		// if (option.equals(StaticValue.IsPresent)) {
		// currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		// // action.waitForElement(policypage.MakePolicyChangeButtonVisibleElm);
		// isDisplayed(policypage.MakePolicyChangeButtonVisibleElm,
		// StaticValue.MakePolicyChanges);
		// isEnabled(policypage.MakePolicyChangeButtonVisibleElm,
		// StaticValue.MakePolicyChanges);
		// if (isDisplayed(policypage.Mobile_MakePolicyChangeButtonVisibleElm,
		// StaticValue.MakePolicyChanges)
		// && isEnabled(policypage.Mobile_MakePolicyChangeButtonVisibleElm,
		// StaticValue.MakePolicyChanges)) {
		// currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		// fail("<<< 'Make Policy Change ' Button with id of mobile device is found
		// which is not as expected >>>");
		// }
		// } else {
		// if (IsObjectPresentWithoutWait(policypage.MakePolicyChangeNotVisibleElm,
		// StaticValue.MakePolicyChanges)
		// ||
		// IsObjectPresentWithoutWait(policypage.Mobile_MakePolicyChangeButtonVisibleElm,
		// StaticValue.MakePolicyChanges)) {
		// System.out.println("<<< 'Make Policy Change ' Button not visible as Expected
		// >>>");
		// currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		// } else {
		// currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		// fail("<<< 'Make Policy Change ' Button found which is not as expected >>>");
		// }
		// }
		// }

		if (isDisplayed(policypage.MakePolicyChangeButtonVisibleElm, StaticValue.MakePolicyChanges)
				|| isDisplayed(policypage.Mobile_MakePolicyChangeButtonVisibleElm, StaticValue.MakePolicyChanges)) {
			if (option.equals(StaticValue.IsNotPresent)) {
				System.out.println("<<<  'Make Policy Change ' Button visible which is not Expected  >>>");
				action.focusonElementinMiddleTakeScreenshot(policypage.MailingAddressText);
				fail("<<<  'Make Policy Change ' Button visible which is not Expected   >>>");
			} else {
				System.out.println("<<<  'Make Policy Change ' Button visible as Expected  >>>");
				if (isDisplayed(policypage.MakePolicyChangeButtonVisibleElm, StaticValue.MakePolicyChanges)){
					action.focusonElementinMiddleTakeScreenshot(policypage.MakePolicyChangeButtonVisibleElm);
				} else {
					action.focusonElementinMiddleTakeScreenshot(policypage.Mobile_MakePolicyChangeButtonVisibleElm);
				}
				
			}

		} else {
			if (option.equals(StaticValue.IsPresent)) {
				System.out.println("<<<  'Make Policy Change ' Button not visible which is not Expected  >>>");
				action.focusonElementinMiddleTakeScreenshot(policypage.MailingAddressText);
				fail("<<<  'Make Policy Change ' Button not visible which is not as expected   >>>");
			} else {
				System.out.println("<<<  'Make Policy Change ' Button not visible as Expected  >>>");
				action.focusonElementinMiddleTakeScreenshot(policypage.MailingAddressText);
			}

		}

	}

	public void clickMakePolicyChangeButton() {
		if (action.isDisplayed(policypage.Mobile_MakePolicyChangeButtonVisibleElm,
				"Mobile_MakePolicyChangeButtonVisibleElm ")) {
			//action.focusonElementinMiddleTakeScreenshot(policypage.Mobile_MakePolicyChangeButtonVisibleElm);
			action.ClickJSElement(policypage.Mobile_MakePolicyChangeButtonVisibleElm,
					"Mobile_MakePolicyChangeButtonVisibleElm");
		} else if (action.isDisplayed(policypage.MakePolicyChangeButtonVisibleElm,
				"MakePolicyChangeButtonVisibleElm ")) {
			//action.focusonElementinMiddleTakeScreenshot(policypage.MakePolicyChangeButtonVisibleElm);
			action.ClickJSElement(policypage.MakePolicyChangeButtonVisibleElm, "MakePolicyChangeButtonVisibleElm");
		}

	}

}