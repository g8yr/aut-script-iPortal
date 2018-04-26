package webelementrepository.MyPolicy;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import stepDefinitions.common.MasterStepDefs;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.Webaction;

public class MakePolicyChangesPage extends MasterStepDefs {

	static Logger log = Logger.getLogger(MakePolicyChangesPage.class);
	WebDriver driver = DriverManager.getWebDriver();
	Webaction action = new Webaction();
	
	public By EnodorsementpageTitleElm = By.xpath("//div[@class='card-title']//h3");
	public By WhatWouldYouLikeToDoTodayElm = By.xpath("//div[@class='allowed-endorsements-container']//div[contains(@class,'card-question')]//p");
	//public By AddRemoveVechicleElm = By.xpath("//div[@class='policies-row small-12 large-4 columns card-text-block vehicle-management']");
	public By AddRemoveVechicleElm = By.xpath("//div[@class='allowed-endorsements-container']//button[contains(@class,'vehicle-management')]");
	public By AddRemoveVechicleImageElm = By.xpath("//div[@class='allowed-endorsements-container']//li[@class='vehicle-image']");
	public By AddRemoveVechicleTextElm = By.xpath("//div[@class='allowed-endorsements-container']//strong[@class='action-text']");
	public By DescriptionTextElm = By.xpath("//div[@class='your-policy']//p[@class='description-title']");
	public By DescriptionSubTextElm = By.xpath("//div[@class='your-policy']//p[@class='sub-text']");
	public By DisclaimerElm1 = By.xpath("//div[@class='allowed-endorsements-container']/div[3]//div[contains(@class,'card-text-block disclaimer')]/p[1]");
	public By DisclaimerElm2 = By.xpath("//div[@class='allowed-endorsements-container']/div[3]//div[contains(@class,'card-text-block disclaimer')]/p[2]");
	
	
	//public By AllowedEndorsementContainerElm = By.xpath("//div[@class='allowed-endorsements-container']");
	//public By CardContentElm = By.xpath("//div[@class='card-content']");
	
	
	public By VehicleModelElm = By.xpath("//span[contains(@class,'vehicle-model')]");
	
	public By CardContentPolicyNumberElm = By.xpath("//span[@id='policyNumber']");
	//public By VehicleSecDescriptionTextElm = By.xpath("//div[@class='small-12 large-12 columns card-text-block']//div[2]//div[1]//span[1]");
	//public By VehicleSecDescriptionSubTextElm = By.xpath("//div[@class='small-12 large-12 columns card-text-block']//div[2]//div[1]//span[2]");
	public By VehicleSecDescriptionTextElm = By.xpath("//div[contains(@class,'vehicle-list')]//div[2]//div[1]//span[1]");
	public By VehicleSecDescriptionSubTextElm = By.xpath("//div[contains(@class,'vehicle-list')]//div[2]//div[1]//span[2]");
	public By VehicleVINModelDetails = By.xpath("//div[@class='small-12 large-12 columns card-text-block']//div[2]//div[2]//span[1]");
	public By VehicleCount = By.xpath("//div[contains(@class,'vehicle-list')]//span[@class='vehicle-info']");
	public By AddVehicleHyperlink = By.xpath("//div[contains(@class,'vehicle-list')]//span[.=' ADD VEHICLE ']");
	public By AddVehicleHyperlinktext = By.xpath("//div[contains(@class,'vehicle-list')]//span[.=' ADD VEHICLE ']/parent::div//Span[2]");
	public By AddVehicleDrawerViewSection = By.xpath("//div[contains(@class,'add-vehicle-lower-section')]");
	public By AddVehicleDrawerViewText = By.xpath("//div[contains(@class,'add-vehicle-lower-section')]//strong");
	public By PurchaseDateTextBox =By.xpath("//div[contains(@class,'add-vehicle-lower-section')]//input[@id='purchaseDate']");
	public By PurchaseDateSubText =By.xpath("//div[contains(@class,'add-vehicle-lower-section')]//div[@class='one-ui-date-input']/following-sibling::div");
	public By PurchaseDateCaption =By.xpath("//div[contains(@class,'add-vehicle-lower-section')]//label[@for='purchaseDate']/span");
	public By PurchaseDateFormat =By.xpath("//div[contains(@class,'add-vehicle-lower-section')]//span[@class='one-ui-date-input--format-span']");
	public By PurchaseDateErrorID=By.xpath("//div[contains(@class,'add-vehicle-lower-section')]//div[@class='one-ui-date-input']/div/div");
	public By VINTextBox =By.xpath("//div[contains(@class,'add-vehicle-lower-section')]//input[@id='vinField']");
	public By VINCaption =By.xpath("//div[contains(@class,'add-vehicle-lower-section')]//label[@for='Vehicle Identification Number (VIN)']/span");
	public By VINSubText =By.xpath("//div[contains(@class,'add-vehicle-lower-section')]//div[contains(@class,'vinField')]/following-sibling::div");
	public By VINErrorID=By.xpath("//div[contains(@class,'message-visible message-error')]//div");
	public By AddVehiclePageNEXTButtonDisabled =By.xpath("//div[contains(@class,'add-vehicle-lower-section')]//input[@id='validateAddVehicleForm' and @class='right false']");
	public By AddVehiclePageNEXTButtonEnabled =By.xpath("//div[contains(@class,'add-vehicle-lower-section')]//input[@id='validateAddVehicleForm' and @class='right next-enabled']");
	public By AddVehiclePageNEXTButton =By.xpath("//div[contains(@class,'add-vehicle-lower-section')]//input[@id='validateAddVehicleForm']");
	public By AddVehicleCollapseArrow =By.xpath("//div[@class='policies-row add-vehicle-container']//*[name()='svg']//*[name()='use']");
	//public By CollapseArrow =By.cssSelector("#cards-holder > svg > use");
	//driver.findElement(By.cssSelector("#imageholder > svg > g > image"))  cards-holder;
	public By VechicleListTitle = By.xpath( "//div[contains(@class,'vehicle-list')]//div[@class='card-title']//h3");
	
	
	
	public static final String VechicleBlockGroup = "//div[contains(@class,'vehicle-list')]//div[contains(@class,'policies-row vehicle-block')]";
	public static final String VechicleModel = "//span[contains(@class,'vehicle-model')]";
	public static final String VechicleVIN = "//span[contains(@class,'vin-box')]";
	
	
	
	

	
	
	
	

}