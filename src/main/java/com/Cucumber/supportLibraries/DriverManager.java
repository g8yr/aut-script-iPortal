package com.Cucumber.supportLibraries;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

//import com.experitest.selenium.MobileWebDriver;




import io.appium.java_client.AppiumDriver;

/**
 * A generic WebDriver manager, which handles multiple instances of WebDriver.
 * 
 * @author Cognizant
 */
public class DriverManager {

	/*
	 * Used for Multithreading of WebDriver Object
	 */
	@SuppressWarnings("rawtypes")
	private static ThreadLocal<AppiumDriver> appiumDriver = new ThreadLocal<AppiumDriver>();
	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
//	private static ThreadLocal<MobileWebDriver> seetestDriver = new ThreadLocal<MobileWebDriver>();
	private static ThreadLocal<SeleniumTestParameters> testParameters = new ThreadLocal<SeleniumTestParameters>();
	public static Properties properties = Settings.getInstance();;

	static Logger log;

	static {
		log = Logger.getLogger(DriverManager.class);
	}

	// WebDriver Object Creation

	public static WebDriver getWebDriver() {
		if (webDriver.get() == null) {
			// this is need when running tests from IDE
			log.info("Thread has no WedDriver, creating new one");
			setWebDriver(DriverFactory.createInstanceWebDriver(null));
		}
		log.debug("Getting instance of remote driver" + webDriver.get().getClass());
		return webDriver.get();
	}

	// AppiumDriver Object Creation

	@SuppressWarnings("rawtypes")
	public static AppiumDriver getAppiumDriver() {
		if (appiumDriver.get() == null) {
			// this is need when running tests from IDE
			log.info("Thread has no Appium driver, creating new one");
			setAppiumDriver(DriverFactory.createInstance(null));
		}
		log.debug("Getting instance of remote driver" + appiumDriver.get().getClass());
		return appiumDriver.get();
	}
	
	// SeetestDriver Object Creation
	/*public static MobileWebDriver getSeetestDriver() {
		if (seetestDriver.get() == null) {
			// this is need when running tests from IDE
			log.info("Thread has no Seetest driver, creating new one");
			setSeetestDriver(DriverFactory.createInstanceSeetestDriver(null));
		}
		log.debug("Getting instance of remote driver" + seetestDriver.get().getClass());
		return seetestDriver.get();
	}*/

	@SuppressWarnings("rawtypes")
	public static void setAppiumDriver(AppiumDriver driver) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		DriverManager.appiumDriver.set(driver);
	}

	public static void setWebDriver(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if(properties.getProperty("MobileExecutionType").equalsIgnoreCase("Desktop_Responsive"))
		{
		driver.manage().window().setSize(new Dimension(375, 667));
		}
		DriverManager.webDriver.set(driver);
	}

	/*public static void setSeetestDriver(MobileWebDriver driver) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		DriverManager.seetestDriver.set(driver);
	}*/
	protected void setRelativePath() {
		String relativePath = (new File(System.getProperty("user.dir")))
				.getAbsolutePath();// 61
		if (relativePath.contains("supportlibraries")) {// 62
			relativePath = (new File(System.getProperty("user.dir")))
					.getParent();// 63
		}

		System.out.println("Relative Path in Result Summary manager: "
				+ relativePath);// 65
		
		// 66
	//	logger.debug("Setting path variables in relative to the workspace directory");// 67
	}// 68

	public static void setTestParameters(SeleniumTestParameters testParameters) {
		DriverManager.testParameters.set(testParameters);

	}

	public static SeleniumTestParameters getTestParameters() {
		return testParameters.get();
	}
}