package businesscomponents.mypolicy;

import static org.junit.Assert.fail;
import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.apache.tools.ant.util.WeakishReference.HardReference;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import stepDefinitions.common.MasterStepDefs;
import webelementrepository.MyPolicy.PaymentPage;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.StaticValue;
import com.Cucumber.supportLibraries.Util;
import com.Cucumber.supportLibraries.Webaction;

public class PaymentsFunctions extends MasterStepDefs {
	static Logger log = Logger.getLogger(PaymentPage.class);
	static int i;
	WebDriver driver = DriverManager.getWebDriver();
	Webaction action = new Webaction();
	PaymentPage paymentpage = new PaymentPage();
	int TotalNoOfPolicy = StaticValue.zero;
	int TotalNoOfPolicyWithMinimumDue = StaticValue.zero;
	int TotalNoOfPolicyPaidFull = StaticValue.zero;
	int CntOfPolicyWithMinimumDueForGivenInstance = StaticValue.zero;

	// Function to Ensure the Policy Details Loaded
	public void EnsureThePolicyDetailsLoaded() {
		TotalNoOfPolicy = StaticValue.zero;
		TotalNoOfPolicy = FindTheTotalNoOfAvailablePolicy();
		System.out.println("PaymentPage.EnsureThePolicyDetailsLoaded() Total:" + TotalNoOfPolicy);
		if (TotalNoOfPolicy > StaticValue.zero) {
			System.out.println("PaymentPage.EnsureThePolicyDetailsLoaded() Policy Loaded Sucessfully ");
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		} else {
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			fail ("<<<< There is no policies in the payment page >>>> ");
		}
	}

	// Function to Ensure the Policy due amount is GT than zero
	public void EnsureThePolicyAmountGtThanZero(int policyinstant) {
		// TotalNoOfPolicyPaidFull = FindTheTotalNoOfPolicyPaidFull();
		System.out.println("PaymentPage.EnsureThePolicyAmountGtThanZero() Paid Full:" + TotalNoOfPolicyPaidFull);
		// TotalNoOfPolicyWithMinimumDue = FindTheTotalNoOfPolicyWithMinimumDue();
		// System.out.println("PaymentPage.EnsureThePolicyAmountGtThanZero() Minimum
		// Due:" + TotalNoOfPolicyWithMinimumDue);
		if (TotalNoOfPolicyPaidFull == TotalNoOfPolicy) {
			System.out.println(
					"PaymentsFunctions.EnsureThePolicyAmountGtThanZero() : No Due amount for any of the Available policy");
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		} else {
			CntOfPolicyWithMinimumDueForGivenInstance = action
					.fnExistOfElm(ChkExistinaceOfPolicyDueAmountForTheGivenInstanceElm(policyinstant), 4);
			if (CntOfPolicyWithMinimumDueForGivenInstance > StaticValue.zero) {
				System.out.println(
						"PaymentsFunctions.EnsureThePolicyAmountGtThanZero() : Policy Due Amount is Greater than Zero ");
			} else {
				System.out.println(
						"PaymentsFunctions.EnsureThePolicyAmountGtThanZero() : Policy Due Amount is not Grater than Zero ");
			}
		}

	}

	public By ChkExistinaceOfPolicyDueAmountForTheGivenInstanceElm(int instance) {
		String path = BuildXpathForPaymentPage(paymentpage.PolicyDetailContainer, paymentpage.PolicyDetailsMinimumDue,
				instance);
		return By.xpath(path);
	}

	public By PolicyDetailsChkBoxIndForTheGivenInstanceElm(int instance) {
		String path = BuildXpathForPaymentPage(paymentpage.PolicyDetailContainer, paymentpage.PolicyDetailsChkBoxInd,
				instance);
		return By.xpath(path);
	}

	public By PolicyDetailsAmountElm(int instance) {
		String path = BuildXpathForPaymentPage(paymentpage.PolicyDetailContainer, paymentpage.PolicyDetailsAmount,
				instance);
		return By.xpath(path);
	}

	public String BuildXpathForPaymentPage(String path1, String path2, int instance) {
		String AcutualXpath = StaticValue.Null;
		AcutualXpath = path1 + "[" + instance + "]" + path2;
		return AcutualXpath;
	}

	// Function to find the Total number of Available Policy
	public int FindTheTotalNoOfAvailablePolicy() {
		int TotalNoOfPolicy = StaticValue.zero;
		TotalNoOfPolicy = action.fnExistOfElm(paymentpage.PolicyDetailContainerElm(), 10);
		return TotalNoOfPolicy;
	}

	// Function to find the Total number of Policies which has paid full
	public int FindTheTotalNoOfPolicyPaidFull() {
		int TotalNoOfPolicy = StaticValue.zero;
		TotalNoOfPolicy = action.fnExistOfElm(paymentpage.PolicyDetailsPaidInFullElm(), 10);
		return TotalNoOfPolicy;
	}

	// Function to find the Total number of Policies which has minimum due
	public int FindTheTotalNoOfPolicyWithMinimumDue() {
		int TotalNoOfPolicy = StaticValue.zero;
		TotalNoOfPolicy = action.fnExistOfElm(paymentpage.PolicyDetailMinimumDueElm(), 10);
		return TotalNoOfPolicy;
	}

	// Function to find the Given Policy has due amount Greater than Zero
	public int FindTheGivenPolicyHasDueAmount() {
		int TotalNoOfPolicy = StaticValue.zero;
		TotalNoOfPolicy = action.fnExistOfElm(paymentpage.PolicyDetailContainerElm(), 10);
		return TotalNoOfPolicy;
	}

	// Function to select the payment method
	public void SelectThePaymentMethod(String optiontselect) {
		WebElement Selectelement = driver.findElement(By.xpath(paymentpage.PaymentMethodContainsText + optiontselect + "')]"));
		Selectelement.click();

	}

	// Function to select the payment method
	public void VerifyPaymentSuccess() {
		action.waitForElement(paymentpage.SucessmessageElm());
		action.focusonElementTakeScreenshot(paymentpage.SucessmessageElm());
		String SucessMessage = action.GetText(paymentpage.SucessmessageElm());		
		Assert.assertTrue("PaymentNotScuess", SucessMessage.contains(StaticValue.PaymentSuccessMessage));
		
	}

	// Function to get the policy Sequence Number as per My policy portal
	public int GetPolicySequenceNumber(String policyInstance) {
		int seqNum = StaticValue.zero;
		switch (policyInstance) {
		case StaticValue.first:
			seqNum = StaticValue.one;
			break;
		case StaticValue.second:
			seqNum = StaticValue.two;
			break;
		case StaticValue.third:
			seqNum = StaticValue.three;
			break;
		case StaticValue.fourth:
			seqNum = StaticValue.four;
			break;
		case StaticValue.fifth:
			seqNum = StaticValue.five;
			break;
		case StaticValue.sixth:
			seqNum = StaticValue.six;
			break;
		case StaticValue.seventh:
			seqNum = StaticValue.seven;
			break;
		}
		return seqNum;

	}

	
	// Function to get the payment method as per My policy portal
	public String GetPaymentMethod(String method) {
		String paymethod = StaticValue.Null;
		switch (method) {
		case StaticValue.CheckingAccount:
			paymethod = StaticValue.NewCheckingAccount;
			break;
		case StaticValue.SavingsAccount:
			paymethod = StaticValue.NewSavingsAccount;
			break;
		case StaticValue.CreditCard:
			paymethod = StaticValue.NewCreditCard;
			break;
		case StaticValue.DebitCard:
			paymethod = StaticValue.NewDebitCard;
			break;
		}
		return paymethod;

	}
	public void validatePolicyDisplayDashboard(String PolicyNumber ) {
		// String PolicyNumber = dataTable.getData(testParameter,
		// "Evalue_PolicyBind", "Policy_Number");
		// String PolicyNumber = "VASS900031334";
	//	String PolicyNumber = dataTable.getData(testParameter, "Evalue_MyPolicy", "SearchPolicyNumber");
		int Policy_count = action.NumberOfRows_XPATH(paymentpage.policyDisplaylocator())-1;
		for (i = 1; i <= Policy_count; i++) {

			if (action.IsObjectPresent(By.xpath(String.format(paymentpage.Policy_Display_number, i, PolicyNumber)), "Policy Display")) {
			//	report.updateTestLog("MyPolicy portal ", "Policy is displayed in Payment section in dashboard" + PolicyNumber, Status.PASS);
				action.movetoElement(By.xpath(String.format(paymentpage.Policy_Display_number, i, PolicyNumber)),"Policy");
			//	action.ClickElement(By.xpath(String.format(paymentpage.Policy_Display_number, i, PolicyNumber)));
				action.HardDelay(2000);
				break;

			} else {
				continue;
			}
		}

	}
	public int validatePolicyInstance(String PolicyNumber) {
		int Policy_count = action.NumberOfRows_XPATH(paymentpage.policyDisplaylocator())-1;
		for (i = 1; i <= Policy_count; i++) {

			if (action.IsObjectPresent(By.xpath(String.format(paymentpage.Policy_Display_number, i, PolicyNumber)), "Policy Display")) {
			//	report.updateTestLog("MyPolicy portal ", "Policy is displayed in Payment section in dashboard" + PolicyNumber, Status.PASS);
			//	action.ClickElement(By.xpath(String.format(paymentpage.Policy_Display_number, i, PolicyNumber)));
				action.HardDelay(2000);
				break;

			} else {
				continue;
			}
		}
		return i;

	}
	
	public void clickAutoPayOff()
	{
		if (i >= 4) {
			for (int j = 4; j <= i; j++) {
				action.ClickElement(PaymentPage.AutoPay_popup_next);
			}
			action.ClickElement(By.xpath(String.format(PaymentPage.Set_AutoPay_popup_Policy_display, i - 1)));
		} else {
			action.ClickElement(By.xpath(String.format(PaymentPage.AutoPay_popup_display_three_policy, i)));
		}
	}
	
	public void clickAutoPayOn()
	{

		if (i >= 4) {
			for (int j = 4; j <= i; j++) {
				action.ClickElement(paymentpage.AutoPay_popup_next);
			}
		
			action.ClickElement(By.xpath(String.format(paymentpage.Stop_AutoPay_popup_Policy_display, i - 1)));
		} else {
			action.ClickElement(By.xpath(String.format(paymentpage.Stop_AutoPay_popup_Policy_display, i - 1)));
		//	action.ClickElement(By.xpath(String.format(paymentpage.AutoPay_popup_display_three_policy, i)));
		}
	}
	public void setAutoPay(String Account_Type_Value,String Credit_Card_Name, String Credit_Card_Number,String Debit_Card_Number, String Credit_Card_Expirationdate, String Credit_Card_Zipcode, String Saving_Name, String Account_Holder_Name, String Routing_Number, String Account_Number, String Account_Nick_Name) {
		/*
		String Credit_Card_Name = "Lee";
		String Credit_Card_Number = "5454545454545454";
		String Credit_Card_Zipcode = "84003";
		String Credit_Card_Expirationdate = "12/2020";
		String Saving_Name="le";
		*/
	//	String Account_Type_Value="Credit Card";
		
		action.ClickElement(PaymentPage.PaymentMethodField_updated);
		action.waitForElement(By.xpath("//div[@class='Select-menu-outer']"));
	
		if ((Account_Type_Value.equals("Credit Card"))) {
			By test = By.xpath("//div[@class='Select-menu-outer']");
			if (action.IsObjectPresent(test, "test object")) {
				action.HardDelay(2000);
				By Credit_Card_link_2 = By.xpath("//*[text()='New Credit Card']");
				action.ClickElement(Credit_Card_link_2,"Credit Card");
			}
			action.EnterText(PaymentPage.CARD_NAME1, Credit_Card_Name);
			action.EnterText(PaymentPage.CREDIT_CARD_NUMBER1, Credit_Card_Number);
			action.EnterText(PaymentPage.CREDIT_CARD_ZIPCODE1, Credit_Card_Zipcode);
			action.EnterText(PaymentPage.CREDIT_CARD_ZIPCODE1, Credit_Card_Zipcode);
			action.EnterText(PaymentPage.CREDIT_CARD_EXPIRATION_DATE1, Credit_Card_Expirationdate);
			action.EnterText(PaymentPage.Nick_Name_Updated, Saving_Name);
		}
		else if ((Account_Type_Value.equals("Debit Card"))) {
			//By Debit_card_link_2 = By.xpath("//*[.='New Debit Card']");
		//	action.ClickElement(Debit_card_link_2);
			By test = By.xpath("//div[@class='Select-menu-outer']");
		if (action.IsObjectPresent(test, "test object")) {
				action.HardDelay(2000);
				By Debit_card_link_2 = By.xpath("//*[text()='New Debit Card']");
				action.ClickElement(Debit_card_link_2,"Debit Card");
			}
			action.EnterText(PaymentPage.CARD_NAME1, Credit_Card_Name);
			action.EnterText(PaymentPage.CREDIT_CARD_NUMBER1, Debit_Card_Number);
			action.EnterText(PaymentPage.CREDIT_CARD_ZIPCODE1, Credit_Card_Zipcode);
			action.EnterText(PaymentPage.CREDIT_CARD_ZIPCODE1, Credit_Card_Zipcode);
			action.EnterText(PaymentPage.CREDIT_CARD_EXPIRATION_DATE1, Credit_Card_Expirationdate);
			action.EnterText(PaymentPage.Nick_Name_Updated, Saving_Name);

		} 
		
		else if ((Account_Type_Value.equals("Checking Account"))) {
			By test = By.xpath("//div[@class='Select-menu-outer']");
			
			if (action.IsObjectPresent(test, "test object")) {
			action.HardDelay(2000);
			By Checking_Account_link_2 = By.xpath("//*[text()='New Checking Account']");
			action.ClickElement(Checking_Account_link_2,"Checking Account");
				
			}
			action.EnterText(PaymentPage.Account_Holder_Name1_Updated, Account_Holder_Name);
			action.EnterText(PaymentPage.ROUTING_NUMBER1_Updated, Routing_Number);
			action.EnterText(PaymentPage.ACCOUNT_NUMBER1_Updated, Account_Number);
			action.EnterText(PaymentPage.Nick_Name_Updated, Account_Nick_Name);

		} 
		else if ((Account_Type_Value.equals("Saving Account"))) {
		By test = By.xpath("//div[@class='Select-menu-outer']");
		if (action.IsObjectPresent(test, "test object")) {
			// By
			// Savings_Account_link_1=By.xpath("//div[@aria-activedescendant='react-select-3--option-2']");
			action.HardDelay(2000);
			By Savings_Account_link_2 = By.xpath("//*[text()='New Savings Account']");
			action.ClickElement(Savings_Account_link_2,"Savings Account");
		}
		
		/*
		else if ((Account_Type_Value.equals("Saving Account"))) {

			By test = By.xpath("//div[@class='Select-menu-outer']");
			By Savings_Account_link_2 = By.xpath("//*[text()='New Savings Account']");
			action.ClickElement(Savings_Account_link_2);
		//	if (IsObjectPresent(test, "test object")) {
				// By
				// Savings_Account_link_1=By.xpath("//div[@aria-activedescendant='react-select-3--option-2']");
			action.HardDelay(2000);
				
		//	}*/
			action.EnterText(PaymentPage.Account_Holder_Name1_Updated, Account_Holder_Name);
			action.EnterText(PaymentPage.ROUTING_NUMBER1_Updated, Routing_Number);
			action.EnterText(PaymentPage.ACCOUNT_NUMBER1_Updated, Account_Number);
			action.EnterText(PaymentPage.Nick_Name_Updated, Account_Nick_Name);

		}
		
		


	}
	public void setAutoPayExistingAcc(String paymentMethod,String NickName,String CreditCardNumber,String DebitCardNumber,String AccountNickname,String AccountNumber) {
		
		
		if (i >= 4) {
			for (int j = 4; j <= i; j++) {
				action.ClickElement(PaymentPage.AutoPay_popup_next);
			}
			action.ClickElement(By.xpath(String.format(PaymentPage.Set_AutoPay_popup_Policy_display, i - 1)));
		} else {
			action.ClickElement(By.xpath(String.format(PaymentPage.AutoPay_popup_display_three_policy, i)));
		}
		action.ClickElement(PaymentPage.PaymentMethodField_updated);
		if ((paymentMethod.equals("Credit Card"))) {
			String Existing_Account = NickName+" "+CreditCardNumber.substring(12, 16);
			By test = By.xpath("//div[@class='Select-menu-outer']");
			if (action.IsObjectPresent(test, "test object")) {
				action.HardDelay(3000);
				By Credit_Card_link_2 = By.xpath(String.format("//*[text()='%s']",Existing_Account));
				action.ClickElement(Credit_Card_link_2);
			}
		}
		else if ((paymentMethod.equals("Debit Card"))) {
			String Existing_Account = NickName+" "+DebitCardNumber.substring(12, 16);
			By test = By.xpath("//div[@class='Select-menu-outer']");
			if (action.IsObjectPresent(test, "test object")) {
				action.HardDelay(3000);
				By Credit_Card_link_2 = By.xpath(String.format("//*[text()='%s']",Existing_Account));
				action.ClickElement(Credit_Card_link_2);
			}
		}
		else if ((paymentMethod.equals("Checking Account"))) {
			String Existing_Account = AccountNickname+" "+AccountNumber.substring(6, 10);
			By test = By.xpath("//div[@class='Select-menu-outer']");
			if (action.IsObjectPresent(test, "test object")) {
				action.HardDelay(3000);
				By Credit_Card_link_2 = By.xpath(String.format("//*[text()='%s']",Existing_Account));
				action.ClickElement(Credit_Card_link_2);
			}
		}
		else if ((paymentMethod.equals("Saving Account"))) {
			String Existing_Account = AccountNickname+" "+AccountNumber.substring(6,10);
			By test = By.xpath("//div[@class='Select-menu-outer']");
			if (action.IsObjectPresent(test, "test object")) {
				action.HardDelay(3000);
				By Credit_Card_link_2 = By.xpath(String.format("//*[text()='%s']",Existing_Account));
				action.ClickElement(Credit_Card_link_2);
			}
		}
		action.ClickJSElement(PaymentPage.Save_button,"Save");
		action.HardDelay(10000);
		

	}
	
	public void clickAutoPaySettingsLink() {
		action.movetoElement(paymentpage.AutoPaylink_Updated,"paymentpage AutoPaylink");
		action.ClickElement(paymentpage.AutoPaylink_Updated);
		
	}
	
	public void verifyStopAutoPaylink()
	{
	action.waitForElement(paymentpage.AutoPayOff_Updated,"AutoPay Stop");
	currentScenario.embed(Util.takeScreenshot(driver),
			"image/png");
	}
	public void stopAutoPay() {

	
		action.movetoElement(paymentpage.AutoPayOff_Updated,"AutoPay Stop");
		action.ClickElement(paymentpage.AutoPayOff_Updated);
		action.HardDelay(15000);
		

	}
	
	// Function to verify SetAutoPay confirmation message
		public void verifySetAutoPaySuccess() {
			/*
			if (action.isTextPresent("You have successfully set up AutoPay.")) {
				//	report.updateTestLog("MyPolicy portal ", "Autopay is set up successfully", Status.PASS);
				//	report.updateTestLog("MyPolicy portal ", "Credit Card/ACH details is added successfully", Status.PASS);
				assertTrue(true, "Autopay is set successfully for the policy");
				}
				else
				{
					fail("<<<< Autopay is not set for the policy as expected >>>>");
				}
*/
				
			action.HardDelay(13000);
			action.waitForElement(paymentpage.AutoPayConfirmMsgeElm());
			action.focusonElementTakeScreenshot(paymentpage.AutoPayConfirmMsgeElm());
			String SucessMessage = action.GetText(paymentpage.AutoPayConfirmMsgeElm());		
			Assert.assertTrue("PaymentNotScuess", SucessMessage.contains(StaticValue.AutoPaySetSuccessMessage));
			action.ClickElement(PaymentPage.AutoPay_popup_close);
			
		}
		// Function to verify StopAutoPay confirmation message
				public void verifyStopAutoPaySuccess() {
					/*
					if (action.IsObjectPresent(paymentpage.AutoPay_Popup_Confirm, "STOP_AUTOPAY_TEXT") && action.isDisplayed(paymentpage.AutoPay_Popup_Confirm, "STOP_AUTOPAY_TEXT")) {
						//	report.updateTestLog("MyPolicy", "AutoPay Stop Alert message is displayed", Status.PASS);
							assertTrue(true, "AutoPay Stop Alert message is displayed");
					
							action.ClickJSElement(paymentpage.AutoPay_Popup_Confirm, "Stop Buton");
							action.HardDelay(13000);
						}
						else
						{
							fail("<<<< AutoPay Stop Alert message is not displayed as expected >>>>");	
						}
						if (action.IsObjectPresent(paymentpage.AutoPay_Setup_text, "AutoPay_Setup_text")) {
							assertTrue(true, "AutoPay is stopped for the policy");
						} else if (action.IsObjectPresent(paymentpage.Autopay_Remove_Confirmation_text, "Autopay_Remove_Confirmation_text")) {
						//	report.updateTestLog("MyPolicy portal ", "Autopay is removed successfully and evalue discount is removed", Status.PASS);
						}
						else
						{
						fail("<<<< Autopay is not stopped for the policy as expected >>>>");
						}
						action.ClickElement(paymentpage.AutoPay_popup_close);
*/
					
					action.waitForElement(paymentpage.AutoPayConfirmMsgeElm());
					action.focusonElementTakeScreenshot(paymentpage.AutoPayConfirmMsgeElm());
					String SucessMessage = action.GetText(paymentpage.AutoPayConfirmMsgeElm());		
					Assert.assertTrue("AutoPayStop", SucessMessage.contains(StaticValue.AutoPayStopSuccessMessage));
					action.ClickElement(PaymentPage.AutoPay_popup_close);
				}
	
				public void verifyAutopayPopUP() {
					action.waitForElement(paymentpage.AutoPay_PopUp, StaticValue.AutoPayPopUpHeader);
					action.isDisplayed(paymentpage.AutoPay_PopUp, StaticValue.AutoPayPopUpHeader);
					
				}

				public void verifyPaymentOptionDropdown() {
				
					action.waitForElement(PaymentPage.PaymentMethodField_updated, StaticValue.AutoPayPaymentOptionsDropdown);
					action.isDisplayed(paymentpage.PaymentMethodField_updated, StaticValue.AutoPayPaymentOptionsDropdown);
				}

				
}
