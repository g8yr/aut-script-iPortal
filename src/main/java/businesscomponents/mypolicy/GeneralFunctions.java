package businesscomponents.mypolicy;

import static org.junit.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import stepDefinitions.common.MasterStepDefs;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.StaticValue;
import com.Cucumber.supportLibraries.Util;
import com.Cucumber.supportLibraries.Webaction;
import com.Cucumber.supportLibraries.custom.ReadCSVFileFunctions;

public class GeneralFunctions extends MasterStepDefs {
	WebDriver driver = DriverManager.getWebDriver();
	StaticValue staticvalue = new StaticValue(); 
	Webaction action = new Webaction();
	boolean result;
	ReadCSVFileFunctions readCSVfilefunctions =new ReadCSVFileFunctions();
	
	
	//function to check the UI value with the content(Static Value)
	public void ChkUILabelAndValueIsAsExpected(By Element, String ExpectedValue, String ElementDesc) {
		action.waitForElement(Element,ElementDesc);	
		if (!action.isDisplayed(Element, ElementDesc)) {
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			fail("<<<<  " + ElementDesc
					+ " is not displayed. And this is not as expected >>>>");
		}
		String UIValue = StaticValue.Empty;
		UIValue = action.GetText(Element);
		UIValue = UIValue.trim();
		System.out.println("UIValue:-"+UIValue+"-:");
		System.out.println("ExpectedValue:-"+ExpectedValue+"-:");
		chkTheContentWithOutCaseSensitive(UIValue,ExpectedValue, ElementDesc);
		
	}
	
	//function to check the UI value with the given concat value
	public void ChkUILabelAndValueIsAsExpected(By Element, String ExpectedValue1, String ExpectedValue2, String ExpectedValue3, String ElementDesc) {
		action.waitForElement(Element,ElementDesc);
		if (!action.isDisplayed(Element, ElementDesc)) {
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			fail("<<<<  " + ElementDesc
					+ " is not displayed. And this is not as expected >>>>");
		}
		String UIValue = StaticValue.Empty;
		String ExpectedValue = StaticValue.Empty;
		UIValue = action.GetText(Element);
		UIValue = UIValue.trim();
		ExpectedValue = ExpectedValue1 + ExpectedValue2 + ExpectedValue3;
		System.out.println("UIValue:-"+UIValue+"-:");
		System.out.println("ExpectedValue:-"+ExpectedValue+"-:");
		chkTheContentWithOutCaseSensitive(UIValue,ExpectedValue, ElementDesc);
		
	}
	
		
	
	// Function to e compare the Actual and Expected without case sensitive and fail if it is mismatch
	public void chkTheContentWithOutCaseSensitive(String Actual, String Expected, String ContentName) {
		if (!Actual.equalsIgnoreCase(Expected)) {
			Actual = Actual.trim();
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			fail(" <<<< Content from " + ContentName + " is not as expected :" + Actual + ": >>>>");
		}
	}
	
	// Function to e compare the Actual and Expected and fail if it is mismatch
		public void chkTheContent(String Actual, String Expected, String ContentName) {
			Actual = Actual.trim();
			if (!Actual.equals(Expected)) {
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				fail(" <<<< Content from " + ContentName + " is not as expected :" + Actual + ": >>>>");
			}
//			SoftAssert sa = new SoftAssert();
//			sa.assertEquals(Actual, Expected);
//			sa.assertAll();
		}
	
		// Function to e compare the Actual and Expected and fail if it is mismatch
				public void chkTheContent(String Actual, String Expected1, String Expected2, String ContentName) {
					Actual = Actual.trim();
					if (Actual.equals(Expected1) || Actual.equals(Expected2)) { 
						System.out.println( " <<<< Content from " + ContentName + " is as expected :" + Actual + ": >>>>");
					} else {
						currentScenario.embed(Util.takeScreenshot(driver), "image/png");
						fail(" <<<< Content from " + ContentName + " is not as expected :" + Actual + ": >>>>");
						}
						
					
				}
	
		// Function to build a key to get the value from Hashmap
				public String  ReadDataFromHashMap(String Keyvalue1, String Keyvalue2) {
					System.out.println("Keyvalue1:"+Keyvalue1+":");
					currentTestParameters = DriverManager.getTestParameters();
					Keyvalue1=currentTestParameters.getScenarioName();
					System.out.println("Keyvalue1fromparameters:"+Keyvalue1+":");
					System.out.println("Keyvalue2:"+Keyvalue2+":");
					String key = StaticValue.Empty;
					String CSVData = StaticValue.Empty;
					key = Keyvalue1 + "_" + Keyvalue2;
					System.out.println("key:"+key+":");
					CSVData = readCSVfilefunctions.SingleVehicleData.get(key);
					System.out.println("CSVData:"+CSVData+":");
					return CSVData;
				}
	
}