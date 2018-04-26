package webelementrepository.iPortal;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import stepDefinitions.common.MasterStepDefs;

import com.Cucumber.supportLibraries.DriverManager;

import io.appium.java_client.AppiumDriver;

public class iPortalCustomerOverviewPage {
	static Logger log = Logger.getLogger(iPortalCustomerOverviewPage.class);
	
	public By POLICY_NUMBER = By.xpath("//div[@id='policy-body-desktop']//div[2]/div[2]/div[1]");
	public By Policy_List_Length=By.xpath("//*[@id='policy-body-mobile']/li");
	//public String Policy_List="//*[@id='policy-body-mobile']/li[%s]//div[@class='policy-nr']";
	public String Policy_List="//*[@id='policy-body-desktop']/ul[1]/li[%s]//div[@class='policy-nr']";
	 
	
}
