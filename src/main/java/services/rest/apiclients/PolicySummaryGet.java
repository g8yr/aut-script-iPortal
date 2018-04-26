package services.rest.apiclients;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicHeader;

import services.rest.commons.HTTP_ContentType;
import services.rest.commons.HTTP_RequestType;
import services.rest.commons.REST_Client;
import services.rest.commons.oAuthClientDetails;
import services.rest.pojo.PolicySummaryGetResponse.PolicySummaryGetResponse;
import services.rest.pojo.PolicySummaryGetResponse.ResidentialAddress;
import businesscomponents.mypolicy.GeneralFunctions;

import com.Cucumber.supportLibraries.StaticValue;
import com.Cucumber.supportLibraries.custom.ReadCSVFileFunctions;
//import webservices.RestApiWraper;
/*
import com.cognizant.framework.Status;
import com.cognizant.supportlibraries.ReusableLibrary;
import com.cognizant.supportlibraries.ScriptHelper;
*/
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class PolicySummaryGet extends REST_Client{
	ReadCSVFileFunctions readCSVfilefunctions = new ReadCSVFileFunctions();
	oAuthClientDetails oauthClientDetails = new oAuthClientDetails();
	GeneralFunctions GenFns = new GeneralFunctions();
	GenerateXApplicationContext generateXApplicationContext = new GenerateXApplicationContext();
	String scheme = null;
	String host = null;
	String resource = null;
	HashMap<String, String> urlParameterMap = null;
	private String PolicyNumber;
	private String AddressLine1;
	private PolicySummaryGetResponse policySummaryGetResponse;
	public String invokeWebApi(String ScenarioName) throws ClientProtocolException, IOException, URISyntaxException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		OAuthTokenPOST oAuthTokenPOST = new OAuthTokenPOST();
		setRequestType(HTTP_RequestType.GET);
		setContentType(HTTP_ContentType.JSON);
      	String tokenGenerated = StaticValue.Empty;      	
      	tokenGenerated = oAuthTokenPOST.invokeWebApi();
      	System.out.println("tokenGenerated for PolicySummary Service:" + tokenGenerated + ":");      	
      	setRequest_Authentication("Authorization",tokenGenerated);
		constructAndSet_URI_Parameters(ScenarioName);
		constructAndSet_Request_Header();
		constructAndSet_Request_JSON();
		invokeService();
		AddressLine1 = StaticValue.Empty;
		AddressLine1 = getResponse_Class(PolicyNumber);
		return AddressLine1;
	}

	public void constructAndSet_URI_Parameters(String ScenarioName) {

		// TODO Auto-generated method stub
		construct_URI(ScenarioName);
		construct_Parameters();
		setURI_Path(scheme, host, resource, urlParameterMap);

	}

	public void construct_URI(String ScenarioName) {
		scheme = "https";
		host = "pasqa101.apps.prod.pdc.digital.csaa-insurance.aaa.com";
		resource = "/api/v1/policies/%s";
		PolicyNumber = GenFns.ReadDataFromHashMap(ScenarioName,StaticValue.PolicyNumber);
		resource = String.format(resource,PolicyNumber);
	}

	private void construct_Parameters() {
		// TODO Auto-generated method stub
		urlParameterMap = null;
	}

	private void constructAndSet_Request_Header() {
		// introduce appropriate header - below code is a sample
		BasicHeader[] apiHeader = new BasicHeader[2];
		apiHeader[0] = new BasicHeader("Accept", "application/json");
		//apiHeader[1] = new BasicHeader("X-ApplicationContext", oauthClientDetails.ApplicationContextDetails);
		String CurrentXApplicationContext;
        CurrentXApplicationContext = generateXApplicationContext.getXApplicationContext();
        System.out.println("CurrentXApplicationContext" + CurrentXApplicationContext);	
        apiHeader[1] = new BasicHeader("X-ApplicationContext", CurrentXApplicationContext);
		setRequest_Header(apiHeader);
	}
	
	private void constructAndSet_Request_JSON() throws IOException {
	//	setRequest_Content(construct_Request_JSON());
	}
	
	
	public String getResponse_Class(String PolicyNumber) throws JsonParseException, JsonMappingException, IOException {
		// Code that applies the string to Java Class
		ObjectMapper objMapper = new ObjectMapper();
		this.policySummaryGetResponse = objMapper.readValue(responseAsString, PolicySummaryGetResponse.class);
		if (!policySummaryGetResponse.getPolicyNumber().equals(PolicyNumber)) {
			fail(" <<<< Issue While calling PAS Policy Summary Service. Policy Number mismatch from response '" + policySummaryGetResponse.getPolicyNumber() + "' >>>> ");
		}
		ResidentialAddress residentialAddress = policySummaryGetResponse.getResidentialAddress();	
		if (residentialAddress.getAddressLine1().isEmpty()) {
			fail(" <<<< Issue While calling PAS Policy Summary Service. Address Line 1 is empty for the policy '" + policySummaryGetResponse.getPolicyNumber() + "' >>>> ");
		}
		String addressLine1 = residentialAddress.getAddressLine1();
		return addressLine1;


	}
		
	

	
	
}
