package webelementrepository.MyPolicy;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public interface DashboardPage  {

	static Logger log = Logger.getLogger(DashboardPage.class);

	public default By LefNavPayment() {
		return By.xpath("//div[@id='main-container']//a[@href='transactions']");
	}
	
	

	By LefNavPayment=By.xpath("//div[@id='main-container']//a[@href='transactions']");
	By Dashboard_policies = By.xpath("//*[@id='cards-holder']/div[2]/aaaie-policies-card/div[2]/div/div/div/div/ul/li/div[1]/ul/li[1]");
	//By Policies_Section = By.xpath("//li//a[.='Policies']");
	By Policies_Section = By.xpath("//div[@id='main-container']//a[@href='policies']");
	By Dashboard_Section = By.xpath("//li//a[.='Dashboard']");
	String Policies_Displayed = "//*[@id='cards-holder']//div//ul//li//div[.='%s']";
//	String PolicyIdentifier1 = "//div[@id='cards-holder']//div[@data-url='autoPolicyDetails?policyNumber=";
//    String PolicyIdentifier2 = "&fromPage=policies']";
	By Mobile_Menu_Link = By.xpath("//div[@id='mobile-header']//div[contains(@class,'menu-icon')]");
	//By Mobile_Policies_Section = By.xpath("//a[@href='policies']");
	By Mobile_Policies_Section = By.xpath("//section[@id='mobile-nav']//a[@href='policies']");
	
}