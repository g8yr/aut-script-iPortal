package stepDefinitions.MyPolicy;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import stepDefinitions.common.MasterStepDefs;
import webelementrepository.MyPolicy.LoginPage;
import businesscomponents.mypolicy.LoginFunctions;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.StaticValue;
import com.Cucumber.supportLibraries.Util;
import com.Cucumber.supportLibraries.Webaction;
import com.Cucumber.supportLibraries.custom.ReadCSVFileFunctions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
//import com.Cucumber.Transform.ExcelDataToDataTable;

public class LoginStepDefs extends MasterStepDefs {

	static Logger log = Logger.getLogger(LoginStepDefs.class);
	WebDriver driver = DriverManager.getWebDriver();
	LoginPage loginpage = new LoginPage();
	LoginFunctions loginfunctions = new LoginFunctions();
	Webaction action = new Webaction();
	ReadCSVFileFunctions readCSVfilefunctions = new ReadCSVFileFunctions();
	String userId = StaticValue.Empty;
	String password = StaticValue.Empty;
	public String Iteration;
	
	@Given("^I am in the login page of the application$")
	public void i_am_in_login_page() {
		driver.get("https://mypolicy-evalue.digital.pncie.com/login");
		driver.manage().window().maximize();
		currentScenario.embed(Util.takeScreenshot(driver),
				"image/png");

		assertTrue(driver.getTitle().contains("Welcome"));
		//assertTrue(driver.getTitle().contains("My Policy")
		//		|| driver.getTitle().contains("Sign-on"));
		
	}
	@When("^I visit the login page of the application$")
	public void LoginPage() throws IOException {
		loginfunctions.LoadloginPage();
		//GenStep.LoadloginPageWithID();	
	}
	@When("^I visit the login page of the application using id$")
	public void LoginPageid() {
		loginfunctions.LoadloginPageWithID();
				
	}
	
	@When("^I login using the valid username and the valid password from csv$")
	public void LoginWithCSVCredentials() {
		System.out.println("LoginWithCSVCredentials()");
		loginfunctions.entercredential();
		
	}
	
	@When("^I login using the valid username (.*) and the valid password (.*)$")
	public void LoginWithCredentials(String userName, String password) {
		System.out.println("LoginStepDefs2.LoginWithCredentials()");
		loginfunctions.enterLoginDetails(userName, password);
		
	}

	@When("^I login using the valid credentials (.*)$")
	public void LoginPage(String Iteration) {
		System.out.println("LoginPage( )");
		System.out.println("LoginPage( )");
		userId = readCSVfilefunctions.readCsvFile(StaticValue.UserId, Iteration);
		password = readCSVfilefunctions.readCsvFile(StaticValue.Password, Iteration);
		action.waitForElement(loginpage.LoginEmailElm());	
		action.EnterText    (loginpage.LoginEmailElm()   , userId);		
		action.EnterText    (loginpage.LoginPasswordElm(), password);
		action.ClickElement (loginpage.LoginSignInElm());
				
	}
}
