package stepDefinitions.MyPolicy;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import stepDefinitions.common.MasterStepDefs;
import webelementrepository.MyPolicy.PaymentPage;
import businesscomponents.mypolicy.GeneralFunctions;
import businesscomponents.mypolicy.PaymentsFunctions;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.StaticValue;
import com.Cucumber.supportLibraries.Util;
import com.Cucumber.supportLibraries.Webaction;
import com.Cucumber.supportLibraries.custom.ReadCSVFileFunctions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PaymentStepDef extends MasterStepDefs {

	static Logger log = Logger.getLogger(PaymentStepDef.class);
	WebDriver driver = DriverManager.getWebDriver();
	PaymentsFunctions paymentfns = new PaymentsFunctions();
	PaymentPage paymentpage = new PaymentPage();
	Webaction action = new Webaction();
	ReadCSVFileFunctions readCSVfilefunctions = new ReadCSVFileFunctions();
	GeneralFunctions GenFns = new GeneralFunctions();
	int PolicyseqNum = StaticValue.zero;
	String PaymentMethod = StaticValue.Null;
	private String policynumber;
	

	
	@Given("^I note the full amount due for my (.*) policy$")
	public void NotetheFullAmountDue(String policyInstance)  {
		paymentfns.EnsureThePolicyDetailsLoaded();
		PolicyseqNum = paymentfns.GetPolicySequenceNumber(policyInstance);
		paymentfns.EnsureThePolicyAmountGtThanZero(PolicyseqNum);
	   
	}
	
	@Given("^I note the full amount due for my policy (.*)$")
	public void SelectPolicy(String policyNUmber)  {
		paymentfns.EnsureThePolicyDetailsLoaded();
		PolicyseqNum=paymentfns.validatePolicyInstance(policyNUmber);
	//	PolicyseqNum = paymentfns.GetPolicySequenceNumber(policyInstance);
		paymentfns.EnsureThePolicyAmountGtThanZero(PolicyseqNum);
	   
	}
	
	@Given("I view the payment details for my policy (.*)$")
	public void viewPolicyPaymentDashboard(String Iteration)  {
		readCSVfilefunctions.readCsvFile(StaticValue.PolicyNumber, Iteration );
		//policynumber = readCSVfilefunctions.SingleVehicleData.get(StaticValue.PolicyNumber);
		policynumber = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), StaticValue.PolicyNumber);
		readCSVfilefunctions.readCsvFile(StaticValue.CardName, Iteration );
		readCSVfilefunctions.readCsvFile(StaticValue.CreditCardNumber, Iteration);
		readCSVfilefunctions.readCsvFile(StaticValue.DebitCardNumber, Iteration);
		readCSVfilefunctions.readCsvFile(StaticValue.CardExpirationDate, Iteration );
		readCSVfilefunctions.readCsvFile(StaticValue.CardZipcode, Iteration);
		readCSVfilefunctions.readCsvFile(StaticValue.CardNickname, Iteration );
		readCSVfilefunctions.readCsvFile(StaticValue.AccountHolderName, Iteration);
		readCSVfilefunctions.readCsvFile(StaticValue.RoutingNumber, Iteration );
		readCSVfilefunctions.readCsvFile(StaticValue.AccountNumber, Iteration);
		readCSVfilefunctions.readCsvFile(StaticValue.AccountNickname, Iteration );
		
		paymentfns.EnsureThePolicyDetailsLoaded();
		paymentfns.validatePolicyDisplayDashboard(policynumber);
	   
	}
	@When("^I enter a payment amount of one cent$")
	public void EnterPaymentAmountOfOneCent()  {
	   action.ClickElement(paymentfns.PolicyDetailsChkBoxIndForTheGivenInstanceElm(PolicyseqNum));
	   action.ClickElement(paymentfns.PolicyDetailsAmountElm(PolicyseqNum));
	   action.EnterText(paymentfns.PolicyDetailsAmountElm(PolicyseqNum), StaticValue.onecent);
	   
	}

	@When("^I click AutoPay Settings link$")
	public void clickAutoPaySettings()
	{
		paymentfns.clickAutoPaySettingsLink();
	}
	
	@When("^I click Autopay Off link$")
	public void clickAutoPayOff()
	{
		paymentfns.clickAutoPayOff();
	}
	
	@When("^I click Autopay On link$")
	public void clickAutoPayOn()
	{
		paymentfns.clickAutoPayOn();
	}
	
	@Then("I view the Autopay popup window$")
	public void ChkAutopayPopUP()  {
		paymentfns.verifyAutopayPopUP();
    
	}
	
	@Then("I view Stop Autopay link$")
	public void ChkStopAutopaylink()  {
		paymentfns.verifyStopAutoPaylink();
    
	}
	@Then("^I view the payment option dropdown$")
	public void ChkPaymentOptionDropdown()  {
		paymentfns.verifyPaymentOptionDropdown();
    
	}
	
	@When("^I enter my (.*) details: name (.*) cardnumber (.*) expirydate (.*) zip (.*) nickname (.*)$")
	public void enterMyCardDetails(String paymentMethod, String Fullname, String CardNumber, String ExpiryDate, String ZipCode, String NickName)  {
		action.ClickElement(paymentpage.PaymentMethodDropDownElm());
		PaymentMethod = paymentfns.GetPaymentMethod(paymentMethod);
		paymentfns.SelectThePaymentMethod(PaymentMethod);
		action.EnterText(paymentpage.NameOnCardElm(), Fullname);
		action.EnterText(paymentpage.CardNumberElm(), CardNumber);
		action.EnterText(paymentpage.ExpiryDateElm(), ExpiryDate);
		action.EnterText(paymentpage.ZipCodeElm(), ZipCode);
		action.EnterText(paymentpage.NickNameElm(), NickName);
		currentScenario.embed(Util.takeScreenshot(driver), "image/png");

	  
	}
	/*
	@When("^I enter my (.*) details for AutoPay : name (.*) cardnumber (.*) expirydate (.*) zip (.*) nickname (.*)$")
	public void enterMyCardDetailsAutopay(String paymentMethod, String Fullname, String CardNumber, String ExpiryDate, String ZipCode, String NickName)  {
		paymentfns.setAutoPay(paymentMethod,Fullname,CardNumber,ExpiryDate,ZipCode,NickName);
		currentScenario.embed(Util.takeScreenshot(driver), "image/png");

	  
	}*/
	@When("^I enter my (.*) details for AutoPay$")
	public void enterMyAccountDetailsAutopay(String paymentMethod)  {
		String Fullname = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), StaticValue.CardName);
		String CreditCardNumber = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), StaticValue.CreditCardNumber);
		String DebitCardNumber = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), StaticValue.DebitCardNumber);
		String ExpiryDate = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), StaticValue.CardExpirationDate);
		String ZipCode = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), StaticValue.CardZipcode);
		String NickName = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), StaticValue.CardNickname);
		String AccountHolderName = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), StaticValue.AccountHolderName);
		String RoutingNumber = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), StaticValue.RoutingNumber);
		String AccountNumber = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), StaticValue.AccountNumber);
		String AccountNickname = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), StaticValue.AccountNickname);	
		
		paymentfns.setAutoPay(paymentMethod,Fullname,CreditCardNumber,DebitCardNumber,ExpiryDate,ZipCode,NickName,AccountHolderName,RoutingNumber,AccountNumber,AccountNickname);
		currentScenario.embed(Util.takeScreenshot(driver), "image/png");

	  
	}
	@When("^I selected existing (.*) details and click Save button$")
	public void selectExistingAccountAutopay(String paymentMethod)  {
		String NickName = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), StaticValue.CardNickname);
		String AccountNickname = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), StaticValue.AccountNickname);
		String CreditCardNumber = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), StaticValue.CreditCardNumber);
		String DebitCardNumber = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), StaticValue.DebitCardNumber);
		String AccountNumber = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), StaticValue.AccountNumber);
		paymentfns.setAutoPayExistingAcc(paymentMethod,NickName,CreditCardNumber,DebitCardNumber,AccountNickname,AccountNumber);
		currentScenario.embed(Util.takeScreenshot(driver), "image/png"); 
	}
	
	@When("^I click Save button in Autopay popup window$")
	public void clickAutoPaySave() {
		action.ClickJSElement(PaymentPage.Save_button,"Save");
	}
	
	@When("^I click continue$")
	public void clickContinue() {
		action.ClickElement(paymentpage.ContinueElm());
	}

	@When("^I click pay again to submit the payment$")
	public void clickPay()  {
	    action.waitForElement(paymentpage.PaymentMethodPayElm());
		action.focusonElementTakeScreenshot(paymentpage.PaymentMethodPayElm());
		action.ClickElement(paymentpage.PaymentMethodPayElm());
		 action.waitForElement(paymentpage.ConfirmationPayElm());
		action.focusonElementTakeScreenshot(paymentpage.ConfirmationPayElm());
		action.ClickElement(paymentpage.ConfirmationPayElm());
	}

	@Then("^I see the text:You successfully submitted a payment.$")
	public void ChkTheSucessMessage()  {
		paymentfns.VerifyPaymentSuccess();
    
	}
	
	@When("^I Click Stop Autopay link$")
	public void stopAutoPay()  {
		paymentfns.stopAutoPay();
    
	}
	@Then("^I see the text:You have successfully set up AutoPay.$")
	public void ChkSetAutoPaySucessMessage()  {
		paymentfns.verifySetAutoPaySuccess();
    
	}
	@Then("^I see the text:You have successfully unenrolled from AutoPay.$")
	public void ChkAutopayStopSucessMessage()  {
		paymentfns.verifyStopAutoPaySuccess();
    
	}
	
}
