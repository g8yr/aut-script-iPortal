package stepDefinitions.common;

import io.appium.java_client.AppiumDriver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.Cucumber.supportLibraries.Browser;
import com.Cucumber.supportLibraries.DriverFactory;
import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.ExecutionMode;
import com.Cucumber.supportLibraries.PerfectoLabUtils;
import com.Cucumber.supportLibraries.RestApiForJira;
import com.Cucumber.supportLibraries.SeleniumTestParameters;
import com.Cucumber.supportLibraries.Settings;
import com.Cucumber.supportLibraries.StaticValue;
import com.Cucumber.supportLibraries.TimeStamp;
import com.Cucumber.supportLibraries.Util;
import com.Cucumber.supportLibraries.Webaction;
import com.Cucumber.supportLibraries.custom.ReadCSVFileFunctions;
//import com.experitest.selenium.MobileWebDriver;
import com.github.mkolisnyk.cucumber.reporting.CucumberResultsOverview;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class CukeHooks extends MasterStepDefs  {
	
	static Logger log;
	static Properties properties = Settings.getInstance();
	Webaction action;
	

	static {
		log = Logger.getLogger(CukeHooks.class);
	}
	
	@Before
	public void setUp(Scenario scenario) {
		try {
	//		serveAllureReport();
			ReadCSVFileFunctions readCSVfilefunctions = new ReadCSVFileFunctions();
			currentScenario = scenario;
			propertiesFileAccess = properties;
			Thread.sleep(2000);
			currentTestParameters = DriverManager.getTestParameters();
			currentTestParameters.setScenarioName(scenario.getName());
//			String browserName= readCSVfilefunctions.readCsvFileCommonData(scenario.getName());
//			log.info("Browser name received from csv - "+browserName);
//			Thread.sleep(2000);
//			/*testParameters.setBrowser(Browser
//					.valueOf(method.getTestMethod().getXmlTest().getLocalParameters().get("BrowserName")));*/
//			currentTestParameters.setBrowser(Browser.valueOf(browserName));
//			log.info("Running the Scenario : " + scenario.getName());
			
			String csvparameter = StaticValue.Empty;
			csvparameter= readCSVfilefunctions.readCsvFileCommonData(scenario.getName());
			String [] Splitcsvparameter = csvparameter.split("#");
			System.out.println("Splitcsvparameter:"+Splitcsvparameter[0]+":");
			System.out.println("Splitcsvparameter:"+Splitcsvparameter[1]+":");	
			System.out.println("Splitcsvparameter:"+Splitcsvparameter[2]+":");
			log.info("Browser name received from csv - "+Splitcsvparameter[0]);
			Thread.sleep(2000);
			/*testParameters.setBrowser(Browser
					.valueOf(method.getTestMethod().getXmlTest().getLocalParameters().get("BrowserName")));*/
			currentTestParameters.setBrowser(Browser.valueOf(Splitcsvparameter[0]));
			currentTestParameters.setBrowserVersion(Splitcsvparameter[1]);
			System.out.println(Platform.fromString(Splitcsvparameter[2]));
			currentTestParameters.setPlatform(Platform.fromString(Splitcsvparameter[2]));
			
			if (Boolean.parseBoolean(properties.getProperty("ExecuteInMobile"))) {
				invokeForMobileExecution(scenario);
			} else {
				invokeForDesktopExecution(scenario);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error at Before Scenario " + e.getMessage());
		}
	}

	private void invokeForDesktopExecution(Scenario scenario) {
		switch (currentTestParameters.getExecutionMode()) {

		case LOCAL:
		case REMOTE:
		case SAUCELABS:
			log.info(
					"Running the Scenario : " + scenario.getName() + " in " + currentTestParameters.getExecutionMode());
			log.info(
					"Running the Scenario : " + scenario.getName() + " in browser" + currentTestParameters.getBrowser());
			WebDriver driver = DriverFactory.createInstanceWebDriver(currentTestParameters);
			DriverManager.setWebDriver(driver);
			action=new Webaction();
			action.InitializeBrowserTimeout();
			break;

		default:
			try {
				throw new Exception("Unhandled Execution Mode!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private void invokeForMobileExecution(Scenario scenario) {
		switch (currentTestParameters.getExecutionMode()) {

		case MOBILE:
		case SAUCELABS:
		case PERFECTO:
		case MINT:

			log.info(
					"Running the Scenario : " + scenario.getName() + " in " + currentTestParameters.getExecutionMode());
			AppiumDriver driver = DriverFactory.createInstance(currentTestParameters);
			DriverManager.setAppiumDriver(driver);
			action=new Webaction();
			action.InitializeBrowserTimeout();
			break;

//		case SEETEST:
//
//			log.info(
//					"Running the Scenario : " + scenario.getName() + " in " + currentTestParameters.getExecutionMode());
//			//MobileWebDriver seetestDriver = DriverFactory.createInstanceSeetestDriver(currentTestParameters);
//			DriverManager.setSeetestDriver(seetestDriver);
//			break;

		default:
			try {
				throw new Exception("Unhandled Execution Mode!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("rawtypes")
	@After
	public void embedScreenshot(Scenario scenario) {
		try {
			if (Boolean.valueOf(properties.getProperty("TrackIssuesInJira"))) {
				updateDefectInJira(scenario);
			}
			if (Boolean.parseBoolean(properties.getProperty("ExecuteInMobile"))
					&& Boolean.valueOf(properties.getProperty("PerfectoReportGeneration"))) {
				capturePerfectoReportsForMobile(scenario);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error("Problem while closing the Driver Object " + ex.getMessage());

		} finally {

			if (Boolean.parseBoolean(properties.getProperty("ExecuteInMobile"))) {
				if (currentTestParameters.getExecutionMode() == ExecutionMode.SEETEST) {
//					MobileWebDriver driver = DriverManager.getSeetestDriver();
//					driver.client.releaseDevice("*", true, false, true);
//					driver.client.releaseClient();
//					driver.quit();
				} else {
					AppiumDriver driver = DriverManager.getAppiumDriver();
					if (driver != null) {
						driver.quit();
					}
				}
			} else {
				WebDriver driver = DriverManager.getWebDriver();
				if (driver != null) {
					capturePerfectoReportForDesktop(scenario, currentTestParameters, driver);
				if(!properties.getProperty("MultiScenario").equals("True"))
					{
					driver.quit();
					}
				
					
				}
			}
		}
	}

	/**
	 * Embed a screenshot in test report if test is marked as failed And Update
	 * Task in JIRA
	 * @throws IOException 
	 */
	private void updateDefectInJira(Scenario scenario) throws IOException {
		if (scenario.isFailed()) {
			try {
				if (Boolean.parseBoolean(properties.getProperty("ExecuteInMobile"))) {
					if (currentTestParameters.getExecutionMode() == ExecutionMode.SEETEST) {/*
						
						byte[] screenshot = Util.takeScreenshot(DriverManager.getAppiumDriver());
						scenario.embed(screenshot, "image/png");
					*/} else {
						byte[] screenshot = Util.takeScreenshot(DriverManager.getAppiumDriver());
						scenario.embed(screenshot, "image/png");
					}
				} else {
					byte[] screenshot = Util.takeScreenshot(DriverManager.getWebDriver());
					scenario.embed(screenshot, "image/png");
				}

				File filePath = ((TakesScreenshot) DriverManager.getWebDriver()).getScreenshotAs(OutputType.FILE);
				RestApiForJira.createLog(scenario.getName(), scenario.getName(), filePath.toString());

			} catch (WebDriverException somePlatformsDontSupportScreenshots) {
				somePlatformsDontSupportScreenshots.printStackTrace();
				log.error(somePlatformsDontSupportScreenshots.getMessage());
			}
		}
	}

	private String getFileName(Browser browser, String deviceName) {
		String fileName = "";
		if (browser == null) {
			fileName = deviceName;
		} else if (deviceName == null) {
			fileName = browser.toString();
		} else {
			fileName = "report";
		}
		return fileName;
	}

	@SuppressWarnings("rawtypes")
	private void capturePerfectoReportsForMobile(Scenario scenario) {
		try {
			AppiumDriver driver = DriverManager.getAppiumDriver();
			driver.close();
			String Udid;

			if (!(driver.getCapabilities().getCapability("model") == null)) {
				Udid = driver.getCapabilities().getCapability("model").toString();
			} else {
				Udid = driver.getCapabilities().getCapability("deviceName").toString();
			}
			PerfectoLabUtils.downloadReport(driver, "pdf",
					Util.getResultsPath() + Util.getFileSeparator() + scenario.getName() + "_" + Udid);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("Problem while downloading Perfecto Report for " + scenario.getName());

		}
	}

	private void capturePerfectoReportForDesktop(Scenario scenario, SeleniumTestParameters testParametrs,
			WebDriver driver) {
		if (Boolean.valueOf(properties.getProperty("PerfectoReportGeneration"))) {
			driver.close();

			Map<String, Object> params = new HashMap<String, Object>();
			((RemoteWebDriver) driver).executeScript("mobile:execution:close", params);
			params.clear();

			try {
				PerfectoLabUtils.downloadReport((RemoteWebDriver) driver, "pdf",
						Util.getResultsPath() + Util.getFileSeparator() + scenario.getName() + "_"
								+ getFileName(testParametrs.getBrowser(), testParametrs.getDeviceName()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void generateCustomReports() {

		CucumberResultsOverview overviewReports = new CucumberResultsOverview();
		overviewReports.setOutputDirectory("target");
		overviewReports.setOutputName("cucumber-results");
		overviewReports.setSourceFile("target/cucumber-report/Smoke/cucumber.json");
		try {
			overviewReports.executeFeaturesOverviewReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void copyReportsFolder() {

		String timeStampResultPath = TimeStamp.getInstance();

		File source = new File(Util.getTargetPath());
		File source1 = new File(Util.getAllurePath());
		File dest = new File(timeStampResultPath);
		File dest1 = new File(timeStampResultPath + Util.getFileSeparator() + "Allure-reports");
		if (!dest1.isDirectory()) {
			dest1.mkdirs();
		}
		try {
			FileUtils.copyDirectory(source, dest);
			FileUtils.copyDirectory(source1, dest1);
			try {
				FileUtils.cleanDirectory(source);
			} catch (Exception e) {

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		TimeStamp.reportPathWithTimeStamp = null;
		
	}
	
	public static void serveAllureReport(){
		Process p= null;
		String Maven_Path = null;
		try {
			
		      if(System.getenv("M2_HOME")!=null)
		      {
		    	  Maven_Path= System.getenv("M2_HOME");
		      }
		      else if(System.getenv("MAVEN_HOME")!=null)
		      {
		    	  Maven_Path= System.getenv("MAVEN_HOME");
		      }
		     
		     // String[] cmd = new String[] {"C://Maven//bin//mvn.bat", "allure:report"};
	      //p = Runtime.getRuntime().exec(cmd);
	        p = Runtime.getRuntime().exec(Maven_Path+"/bin/mvn.cmd allure:report");
	    } catch (IOException e) {
	        System.err.println("Error on exec() method");
	        e.printStackTrace();
	    }

	    try {
			copy(p.getInputStream(), System.out);
			 p.waitFor();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   

	}

	public static void copy(InputStream in, OutputStream out) throws IOException {
	    while (true) {
	        int c = in.read();
	        if (c == -1)
	            break;
	        out.write((char) c);
	    }
	}
	public static void copyEnvironmentFiles() throws IOException
	{
		
		String ExecutionMode=currentTestParameters.getExecutionMode().getValue();
		String OutputFilePath = currentTestParameters.getRelativePath() + Util.getFileSeparator() + "src"
				+ Util.getFileSeparator() + "test" + Util.getFileSeparator() + "resources" + Util.getFileSeparator()
				+ "test-data" + Util.getFileSeparator() + "environmentfile";
		String OutputFile = currentTestParameters.getRelativePath() + Util.getFileSeparator() + "src"
				+ Util.getFileSeparator() + "test" + Util.getFileSeparator() + "resources" + Util.getFileSeparator()
				+ "test-data" + Util.getFileSeparator() + "environmentfile" + Util.getFileSeparator() +"environment.xml";
		
		if(ExecutionMode.equalsIgnoreCase("Local"))
		{
			File trgDir = new File(OutputFilePath);
			FileUtils.forceDelete(trgDir);
		String InputFile = currentTestParameters.getRelativePath() + Util.getFileSeparator() + "src"
				+ Util.getFileSeparator() + "test" + Util.getFileSeparator() + "resources" + Util.getFileSeparator()
				+ "test-data" + Util.getFileSeparator() + "environment_Local_Windows.xml";
		String TempFile = currentTestParameters.getRelativePath() + Util.getFileSeparator() + "src"
				+ Util.getFileSeparator() + "test" + Util.getFileSeparator() + "resources" + Util.getFileSeparator()
				+ "test-data" + Util.getFileSeparator() + "environmentfile" + Util.getFileSeparator() +"environment_Local_Windows.xml";
		
		
	
		File file1 = new File(InputFile);
		FileUtils.copyFileToDirectory(file1, trgDir, true);
		File file2 = new File(TempFile);
		File file3 = new File(OutputFile);
		boolean success = file2. renameTo(file3);
		if (! success) {
		System. out. println("Error trying to rename file");
		}
		}
		
		else if (ExecutionMode.equalsIgnoreCase("Remote"))
		{
			File trgDir = new File(OutputFilePath);
			FileUtils.forceDelete(trgDir);
			String InputFile = currentTestParameters.getRelativePath() + Util.getFileSeparator() + "src"
					+ Util.getFileSeparator() + "test" + Util.getFileSeparator() + "resources" + Util.getFileSeparator()
					+ "test-data" + Util.getFileSeparator() + "environment_Remote_Windows.xml";
			String TempFile = currentTestParameters.getRelativePath() + Util.getFileSeparator() + "src"
					+ Util.getFileSeparator() + "test" + Util.getFileSeparator() + "resources" + Util.getFileSeparator()
					+ "test-data" + Util.getFileSeparator() + "environmentfile" + Util.getFileSeparator() +"environment_Remote_Windows.xml";
			
			
		
			File file1 = new File(InputFile);
			FileUtils.copyFileToDirectory(file1, trgDir, true);
			File file2 = new File(TempFile);
			File file3 = new File(OutputFile);
			boolean success = file2. renameTo(file3);
			if (! success) {
			System. out. println("Error trying to rename file");
			}
		}
	
	}
}