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
import services.rest.pojo.AddVehiclePostRequest;
import services.rest.pojo.AddVehiclePostResponse;
import businesscomponents.mypolicy.GeneralFunctions;

import com.Cucumber.supportLibraries.StaticValue;
import com.Cucumber.supportLibraries.custom.ReadCSVFileFunctions;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;




public class AddVehiclePOST extends REST_Client {
	String scheme = null;
	String host = null;
	String resource = null;
	HashMap<String, String> urlParameterMap = null;
	AddVehiclePostRequest addvehiclepostrequest = new AddVehiclePostRequest();
	AddVehiclePostResponse addvehiclepostresponse = new AddVehiclePostResponse();
	ReadCSVFileFunctions readCSVfilefunctions = new ReadCSVFileFunctions();
	GeneralFunctions GenFns = new GeneralFunctions();
	
	private String PolicyNumber = StaticValue.Empty;
	private String VIN = StaticValue.Empty;

	public void invokeWebApi(int year, String ScenarioName) throws ClientProtocolException, IOException {
		setRequestType(HTTP_RequestType.POST);
		setContentType(HTTP_ContentType.JSON);
		constructAndSet_URI_Parameters(ScenarioName);
		constructAndSet_Request_Header();
		constructAndSet_Request_JSON(year,ScenarioName);
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
		resource = "/api/v1/policies/%s/endorsement/vehicles";
		PolicyNumber = StaticValue.Empty;
		VIN = StaticValue.Empty;
		//PolicyNumber = readCSVfilefunctions.SingleVehicleData.get(StaticValue.PolicyNumber);
		PolicyNumber = GenFns.ReadDataFromHashMap(ScenarioName,StaticValue.PolicyNumber);
		//VIN = readCSVfilefunctions.SingleVehicleData.get(StaticValue.VehicleVIN);
		VIN = GenFns.ReadDataFromHashMap(ScenarioName,StaticValue.VehicleVIN);
		resource = String.format(resource,PolicyNumber,VIN);
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

	private void constructAndSet_Request_JSON(int year, String ScenarioName) throws UnsupportedEncodingException {
		if (currReqType.toString().equalsIgnoreCase("POST")) {
			setRequest_Content(construct_Request_JSON(year, ScenarioName));
		}
	}

	private String construct_Request_JSON(int year, String ScenarioName) {

	

        String yearInString = Integer.toString(year);
		String vin = StaticValue.Empty;
		//vin=readCSVfilefunctions.SingleVehicleData.get(StaticValue.VehicleVIN);
		vin=GenFns.ReadDataFromHashMap(ScenarioName,StaticValue.VehicleVIN);
		String purchasedate = StaticValue.Empty;
		//purchasedate=readCSVfilefunctions.SingleVehicleData.get(StaticValue.VehiclePurchaseDate);
		purchasedate=GenFns.ReadDataFromHashMap(ScenarioName,StaticValue.VehiclePurchaseDate);
		
		addvehiclepostrequest.setModelYear(yearInString);
		addvehiclepostrequest.setVin(vin);
		addvehiclepostrequest.setPurchaseDate(purchasedate);
		

		// Code that converts data encapsuled in java classes to string
		ObjectMapper objMapper = new ObjectMapper();
		String strRequest = null;
		try {
			strRequest = objMapper.writeValueAsString(addvehiclepostrequest);
		} catch (Exception e) {
			fail(" <<<< Issue While calling Giving Request data at the time of invoking ADDVehicle POST service >>>> ");
		}

		return strRequest;
	}

	public void getResponse_Class() throws JsonParseException, JsonMappingException, IOException {
		// Code that applies the string to Java Class
		ObjectMapper objMapper = new ObjectMapper();
		this.addvehiclepostresponse = objMapper.readValue(responseAsString, AddVehiclePostResponse.class);
		System.out.println("oid:"+addvehiclepostresponse.getOid());
		System.out.println("oid:"+addvehiclepostresponse);
		if (addvehiclepostresponse.getOid().isEmpty()){
			fail(" <<<<OID is empty while invoking ADDVehicle POST service. This is not as expected >>>> ");
		}		
	}

//	public void validate_ResponseData() {
//		SearchedCustomer firstCustomerInResult = this.customerSearch_ApiResponse.getResponseJson().getSearchedCustomers().get(0);
//		report.updateTestLog("verification step 1", "First Name : " + firstCustomerInResult.getFirstName(), Status.PASS);
//	}
}
