package businesscomponents.iportal;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.json.JSONException;
import org.openqa.selenium.WebDriver;


import stepDefinitions.common.MasterStepDefs;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.IPortalStaticValue;
import com.Cucumber.supportLibraries.Util;
import com.Cucumber.supportLibraries.Webaction;
import com.Cucumber.supportLibraries.custom.ReadCSVFileFunctions;






import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;



import services.rest.apiclients.CustomerSearch;
//import services.rest.apiclients.CustomerSearch;
import services.rest.apiclients.oauthTokenGeneration;
import services.soap.FindPolicyServiceV21;
import services.soap.RetrieveBillHistory;
import services.soap.RetrieveCustomerDetailsV2point2;
import services.soap.RetrievePaymentTransactionV2;
import services.soap.RetrievePolicyBillingSummaries;
import services.soap.RetrievePolicyDetailsAuto;
import services.soap.RetrievePolicySummaries;
import services.soap.RetrievePolicyTransactionDetail;

public class WebserviceComponents extends MasterStepDefs {
	WebDriver driver = DriverManager.getWebDriver();
	IPortalStaticValue iportalstaticvalue = new IPortalStaticValue(); 
	boolean result;
	ReadCSVFileFunctions readcsvfilefunctions = new ReadCSVFileFunctions();
	Webaction action = new Webaction();
	int iterator1 = 0;
	int iterator2 = 6;
	public static String token;

	public void tokenGeneration()
	{
		oauthTokenGeneration oauthtokengeneration = new oauthTokenGeneration();
		oauthtokengeneration.tokenGeneration();
		token = oauthtokengeneration.getToken();
	}

	public void callRetrievePolicyDetailsAutoReponse() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, MalformedURLException, UnsupportedEncodingException, URISyntaxException, JSONException 
	{
		RetrievePolicyDetailsAuto retrievepolicydetailsauto = new RetrievePolicyDetailsAuto();
		retrievepolicydetailsauto.compareAutoPolicyDetailsResponse();		
	}

	public void callRetrievePolicyBillingSummaries() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, MalformedURLException, UnsupportedEncodingException, URISyntaxException, JSONException 
	{
		RetrievePolicyBillingSummaries retrievepolicybillingsummaries = new RetrievePolicyBillingSummaries();
		retrievepolicybillingsummaries.compareBillingSummarryResponse();		
	}

	public void callRetrievePaymentTransactionV2() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, MalformedURLException, UnsupportedEncodingException, URISyntaxException, JSONException 
	{
		RetrievePaymentTransactionV2 retrievepaymenttransactionV2 = new RetrievePaymentTransactionV2();
		retrievepaymenttransactionV2.compareRetrievePaymentTransactionV2();		
	}
 
	public void retrieveCustomerDetails() 
	{
		CustomerSearch customersearch = new CustomerSearch();
		customersearch.getCustomerDetails();		
	}

	public void callRetrievePolicySummariesDetails() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, MalformedURLException, UnsupportedEncodingException, URISyntaxException, JSONException 
	{
		RetrievePolicySummaries retrievepolicysummaries = new RetrievePolicySummaries();
		retrievepolicysummaries.compareRetrievePolicySummariesV2();		
	}

	public void verifyFindPolicy() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, MalformedURLException, UnsupportedEncodingException, URISyntaxException, JSONException 
	{
		FindPolicyServiceV21 findpolicyservice = new FindPolicyServiceV21();
		findpolicyservice.verifyFindPolicy();		
	}

	public void verifyRetrieveCustomerDetailsV2point2() throws Exception 
	{
		RetrieveCustomerDetailsV2point2 retrievecustomerdetails = new RetrieveCustomerDetailsV2point2();
		retrievecustomerdetails.verifyRetrieveCustomerDetailsV2point2();
		
		
	}

	public void callRetrieveBillingHistoryReponse() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, MalformedURLException, UnsupportedEncodingException, URISyntaxException, JSONException 
	{
		RetrieveBillHistory retriveBillHistory= new RetrieveBillHistory();
		retriveBillHistory.compareBillingHistoryResponse();
	}
	
	public void callRetrievePolicyTransactionReponse() throws JsonParseException, JsonMappingException, KeyManagementException, UnsupportedOperationException,
	NoSuchAlgorithmException, KeyStoreException, IOException, URISyntaxException, JSONException {
		RetrievePolicyTransactionDetail policyTransactionDetail = new RetrievePolicyTransactionDetail();
		policyTransactionDetail.comparePolicyTransactionResponse();
}
	public void retrievePolicyDetails() {
		services.rest.apiclients.PolicySearch cs = new services.rest.apiclients.PolicySearch();
		cs.getPolicyDetails();
	}

	
}