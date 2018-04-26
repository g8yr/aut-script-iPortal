package stepDefinitions.iPortal;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import stepDefinitions.common.MasterStepDefs;
import businesscomponents.iportal.iPortalCustomerOverviewFunctions;
import businesscomponents.iportal.iPortalLoginFunctions;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.IPortalStaticValue;
import com.Cucumber.supportLibraries.Webaction;
import com.Cucumber.supportLibraries.custom.ReadCSVFileFunctions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
//import com.Cucumber.Transform.ExcelDataToDataTable;

public class iPortalCustomerOverviewStepDefs extends MasterStepDefs {

	static Logger log = Logger.getLogger(iPortalCustomerOverviewStepDefs.class);
	WebDriver driver = DriverManager.getWebDriver();	
	iPortalLoginFunctions iPortalloginfunctions = new iPortalLoginFunctions();
	Webaction action = new Webaction();
	ReadCSVFileFunctions readCSVfilefunctions = new ReadCSVFileFunctions();
	iPortalCustomerOverviewFunctions iPortalcustomeroverviewfunctions = new iPortalCustomerOverviewFunctions();
	String userId = IPortalStaticValue.Empty;
	String password = IPortalStaticValue.Empty;
	public String Iteration;
	
	@When("^I see Customer Overview page$")
	public void validateCustomerPageDisplay()
	{
		iPortalcustomeroverviewfunctions.validateCustomerPage();
	}
	
	@Then("^I see the searched policy and the newly created Auto (.*) Home (.*) PUP (.*) policy in the Dashboard$")
	public void iPortalvalidatePolicyList(String Auto_Policy_Number, String HO_Policy_Number, String PUP_Policy_Number)
	{
		iPortalcustomeroverviewfunctions.validatePolicyList(Auto_Policy_Number,HO_Policy_Number,PUP_Policy_Number);
	}

}
