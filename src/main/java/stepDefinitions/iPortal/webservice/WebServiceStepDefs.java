package stepDefinitions.iPortal.webservice;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.openqa.selenium.WebDriver;

import stepDefinitions.common.MasterStepDefs;
import businesscomponents.iportal.WebserviceComponents;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.IPortalStaticValue;
import com.Cucumber.supportLibraries.Webaction;
import com.Cucumber.supportLibraries.custom.ReadCSVFileFunctions;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
//import com.Cucumber.Transform.ExcelDataToDataTable;

public class WebServiceStepDefs extends MasterStepDefs {

	static Logger log = Logger.getLogger(WebServiceStepDefs.class);
	WebDriver driver = DriverManager.getWebDriver();	
	
	Webaction action = new Webaction();
	ReadCSVFileFunctions readCSVfilefunctions = new ReadCSVFileFunctions();
	WebserviceComponents iPortalwebservicefunctions = new WebserviceComponents();
	String userId = IPortalStaticValue.Empty;
	String password = IPortalStaticValue.Empty;
	public String Iteration;
	
	@Given("^I generate the iPortal Generate OauthToken")
	public void getOauthToken()
	{
		iPortalwebservicefunctions.tokenGeneration();
	}
	
	@Given("^I generate the OauthToken")
	public void getOauthTokens()
	{
		iPortalwebservicefunctions.tokenGeneration();
	}
	
	@Then("^I Validate RetrievePolicyDetails Auto PAS SOAP Service")
	public void retrievePolicyDetailsAutoReponse() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, MalformedURLException, UnsupportedEncodingException, URISyntaxException, JSONException
	{
		iPortalwebservicefunctions.callRetrievePolicyDetailsAutoReponse();
	}
	
	@Then("^I Validate Retrieve Policy Billing Summaries SIS SOAP Service")
	public void retrievePolicyBillingSummariesResponse() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, MalformedURLException, UnsupportedEncodingException, URISyntaxException, JSONException
	{
		iPortalwebservicefunctions.callRetrievePolicyBillingSummaries();
	}
	
	@Then("^I Validate Retrieve Payment Transaction SOAP Service")
	public void  retrievePaymentTransactionResponse() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, MalformedURLException, UnsupportedEncodingException, URISyntaxException, JSONException
	{
		iPortalwebservicefunctions.callRetrievePaymentTransactionV2();
	}
	
	
	@Then("I Validate Customer Search Webservice")
	public void customerSearchWebserviceResponse()
	{
		iPortalwebservicefunctions.retrieveCustomerDetails();
	}
	
	@Then("I Validate Policy Search Webservice")
	public void policySearchWebserviceResponse()
	{
		iPortalwebservicefunctions.retrievePolicyDetails();
	}
	
	@Then("^I Validate Retrieve Policy Summary SOAP Service")
	public void retrievePolicySummaryResponse() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, MalformedURLException, UnsupportedEncodingException, URISyntaxException, JSONException
	{
		iPortalwebservicefunctions.callRetrievePolicySummariesDetails();		
	}
	
	@Then("^I Validate Find Policy SOAP Service")
	public void findPolicyResponse() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, MalformedURLException, UnsupportedEncodingException, URISyntaxException, JSONException
	{
		iPortalwebservicefunctions.verifyFindPolicy();
	}
	
	@Then("^I Validate Retrieve Customer Details SOAP Service")
	public void retrieveCustomerDetailsResponse() throws Exception
	{
		iPortalwebservicefunctions.verifyRetrieveCustomerDetailsV2point2();
	}
	
	@Then("^I Validate Retrieve Bill History SOAP Service")
	public void retrieveBillHistoryResponse() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, MalformedURLException, UnsupportedEncodingException, URISyntaxException, JSONException
	{
		iPortalwebservicefunctions.callRetrieveBillingHistoryReponse();
	}
	@Then("^I Validate Retrieve Policy Transaction Details SOAP Service")
	public void retrievePolicyTransactionResponse() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, URISyntaxException, JSONException, JsonParseException, JsonMappingException, UnsupportedOperationException, IOException
	{
		iPortalwebservicefunctions.callRetrievePolicyTransactionReponse();
	}
	
	
}
