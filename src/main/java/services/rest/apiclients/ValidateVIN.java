package services.rest.apiclients;

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
import services.rest.pojo.validateVIN.validateVINResponse.ValidateVINResponse;
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


public class ValidateVIN extends REST_Client{
	ReadCSVFileFunctions readCSVfilefunctions = new ReadCSVFileFunctions();
	oAuthClientDetails oauthClientDetails = new oAuthClientDetails();
	GeneralFunctions GenFns = new GeneralFunctions();
	GenerateXApplicationContext generateXApplicationContext = new GenerateXApplicationContext();
	String scheme = null;
	String host = null;
	String resource = null;
	HashMap<String, String> urlParameterMap = null;
	private String setPath;
	private String PolicyNumber;
	private String VIN;
	private ValidateVINResponse validateVINresponse;
	public String result;
	public static String vehicleMAKE;
	public static String vehicleMODEL;
	public static int vehicleYEAR;
	private String VINResponse;
	public void invokeWebApi(String ScenarioName) throws ClientProtocolException, IOException, URISyntaxException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		OAuthTokenPOST oAuthTokenPOST = new OAuthTokenPOST();
		setRequestType(HTTP_RequestType.GET);
		setContentType(HTTP_ContentType.JSON);
      	String tokenGenerated = StaticValue.Empty;      	
      	tokenGenerated = oAuthTokenPOST.invokeWebApi();
      	System.out.println("tokenGenerated for ValidateVIN:" + tokenGenerated + ":");
      	
      	setRequest_Authentication("Authorization",tokenGenerated);
      	//setRequest_Authentication("admin","admin");
		constructAndSet_URI_Parameters(ScenarioName);
		constructAndSet_Request_Header();
		constructAndSet_Request_JSON();
		invokeService();
		getResponse_Class();
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
		resource = "/api/v1/policies/%s/vin-info/%s";
		//PolicyNumber = readCSVfilefunctions.SingleVehicleData.get(StaticValue.PolicyNumber);
		//VIN = readCSVfilefunctions.SingleVehicleData.get(StaticValue.VehicleVIN);
		PolicyNumber = GenFns.ReadDataFromHashMap(ScenarioName,StaticValue.PolicyNumber);
		VIN = GenFns.ReadDataFromHashMap(ScenarioName,StaticValue.VehicleVIN);
		resource = String.format(resource,PolicyNumber,VIN);
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
	public String getResponse_Class() throws JsonParseException, JsonMappingException, IOException {
		// Code that applies the string to Java Class
		ObjectMapper objMapper = new ObjectMapper();
		this.validateVINresponse = objMapper.readValue(responseAsString, ValidateVINResponse.class);
		String VInvalidationMessage = validateVINresponse.getValidationMessage();
		if(VInvalidationMessage.isEmpty()) {
			result = StaticValue.Pass;	
			vehicleMAKE = validateVINresponse.getVehicles().get(0).getMake();
			vehicleMODEL = validateVINresponse.getVehicles().get(0).getModelText();
			vehicleYEAR = validateVINresponse.getVehicles().get(0).getYear();
		} else {
			result = StaticValue.Fail;
		}
		
		return result;


	}
		
	

	
	
}
