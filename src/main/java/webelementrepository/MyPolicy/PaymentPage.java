package webelementrepository.MyPolicy;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import stepDefinitions.common.MasterStepDefs;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.Webaction;

public class PaymentPage extends MasterStepDefs {

	static Logger log = Logger.getLogger(PaymentPage.class);
	WebDriver driver = DriverManager.getWebDriver();
	Webaction action = new Webaction();
	

	public By PolicyDetailContainerElm() {
		return By.xpath("//div[@class='policy-details-container']");
	}
	
	public By PolicyDetailMinimumDueElm() {
		return By.xpath("//div[@class='policy-details-minimum-due']");
	}
	
	public By PolicyDetailsPaidInFullElm() {
		return By.xpath("//div[@class='policy-details-paid-in-full']");
	}
	
	
	public By PaymentMethodDropDownElm() {
		return By.xpath("//div[@class='payment-method-dropdown']");
	}
	
	public By PaymentMethodFirstAccountElm() {
		return By.xpath("//div[@aria-activedescendant='react-select-2--option-0']");
	}
	
	public By NameOnCardElm() {
		return By.xpath("//input[@label='Name on Card']");
	}
	
		
	public By CardNumberElm() {
		return By.xpath("//input[@label='Card Number']");
	}
	
	public By ExpiryDateElm() {
		return By.xpath("//input[@label='Expiration (MM/YYYY)']");
	}
	
	public By ZipCodeElm() {
		return By.xpath("//input[@label='Zip Code']");
	}

	public By NickNameElm() {
		return By.xpath("//input[@label='Nickname']");
	}

	public By ContinueElm() {
		return By.xpath("//button[@value='Continue']");
	}
	
	public By PaymentMethodPayElm() {
		return By.xpath("//div[@class='payment-method-button-sub-container']//button[@value='Pay']");
	}
	
	public By ConfirmationPayElm() {
		return By.xpath("//div[@class='confirmation-modal-button-container']//button[@value='Pay']");
	}
	
	public By SucessmessageElm() {
		return By.xpath("//div[@class='payment-detail-success-message']/div/div");
	}
	public By AutoPayConfirmMsgeElm() {
		return By.xpath("//div[contains(@class,'message-confirmation')]/div");
	}
	public static final String PolicyDetailContainer = "//div[@class='policy-details-container']";
	public static final String PolicyDetailsMinimumDue = "//div[@class='policy-details-minimum-due']";
	public static final String PolicyDetailsPaidInFull = "//div[@class='policy-details-paid-in-full']";
	public static final String PolicyDetailsChkBoxInd = "//div[@class='one-ui-checkbox']";
	public static final String PolicyDetailsAmount	 = "//input[@label='Amount']";
	public static final String PaymentMethodFirstAccount	 = "//div[@aria-activedescendant='react-select-2--option-0']";
	public static final String PaymentMethodContainsText	 = "//div[contains(text(),'";
	
	public By policyDisplaylocator() {
	//	return By.xpath("//div[@id='aaaiePaymentsRenderHook']//li");
		return By.xpath("//div[@class='policy-details-container']");
	}
	public static final By AutoPaylink_Updated = By.xpath("//a[.='AutoPay Settings']");
	public static final By AutoPay_PopUp = By.xpath("//div[.='AutoPay Settings']");
	
	
	
	//public static final String Policy_Display_number = "//li[%s]//a[@title='Pay toward
	//div[.='AutoPay Settings']s policy %s']";
	public static final String Policy_Display_number = "//div[@class='policy-details-container'][%s]//input[@id='%s-checkbox']";
	
	String AutoPaylink = "//ul//div[.='%s']/parent::div/following-sibling::span";
	
	String ChangePaymentMethod = "//ul//div[.='%s']/parent::div/following-sibling::span[.='Change Payment Method']";
	String AutoPayindicator = "//ul//div[.='%s']/parent::div/parent::li//preceding-sibling::li/div[.='On AutoPay']";
	By PaymentMethodField = By.xpath("//div[@data-aaaie-dropdown-content-id='paymentType']//div[3]");
	public static final By PaymentMethodField_updated = By.xpath("//div//span[@id='react-select-3--value']");
	By AutoPayOff = By.xpath("*[@id='autopayOnOff']/label");
	public static final By AutoPayOff_Updated = By.xpath("//div//a[.='STOP AUTOPAY']");
	public static final By AutoPay_Popup_Confirm = By.xpath("//div//button[.='Stop AutoPay']");
	public static final By AutoPay_popup_next = By.xpath("//button[.=' Next']");
	public static final String Stop_AutoPay_popup_Policy_display = "//div[@data-index='%s']//span[.=' On']";
	public static final String Set_AutoPay_popup_Policy_display = "//div[@data-index='%s']//span[.=' Off']";
	// String AutoPay_popup_display_three_policy ="//div[@aria-label='AutoPay
	// Settings']//div[@class='auto-pay-settings-container']//div//div[i]/div[1]";
	public static final String AutoPay_popup_display_three_policy = "//div[@class='auto-pay-settings-container']//div//div[%s]";
	public static final By AutoPay_popup_close = By.xpath("//button[@class='close-modal']");
	public static final By AutoPay_Setup_text = By.xpath("//div[.='You have successfully unenrolled from AutoPay.']");
	public static final By Autopay_Remove_Confirmation_text = By.xpath("//div[.='You have successfully unenrolled from AutoPay and will no longer receive the eValue discount.']");

	By Account_Type_Credit_Card = By.xpath("//li[@data-value='CreditCard']");
	By Account_Type_Checking_Account = By.xpath("//li[@data-value='CheckingAccount']");
	By Account_Type_Saving_Account = By.xpath("//li[@data-value='SavingsAccount']");
	By CREDIT_CARD_NUMBER = By.xpath("//li//input[@id='cc-number']");
	By CARD_NAME = By.xpath("//li//input[@id='nameOnCard']");
	By CREDIT_CARD_EXPIRATION_DATE = By.xpath("//li//input[@id='expDate']");
	By CREDIT_CARD_ZIPCODE = By.xpath("//li//input[@id='zipcode']");

	By BANK_ACCOUNT_NAME = By.xpath("//li//input[@id='accountHolderName']");
	By ROUTING_NUMBER = By.xpath("//li//input[@id='routingNumber']");
	By ACCOUNT_NUMBER = By.xpath("//li//input[@id='accountNumber']");

	By CARD_ACH_DETAILS_SAVE = By.xpath("//div[@id='payments-autopay-save']/a");

	// Updated ID

	By ManagePaymentMethod = By.xpath("//li//a[.='Manage Payment Method']");
	By Add_Account = By.xpath("//div//button[.='Add Account']");
	// By
	// PaymentMethodFieldUpdated=By.xpath("//div[@class='payment-method-dropdown']//span//div//span[@id='react-select-4--value-item']");
	By PaymentMethodFieldUpdated = By.xpath("//div[@class='payment-method-dropdown']//span[@class='Select-arrow'][1]");

	By Savings_Account_link = By.xpath("//div[@aria-activedescendant='react-select-3--option-2']");
	// By Savings_Account_link=By.xpath("//div[@class='Select-input']");
	// By
	// Savings_Account_link=By.xpath("//span//div[@aria-activedescendant='react-select-2--option-1']");
	By Account_Holder_Name1 = By.xpath("//div//input[@label='Account Holder Name']");
	By ROUTING_NUMBER1 = By.xpath("//div//input[@label='Routing Number']");
	By ACCOUNT_NUMBER1 = By.xpath("//div//input[@label='Account Number']");
	By Nick_Name = By.xpath("//div//input[@label='Nickname']");
	By Continue_Button = By.xpath("//button[.='Continue']");
	public static final By Save_button = By.xpath(".//button[.='Save']");

	public static By Savings_Account_link_Updated = By.xpath("//div//span[.='New Savings Account']");
	public static By Account_Holder_Name1_Updated = By.xpath("//div//input[@label='Account Holder Name']");
	public static By ROUTING_NUMBER1_Updated = By.xpath("//div//input[@label='Routing Number']");
	public static By ACCOUNT_NUMBER1_Updated = By.xpath("//div//input[@label='Account Number']");
	public static final By Nick_Name_Updated = By.xpath("//div//input[@label='Nickname']");
	public static By Continue_Button_Updated = By.xpath("//button[.='Continue']");

	public static final By CARD_NAME1 = By.xpath("//div//input[@label='Name on Card']");
	public static final By CREDIT_CARD_NUMBER1 = By.xpath("//div//input[@label='Card Number']");
	public static final By CREDIT_CARD_EXPIRATION_DATE1 = By.xpath("//div//input[@label='Expiration (MM/YYYY)']");
	public static final By CREDIT_CARD_ZIPCODE1 = By.xpath("//div//input[@label='Zip Code']");
	
	
	

	
	
	
	

}