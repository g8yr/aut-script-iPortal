package stepDefinitions.MyPolicy.webservice;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import webelementrepository.MyPolicy.LoginPage;
import webelementrepository.MyPolicy.MakePolicyChangesPage;
import businesscomponents.mypolicy.AddVehicleFunctions;
import businesscomponents.mypolicy.WebserviceComponents;

import com.Cucumber.supportLibraries.StaticValue;
import com.Cucumber.supportLibraries.Webaction;
import com.Cucumber.supportLibraries.custom.ReadCSVFileFunctions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class WebServiceStepDef {
	
	Webaction action  = new Webaction();
	LoginPage loginpage = new LoginPage();
	WebserviceComponents webserviceComponents = new WebserviceComponents();

	
	
	@When("^I set Request for the Record Wallet Webservice (.*)$")
	public void callRecordWalletWS(String Iteration) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, MalformedURLException, UnsupportedEncodingException, URISyntaxException, JSONException{
		webserviceComponents.setRecordWalletWS(Iteration);	
	}
	
	@Then("^I Call and validate the Record Wallet Webservice response$")
	public void verifyRecordWalletWS() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, MalformedURLException, UnsupportedEncodingException, URISyntaxException, JSONException{
		webserviceComponents.callRecordWalletWS();		
	}
	

	@When("^I set Request for the Retrieve Wallet Details Webservice (.*)$")
	public void callRetrieveWalletWS(String Iteration) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, MalformedURLException, UnsupportedEncodingException, URISyntaxException, JSONException{
		webserviceComponents.setRetrieveWalletWS(Iteration);
	}
	
	@Then("^I Call and validate the Retrieve Wallet Details Webservice response$")
	public void verifyRetrieveWalletDetailsWS() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, MalformedURLException, UnsupportedEncodingException, URISyntaxException, JSONException{
		webserviceComponents.callRetrieveWalletWS();	
	}
	
	@When("^I set Request for the Update Preference Webservice (.*)$")
	public void callUpdatePreferenceWS(String Iteration) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, URISyntaxException, JSONException, IOException{
		webserviceComponents.setUpdatePreferenceWS(Iteration);
	}
	
	@Then("^I Call and validate the Update Preference Webservice Response$")
	public void verifyUpdatePreferenceWS() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, URISyntaxException, JSONException, ClientProtocolException, IOException{
		webserviceComponents.callUpdatePreferenceWS();
	}
	
	@When("^I set Request for the Get Policy Preference Webservice (.*)$")
	public void callGetPreferenceWS(String Iteration) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, URISyntaxException, JSONException, IOException{
		webserviceComponents.setGetPreferenceWS(Iteration);
	}
	
	@Then("^I Call and validate the Get Policy Preference Webservice Response$")
	public void verifyGetPreferenceWS() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, URISyntaxException, JSONException, ClientProtocolException, IOException{
		webserviceComponents.callGetPreferenceWS();
	}
	
	
	}

