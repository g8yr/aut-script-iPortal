package businesscomponents.mypolicy;

import static org.junit.Assert.fail;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.StaticValue;
import com.Cucumber.supportLibraries.Util;
import com.Cucumber.supportLibraries.Webaction;

import services.rest.apiclients.GenerateXApplicationContext;
import services.rest.pojo.XApplicationContext;
import webelementrepository.MyPolicy.DashboardPage;

public class DashboardFunctions extends Webaction implements DashboardPage {
	WebDriver driver = DriverManager.getWebDriver();
	StaticValue staticvalue = new StaticValue(); 
	Webaction action = new Webaction();
	GenerateXApplicationContext generateXApplicationContext = new GenerateXApplicationContext();
//	public String CurrentXApplicationContext;
	boolean result;
	public void validateDashboardPage()
	{
	
		
		action.waitForElement(LefNavPayment,"Dashboard Section");
		//IsObjectPresentWithoutWait(Policies_Section, "Policies_Section")
		String URL= driver.getCurrentUrl();
	if (URL.contains("dashboard")) {
		System.out.println(URL);
		System.out.println("Dashboard is displayed successfully");
		currentScenario.embed(Util.takeScreenshot(driver),
				"image/png");
		/*
	     final Screenshot screenshot = new AShot().shootingStrategy(
	                new ViewportPastingStrategy(500)).takeScreenshot(driver);
	 
	        final BufferedImage image = screenshot.getImage();
	        try {
				ImageIO.write(image, "PNG", new File(
				        "write here path of location to save image"
				                + "AShot_BBC_Entire.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
	    //    currentScenario.embed(screenshot,"image/png");
	    //    final BufferedImage image = screenshot.getImage();
	      //  image.get
	        
	     //   final byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
	       
	//	currentScenario.embed(Util.takeScreenshot(driver),
		//		"image/png");
		 result = IsObjectPresentWithoutWait(Mobile_Menu_Link, "Mobile_Menu_Link");
//		 JavascriptExecutor js = (JavascriptExecutor) driver;
//		 System.out.println("DashboardFunctions.validateDashboardPage()");
//		 String wmsCustomerInfo = (String) js.executeScript("return JSON.stringify(window.wms_customer_info, null, 1)");
//		 
//		JSONObject obj;
//		try {
//			obj = new JSONObject(wmsCustomerInfo);
//			System.out.println("window.wms_customer_info:" + obj.toString());
//			System.out.println("regid:" + obj.get("regId"));
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		 CurrentXApplicationContext = generateXApplicationContext.getXApplicationContext();
//		 System.out.println("CurrentXApplicationContext" + CurrentXApplicationContext);
		assertTrue(result,"true");
		
		
	} else {
		System.out.println("Dashboard is not displayed successfully");
		currentScenario.embed(Util.takeScreenshot(driver),
				"image/png");
		fail("Dashboard is not landed sucessfully");
	}
	}
	
	public void clickPolicySection()
	{
		if(DriverManager.properties.getProperty("MobileExecutionType").equalsIgnoreCase("Desktop_Responsive"))
		{
		ClickJSElement(Mobile_Menu_Link,"Mobile_Menu_Link");
		}
		
		if (isDisplayed(Mobile_Policies_Section, "Mobile_Policies_Section ")) 
		{
		ClickJSElement(Mobile_Policies_Section,"Policies_Section");	
		}
		else if(isDisplayed(Policies_Section, "Policies_Section ")) 
		{
		ClickJSElement(Policies_Section,"Policies_Section");	
		}
		
		currentScenario.embed(Util.takeScreenshot(driver),
				"image/png");
		
	}
	
	public void validatePolicySection(String PolicyNumber) {
		System.out.println("DashboardFunctions.validatePolicySection() :");

		By policies_Displayed = By.xpath(String.format(Policies_Displayed, PolicyNumber));
		waitForElement(policies_Displayed);
		if (isDisplayed(policies_Displayed, "Policies ")) {
			ClickElement(policies_Displayed, 100);
//			currentScenario.embed(Util.takeScreenshot(driver),
//					"image/png");
		} else {
			System.out.println("Policy is not displayed successfully");
			currentScenario.embed(Util.takeScreenshot(driver),
					"image/png");
		}

	}
	
}