package webelementrepository.iPortal;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import stepDefinitions.common.MasterStepDefs;

import com.Cucumber.supportLibraries.DriverManager;

import io.appium.java_client.AppiumDriver;

public class iPortalPaperlessPreferencesPage 
{
	static Logger log = Logger.getLogger(iPortalPaperlessPreferencesPage.class);
	
	public By EDIT_PREFERENCES = By.xpath("//*[.='EDIT']");
	
	public By Policy_Document_US_Mail_ID = By.xpath("//button[@id='selectMailDocuments']");
	public By Policy_Document_EMail_ID = By.xpath("//button[@id='selectEmailDocuments']");
	public By Billing_Payment_US_Mail_ID = By.xpath("//button[@id='selectMailPayments']");
	public By Billing_Payment_EMail_ID = By.xpath("//button[@id='selectEmailPayments']");
	
	public By Save_button = By.xpath(".//button[.='Save']");
	
	public By PREFERENCE_ENROLLMENT_PENDING_TEXT = By.xpath("//div//p[.='The customer must confirm enrollment via the email sent.']");
	public By PREFERENCE_EMAIL = By.xpath("//div/aaaie-b2b-paperless-settings/div/div[1]/div[10]/ul/li[3]/div[2]");
	public By Preference_Alert_Pop_Up = By.xpath("//*[@id='evalue-discount-warning']/div[2]/p");
	public By Preference_Cancel = By.xpath("//*[@id='paperless-settings-cancel-prefs']");
	public By Get_Policy_Preference_Status = By.xpath("//div[.='Policy Documents']//parent::li//div[2]");
	public By Get_Billing_Preference_Status = By.xpath("//div[.='Billing & Payments']//parent::li//div[2]");
	public By Get_Billing_Preference_Text = By.xpath("//*[@class='reveal-modal row paperless-preferences-edit open']/div[3]/ul/li[2]/div[1]/p");
	
	public By Edit_Email = By.xpath("//input[@id='optionsEmail']");
	public By Edit_Email_Clear = By.xpath("//div[@id='clearOptionsEmail']");	
}
