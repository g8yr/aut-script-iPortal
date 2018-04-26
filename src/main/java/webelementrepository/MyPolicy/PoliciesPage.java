package webelementrepository.MyPolicy;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import stepDefinitions.common.MasterStepDefs;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.Webaction;

public class PoliciesPage extends MasterStepDefs {

	static Logger log = Logger.getLogger(PoliciesPage.class);
	WebDriver driver = DriverManager.getWebDriver();
	Webaction action = new Webaction();
	
	
	public By PolicyDetailContainerElm() {
		return By.xpath("//div[@class='policy-detail-row-holder policy-detail-container']");
	}
	
	public By PolicyNumberElm() {
		return By.xpath("//li[@class='policy-number']/div");
	}
	
	public By MakePolicyChangeVisibleElm() {
		return By.xpath("//button[@class='endorsement-button' and @style='']");
	}
	public By Mobile_PoliciesSectionArrow= By.xpath("//div[@id='cards-holder']//div[contains(@class,'arrow-right')]");
	//public By MakePolicyChangeButtonVisibleElm	 = By.xpath("//div[contains(@class,'columns policy-info-col-1')]//button[contains(@class,'endorsement-button')]");
	public By MakePolicyChangeButtonVisibleElm	 = By.xpath("//button[contains(@class,'endorsement-button-large')]");
	public By Mobile_MakePolicyChangeButtonVisibleElm	 = By.xpath("//button[contains(@class,'endorsement-button-small')]");
	
	public By MakePolicyChangeNotVisibleElm = By.xpath("//button[@class='endorsement-button' and @style='display:none']");

	public By MailingAddressText = By.xpath("//div[@id='PI-fname-lname-data-block']");	
	
	By PolicyIdentifier1 = By.xpath("//aaaie-policies-card[@data-analytics-label='PoliciesCard']//div[@data-url='autoPolicyDetails?policyNumber=");
	By PolicyIdentifier2 = By.xpath("&fromPage=policies']");	
	
	//Policies Page
	public static String Policies_Displayed = "//*[@id='cards-holder']//div//ul//li//div[.='%s']";
	public static By Policies_Section = By.xpath("//li//a[.='Policies']");
	public static String Discount_Section = "//*[@class='card-content']/ul/li";

}