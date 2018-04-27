package businesscomponents.iportal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.testng.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.screentaker.ViewportPastingStrategy;


import stepDefinitions.common.MasterStepDefs;
import webelementrepository.MyPolicy.PaymentPage;
import webelementrepository.iPortal.iPortalPaperlessPreferencesPage;
import businesscomponents.mypolicy.GeneralFunctions;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.IPortalStaticValue;
import com.Cucumber.supportLibraries.Util;
import com.Cucumber.supportLibraries.Webaction;



import cucumber.api.java.en.When;
import junit.framework.Assert;

public class iPortalPaperlessPreferencesFunctions extends MasterStepDefs
{
	WebDriver driver = DriverManager.getWebDriver();
	Webaction action = new Webaction();
	IPortalStaticValue iportalstaticvalue = new IPortalStaticValue();
	
	iPortalPaperlessPreferencesPage iPortalpaperlesspreferences = new iPortalPaperlessPreferencesPage();
	GeneralFunctions GenFns = new GeneralFunctions();
	
	boolean result;
	public void clickpaperlesspreferences() 
	{
		action.HardDelay(300);
		action.waitForElement(iPortalpaperlesspreferences.EDIT_PREFERENCES);
		action.ClickJSElement(iPortalpaperlesspreferences.EDIT_PREFERENCES, "EDIT_PREFERENCES Button");	
		action.HardDelay(300);
		currentScenario.embed(Util.takeScreenshot(driver),
				"image/png");
	}
	
	public void validatePreferencesStatus(String PolicyValue,String evalueStatus) 
	{
		//Validate the Policy Billing & Policy Document Status in Paperless Preference Card
		
				if ((evalueStatus.equalsIgnoreCase("Opt Out")) || (evalueStatus.equalsIgnoreCase("Pending")))
				{
					String Result_Policy_Documents_US_Mail_ID = action.GetText(iPortalpaperlesspreferences.Policy_Document_US_Mail_ID);
					String Result_Billing_Payment_US_Mail_ID = action.GetText(iPortalpaperlesspreferences.Billing_Payment_US_Mail_ID);
					currentScenario.embed(Util.takeScreenshot(driver),
							"image/png");
					
					if ((Result_Policy_Documents_US_Mail_ID.equalsIgnoreCase("US Mail"))&&(Result_Billing_Payment_US_Mail_ID.equalsIgnoreCase("US Mail"))) 
					{
						System.out.println("IPortal Preferences Card: Policy Documents Status " + Result_Policy_Documents_US_Mail_ID + " displayed successfully in preference card");
						System.out.println("IPortal Preferences Card: Policy Billing Status " + Result_Billing_Payment_US_Mail_ID + " displayed successfully in preference card");
						currentScenario.embed(Util.takeScreenshot(driver),
								"image/png");
//						report.updateTestLog("IPortal Preferences Card", "Policy Documents Status " + Result_Policy_Documents_US_Mail_ID + " displayed successfully in preference card", Status.PASS);
//						report.updateTestLog("IPortal Preferences Card", "Policy Billing Status " + Result_Billing_Payment_US_Mail_ID + " displayed successfully in preference card", Status.PASS);
					} 
					else 
					{
						fail("<<<< IPortal Preferences Card : Policy Documents & Policy Billing Status is not displayed in preference card >>>>");
						currentScenario.embed(Util.takeScreenshot(driver),
								"image/png");
//						report.updateTestLog("IPortal Preferences Card", "Policy Documents Status id not displayed in preference card", Status.FAIL);
//						report.updateTestLog("IPortal Preferences Card", "Policy Billing Status is not displayed in preference card", Status.FAIL);
					}
				}
				else if(evalueStatus.equalsIgnoreCase("Active"))
				{
					String Result_Policy_Documents_EMail_ID = action.GetText(iPortalpaperlesspreferences.Policy_Document_EMail_ID);
					String Result_Billing_Payment_EMail_ID = action.GetText(iPortalpaperlesspreferences.Billing_Payment_EMail_ID);
					
					if ((Result_Policy_Documents_EMail_ID.equalsIgnoreCase("Email"))&&(Result_Billing_Payment_EMail_ID.equalsIgnoreCase("Email")))
					{
						System.out.println("IPortal Preferences Card: Policy Documents Status " + Result_Policy_Documents_EMail_ID + " displayed successfully in preference card");
						System.out.println("IPortal Preferences Card: Policy Billing Status " + Result_Billing_Payment_EMail_ID + " displayed successfully in preference card");
						currentScenario.embed(Util.takeScreenshot(driver),
								"image/png");
//						report.updateTestLog("IPortal Preferences Card", "Policy Documents Status " + Result_Policy_Documents_EMail_ID + " displayed successfully in preference card", Status.PASS);
//						report.updateTestLog("IPortal Preferences Card", "Policy Billing Status " + Result_Billing_Payment_EMail_ID + " displayed successfully in preference card", Status.PASS);
					} 
					else 
					{
						fail("<<<< IPortal Preferences Card : Policy Documents & Policy Billing Status is not displayed in preference card >>>>");
						currentScenario.embed(Util.takeScreenshot(driver),
								"image/png");
//						report.updateTestLog("IPortal Preferences Card", "Policy Documents Status id not displayed in preference card", Status.FAIL);
//						report.updateTestLog("IPortal Preferences Card", "Policy Billing Status is not displayed in preference card", Status.FAIL);
					}
				}
				currentScenario.embed(Util.takeScreenshot(driver),
						"image/png");
				action.ClickJSElement(iPortalpaperlesspreferences.Save_button, "SAVE");		
	}
	
	public void validateEnrollmentText() 
	{
		action.waitForElement(iPortalpaperlesspreferences.PREFERENCE_ENROLLMENT_PENDING_TEXT);
		action.focusonElementTakeScreenshot(iPortalpaperlesspreferences.PREFERENCE_ENROLLMENT_PENDING_TEXT);
		String SucessMessage = action.GetText(iPortalpaperlesspreferences.PREFERENCE_ENROLLMENT_PENDING_TEXT);		
		Assert.assertTrue("PendingEnrollmentStatus", SucessMessage.contains(IPortalStaticValue.paperlessEnrollmentStatus));
		currentScenario.embed(Util.takeScreenshot(driver),
				"image/png");
	}	
	
	

	public void editPolicyDocumentsBilling(String Policy_Documents_Enrollment,String Billing_Payment_Enrollment) 
	{
		if (Policy_Documents_Enrollment.equalsIgnoreCase("USMAIL")) {
			action.ClickJSElement(iPortalpaperlesspreferences.Policy_Document_US_Mail_ID, "Policy_Document_US_Mail_ID");
		} else if (Policy_Documents_Enrollment.equalsIgnoreCase("EMAIL")) {
			action.ClickJSElement(iPortalpaperlesspreferences.Policy_Document_EMail_ID, "Policy_Document_EMail_ID");
		}
		currentScenario.embed(Util.takeScreenshot(driver),
				"image/png");
		if (Billing_Payment_Enrollment.equalsIgnoreCase("USMAIL")) {
			action.ClickJSElement(iPortalpaperlesspreferences.Billing_Payment_US_Mail_ID, "Billing_Payment_US_Mail_ID");
		} else if (Billing_Payment_Enrollment.equalsIgnoreCase("EMAIL")) {
			action.ClickJSElement(iPortalpaperlesspreferences.Billing_Payment_EMail_ID, "Billing_Payment_EMail_ID");
		}
		currentScenario.embed(Util.takeScreenshot(driver),
				"image/png");
		
	}

	public void editEmailAddress(String email) {
		if(action.isDisplayed(iPortalpaperlesspreferences.Edit_Email,"PREFERENCE_EMAIL")&&action.isEnabled(iPortalpaperlesspreferences.Edit_Email,"PREFERENCE_EMAIL"))
			{
				if(!email.isEmpty() && (email != null))
				{
					action.ClickJSElement(iPortalpaperlesspreferences.Edit_Email_Clear, "Edit_Email_Clear");
					action.HardDelay(300);
					action.EnterTextWithJS(iPortalpaperlesspreferences.Edit_Email, email);
					action.HardDelay(300);
				}
				currentScenario.embed(Util.takeScreenshot(driver),
						"image/png");
			}
		action.HardDelay(300);
	}

	public void clickSave() 
	{
		currentScenario.embed(Util.takeScreenshot(driver),
				"image/png");
		action.ClickJSElement(iPortalpaperlesspreferences.Save_button, "SAVE");	
		action.HardDelay(2000);
	}

	public void validateEmailAddress(String email) {
		
		if(action.isDisplayed(iPortalpaperlesspreferences.PREFERENCE_EMAIL,"PREFERENCE_EMAIL"))
			{
				String Email_Address = action.GetText(iPortalpaperlesspreferences.PREFERENCE_EMAIL);
				if(Email_Address.equalsIgnoreCase(email)) 
				{
					//report.updateTestLog("Preferences ", "Email Address are updated successfully", Status.PASS);
					currentScenario.embed(Util.takeScreenshot(driver),
							"image/png");
				}
				else
				{
					fail("<<<< IPortal Preferences Card : Error in updating Email Address in iPortal >>>>");
					//report.updateTestLog("Preferences ", "Error in updating Email Address in iPortal", Status.FAIL);
					currentScenario.embed(Util.takeScreenshot(driver),
							"image/png");
				}
			}
		action.HardDelay(300);
	}
	

	public void validatePreferenceDiscountAlert() 
	{
		action.waitForElement(iPortalpaperlesspreferences.Preference_Alert_Pop_Up);
		action.focusonElementTakeScreenshot(iPortalpaperlesspreferences.Preference_Alert_Pop_Up);
		String SucessMessage = action.GetText(iPortalpaperlesspreferences.Preference_Alert_Pop_Up);		
		Assert.assertTrue("PendingEnrollmentStatus", SucessMessage.contains(IPortalStaticValue.preferenceDiscountAlertText));
		currentScenario.embed(Util.takeScreenshot(driver),
				"image/png");
		
	}

	
	public void ChkPreferenceAlert() 
	{
		if(action.isDisplayed(iPortalpaperlesspreferences.Preference_Alert_Pop_Up,"Preference_Alert_Pop_Up"))
		{
			fail("<<<< IPortal Preference : Preference Alert Pop Up is displayed>>>>");
			//report.updateTestLog("Preferences ", "Error in updating Email Address in iPortal", Status.FAIL);
			currentScenario.embed(Util.takeScreenshot(driver),
					"image/png");
		}
		else
		{
			System.out.println("IPortal Preference : Preference Alert Pop Up is not displayed");
			currentScenario.embed(Util.takeScreenshot(driver),
					"image/png");
		}
			
			
	}

	public void ClickPreferenceCancelButton() 
	{
		action.ClickJSElement(iPortalpaperlesspreferences.Preference_Cancel, "Preference Cancel");
		currentScenario.embed(Util.takeScreenshot(driver),
				"image/png");
		
	}

	public void ValidateEValueIconStatus(String Evalue_Status_Expected) 
	{
//		String Evalue_Discount_Apply = dataTable.getData(testParameter, "Evalue_IPortal", "Evaluediscount_Apply");
//		String Evalue_Status_Expected = dataTable.getData(testParameter, "Evalue_IPortal", "eValue_Status");
		String eValue_Status_displayed = "";
		if (action.isDisplayed(iPortalpaperlesspreferences.EValue_Status_Active, "EValue_Status") || action.isDisplayed(iPortalpaperlesspreferences.EValue_Status_Pending, "EValue_Status_Pending")) 
		{
			eValue_Status_displayed = action.GetText(iPortalpaperlesspreferences.EValue_Status_Text);
			currentScenario.embed(Util.takeScreenshot(driver),
					"image/png");
		}
	
			if (eValue_Status_displayed.equalsIgnoreCase("eValue") ) {
				if(Evalue_Status_Expected.equalsIgnoreCase("Active"))
				{
					//report.updateTestLog("Customer Overview page", "For eValue Active policy, eValue Alert is displayed as Active", Status.PASS);
					System.out.println("Customer Overview page  : For eValue Active policy, eValue Alert is displayed as Active");
					currentScenario.embed(Util.takeScreenshot(driver),
							"image/png");
				}
				else if (Evalue_Status_Expected.equalsIgnoreCase("Pending"))
				{
					//report.updateTestLog("Customer Overview page", "For eValue Pending policy, eValue Alert is displayed as Active", Status.FAIL);
					fail("<<<< Customer Overview page : For eValue Pending policy, eValue Alert is displayed as Active>>>>");
					currentScenario.embed(Util.takeScreenshot(driver),
							"image/png");
				}
				else
				{
					//report.updateTestLog("Customer Overview page", "For Non eValue policy, eValue Alert is displayed as Active", Status.FAIL);
					fail("<<<< Customer Overview page : For Non eValue policy, eValue Alert is displayed as Active>>>>");
					currentScenario.embed(Util.takeScreenshot(driver),
							"image/png");
				}
			} 			
			
			else if (eValue_Status_displayed.equalsIgnoreCase("eValue Pending") ) {
				if(Evalue_Status_Expected.equalsIgnoreCase("Pending"))
				{
					//report.updateTestLog("Customer Overview page", "For eValue Pending policy, eValue Alert is displayed as eValue Pending", Status.PASS);
					System.out.println("Customer Overview page  : For eValue Pending policy, eValue Alert is displayed as eValue Pending");
					currentScenario.embed(Util.takeScreenshot(driver),
							"image/png");
				}
				else if (Evalue_Status_Expected.equalsIgnoreCase("Active"))
				{
					//report.updateTestLog("Customer Overview page", "For eValue Active policy, eValue Alert is displayed as eValue Pending", Status.FAIL);	
					fail("<<<< Customer Overview page : For eValue Active policy, eValue Alert is displayed as eValue Pending>>>>");
					currentScenario.embed(Util.takeScreenshot(driver),
							"image/png");
				}
				else
				{
					//report.updateTestLog("Customer Overview page", "For Non eValue policy, eValue Alert is displayed as eValue Pending", Status.FAIL);
					fail("<<<< Customer Overview page : For Non eValue policy, eValue Alert is displayed as eValue Pending>>>>");
					currentScenario.embed(Util.takeScreenshot(driver),
							"image/png");
				}
				
				}
			else  {
				if(Evalue_Status_Expected.equalsIgnoreCase("Pending"))
				{
					//report.updateTestLog("Customer Overview page", "For eValue Pending policy, eValue alert is not displayed ", Status.FAIL);
					fail("<<<< Customer Overview page : For eValue Pending policy, eValue alert is not displayed>>>>");
					currentScenario.embed(Util.takeScreenshot(driver),
							"image/png");
				}
				else if (Evalue_Status_Expected.equalsIgnoreCase("Active"))
				{
					//report.updateTestLog("Customer Overview page", "For eValue Active policy, eValue alert is not displayed ", Status.FAIL);
					fail("<<<< Customer Overview page : For eValue Active policy, eValue alert is not displayed>>>>");
					currentScenario.embed(Util.takeScreenshot(driver),
							"image/png");
				}
				else
				{
					//report.updateTestLog("Customer Overview page", "For Non eValue policy, eValue Alert is not displayed", Status.PASS);
					System.out.println("Customer Overview page  : For Non eValue policy, eValue Alert is not displayed");
					currentScenario.embed(Util.takeScreenshot(driver),
							"image/png");
				}
				
				}		
	}

	public void ChkPaymentReminderConfirmationStatus(String status) 
	{
		if(status.equalsIgnoreCase("off"))
		{
			if((action.isEnabled(iPortalpaperlesspreferences.PaymentReminders_Checkbox, "PaymentReminders_Checkbox"))&&(action.isEnabled(iPortalpaperlesspreferences.PaymentConfirrmation_Checkbox,"PaymentConfirrmation_Checkbox")))
			{
				fail("<<<< Paperless Preference: Payment Remainders and Payment Confirmations are turned On for OptOut Policy >>>>");
				currentScenario.embed(Util.takeScreenshot(driver),
						"image/png");
			}
			else
			{
				System.out.println("Paperless Preference: Payment Remainders and Payment Confirmations are turned Off for OptOut Policy");
				currentScenario.embed(Util.takeScreenshot(driver),
						"image/png");
			}
		}
		else if(status.equalsIgnoreCase("on"))
		{
			if((action.isEnabled(iPortalpaperlesspreferences.PaymentReminders_Checkbox, "PaymentReminders_Checkbox"))&&(action.isEnabled(iPortalpaperlesspreferences.PaymentConfirrmation_Checkbox,"PaymentConfirrmation_Checkbox")))
			{
				System.out.println("Paperless Preference: Payment Remainders and Payment Confirmations are turned On for OptOut Policy");
				currentScenario.embed(Util.takeScreenshot(driver),
						"image/png");
				
			}
			else
			{
				fail("<<<< Paperless Preference: Payment Remainders and Payment Confirmations are turned Off for OptOut Policy >>>>");
				currentScenario.embed(Util.takeScreenshot(driver),
						"image/png");
			}
		}
		
	}

}
