package businesscomponents.iportal;
import static org.junit.Assert.fail;
import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import stepDefinitions.common.MasterStepDefs;
import webelementrepository.MyPolicy.PaymentPage;
import webelementrepository.iPortal.iPortalPaymentpage;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.IPortalStaticValue;
import com.Cucumber.supportLibraries.StaticValue;
import com.Cucumber.supportLibraries.Util;
import com.Cucumber.supportLibraries.Webaction;


public class iPortalPaymentsFunctions extends MasterStepDefs
{
	static Logger log = Logger.getLogger(iPortalPaymentpage.class);
	static int i;
	WebDriver driver = DriverManager.getWebDriver();
	Webaction action = new Webaction();
	iPortalPaymentpage iportalpaymentpage = new iPortalPaymentpage();
	public String Total_Policy_Amt;
	
	public void clickSetUpAutoPayLink() 
	{
		action.movetoElement(iportalpaymentpage.AUTOPAY_BUTTON,"paymentpage AutoPaylink");
		action.ClickElement(iportalpaymentpage.AUTOPAY_BUTTON);
		
	}

	public void setAutoPay(String Credit_Card_Name, String Credit_Card_Number,String Credit_Card_Expirationdate, String Credit_Card_Zipcode, String email) 
	{	
			action.EnterText(iportalpaymentpage.CARD_NAME, Credit_Card_Name);
			action.EnterText(iportalpaymentpage.CREDIT_CARD_NUMBER, Credit_Card_Number);
			action.EnterText(iportalpaymentpage.CREDIT_CARD_ZIPCODE, Credit_Card_Zipcode);
			action.EnterText(iportalpaymentpage.CREDIT_CARD_EXPIRATION_DATE, Credit_Card_Expirationdate);
			action.EnterText(iportalpaymentpage.CARD_EMAIL, email);
		action.ClickJSElement(iportalpaymentpage.AUTOPAY_TERMS, "Autopay_Terms");
		action.ClickJSElement(iportalpaymentpage.CARD_ACH_DETAILS_SAVE,"Save Button");		
		action.HardDelay(13000);
	}

	public void verifySetAutoPaySuccess() 
	{
		action.waitForElement(iportalpaymentpage.AutoPayConfirmMsgeElm());
		action.focusonElementTakeScreenshot(iportalpaymentpage.AutoPayConfirmMsgeElm());
		String SucessMessage = action.GetText(iportalpaymentpage.AutoPayConfirmMsgeElm());		
		Assert.assertTrue("PaymentNotSuccess", SucessMessage.contains(IPortalStaticValue.iPortalAutoPaySetSuccessMessage));
		action.ClickElement(iportalpaymentpage.CLOSE_AUTOPAY_POPUP);
		
	}

	public void stopAutoPay() 
	{
		action.waitForElement(iportalpaymentpage.STOP_AUTOPAY_BUTTON);
		action.focusonElementTakeScreenshot(iportalpaymentpage.STOP_AUTOPAY_BUTTON);
		action.ClickElement(iportalpaymentpage.STOP_AUTOPAY_BUTTON);
		action.ClickElement(iportalpaymentpage.STOP_AUTOPAY_CONFIRM_BUTTON);
	}
	
	public void verifyStopAutoPaySuccess() 
	{
		action.waitForElement(iportalpaymentpage.StopAutoPayConfirmMsgeElm());
		action.focusonElementTakeScreenshot(iportalpaymentpage.StopAutoPayConfirmMsgeElm());
		String SucessMessage = action.GetText(iportalpaymentpage.StopAutoPayConfirmMsgeElm());		
		Assert.assertTrue("PaymentNotSuccess", SucessMessage.contains(IPortalStaticValue.iPortalStopAutoPaySuccessMessage));
		action.ClickElement(iportalpaymentpage.CLOSE_AUTOPAY_POPUP);
	}

	public void clickPayNow() 
	{
		action.movetoElement(iportalpaymentpage.PAY_BUTTON,"paymentpage AutoPaylink");
		currentScenario.embed(Util.takeScreenshot(driver),
				"image/png");
		action.ClickElement(iportalpaymentpage.PAY_BUTTON);
		
	}
	
	public void setAutoPay(String Account_Type_Value,String Credit_Card_Name, String Credit_Card_Number,String Debit_Card_Number, String Credit_Card_Expirationdate, String Credit_Card_Zipcode, String Saving_Name, String Account_Holder_Name, String Routing_Number, String Account_Number, String Account_Nick_Name) 
	{
		
		 Total_Policy_Amt = action.GetTextValue(iportalpaymentpage.Amount_Payable,"Amount Payable");
		action.EnterText(iportalpaymentpage.Amount_Payable, IPortalStaticValue.Amount_Paid);
		action.ClickJSElement(iportalpaymentpage.Account_Type, "Account_Dropdown");
		
		if ((Account_Type_Value.equals("Credit Card"))) 
		{
			action.ClickJSElement(iportalpaymentpage.Account_Type_Credit_Card, "Credit card option");
			action.EnterText(iportalpaymentpage.CARD_NAME, Credit_Card_Name);
			action.EnterText(iportalpaymentpage.CREDIT_CARD_NUMBER, Credit_Card_Number);
			action.EnterText(iportalpaymentpage.CREDIT_CARD_ZIPCODE, Credit_Card_Zipcode);
			action.EnterText(iportalpaymentpage.CREDIT_CARD_EXPIRATION_DATE, Credit_Card_Expirationdate);
			action.HardDelay(1000);	
			currentScenario.embed(Util.takeScreenshot(driver),
					"image/png");
		}
		else if ((Account_Type_Value.equals("Debit Card"))) 
		{
			action.ClickJSElement(iportalpaymentpage.Account_Type_Debit_Card, Account_Type_Value);
			action.EnterText(iportalpaymentpage.CARD_NAME, Credit_Card_Name);
			action.EnterText(iportalpaymentpage.CREDIT_CARD_NUMBER, Credit_Card_Number);
			action.EnterText(iportalpaymentpage.CREDIT_CARD_ZIPCODE, Credit_Card_Zipcode);
			action.EnterText(iportalpaymentpage.CREDIT_CARD_EXPIRATION_DATE, Credit_Card_Expirationdate);
			currentScenario.embed(Util.takeScreenshot(driver),
					"image/png");

		}  
		else if ((Account_Type_Value.equals("Checking Account"))) 
		{
			action.ClickJSElement(iportalpaymentpage.Account_Type_Checking_Account, Account_Type_Value);		
			action.EnterText(iportalpaymentpage.CARD_NAME, Account_Holder_Name);
			action.EnterText(iportalpaymentpage.ROUTING_NUMBER, Routing_Number);
			action.EnterText(iportalpaymentpage.ACCOUNT_NUMBER, Account_Number);
			currentScenario.embed(Util.takeScreenshot(driver),
					"image/png");
		} 
		else if ((Account_Type_Value.equals("Saving Account"))) 
		{
			action.ClickJSElement(iportalpaymentpage.Account_Type_Saving_Account, Account_Type_Value);
			action.EnterText(iportalpaymentpage.CARD_NAME, Account_Holder_Name);
			action.EnterText(iportalpaymentpage.ROUTING_NUMBER, Routing_Number);
			action.EnterText(iportalpaymentpage.ACCOUNT_NUMBER, Account_Number);
			currentScenario.embed(Util.takeScreenshot(driver),
					"image/png");
		}

		action.ClickJSElement(iportalpaymentpage.CARD_ACH_PAY,"Pay Button");		
		action.HardDelay(1000);
	}

	

	public void verifyEFTPayConfirmationPage() 
	{
		String name ="Anusha.Srinivasan@csaa.com";
		String number = action.GetTextValue(iportalpaymentpage.CONFIRMATION_NUMBER_EFT_Pay,"CONFIRMATION NUMBER EFT Pay");
		String actMsg=action.GetText(iportalpaymentpage.EFTPAY_CONFIRMATION_TEXT);
		actMsg = actMsg.replaceAll("[\\[\\]]", "").replaceAll(",", "");
		String expMsg = "SUCCESS: Your payment was submitted. A confirmation will be sent to" +name +" . It may take up to 48 hours before your account reflects the transaction.";
		if (!number.isEmpty() && number != null) {
			//report.updateTestLog("Text Validation", "Confirmation Number is displayed: " + number, Status.PASS);
		} else {
			//report.updateTestLog("Text Validation", "Confirmation Number is not displayed", Status.FAIL);
		}

		actMsg = actMsg.replaceAll("\\s", "");
		expMsg = expMsg.replaceAll("\\s", "");
		if (actMsg.contains(expMsg))
		{
			System.out.println("Automatic Payments pop up - Confirmation message is displayed as expected.");
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			//report.updateTestLog("Automatic Payments pop up", "Confirmation message is displayed as expected. "+expMsg, Status.PASS);
		}
		else
		{
			//report.updateTestLog("Automatic Payments pop up", "Confirmation message is not displayed as expected- " + expMsg + " but actual- " + actMsg, Status.FAIL);
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			fail("<<<<  Automatic Payments pop up - Confirmation message is not displayed as expected >>>>");
		}
		action.ClickElement(iportalpaymentpage.CLOSE_AUTOPAY_POPUP, "CLOSE_AUTOPAY_POPUP");
		action.HardDelay(1000);
	}

	public void iportalPaymentChange() 
	{
		//System.out.println(Total_Policy_Amt);
		String TotalAmt = action.GetTextValue(iportalpaymentpage.POLICY_TOTAL_AMOUNT,"POLICY TOTAL AMOUNT");
			TotalAmt=TotalAmt.replace("$","");
			TotalAmt=TotalAmt.replace(".00","");
		int Amt1,Amt2,expAmt,actAmt;
		actAmt=Integer.parseInt(TotalAmt);
			Total_Policy_Amt=Total_Policy_Amt.replace(".00", "");
		Amt1= Integer.parseInt(Total_Policy_Amt);
		Amt2= Integer.parseInt(IPortalStaticValue.Amount_Paid);
		expAmt = Amt1-Amt2;
		
		if(actAmt == expAmt)
		{
			System.out.println("Automatic Payments  - Payment reflected in the page as expected");
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		}
		else
		{
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			fail("<<<<  Automatic Payments  - Payment is not reflected in the page  >>>>");
		}
		
		
		
		
	}
}
