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
import webelementrepository.iPortal.iPortalCustomerOverviewPage;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.IPortalStaticValue;
import com.Cucumber.supportLibraries.Util;
import com.Cucumber.supportLibraries.Webaction;


import junit.framework.Assert;

public class iPortalCustomerOverviewFunctions extends MasterStepDefs
{
	WebDriver driver = DriverManager.getWebDriver();
	Webaction action = new Webaction();
	IPortalStaticValue iportalstaticvalue = new IPortalStaticValue();
	iPortalCustomerOverviewPage iPortalcustomeroverviewpage = new iPortalCustomerOverviewPage();
	
	boolean result;


	public void validateCustomerPage() 
	{
		if(action.isDisplayed(iPortalcustomeroverviewpage.POLICY_NUMBER,"CustomerOverview Policy Number"))
		{
			System.out.println("CustomerOverview Page is displayed successfully");
			currentScenario.embed(Util.takeScreenshot(driver),
					"image/png");	
		}
		else
		{
			System.out.println("CustomerOverview is not displayed successfully");
			currentScenario.embed(Util.takeScreenshot(driver),
					"image/png");
			fail("CustomerOverview is not landed sucessfully");
		}		
	}


	public void validatePolicyList(String Auto_Policy_Number, String HO_Policy_Number, String PUP_Policy_Number) 
	{
//		String Auto_Policy_Number = dataTable.getData(testParameter, "Evalue_IPortal", "Linked_Auto_Policy_Number");
//		String HO_Policy_Number = dataTable.getData(testParameter, "Evalue_IPortal", "Linked_HO_Policy_Number");
//		String PUP_Policy_Number = dataTable.getData(testParameter, "Evalue_IPortal", "Linked_PUP_Policy_Number");
		int length=action.NumberOfRows_XPATH(iPortalcustomeroverviewpage.Policy_List_Length);
		int j = 0,l=0,m=0;
		for (int i=1;i<length;i++)
		{
			String Linked_Policy_Number=action.GetText(By.xpath(String.format(iPortalcustomeroverviewpage.Policy_List,i)));
			if(Linked_Policy_Number.equalsIgnoreCase(Auto_Policy_Number))
			{
				currentScenario.embed(Util.takeScreenshot(driver),
						"image/png");
				System.out.println("Customer Overview page: Linked Auto policy is successfully displayed");
			//	report.updateTestLog("Customer Overview page", "Linked Auto policy is successfully displayed", Status.PASS);
				j=1;
			}
			if(Linked_Policy_Number.equalsIgnoreCase(HO_Policy_Number))
			{
				currentScenario.embed(Util.takeScreenshot(driver),
						"image/png");
				System.out.println("Customer Overview page: Linked HO policy is successfully displayed");
				//report.updateTestLog("Customer Overview page", "Linked HO policy is successfully displayed", Status.PASS);
				l=1;
			}
			if(Linked_Policy_Number.equalsIgnoreCase(PUP_Policy_Number))
			{
				currentScenario.embed(Util.takeScreenshot(driver),
						"image/png");
				fail("Customer Overview page: PUP policy is successfully displayed");
				//report.updateTestLog("Customer Overview page", "PUP policy is linked and displayed", Status.FAIL);
				m=1;
			}
		}
		if(j!=1&&l!=1)
		{
			currentScenario.embed(Util.takeScreenshot(driver),
					"image/png");
			fail("Customer Overview page: Auto and HO policy is not linked and displayed");
			//report.updateTestLog("Customer Overview page", "Auto and HO policy is not linked and displayed", Status.FAIL);
		}
		if(m!=1)
		{
			currentScenario.embed(Util.takeScreenshot(driver),
					"image/png");
			System.out.println("Customer Overview page: PUP policy is not linked and displayed");
			//report.updateTestLog("Customer Overview page", "PUP policy is not linked and displayed", Status.PASS);
		}	
		
	}
		
		
		
}
