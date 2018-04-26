package services.rest.apiclients;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import services.rest.pojo.RateEndorsementPostResponse;
import businesscomponents.mypolicy.GeneralFunctions;

import com.Cucumber.supportLibraries.StaticValue;
import com.Cucumber.supportLibraries.custom.ReadCSVFileFunctions;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;




public class ViewRateEnorsementPost extends REST_Client {
	String scheme = null;
	String host = null;
	String resource = null;
	HashMap<String, String> urlParameterMap = null;
	ReadCSVFileFunctions readCSVfilefunctions = new ReadCSVFileFunctions();
	oAuthClientDetails oauthClientDetails = new oAuthClientDetails();
	GenerateXApplicationContext generateXApplicationContext = new GenerateXApplicationContext();
	private String PolicyNumber = StaticValue.Empty;
	GeneralFunctions GenFns = new GeneralFunctions();

	public void invokeWebApi(String ScenarioName)  {
		try {
			OAuthTokenPOST oAuthTokenPOST = new OAuthTokenPOST();
			setRequestType(HTTP_RequestType.POST);
			setContentType(HTTP_ContentType.JSON);
			constructAndSet_URI_Parameters(ScenarioName);
			constructAndSet_Request_Header();
			String tokenGenerated = StaticValue.Empty;
			tokenGenerated = oAuthTokenPOST.invokeWebApi();
			System.out.println("tokenGenerated for ViewAssignmentsGet:" + tokenGenerated + ":");

			setRequest_Authentication("Authorization", tokenGenerated);
			// setRequest_Authentication("admin","admin");
			constructAndSet_Request_JSON();
			invokeService();
			getResponse_Class();
		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(" <<<< Issue while calling ViewRateEndorsement PAS Service for the scenario name '" + ScenarioName + "' . This is not as expected.  >>>>");
		}
	}

	public void constructAndSet_URI_Parameters(String ScenarioName) {
		construct_URI(ScenarioName);
		construct_Parameters();
		setURI_Path(scheme, host, resource, urlParameterMap);
	}

	public void construct_URI(String ScenarioName) {
		scheme = "https";
		host = "pasqa101.apps.prod.pdc.digital.csaa-insurance.aaa.com";
		resource = "/api/v1/policies/%s/endorsement/rate";
		PolicyNumber = StaticValue.Empty;
		PolicyNumber = GenFns.ReadDataFromHashMap(ScenarioName,StaticValue.PolicyNumber);
		resource = String.format(resource,PolicyNumber);
	}

	public void construct_Parameters() {
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

	private void constructAndSet_Request_JSON() throws UnsupportedEncodingException {
//		if (currReqType.toString().equalsIgnoreCase("POST")) {
//			setRequest_Content(construct_Request_JSON());
//		}
	}

//	private void construct_Request_JSON() {
//
//	
//
////		org.apache.http.Header reqHeader = new org.apache.http.Header();
////
//
//
//		
//	}

	public void getResponse_Class() throws JsonParseException, JsonMappingException, IOException {
		// Code that applies the string to Java Class
		ObjectMapper objMapper = new ObjectMapper();
		RateEndorsementPostResponse rateEndorsementPostResponse = objMapper.readValue(responseAsString, RateEndorsementPostResponse.class);
		if (rateEndorsementPostResponse.getActualAmt().isEmpty()){
			fail(" <<<Premium Amount is empty while invoking RateEndorsement POST service. This is not as expected >>>> ");
			
		}	
		
		System.out.println("Premium type:" + rateEndorsementPostResponse.getPremiumType() + ":");
		System.out.println("Premium Amount:" + rateEndorsementPostResponse.getActualAmt() + ":");
	}

//	public void validate_ResponseData() {
//		SearchedCustomer firstCustomerInResult = this.customerSearch_ApiResponse.getResponseJson().getSearchedCustomers().get(0);
//		report.updateTestLog("verification step 1", "First Name : " + firstCustomerInResult.getFirstName(), Status.PASS);
//	}
}
