package services.rest.apiclients;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicHeader;

import services.rest.commons.HTTP_ContentType;
import services.rest.commons.HTTP_RequestType;
import services.rest.commons.REST_Client;
import services.rest.pojo.StartEndorsementRequest;
import services.rest.pojo.StartEndorsementResponse;
import businesscomponents.mypolicy.GeneralFunctions;

import com.Cucumber.supportLibraries.StaticValue;
import com.Cucumber.supportLibraries.custom.ReadCSVFileFunctions;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;




public class StartEndorsementPOST extends REST_Client {
	String scheme = null;
	String host = null;
	String resource = null;
	HashMap<String, String> urlParameterMap = null;
	StartEndorsementRequest startEndorsementRequest = new StartEndorsementRequest();
	StartEndorsementResponse startEndorsementResponse = new StartEndorsementResponse();
	ReadCSVFileFunctions readCSVfilefunctions = new ReadCSVFileFunctions();
	private String PolicyNumber = StaticValue.Empty;
	GeneralFunctions GenFns = new GeneralFunctions();
	//private String VIN = StaticValue.Empty;

	public void invokeWebApi(String ScenarioName) throws ClientProtocolException, IOException {
		setRequestType(HTTP_RequestType.POST);
		setContentType(HTTP_ContentType.JSON);
		constructAndSet_URI_Parameters(ScenarioName);
		constructAndSet_Request_Header();
		constructAndSet_Request_JSON();
		invokeService();
		getResponse_Class();
	}

	public void constructAndSet_URI_Parameters(String ScenarioName) {
		construct_URI(ScenarioName);
		construct_Parameters();
		setURI_Path(scheme, host, resource, urlParameterMap);
	}

	public void construct_URI(String ScenarioName) {
		scheme = "https";
		host = "pasqa101.apps.prod.pdc.digital.csaa-insurance.aaa.com";
		resource = "/api/v1/policies/%s/endorsement";
		PolicyNumber = StaticValue.Empty;
		//PolicyNumber = readCSVfilefunctions.SingleVehicleData.get(StaticValue.PolicyNumber);
		PolicyNumber = GenFns.ReadDataFromHashMap(ScenarioName,StaticValue.PolicyNumber);
		resource = String.format(resource,PolicyNumber);
	}

	public void construct_Parameters() {
		urlParameterMap = null;
	}

	private void constructAndSet_Request_Header() {
		// introduce appropriate header - below code is a sample
		BasicHeader[] apiHeader = new BasicHeader[2];
		apiHeader[0] = new BasicHeader("Content-Type", "application/json");

		setRequest_Header(apiHeader);
	}

	private void constructAndSet_Request_JSON() throws UnsupportedEncodingException {
		if (currReqType.toString().equalsIgnoreCase("POST")) {
			setRequest_Content(construct_Request_JSON());
		}
	}

	private String construct_Request_JSON() {

	

//		org.apache.http.Header reqHeader = new org.apache.http.Header();
//
//		reqHeader.setChannelType("DSU");
//		reqHeader.setAgencyCode("null");
//		reqHeader.setRequestType("null");
//		reqHeader.setAgentid("null");
		//String vin = StaticValue.Empty;
		//vin=readCSVfilefunctions.SingleVehicleData.get(StaticValue.VehicleVIN);
		String puchadedate = StaticValue.Empty;
		puchadedate=readCSVfilefunctions.SingleVehicleData.get(StaticValue.VehiclePurchaseDate);
		
		startEndorsementRequest.setEndorsementDate("2018-01-30");
		startEndorsementRequest.setEndorsementReason("OTHPB");
		startEndorsementRequest.setEndorsementReasonOther("Executing from Automation Script");
		

		// Code that converts data encapsuled in java classes to string
		ObjectMapper objMapper = new ObjectMapper();
		String strRequest = null;
		try {
			strRequest = objMapper.writeValueAsString(startEndorsementRequest);
		} catch (Exception e) {
			fail(" <<<< Issue While Giving Request data at the time of invoking StartEndorsement POST service >>>> ");
		}

		return strRequest;
	}

	public void getResponse_Class() throws JsonParseException, JsonMappingException, IOException {
		// Code that applies the string to Java Class
		ObjectMapper objMapper = new ObjectMapper();
		this.startEndorsementResponse = objMapper.readValue(responseAsString, StartEndorsementResponse.class);
		if (!startEndorsementResponse.getPolicyNumber().equals(PolicyNumber)){
			fail(" <<<Policy number mismatch in response while invoking StartEndorsement POST service. This is not as expected >>>> ");
			
		}		
	}

//	public void validate_ResponseData() {
//		SearchedCustomer firstCustomerInResult = this.customerSearch_ApiResponse.getResponseJson().getSearchedCustomers().get(0);
//		report.updateTestLog("verification step 1", "First Name : " + firstCustomerInResult.getFirstName(), Status.PASS);
//	}
}
