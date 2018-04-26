package com.Cucumber.supportLibraries;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.*;

/**
 * Factory class for creating the {@link WebDriver} object as required
 * 
 * @author Cognizant
 */
public class WebDriverFactory {
    private static Properties properties;
    static Logger log = Logger.getLogger(WebDriverFactory.class);

    private WebDriverFactory() {
	// To prevent external instantiation of this class
    }

    /**
     * Function to return the appropriate {@link WebDriver} object based on the
     * parameters passed
     * 
     * @param browser
     *            The {@link Browser} to be used for the test execution
     * @return The corresponding {@link WebDriver} object
     */
    @SuppressWarnings("deprecation")
    public static WebDriver getWebDriver(Browser browser) {
	WebDriver driver = null;
	properties = Settings.getInstance();
	boolean proxyRequired = Boolean.parseBoolean(properties.getProperty("ProxyRequired"));
	try {
	    switch (browser) {
	    case CHROME:
		// Takes the system proxy settings automatically

		// System.setProperty("webdriver.chrome.driver",
		// properties.getProperty("ChromeDriverPath"));
		String chromeDriverPath = System.getProperty("user.dir") + Util.getFileSeparator() + "src" + Util.getFileSeparator() + "test" + Util.getFileSeparator()
			+ "resources" + Util.getFileSeparator() + "others" + Util.getFileSeparator() + "browserDrivers" + Util.getFileSeparator() + "chromedriver.exe";
		System.out.println(chromeDriverPath);
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		driver = new ChromeDriver();
		break;

	    case FIREFOX:
	    	
	    	String firefoxDriverPath=	System.getProperty("user.dir") + Util.getFileSeparator() + "src" + Util.getFileSeparator() + "test" + Util.getFileSeparator()
			+ "resources" + Util.getFileSeparator() + "others" + Util.getFileSeparator() + "browserDrivers" + Util.getFileSeparator() + "geckodriver.exe";
	  //  	System.setProperty("webdriver.gecko.driver", "C:\\Firefox driver\\geckodriver.exe");
	    	System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
	    	FirefoxOptions options = new FirefoxOptions()
	        .setProfile(new FirefoxProfile());
	    driver = new FirefoxDriver(options);
	    	
	    	/*
	    	FirefoxOptions options = new FirefoxOptions();
			options.setBinary("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe"); //Location where Firefox is installed
	 
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("moz:firefoxOptions", options);
			driver = new FirefoxDriver(capabilities);
			*/
			//set more capabilities as per your requirements
	 
		//	FirefoxDriver driver = new FirefoxDriver(capabilities);
		//	driver.get("http://www.google.com");
		// Takes the system proxy settings automatically
	    	
	   // 	DesiredCapabilities capabilities=DesiredCapabilities.firefox();
	   // 	capabilities.setCapability("marionette", true);
	   // 	webdriver = new FirefoxDriver(capabilities);
	    	
		//driver = new FirefoxDriver(capabilities);
	//	FirefoxOptions firefoxOptions = new FirefoxOptions();
	 //   firefoxOptions.setCapability("marionette", true);
	 //   driver = new FirefoxDriver(firefoxOptions);
		break;

	    case INTERNET_EXPLORER:
		// Takes the system proxy settings automatically
	    String ieDriverPath=System.getProperty("user.dir") + Util.getFileSeparator() + "src" + Util.getFileSeparator() + "test" + Util.getFileSeparator()
		+ "resources" + Util.getFileSeparator() + "others" + Util.getFileSeparator() + "browserDrivers" + Util.getFileSeparator() + "IEDriverServer.exe";
		System.setProperty("webdriver.ie.driver", ieDriverPath);
		driver = new InternetExplorerDriver();
		break;

	    case OPERA:
		// Does not take the system proxy settings automatically!
		// NTLM authentication for proxy NOT supported

		if (proxyRequired) {
		    DesiredCapabilities desiredCapabilities = getProxyCapabilities();
		    driver = new OperaDriver(desiredCapabilities);
		} else {
		    driver = new OperaDriver();
		}

		break;

	    case SAFARI:
		//Takes the system proxy settings automatically
	    	DesiredCapabilities desiredCapabilities = DesiredCapabilities.safari();
			  SafariOptions safariOptions = new SafariOptions();
			  safariOptions.setUseCleanSession(true);
			  desiredCapabilities.setCapability(SafariOptions.CAPABILITY, safariOptions);
			 

		driver = new SafariDriver();
		break;

	    default:
		throw new Exception("Unhandled browser!");
	    }
	} catch (Exception ex) {
	    log.error(ex.getMessage());
 	    ex.printStackTrace();
	}
	

	return driver;
    }

    private static DesiredCapabilities getProxyCapabilities() {
	properties = Settings.getInstance();
	String proxyUrl = properties.getProperty("ProxyHost") + ":" + properties.getProperty("ProxyPort");

	Proxy proxy = new Proxy();
	proxy.setProxyType(ProxyType.MANUAL);
	proxy.setHttpProxy(proxyUrl);
	proxy.setFtpProxy(proxyUrl);
	proxy.setSslProxy(proxyUrl);

	DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
	desiredCapabilities.setCapability(CapabilityType.PROXY, proxy);

	return desiredCapabilities;
    }

    /**
     * Function to return the {@link RemoteWebDriver} object based on the
     * parameters passed
     * 
     * @param browser
     *            The {@link Browser} to be used for the test execution
     * @param browserVersion
     *            The browser version to be used for the test execution
     * @param platform
     *            The {@link Platform} to be used for the test execution
     * @param remoteUrl
     *            The URL of the remote machine to be used for the test
     *            execution
     * @return The corresponding {@link RemoteWebDriver} object
     */
    public static WebDriver getRemoteWebDriver(Browser browser, String browserVersion, Platform platform, String remoteUrl) {

	properties = Settings.getInstance();
	boolean proxyRequired = Boolean.parseBoolean(properties.getProperty("ProxyRequired"));

	DesiredCapabilities desiredCapabilities = null;
	if (browser.equals(Browser.OPERA) && proxyRequired) {
	    desiredCapabilities = getProxyCapabilities();
	} else {
	    desiredCapabilities = new DesiredCapabilities();
	}

	desiredCapabilities.setBrowserName(browser.getValue());

	if (browserVersion != null) {
	    desiredCapabilities.setVersion(browserVersion);
	}
	if (platform != null) {
	    desiredCapabilities.setPlatform(platform);
	}

	desiredCapabilities.setJavascriptEnabled(true); // Pre-requisite for
							// remote execution
	System.out.println("browserstackYes:"+properties.getProperty("ExecuteInBrowserStack")+":");
	if (properties.getProperty("ExecuteInBrowserStack").equals("yes")) {
		System.out.println("browserstack.local set to true");
		desiredCapabilities.setCapability("browserstack.local", "true");
	} 

	
	
	URL url = getUrl(remoteUrl);

	return new RemoteWebDriver(url, desiredCapabilities);
    }

    private static URL getUrl(String remoteUrl) {
	URL url = null;
	try {
	    url = new URL(remoteUrl);
	} catch (MalformedURLException e) {
	    e.printStackTrace();

	}
	return url;
    }

    /**
     * Function to return the {@link RemoteWebDriver} object based on the
     * parameters passed
     * 
     * @param browser
     *            The {@link Browser} to be used for the test execution
     * @param remoteUrl
     *            The URL of the remote machine to be used for the test
     *            execution
     * @return The corresponding {@link RemoteWebDriver} object
     */
    public static WebDriver getRemoteWebDriver(Browser browser, String remoteUrl) {
	return getRemoteWebDriver(browser, null, null, remoteUrl);
    }

}