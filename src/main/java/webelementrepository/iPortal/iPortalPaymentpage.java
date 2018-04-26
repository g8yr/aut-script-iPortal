package webelementrepository.iPortal;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import stepDefinitions.common.MasterStepDefs;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.Webaction;

public class iPortalPaymentpage extends MasterStepDefs
{
	static Logger log = Logger.getLogger(iPortalPaymentpage.class);
	WebDriver driver = DriverManager.getWebDriver();
	Webaction action = new Webaction();
	
	public By AutoPayConfirmMsgeElm() {
		return By.xpath("//*[@id='autoPaySuccess']");
	}
	
	public By StopAutoPayConfirmMsgeElm() {
		return By.xpath("//*[@class='payment-success autopay-stop-success']");
	}
	
	public By AUTOPAY_BUTTON = By.xpath("//*[@id='payments-id']//ul//li/a[.='Set up Automatic Payments']"); 
	public By CARD_NAME = By.xpath("//*[@id='payments-autopay']/div//div[3]/form//fieldset[@class='creditCardTpl bankAccountTpl']/input");
	public By CREDIT_CARD_NUMBER = By.xpath("//*[@id='payments-autopay']/div//div[3]/form//fieldset[@class='creditCardTpl']/input");
	public By CREDIT_CARD_ZIPCODE = By.xpath("//*[@id='payments-autopay']/div//div[3]/form//fieldset[@class='zip-code creditCardTpl']/input");
	public By CREDIT_CARD_EXPIRATION_DATE = By.xpath("//*[@id='payments-autopay']/div//div[3]/form//fieldset[@class='exp-date creditCardTpl']/input");
	public By CARD_EMAIL = By
			.xpath("//*[@id='payments-autopay']/div//div[3]/form//fieldset[@class='payment-confirmation-email-fieldset email creditCardTpl bankAccountTpl agencySweep']/input");
	public By ROUTING_NUMBER = By.xpath("//*[@id='payments-autopay']/div//div[3]/form//fieldset[@class='routing help bankAccountTpl']/input");
	public By ACCOUNT_NUMBER = By.xpath("//*[@id='payments-autopay']/div//div[3]/form//fieldset[@class='bankAccountTpl help']/input");
	
	public By AUTOPAY_TERMS = By.xpath("//*[@id='payments-autopay']/div//div[3]/form//div[@class='req-docs']/input");
	public By CARD_ACH_DETAILS_SAVE = By.xpath("//*[@id='payments-autopay']/div//div[3]/form//div[@class='payment-options']//a[@id='autopayBtn']");
	public By CLOSE_AUTOPAY_POPUP = By.xpath("//*[@id='payments-autopay']//li[@class='close-payment']//a");
	public By STOP_AUTOPAY_BUTTON = By.xpath("//*[.='Stop Automatic Payments']");
	public By STOP_AUTOPAY_CONFIRM_BUTTON = By.xpath("//*[@class='blue-btn stop-autopay-btn']");
	
	public By PAY_BUTTON = By.xpath("//*[@id='payments-id']/div[1]/a");	
	public By CARD_ACH_PAY = By.xpath("//*[@id='payments-autopay']//a[@id='payBtn']");
	public By Amount_Payable =By.xpath("//input[@id='currentBalance']");
	public By Account_Type = By.xpath("//*[@id='payments-autopay']/div//div[3]/form/fieldset[@class='account no-float b2b-segment-control has-value']");
	public By Account_Type_Credit_Card = By.xpath("//div[@id='payments-autopay']//a[.='Credit Card']");
	public By Account_Type_Checking_Account = By.xpath("//*[.='Checking Account']");
	public By Account_Type_Saving_Account = By.xpath("//*[.='Saving Account']");
	public By Account_Type_Debit_Card = By.xpath("//div[@id='payments-autopay']//a[.='Debit Card']");
	
	public By CONFIRMATION_NUMBER = By.xpath("//li[span[.='Confirmation Number']]//em[@id='confirmationNumberAutopay']");
	public By CONFIRMATION_NUMBER_EFT_Pay = By.xpath("//li[span[.='Confirmation Number']]//em[@id='confirmationNumber']");
	public By EFTPAY_CONFIRMATION_TEXT = By.xpath("//*[@id='payment-success']");
	
	public By POLICY_TOTAL_AMOUNT = By.xpath("//*[@id='payments-id']/div[1]/span[2]");
	

}
