package com.Cucumber.supportLibraries;

import static org.junit.Assert.fail;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
//import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;



import stepDefinitions.common.MasterStepDefs;

public class Webaction extends MasterStepDefs {
	WebDriver driver = DriverManager.getWebDriver();
	public static HashMap<String, Integer> browsertimeout = new HashMap<String, Integer>();
	public static Logger logger = LogManager.getLogger();
	static Properties properties = Settings.getInstance();

	public void ClickElement(By strobj) {
		try {
			System.out.println("Webaction.ClickElement():" + strobj);
			waitForElement(strobj);
			focusonElement(strobj);
			WebElement element = driver.findElement(strobj);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style','border: 2px solid red;');", element);
			HardDelay(1000);
			js.executeScript("arguments[0].removeAttribute('style','border: 2px solid red;');", element);
			element.click();
			HardDelay(2000);
			//currentScenario.embed(Util.takeScreenshot(driver), "image/png");

		} catch (Exception e) {
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			e.printStackTrace();
			fail ("<<<< '" + strobj + "' Element not found. And this is not as expected" );

		}
	}
	public void ClickElement(By strobj, String strButtonName) {
	//	String browser = testParameter.getBrowser().toString();
	//	if (!(browser.equalsIgnoreCase("InternetExplorer"))) {
			try {
				WaitForElement_V0(strobj);
				WebElement element = driver.findElement(strobj);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].setAttribute('style','border: 2px solid red;');", element);
				HardDelay(1000);
				js.executeScript("arguments[0].removeAttribute('style','border: 2px solid red;');", element);
				element.click();
				HardDelay(2000);
				
			//	currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				System.out.println(strButtonName + "clicked");

			} catch (StaleElementReferenceException s) {
			} catch (Exception e) {
				System.out.println(strButtonName + "not clicked");
				e.printStackTrace();
				fail(strButtonName + "not clicked");
				}
		
	}
	public void waitForElement(By by) {
		try {
			new WebDriverWait(driver, 30).until(new ExpectedCondition<Boolean>() {

				@Override
				public Boolean apply(WebDriver driver) {
					return driver.findElement(by).isDisplayed();
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			fail ("<<<< '" + by + "' Element not found. And this is not as expected" );
		}
	}
	/********************************************************************************************************************
	 * Function Name: InitializeBrowserTimeout Description: To store the waiting
	 ********************************************************************************************************************/

	public void InitializeBrowserTimeout() {
		try {
			this.browsertimeout.put("internet explorer", Integer.parseInt(properties.get("IE_Timeout").toString()));
			this.browsertimeout.put("safari", (Integer) Integer.parseInt(properties.get("Safari_Timeout").toString()));
			this.browsertimeout.put("chrome", (Integer) Integer.parseInt(properties.get("Chrome_Timeout").toString()));
			this.browsertimeout.put("firefox", (Integer) Integer.parseInt(properties.get("FF_Timeout").toString()));
		} catch (ClassCastException e) {
			logger.error(e);
		}
	}
	public boolean WaitForElement_V0(final By ObjId) {

		int timeout = browsertimeout.get(this.currentTestParameters.getBrowser().getValue());
		//int timeout = 10;
		ImplicitWaitSwitchOFF();
		long startTime = 0;
		long endTime = 0;
		long elapsedTime = 0;
		boolean flag = false;
		startTime = System.currentTimeMillis();
		Exception eMain = null;
		do {
			try {
				driver.findElement(ObjId).isDisplayed();
				flag = true;
				break;
			}  catch (Exception e) {
			//	report.updateTestLog("WaitForElement", "General Exception : " + StyledError(ObjId.toString()), Status.FAIL);
			//	if (properties.getProperty("stopOnFail").equalsIgnoreCase("Yes")) {
			//		throw new FrameworkException("Terminating test execution on first encounter of failure");
			//	}
				break;
			}
		} while (elapsedTime < timeout);
		if (flag) {
			System.out.println("wait for Element" +ObjId.toString());
	
		} else {
			fail("wait for Element - Timed out: " + elapsedTime + "s. " + (ObjId.toString()));
		//	if (properties.getProperty("stopOnFail").equalsIgnoreCase("Yes")) {
		//		throw new FrameworkException("Terminating test execution on first encounter of failure");
			}
		
		return flag;
	}
	public void waitForElement(By by, String ElmDesc) {
		try {
			int timeout = browsertimeout.get(this.currentTestParameters.getBrowser().getValue());
			new WebDriverWait(driver, timeout).until(new ExpectedCondition<Boolean>() {

				@Override
				public Boolean apply(WebDriver driver) {
					return driver.findElement(by).isDisplayed();
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			fail ("<<<< '" + ElmDesc + "' Element not found. And this is not as expected" );
		}
	}
	
	public void pressTabkey() {
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		HardDelay(1000);

		
	}

	public static void PressEnter() {
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		;
	}

	public void pressBackSpace(int count) {
		Robot robot = null;
		int intI = 0;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		robot.keyPress(KeyEvent.VK_BACK_SPACE);
		robot.keyRelease(KeyEvent.VK_BACK_SPACE);	
		
	}				
	
	
	public <V> void waitToGetElementFluent(By by, String ElementDesc) {
		try {
			
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
			wait.pollingEvery(250,  TimeUnit.MILLISECONDS);
			wait.withTimeout(1, TimeUnit.MINUTES);
			wait.ignoring(NoSuchElementException.class);
			System.out.println("Webaction.waitToGetElementFluent()1");

			Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>()
					{

						public Boolean apply(WebDriver arg0) {							
							WebElement Element = driver.findElement(by);
							if (Element.isDisplayed()) {
								return true;								
							} else {
								return false;
							}
						}
					};
					System.out.println("Webaction.waitToGetElementFluent()2");
					wait.until((Function<? super WebDriver, V>) function);

		} catch (Exception e) {
			fail("<<<<  '" + ElementDesc + "' is not Available >>>>");
		}
	}
	

	public void focusonElement(By element) {
		WaitForElement_v0(element);
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(element));
			jse.executeScript("window.scrollBy(0,-1200);");
	//		currentScenario.embed(Util.takeScreenshot(driver), "image/png");

		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		} catch (Exception e) {
			e.printStackTrace();
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		}
	}

	public void focusonElementTakeScreenshot(By element) {
		WaitForElement_v0(element);
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(element));
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");

		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		} catch (Exception e) {
			e.printStackTrace();
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		}
	}

	public void focusonElementinMiddleTakeScreenshot(By element) {
		WaitForElement_v0(element);
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(element));
			jse.executeScript("window.scrollBy(0,-500);");
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");

		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		} catch (Exception e) {
			e.printStackTrace();
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		}
	}
	/**
	 * Constructor to Verify the Web Element of the Object Availability
	 * 
	 * @param report
	 *            The {@link Report} object
	 * @param strdesc
	 *            The {@link String} object
	 * @param strobj
	 *            The {@link By} object
	 */
	public boolean IsObjectPresent(By strobj, String objdesc) {
		try {
			ImplicitWaitSwitchOFF();
			List<WebElement> Element = driver.findElements(strobj);
			if (Element.size() > 0) {
				// report.updateTestLog("Element Verification", objdesc+" Presence",
				// Status.PASS);
				ImplicitWaitSwitchON();
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				return true;
			} else {
				// report.updateTestLog("Element Verification", objdesc+" not Presence",
				// Status.DONE);
				ImplicitWaitSwitchON();
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				return false;
			}
		} catch (Exception e) {
			// report.updateTestLog("Element Verification", objdesc+" Presence",
			// Status.DONE);
			ImplicitWaitSwitchON();
			e.printStackTrace();
			return false;
		}
	}

	public void ImplicitWaitSwitchON() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}

	public void ImplicitWaitSwitchOFF() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
	}

	public void WaitForElement_v0(final By ObjId) {
		int timeout = browsertimeout.get(this.currentTestParameters.getBrowser().getValue());
		//int timeout = 40;
		ImplicitWaitSwitchOFF();
		long startTime = 0;
		long endTime = 0;
		long elapsedTime = 0;
		boolean flag = false;
		startTime = System.currentTimeMillis();
		do {
			try {
				driver.findElement(ObjId).isDisplayed();
				flag = true;
				break;
			} catch (Exception e) {
				endTime = System.currentTimeMillis();
				elapsedTime = (endTime - startTime) / 1000;
				// logger.info("Waiting For Element " + ObjId + ", polling after ");
				HardDelay(100);
			}
		} while (elapsedTime < timeout);
		if (flag) {
			// report.updateTestLog("wait for Element", ObjId+" Element Present",
			// Status.DONE);
		} else {
		//	fail (ObjId+" Element not Present after elapsed time");
			// + elapsedTime + "Seconds", Status.DONE);
		}
	}

	/**
	 * Name : HardDelay Author: Uvaraj Date: 9/4/2014 Description: a very crude form
	 * of delay. used it if there is no other choice
	 * 
	 * @throws InterruptedException
	 */
	public static void HardDelay(long delayTime) {
		try {
			Thread.sleep(delayTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Function to Click the WebElement with implicit wait time
	public void ClickElement(By by, int explicitWaitTime) {
		waitForElement(by);
		focusonElement(by);
		driver.findElement(by).click();
		HardDelay(2);
	}

	// Function to Click the WebElement with implicit wait time
	public void EnterText(By by, String InputText) {
		/*
		waitForElement(by);
		focusonElement(by);
		WebElement element = driver.findElement(by);
		element.sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END,Keys.DELETE));
		element.clear();
		element.sendKeys(InputText);
		*/
		
		if (!InputText.isEmpty()) {
		//	String browser = testParameter.getBrowser().toString();
			// if (!browser.equalsIgnoreCase("Chrome") &&
			// !browser.equalsIgnoreCase("InternetExplorer") &&
			// !browser.equalsIgnoreCase("Safari")) {
			try {
				waitForElement(by);
				// highlightElement(elementQuery);
				WebElement element = driver.findElement(by);
				if (!InputText.isEmpty()) {
					InputText = InputText.trim();
					element.clear();
					HardDelay(500);
					element.sendKeys(InputText);
					// ajaxFluentPredicateWait();
				}
				// unhighlightElement(elementQuery);
			System.out.println( InputText + " is entered in the field ");

			} catch (StaleElementReferenceException a) {
			//	StaleEnterTextFunction(elementQuery, strValue, strdesc);
			} catch (Exception e) {
			//	report.updateTestLog("EnterText", strValue + " is not entered in " + strdesc, Status.FAIL);
		//	//	report.updateTestLog("EnterText", "Exception Message : " + e.getLocalizedMessage(), Status.FAIL);
			//	if (properties.getProperty("stopOnFail").equalsIgnoreCase("Yes")) {
			//		throw new FrameworkException("Terminating test execution on first encounter of failure");
				}
			}
			/*
			 * } else { EnterTextWithJS(elementQuery, strValue, strdesc); }
			 */
	//	}

	}
	

	// Function to get the text value from WebElement
	public String GetText(By by) {
		String ReturnText = StaticValue.Null;
		waitForElement(by);
		focusonElement(by);
		ReturnText = driver.findElement(by).getText();
		System.out.println("Webaction.GetText():" + ReturnText + ":");
		return ReturnText;

	}

	// Function to get the text value from WebElement
	public String GetAttribute(By by, String AttributeName) {
		String ReturnText = StaticValue.Null;
		waitForElement(by);
		focusonElement(by);
		ReturnText = driver.findElement(by).getAttribute(AttributeName);
		System.out.println("Webaction.GetAttribute():" + ReturnText + ":");
		return ReturnText;

	}

	// Function to wait the webdriver
	public void wait(int intValue) {
		int intI;

		for (intI = 1; intI <= intValue; intI++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	// Function to find the Existence of WebElement
	public int fnExistOfElm(By by, int intSec) {
		int Count = 0, intI;

		for (intI = 1; intI <= intSec; intI++) {

			Count = driver.findElements(by).size();

			if (Count > 0) {
				break;
			} else {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return Count;

	}

	public boolean IsObjectPresentWithoutWait(By strobj, String objdesc) {
		try {
			List<WebElement> Element = driver.findElements(strobj);
			if (Element.size() > 0) {
				System.out.println("Element Verification: " + objdesc + " Presence");
				ImplicitWaitSwitchON();
				return true;
			} else {
				System.out.println("Element Verification: " + objdesc + " Not Presence");
				ImplicitWaitSwitchON();
				return false;
			}
		} catch (Exception e) {
			System.out.println("Element Verification: " + objdesc + ": Not Presence");
			ImplicitWaitSwitchON();
			return false;

		}
	}

	public boolean isEnabled(By ObjId, String objdesc) {
		Boolean enabled = false;
		try {
			ImplicitWaitSwitchOFF();
			enabled = driver.findElement(ObjId).isEnabled();
			if (enabled) {
				System.out.println("Element Verification: " + objdesc + " is enabled");
				ImplicitWaitSwitchON();
			} else {
				System.out.println("Element Verification: " + objdesc + " is not enabled");
				ImplicitWaitSwitchON();
			}
			return enabled;
		} catch (Exception e) {
			System.out.println("Element Verification: " + objdesc + " is not enabled" + e);
			ImplicitWaitSwitchON();
			return enabled;
		}
	}

	public boolean isDisplayed(By ObjId, String objdesc) {
		Boolean displayed = false;
		try {
			// getPageSourceToHTML();
			//WaitForElement_v0(ObjId);
			// highlightElement(ObjId);// demo usage
			ImplicitWaitSwitchOFF();
			displayed = driver.findElement(ObjId).isDisplayed();
			// unhighlightElement(ObjId); // demo usage
			if (displayed) {
				System.out.println("Element Verification: " + objdesc + " is displayed");
				ImplicitWaitSwitchON();
			} else {
				System.out.println("Element Verification: " + objdesc + " is not displayed");
				ImplicitWaitSwitchON();
			}
//			currentScenario.embed(Util.takeScreenshot(driver),
//					"image/png");
			return displayed;
		} catch (Exception e) {
			System.out.println("Element Verification: " + objdesc + " is not displayed" + e);
			ImplicitWaitSwitchON();
			return displayed;
		}
	}

	public void ClickJSElement(By strobj, String strButtonName) {

		try {
			waitForElement(strobj);
			focusonElement(strobj);
			WebElement element = driver.findElement(strobj);
			JavascriptExecutor executor = (JavascriptExecutor) driver;	
			executor.executeScript("arguments[0].setAttribute('style','border: 2px solid red;');", element);
			HardDelay(1000);
			executor.executeScript("arguments[0].removeAttribute('style','border: 2px solid red;');", element);
			executor.executeScript("arguments[0].click();", element);
			System.out.println("Click Button -" + strButtonName + " clicked");
			HardDelay(2000);
		} catch (StaleElementReferenceException s) {
			System.out.println("Button - '" + strButtonName + "' not clicked. </ br> " + s);
			currentScenario.embed(Util.takeScreenshot(driver),
					"image/png");
			fail(" <<<< Issue while Clicking the element  '" + strButtonName
					+ "' Element not found. This is not as Expected >>>>");
		} catch (Exception e) {
			System.out.println("Button - '" + strButtonName + "' not clicked. </ br> " + e);
			currentScenario.embed(Util.takeScreenshot(driver),
					"image/png");
			fail(" <<<< Issue while Clicking the element  '" + strButtonName
					+ "' Element not found. This is not as Expected >>>>");

		}

	}

	public int NumberOfRows_XPATH(By xpath) {
		// WaitForElement_v0(xpath);
		int size = 0;
		try {
			size = driver.findElements(xpath).size();
			// report.updateTestLog("Number of Rows for the xpath", size + " : rows found
			// for the xpath :" + xpath, Status.DONE);
		} catch (Exception e) {
			// report.updateTestLog("Number of Rows for the xpath", "Exception : " +
			// e.getLocalizedMessage(), Status.FAIL);
			e.printStackTrace();
		}
		return size;
	}

	public void movetoElement(By obj, String desc) {

		WaitForElement_v0(obj);
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(obj));
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			// report.updateTestLog("Move To", "Focused move to " + desc, Status.DONE);
		} catch (StaleElementReferenceException e) {
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			e.printStackTrace();
		} catch (Exception e) {
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			e.printStackTrace();
		}

	}
		public boolean isTextPresent(String textToVerify) {
		Boolean textAvail = driver.getPageSource().contains(textToVerify);
		return textAvail;
	}				
		public void EnterTextWithJS(By strobj, String text) {
			try {
				WebElement element = driver.findElement(strobj);
				element.clear();
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].value='" + text + "'", element);
		System.out.println(text + " is entered " );

			} catch (Exception e) {
				e.printStackTrace();
				fail(text + " is not entered" + " in the element " + strobj );
			}
		}
		
		public String GetTextValue(By objId, String strdesc) {
			String selectedvalue = "";
			int count = 0;
			while (count < 200) {
				try {
					ImplicitWaitSwitchOFF();

					/*
					 * while(!driver.findElement(objId).isDisplayed()){
					 * WaitForElement(objId); count++; if (count==5){break;} }
					 */
					waitForElement(objId);
					selectedvalue = driver.findElement(objId).getText();
					if (selectedvalue != null) {
						if (selectedvalue.isEmpty()) {
							selectedvalue = driver.findElement(objId).getAttribute("textContent");
							if (selectedvalue.isEmpty()) {
								selectedvalue = driver.findElement(objId).getAttribute("value");
								if (selectedvalue.isEmpty()) {
									selectedvalue = "";
								}
							}
						}
					}
					System.out.println("Text Value -"+ selectedvalue + " is populated in " + strdesc);
					ImplicitWaitSwitchON();
					count = count + 201;
					// return selectedvalue;
				} catch (NoSuchElementException e) {
					fail("No value is populated in " + strdesc);
					//if (properties.getProperty("stopOnFail").equalsIgnoreCase("Yes")) {
					//	throw new FrameworkException("Terminating test execution on first encounter of failure");
					//}
					ImplicitWaitSwitchON();// To avoid Null
					if (selectedvalue != null) {
						if (selectedvalue.isEmpty()) {
							selectedvalue = "";
						}
					}
					count = count + 201;
					// return selectedvalue;
				} catch (NullPointerException n) {
					fail("No value is populated in " + strdesc);
					//if (properties.getProperty("stopOnFail").equalsIgnoreCase("Yes")) {
					//	throw new FrameworkException("Terminating test execution on first encounter of failure");
					//}
					selectedvalue = "";
					count = count + 201;
					// return selectedvalue;
				} catch (StaleElementReferenceException e) {
					count = count + 1;
				} catch (Exception e) {
					fail( "No value is populated in " + strdesc);
					//if (properties.getProperty("stopOnFail").equalsIgnoreCase("Yes")) {
					//	throw new FrameworkException("Terminating test execution on first encounter of failure");
					//}
					count = count + 201;
				}
			}
			return selectedvalue;
		}

}
