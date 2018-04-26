package services.rest.apiclients;

import java.net.InetAddress;

import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.StaticValue;

import services.rest.pojo.XApplicationContext;

public class GenerateXApplicationContext {
	XApplicationContext xApplicationContext = new XApplicationContext();
	String regId = StaticValue.Empty;
	WebDriver driver = DriverManager.getWebDriver();
	
	public String getXApplicationContext() {
		xApplicationContext.setSessionId(getRegID());
		xApplicationContext.setAddress(getIP());
		xApplicationContext.setApplication("MyPolicy");
		xApplicationContext.setSourceApplication("DigitalServices");
		xApplicationContext.setUserId("AutomationBDD");
		xApplicationContext.setCorrelationId("Automation_"+ System.currentTimeMillis());
		System.out.println("xApp:" + xApplicationContext);
		
		
		String actual = "{\"address\":\"" + 
				xApplicationContext.getAddress() +
				"\",\"application\":\"" +
				xApplicationContext.getApplication() +
				"\",\"correlationId\":\"" +
				xApplicationContext.getCorrelationId() +
				"\",\"sessionId\":\"" +
				xApplicationContext.getSessionId() +
				"\",\"userId\":\"" +
				xApplicationContext.getUserId() +
				"\"}";
				
				
				
				
		return actual;
		}

	private String getRegID() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String wmsCustomerInfo = (String) js.executeScript("return JSON.stringify(window.wms_customer_info, null, 1)");
		JSONObject obj;
		try {
			obj = new JSONObject(wmsCustomerInfo);
			System.out.println("regid:" + obj.get("regId").toString());
			regId = obj.get("regId").toString();
			System.out.println("regid:" + regId + ":");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return regId;

	}

	private static String getIP() {
		try {
			System.out.println(InetAddress.getLocalHost().getHostAddress());
			return InetAddress.getLocalHost().getHostAddress();

		} catch (Exception var1) {
			return "10.0.75.1";
		}
	}
	
	

}