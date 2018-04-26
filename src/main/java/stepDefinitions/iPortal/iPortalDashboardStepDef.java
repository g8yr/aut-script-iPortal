package stepDefinitions.iPortal;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;





import stepDefinitions.common.MasterStepDefs;
import businesscomponents.iportal.iPortalDashboardFunctions;
import businesscomponents.mypolicy.GeneralFunctions;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.IPortalStaticValue;
import com.Cucumber.supportLibraries.Webaction;
import com.Cucumber.supportLibraries.custom.ReadCSVFileFunctions;

import cucumber.api.java.en.And;

public class iPortalDashboardStepDef extends MasterStepDefs 
{

	static Logger log = Logger.getLogger(iPortalDashboardStepDef.class);
	WebDriver driver = DriverManager.getWebDriver();
	iPortalDashboardFunctions iportaldashboardfunction= new iPortalDashboardFunctions();

	ReadCSVFileFunctions readCSVfilefunctions = new ReadCSVFileFunctions();
	GeneralFunctions GenFns = new GeneralFunctions();
	Webaction action = new Webaction();
	private String policynumber;
	
	@And("^I search and navigate To Iportal Policy OverviewPage (.*)$")
	public void iPortalSearchPolicy(String Iteration) 		
	{
		//iportaldashboardfunction.searchIportalPolicy(String PolicyNumber);
		readCSVfilefunctions.readCsvFile(IPortalStaticValue.PolicyNumber, Iteration );		
		policynumber = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), IPortalStaticValue.PolicyNumber);
		iportaldashboardfunction.searchIportalPolicy(policynumber);
		iportaldashboardfunction.navigateToIportalPolicyOverviewPage();
	}

}
