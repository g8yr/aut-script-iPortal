package stepDefinitions.galen;

import java.io.IOException;

import cucumber.api.java.en.Then;
import stepDefinitions.common.MasterStepDefs;
import businesscomponents.mypolicygalen.GalenFunctions;
public class GalenDefs extends MasterStepDefs {

	GalenFunctions galenfun = new GalenFunctions();
	
	@Then("^I should validate the look and feel of add vehicle button$")
	public void galenLoginPage() throws IOException {
		galenfun.testdashboardPage();
	}
	
	 
	@Then("^I should be able to validate the UX features for Make Policy Changes button$")
	public void galenMakePolicyChanges() throws IOException {
		galenfun.testMakePolicyChanges();
		galenfun.testdashboardPage();

	}
	
	
}
