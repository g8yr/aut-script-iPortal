package stepDefinitions.iPortal;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;






import stepDefinitions.common.MasterStepDefs;
import businesscomponents.iportal.iPortalPaperlessPreferencesFunctions;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.IPortalStaticValue;
import com.Cucumber.supportLibraries.Util;
import com.Cucumber.supportLibraries.Webaction;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

public class iPortalPaperlessPreferencesDef extends MasterStepDefs 
{

	static Logger log = Logger.getLogger(iPortalPaperlessPreferencesDef.class);
	WebDriver driver = DriverManager.getWebDriver();
	
	Webaction action = new Webaction();
	iPortalPaperlessPreferencesFunctions iPortalpaperlesspreferencesfunctions = new iPortalPaperlessPreferencesFunctions();

	@When("^I click edit button from Paperless Preferences$")
	public void Clickeditpaperlesspreferencesbutton()  
	{
		iPortalpaperlesspreferencesfunctions.clickpaperlesspreferences();			  
	}
	
	@Then("^I validate the policy documents and billings are pointed towards (.*) with EValue (.*)$")
	public void ChkPreferencesStatus(String PolicyValue, String eValueStatus)
	{
		iPortalpaperlesspreferencesfunctions.validatePreferencesStatus(PolicyValue,eValueStatus);
	}
	
	@Then("^I see the Text : The customer must confirm enrollment via the email sent.")
	public void ChkEnrollmentText()
	{
		iPortalpaperlesspreferencesfunctions.validateEnrollmentText();
	}
	
	@Then("^I select Policy Documents as (.*) and Policy billing as (.*)$")
	public void EditPolicyDocumentsBilling(String Policy_Documents_Enrollment, String Billing_Payment_Enrollment)
	{
		iPortalpaperlesspreferencesfunctions.editPolicyDocumentsBilling(Policy_Documents_Enrollment,Billing_Payment_Enrollment);
	}

	@And("^I change the email address as (.*)$")
	public void ChangeEmailAddress(String emailAddress)
	{
		iPortalpaperlesspreferencesfunctions.editEmailAddress(emailAddress);
	}
	
	@And("^I click Save in iPortal")
	public void ClickSaveButton()
	{
		iPortalpaperlesspreferencesfunctions.clickSave();
	}
	
	@And("^I validate the email address (.*) in Preference Card")
	public void ChkEmailAddress(String emailAddress)
	{
		iPortalpaperlesspreferencesfunctions.validateEmailAddress(emailAddress);
	}
	
	@Then("^I see the Text : If you unenroll the customer from paperless notifications, their eValue discount will be removed.")
	public void ChkPreferenceDiscountAlert()
	{
		iPortalpaperlesspreferencesfunctions.validatePreferenceDiscountAlert();
	}
	
	@Then("^I shouldn't see the Preference Alert Popup Text")
	public void ChkPreferenceAlert()
	{
		iPortalpaperlesspreferencesfunctions.ChkPreferenceAlert();
	}
	
	@And("^I click Cancel button from Preference Window")
	public void ClickPreferenceCancel()
	{
		iPortalpaperlesspreferencesfunctions.ClickPreferenceCancelButton();
	}
	
	@Then("^I check the Evalue (.*) Icon Status")
	public void ChkEValueIconStatus(String EvalueStatus)
	{
		iPortalpaperlesspreferencesfunctions.ValidateEValueIconStatus(EvalueStatus);
	}
	
	@And("^I check Payment Reminders and Payment Confirmations are automatically turned (.*)$")
	public void ChkPaymentReminderConfirmation(String Status)
	{
		iPortalpaperlesspreferencesfunctions.ChkPaymentReminderConfirmationStatus(Status);
	}
	
}
