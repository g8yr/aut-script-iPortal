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


import webelementrepository.MyPolicy.DashboardPage;
import webelementrepository.iPortal.iPortalDashboardPage;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.IPortalStaticValue;
import com.Cucumber.supportLibraries.Util;
import com.Cucumber.supportLibraries.Webaction;







import junit.framework.Assert;

public class iPortalDashboardFunctions extends Webaction 
{
	WebDriver driver = DriverManager.getWebDriver();
	Webaction action = new Webaction();
	IPortalStaticValue iportalstaticvalue = new IPortalStaticValue();
	
	iPortalDashboardPage iportaldashboardpage = new iPortalDashboardPage();
	boolean result;	
	
	public void searchIportalPolicy(String PolicyNumber) 
	{	
		//action.waitForElement(iportaldashboardpage.POLICY_SEARCH_TEXT, PolicyNumber);
		
		action.EnterText(iportaldashboardpage.POLICY_SEARCH_TEXT, PolicyNumber);
		action.HardDelay(5000);
		//action.waitForElement(iportaldashboardpage.POLICY_SEARCH_BUTTON);	
//		if(action.IsObjectPresent(iportaldashboardpage.POLICY_SEARCH_BUTTON,"Policy Search button"))
//		{
		if(action.isDisplayed(iportaldashboardpage.QuickLink,"Quick Link"))
			action.ClickElement(iportaldashboardpage.QuickLink);
		if(action.isDisplayed(iportaldashboardpage.Close_Quicklink,"Quick Link"))
			action.ClickElement(iportaldashboardpage.Close_Quicklink);
		action.ClickJSElement(iportaldashboardpage.POLICY_SEARCH_BUTTON,"Policy Search Button");
		action.HardDelay(5000);
//		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		if ((action.isDisplayed(By.xpath("//span[.='Show More']"), "Show more link") && action.IsObjectPresent(By.xpath("//span[.='Show More']"), "Show more link")))
//			action.ClickElement(By.xpath("//span[.='Show More']"));
		//action.waitForElement(iportaldashboardpage.POLICY_SEARCH_RESULT_CLICK);
		action.ClickJSElement(iportaldashboardpage.POLICY_SEARCH_RESULT_CLICK,"Policy Search Result Click");
		System.out.println("Login Success");
	}

	public void navigateToIportalPolicyOverviewPage() 
	{
		action.ClickJSElement(iportaldashboardpage.POLICY_SEARCH_RESULT_EXPIRE_ICON_CLICK, "Policy search result expire icon click");
		action.HardDelay(5000);
		action.waitForElement(iportaldashboardpage.Discount_Section);
	}
}
