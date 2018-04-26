package stepDefinitions.iPortal;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import stepDefinitions.common.MasterStepDefs;
import businesscomponents.iportal.iPortalLoginFunctions;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.IPortalStaticValue;
import com.Cucumber.supportLibraries.Webaction;
import com.Cucumber.supportLibraries.custom.ReadCSVFileFunctions;

import cucumber.api.java.en.When;
//import com.Cucumber.Transform.ExcelDataToDataTable;

public class iPortalLoginStepDefs extends MasterStepDefs {

	static Logger log = Logger.getLogger(iPortalLoginStepDefs.class);
	WebDriver driver = DriverManager.getWebDriver();	
	iPortalLoginFunctions iPortalloginfunctions = new iPortalLoginFunctions();
	Webaction action = new Webaction();
	ReadCSVFileFunctions readCSVfilefunctions = new ReadCSVFileFunctions();
	String userId = IPortalStaticValue.Empty;
	String password = IPortalStaticValue.Empty;
	public String Iteration;
	
	@When("^I visit the login page of the iPortal application using id$")
	public void LoginPageid() {
		iPortalloginfunctions.loadiPortalLoginPage();
				
	}
	
	@When("^I login iPortal application using the valid username (.*) and the valid password (.*)$")
	public void LoginWithCredentials(String userName, String password) {
		System.out.println("LoginStepDefs.LoginWithCredentials()");
		iPortalloginfunctions.enterLoginDetails(userName, password);
		
	}

}
