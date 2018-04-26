package webelementrepository.MyPolicy;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import stepDefinitions.common.MasterStepDefs;

public class LoginPage extends MasterStepDefs {
	//public static final By LoginSignInElm = By.xpath("//button[@data-analytics-label='AccountSignIn-SignIn-button']");

	static Logger log = Logger.getLogger(LoginPage.class);

	public static By LoginEmailElm() {
	//	return By.name("username");
		return By.xpath("//input[@name='username']");
	}

	public static By LoginPasswordElm() {
	//	return By.name("password");
		return By.xpath("//input[@name='password']");
	}

	public static By LoginSignInElm() {
	//	return By.xpath("//button[@data-analytics-label='AccountSignIn-SignIn-button']");
		return By.id("AccountSignIn-SignIn-button");
	}
	

}