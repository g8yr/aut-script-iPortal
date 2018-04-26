package businesscomponents.mypolicy;

import static org.junit.Assert.fail;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.ParseException;
import org.openqa.selenium.By;
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


public class PoliciesFunctions extends Webaction  {
	WebDriver driver = DriverManager.getWebDriver();
	int cnt, TotalNoOfPolicy = StaticValue.zero;
	PoliciesPage policypage = new PoliciesPage();
	Webaction action = new Webaction();
	GeneralFunctions GenFns = new GeneralFunctions();
	int TotalNoOfPolicyWithMinimumDue = StaticValue.zero;
	int TotalNoOfPolicyPaidFull = StaticValue.zero;
	int CntOfPolicyWithMinimumDueForGivenInstance = StaticValue.zero;
	SoftAssert sa = new SoftAssert();
	String eValuediscount_Apply;
	
	public void validatePolicySection(String PolicyNumber) {
	//	String PolicyNumber = dataTable.getData(testParameter, "Evalue_MyPolicy", "SearchPolicyNumber");
	//	 String PolicyNumber = "VASS900033826";
	//	ClickJSElement(PoliciesPage.Policies_Section, "Dashboard_policies");

		By policies_Displayed = By.xpath(String.format(PoliciesPage.Policies_Displayed, PolicyNumber));
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (isDisplayed(policies_Displayed, "Policies Displayed")) {
			ClickElement(policies_Displayed, "Policies Displayed");
			action.WaitForElement_v0(By.xpath(PoliciesPage.Discount_Section));
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		} 
		
		else {
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			fail("Policy is not displayed in Policy section");
		}

	}

	public void validateDicount() {
		String value2 = null;
		String Discount_displayed = "False";
		eValuediscount_Apply = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), StaticValue.eValueDiscountApply);
		
		int j = NumberOfRows_XPATH((By.xpath(PoliciesPage.Discount_Section)));
		action.focusonElementTakeScreenshot(((By.xpath(PoliciesPage.Discount_Section))));
		for (int i = 1; i <= j; i++) {
			if (isDisplayed(By.xpath("//*[@class='card-content']/ul/li[" + i + "]/ul/li[@class='discount-label']"), "")) {
				value2 = GetTextValue(By.xpath("//*[@class='card-content']/ul/li[" + i + "]/ul/li[@class='discount-label']"), "Discount label section");
				if (value2.contains("eValue")) {
					System.out.println("eValue discount is displayed");
					Discount_displayed = "True";
					break;
				} else {
					continue;
				}
			}
		}

		if (eValuediscount_Apply.equalsIgnoreCase("Yes")) {
			if (Discount_displayed.equalsIgnoreCase("True")) {
				System.out.println("eValue discount is displayed as expected");
			} else {
				fail("eValue discount is not displayed");
			}
		} else {
			if (Discount_displayed.equalsIgnoreCase("True")) {
				fail("eValue discount is displayed for non eValue policy");
			} else {
				System.out.println("eValue discount is not displayed for non eValue policy");
			}
		}

	}
}