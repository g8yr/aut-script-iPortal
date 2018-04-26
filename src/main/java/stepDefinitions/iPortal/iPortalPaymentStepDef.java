package stepDefinitions.iPortal;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import stepDefinitions.common.MasterStepDefs;
import businesscomponents.iportal.iPortalPaymentsFunctions;
import businesscomponents.mypolicy.GeneralFunctions;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.IPortalStaticValue;
import com.Cucumber.supportLibraries.Util;
import com.Cucumber.supportLibraries.Webaction;
import com.Cucumber.supportLibraries.custom.ReadCSVFileFunctions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class iPortalPaymentStepDef extends MasterStepDefs {
	
	static Logger log = Logger.getLogger(iPortalPaymentStepDef.class);
	WebDriver driver = DriverManager.getWebDriver();	
	Webaction action = new Webaction();
	
	iPortalPaymentsFunctions iportalpaymentsfunctions = new iPortalPaymentsFunctions();
	ReadCSVFileFunctions readCSVfilefunctions = new ReadCSVFileFunctions();
	GeneralFunctions GenFns = new GeneralFunctions();
	
	@When("^I click iPortal AutoPay Settings link$")
	public void clickAutoPaySettings()
	{
		iportalpaymentsfunctions.clickSetUpAutoPayLink();
	}
	
	@When("^I enter my (.*) details for iPortal AutoPay : name (.*) cardnumber (.*) expirydate (.*) zip (.*) email (.*)$")
	public void enterMyCardDetailsAutopay(String paymentMethod, String Fullname, String CardNumber, String ExpiryDate, String ZipCode, String email)  {
		iportalpaymentsfunctions.setAutoPay(Fullname,CardNumber,ExpiryDate,ZipCode,email);
		currentScenario.embed(Util.takeScreenshot(driver), "image/png");	  
	}
	
	@Then("^I see the iPortal text:You have successfully set up AutoPay.$")
	public void ChkSetAutoPaySucessMessage()  {
		iportalpaymentsfunctions.verifySetAutoPaySuccess();
    
	}
	
	@When("^I Click Stop Iportal Autopay link$")
	public void stopAutoPay()  {		
		iportalpaymentsfunctions.stopAutoPay();
	}
	
	@When("^I click iPortal Pay Now$")
	public void clickPayNowButton()
	{
		iportalpaymentsfunctions.clickPayNow();
	}
	
	@When("^I enter my (.*) details for iPortal AutoPay (.*)$")
	public void enterMyAccountDetailsAutopay(String paymentMethod,String Iteration)  {
		
		readCSVfilefunctions.readCsvFile(IPortalStaticValue.CardName, Iteration );
		readCSVfilefunctions.readCsvFile(IPortalStaticValue.CreditCardNumber, Iteration );
		readCSVfilefunctions.readCsvFile(IPortalStaticValue.DebitCardNumber, Iteration );
		readCSVfilefunctions.readCsvFile(IPortalStaticValue.CardExpirationDate, Iteration );
		readCSVfilefunctions.readCsvFile(IPortalStaticValue.CardZipcode, Iteration );
		readCSVfilefunctions.readCsvFile(IPortalStaticValue.CardNickname, Iteration );
		readCSVfilefunctions.readCsvFile(IPortalStaticValue.AccountHolderName, Iteration );
		readCSVfilefunctions.readCsvFile(IPortalStaticValue.RoutingNumber, Iteration );
		readCSVfilefunctions.readCsvFile(IPortalStaticValue.AccountNumber, Iteration );
		readCSVfilefunctions.readCsvFile(IPortalStaticValue.AccountNickname, Iteration );
		
		
		String Fullname = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), IPortalStaticValue.CardName);
		String CreditCardNumber = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), IPortalStaticValue.CreditCardNumber);
		String DebitCardNumber = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), IPortalStaticValue.DebitCardNumber);
		String ExpiryDate = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), IPortalStaticValue.CardExpirationDate);
		String ZipCode = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), IPortalStaticValue.CardZipcode);
		String NickName = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), IPortalStaticValue.CardNickname);
		String AccountHolderName = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), IPortalStaticValue.AccountHolderName);
		String RoutingNumber = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), IPortalStaticValue.RoutingNumber);
		String AccountNumber = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), IPortalStaticValue.AccountNumber);
		String AccountNickname = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), IPortalStaticValue.AccountNickname);	
		
		iportalpaymentsfunctions.setAutoPay(paymentMethod,Fullname,CreditCardNumber,DebitCardNumber,ExpiryDate,ZipCode,NickName,AccountHolderName,RoutingNumber,AccountNumber,AccountNickname);
		//currentScenario.embed(Util.takeScreenshot(driver), "image/png");

	  
	}
	
	@When("^I close the payment pop up$")
	public void ChkEFTPayConfirmationPage()
	{
		iportalpaymentsfunctions.verifyEFTPayConfirmationPage();
	}
	
	@Then("^I see the payment reflected in the page$")
	public void chkiportalPaymentChange()
	{
		iportalpaymentsfunctions.iportalPaymentChange();
	}

}
