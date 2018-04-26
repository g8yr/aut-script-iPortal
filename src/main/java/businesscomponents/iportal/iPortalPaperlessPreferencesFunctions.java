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
	
	public void editpreferencesiPortal(String SourceSystem,String Policy_Documents_Enrollment,String Billing_Payment_Enrollment, String paymentreminder, String paymentconfirmation) {
//		String prefenceedit = dataTable.getData(testParameter, "Evalue_IPortal", "EditPreference");
//		String email = dataTable.getData(testParameter, "Evalue_IPortal", "PreferencesEmailId");
//		String Policy_Documents_Enrollment = dataTable.getData(testParameter, "Evalue_IPortal", "Policy_Document_Enrollment");
//		String Billing_Payment_Enrollment = dataTable.getData(testParameter, "Evalue_IPortal", "Billing_Payment_Enrollment");
//		String paymentreminder = dataTable.getData(testParameter, "Evalue_IPortal", "PaymentReminderOptOut");
//		String paymentconfirmation = dataTable.getData(testParameter, "Evalue_IPortal", "PaymentConfirmationOptOut");
//		String SourceSystem = dataTable.getData(testParameter, "Evalue_IPortal", "Source_System");

//		if(SourceSystem.equals("Legacy"))
//			{
//				if(action.isDisplayed(iPortalpaperlesspreferences.Get_Policy_Preference_Status,"Get_Policy_Preference_Status") && action.IsObjectPresent(iPortalpaperlesspreferences.Get_Policy_Preference_Status,"Get_Policy_Preference_Status"))
//				{
//					System.out.println("IPortal Preferences : Policy Documents Section is displayed");
//					//report.updateTestLog("iPortal Preferences", "Policy Documents Section is displayed", Status.FAIL);
//				}
//				else
//				{
//					fail("<<<< IPortal Preferences : Policy Documents Section is not displayed >>>>");
//					currentScenario.embed(Util.takeScreenshot(driver),"image/png");
//					//report.updateTestLog("iPortal Preferences", "Policy Documents Section is not displayed", Status.PASS);
//				}
//			}
//			else if(SourceSystem.equals("Mortgage"))
//			{
//				if(action.isDisplayed(iPortalpaperlesspreferences.Get_Billing_Preference_Text,"Billing_Payment_US_Mail_ID") && action.isDisplayed(iPortalpaperlesspreferences.Get_Billing_Preference_Text,"Billing_Payment_EMail_ID"))
//				{
//					System.out.println("IPortal Preferences : Policy Billing & Payments Section Alert Message is displayed");
//					//report.updateTestLog("iPortal Preferences", "Policy Billing & Payments Section Alert Message is displayed", Status.PASS);
//				}
//				else
//					//report.updateTestLog("iPortal Preferences", "Policy Billing & Payments Section Alert Message is not displayed", Status.FAIL);
//					fail("<<<< IPortal Preferences : Policy Billing & Payments Section Alert Message is not displayed >>>>");
//				currentScenario.embed(Util.takeScreenshot(driver),"image/png");
//				String Mortgage_Alert = action.GetText(iPortalpaperlesspreferences.Get_Billing_Preference_Text);
//				//messageValidation("iPortal Preferences",Mortgage_Alert);
//				//GenFns.chkTheContent(SplitAddVehicleActionBlockTitleUI[1], Mortgage_Alert,StaticValue.AddvehicleActionBlockTitle);
//			}
//			else
//			{
//				if (Policy_Documents_Enrollment.equalsIgnoreCase("USMAIL")) {
//					action.ClickJSElement(Policy_Document_US_Mail_ID, "Policy_Document_US_Mail_ID");
//				} else if (Policy_Documents_Enrollment.equalsIgnoreCase("EMAIL")) {
//					action.ClickJSElement(Policy_Document_EMail_ID, "Policy_Document_EMail_ID");
//				}
//				if (Billing_Payment_Enrollment.equalsIgnoreCase("USMAIL")) {
//					action.ClickJSElement(Billing_Payment_US_Mail_ID, "Billing_Payment_US_Mail_ID");
//				} else if (Billing_Payment_Enrollment.equalsIgnoreCase("EMAIL")) {
//					action.ClickJSElement(Billing_Payment_EMail_ID, "Billing_Payment_EMail_ID");
//				}
//			}
//			
//			if (paymentreminder.equalsIgnoreCase("YES")) {
//				action.ClickJSElement(PaymentReminders_Checkbox, "paymentreminder");
//			}
//			if (paymentconfirmation.equalsIgnoreCase("YES")) {
//				action.ClickJSElement(PaymentConfirrmation_Checkbox, "paymentconfirmation");
//			}
//			
//			// EnterText(Edit_Email, email, "email");
//			if(isDisplayednew(Edit_Email,"PREFERENCE_EMAIL")&&isEnabled(Edit_Email,"PREFERENCE_EMAIL"))
//			{
//				if(!email.isEmpty() && (email != null))
//				{
//					ClickJSElement(Edit_Email_Clear, "Edit_Email_Clear");					
//					EnterTextWithJS(Edit_Email, email, "email");
//					HardDelay(300);
//				}
//			}			
//			
//			ClickJSElement(Save_button, "SAVE");
//			
//			HardDelay(300);
//			if(isDisplayednew(PREFERENCE_EMAIL,"PREFERENCE_EMAIL")&&IsObjectPresent(PREFERENCE_EMAIL,"PREFERENCE_EMAIL")&&!email.isEmpty())
//			{
//				String Email_Address = GetText(PREFERENCE_EMAIL,"PREFERENCE_EMAIL");
//				if(Email_Address.equalsIgnoreCase(email)) 
//				{
//					report.updateTestLog("Preferences ", "Email Address are updated successfully", Status.PASS);
//				}
//				else
//				{
//					report.updateTestLog("Preferences ", "Error in updating Email Address in iPortal", Status.FAIL);
//				}
//			}
//			HardDelay(300);
//			if (isTextPresent("The customer must confirm enrollment via the email sent.")) {
//				report.updateTestLog("Preferences ", "Paperless preferences details are updated successfully", Status.PASS);
//			} else {
//				report.updateTestLog("Preferences ", "Error in updating preferences in iPortal", Status.FAIL);
//			}
//			
//			//Validate Email Alert message when Edit_Email is empty 
//			if(isDisplayednew(Edit_Email_Alert,"Edit_Email_Alert")&&(IsObjectPresent(Edit_Email_Alert,"Edit_Email_Alert")))
//			{
//				Alert_Text_Displayed = GetText(Edit_Email_Alert,"Edit_Email_Alert");
//				messageValidation("Preferences", Alert_Text_Displayed);
//			}
//			
//			switchToDefault();
//			// switchToFrame(getLastFrame());
//			// ClickJSElement(PAS_Done_Button, "PAS_Done_Button");
//			// ajaxFluentPredicateWaitnew();
//			// HardDelay(8000);
		
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

}
