package stepDefinitions.MyPolicy;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import stepDefinitions.common.MasterStepDefs;
import webelementrepository.MyPolicy.DashboardPage;
import businesscomponents.mypolicy.DashboardFunctions;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.StaticValue;
import com.Cucumber.supportLibraries.Util;
import com.Cucumber.supportLibraries.Webaction;

import cucumber.api.java.en.Given;

public class DashboardStepDef extends MasterStepDefs implements DashboardPage {

	static Logger log = Logger.getLogger(DashboardStepDef.class);
	WebDriver driver = DriverManager.getWebDriver();
	DashboardFunctions dashboardfunction= new DashboardFunctions();
	Webaction action = new Webaction();
	

	
	@Given("^I can see DashBoard page$")
	public void validateDashboardPageDisplay()
	{
	dashboardfunction.validateDashboardPage();
	}
	@Given("^I click policies from the left navigation menu$")
	public void ClickPoliciesFromLeftNavMenu() throws Throwable {	
		System.out.println("PageNavigation.ClickPoliciesFromLeftNavMenu()");
		dashboardfunction.clickPolicySection();
	}


	@Given("^I click payments from the left nav menu$")
	public void ClickPaymentsFromLeftNavMenu() throws Throwable {

		action.waitForElement(LefNavPayment,StaticValue.PaymentFromPageNavigationMenu);
		action.HardDelay(7000);
		action.focusonElementTakeScreenshot(LefNavPayment);
		action.ClickJSElement(LefNavPayment,StaticValue.PaymentFromPageNavigationMenu);
		currentScenario.embed(Util.takeScreenshot(driver), "image/png");
	}
	
	/*

	@Given("^I click payments from the left nav menu$")
	public void ClickPaymentsFromLeftNavMenu() throws Throwable {
		action.ClickElement(LefNavPayment());
	}
*/
}
