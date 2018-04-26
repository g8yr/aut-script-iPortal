package webelementrepository.iPortal;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import stepDefinitions.common.MasterStepDefs;

import com.Cucumber.supportLibraries.DriverManager;

import io.appium.java_client.AppiumDriver;

public class iPortalDashboardPage {
	static Logger log = Logger.getLogger(iPortalDashboardPage.class);
	
	public By POLICY_SEARCH_TEXT = By.id("elasticSearch");
	//public By POLICY_SEARCH_BUTTON = By.xpath("//*[@class='core-search-div-grey']/span[@class='typeahead-button core-search core-search-medium']");
	public By POLICY_SEARCH_BUTTON = By.xpath("//div[@id='wms-elastic-search-container']/div/span[@class='typeahead-button core-search core-search-medium']");
	public By POLICY_SEARCH_RESULT = By.xpath("//*[@id='results']//div/div/strong");
	public By POLICY_SEARCH_RESULT_CLICK = By.xpath("//*[@id='results']//div//div[@class='arrow core-arrow-right-small']");
	public By SHOW_MORE_LINK = By.xpath("//span[.='Show More']");
	public By POLICY_SEARCH_RESULT_EXPIRE_ICON_CLICK = By.xpath("//div[@class='policy-table-detail clearfix']");
	public By Discount_Section = By.xpath("//ul[@class='discount-details card']/li/em/strong[.='policy discounts']");
	public By Close_Quicklink = By.xpath("//a[@class='white_core-close-white-white quick-links-close quick-links-switch']");
	public By QuickLink = By.xpath("//div[@class='quick-links-switch quick-links-button outer']");
	
}
