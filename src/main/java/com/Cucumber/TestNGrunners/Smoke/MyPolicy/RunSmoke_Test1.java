package com.Cucumber.TestNGrunners.Smoke.MyPolicy;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

import stepDefinitions.common.CukeHooks;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.Settings;
import com.Cucumber.supportLibraries.TimeStamp;
import com.Cucumber.supportLibraries.Util;
import com.github.mkolisnyk.cucumber.reporting.CucumberDetailedResults;
import com.github.mkolisnyk.cucumber.reporting.CucumberResultsOverview;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@ExtendedCucumberOptions(

		jsonReport = "target/cucumber-report/Smoke/cucumber.json", jsonUsageReport = "target/cucumber-report/Smoke/cucumber-usage.json", outputFolder = "target/cucumber-report/Smoke", detailedReport = true, detailedAggregatedReport = true, overviewReport = true, usageReport = true)

@CucumberOptions(

		features = "src/test/resources/features/Smoke/MyPolicy", glue = { "stepDefinitions" }, tags = { 
				"@SetAutoPayNewCreditCard" }, monochrome = true, plugin = {"pretty:target/cucumber-report/Smoke/pretty.txt",
				"html:target/cucumber-report/Smoke",
				"json:target/cucumber-report/Smoke/cucumber.json",
				"ru.yandex.qatools.allure.cucumberjvm.AllureReporter" })

public class RunSmoke_Test1 extends AbstractTestNGCucumberTests {
	
	static Properties properties = Settings.getInstance();
	CukeHooks cukeHooks = new CukeHooks();
@AfterTest
	
	private void test() throws IOException {
	generateCustomReports();
	copyReportsFolder();
		
	

	if(properties.getProperty("MultiScenario").equals("True"))
	{	
		WebDriver driver = DriverManager.getWebDriver();
	driver.quit();
	
	}
	cukeHooks.copyEnvironmentFiles();
	}

	private void generateCustomReports() {

		CucumberResultsOverview overviewReports = new CucumberResultsOverview();
		overviewReports.setOutputDirectory("target");
		overviewReports.setOutputName("cucumber-results");
		overviewReports.setSourceFile("target/cucumber-report/Smoke/cucumber.json");
		try {
//			overviewReports.executeFeaturesOverviewReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		CucumberDetailedResults detailedResults = new CucumberDetailedResults();
        detailedResults.setOutputDirectory("target");
        detailedResults.setOutputName("cucumber-results");
    //    detailedResults
   //                  .setSourceFile("target/cucumber-report/Smoke/cucumber.json");
   //     detailedResults.setScreenShotLocation("./screenshot");
        try {
     //          detailedResults.executeDetailedResultsReport(false, true);
        } catch (Exception e) {

               e.printStackTrace();
        }

	}

	private void copyReportsFolder() {

		String timeStampResultPath = TimeStamp.getInstance();

		File sourceCucumber = new File(Util.getTargetPath());

		File destCucumber = new File(timeStampResultPath);

		try {
			FileUtils.copyDirectory(sourceCucumber, destCucumber);
			try {
				FileUtils.cleanDirectory(sourceCucumber);
			} catch (Exception e) {

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		TimeStamp.reportPathWithTimeStamp = null;

	}




@AfterSuite

public void AllureReport() throws IOException
{
cukeHooks.serveAllureReport();

}
}