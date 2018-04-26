package webelementrepository.iPortal;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import stepDefinitions.common.MasterStepDefs;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.Webaction;

public class iPortalLoginPage extends MasterStepDefs 
{
	static Logger log = Logger.getLogger(iPortalLoginPage.class);
	WebDriver driver = DriverManager.getWebDriver();
	Webaction action = new Webaction();

	public By EMAIL = By.xpath("//*[@id='Form_UserID']");
	public By PASSWORD = By.xpath("//*[@id='Form_Password']");
	public By SIGNIN = By.id("LoginButton");
}