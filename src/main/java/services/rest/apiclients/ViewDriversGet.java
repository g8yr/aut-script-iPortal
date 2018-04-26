package services.rest.apiclients;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicHeader;

import services.rest.commons.HTTP_ContentType;
import services.rest.commons.HTTP_RequestType;
import services.rest.commons.REST_Client;
import services.rest.commons.oAuthClientDetails;
import services.rest.pojo.ViewDriversGetResponse.ViewDriversGetResponse;
import services.rest.pojo.ViewDriversGetResponse.Driver;
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

public class ViewDriversGet extends REST_Client {
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
//	private String VIN;
	public String result = StaticValue.Empty;
//	public static String vehicleMAKE;
//	public static String vehicleMODEL;
//	public static int vehicleYEAR;
	private String driverOid = StaticValue.Empty;
	List<Driver> driversDetails;

	public String invokeWebApi(String ScenarioName, String DriverOID) {
		try {
			OAuthTokenPOST oAuthTokenPOST = new OAuthTokenPOST();
			setRequestType(HTTP_RequestType.GET);
			setContentType(HTTP_ContentType.JSON);
			String tokenGenerated = StaticValue.Empty;
			tokenGenerated = oAuthTokenPOST.invokeWebApi();
			System.out.println("tokenGenerated for ViewAssignmentsGet:" + tokenGenerated + ":");

			setRequest_Authentication("Authorization", tokenGenerated);
			// setRequest_Authentication("admin","admin");
			constructAndSet_URI_Parameters(ScenarioName);
			constructAndSet_Request_Header();
			constructAndSet_Request_JSON();
			invokeService();
			driverOid = getResponse_Class(DriverOID);
		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driverOid;

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
		resource = "/api/v1/policies/%s/endorsement/drivers";
		PolicyNumber = StaticValue.Empty;
		PolicyNumber = GenFns.ReadDataFromHashMap(ScenarioName, StaticValue.PolicyNumber);
		resource = String.format(resource, PolicyNumber);
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
		// setRequest_Content(construct_Request_JSON());
	}

	public String getResponse_Class(String driverOID) throws JsonParseException, JsonMappingException, IOException {
		// Code that applies the string to Java Class
		result = StaticValue.Empty;
		ObjectMapper objMapper = new ObjectMapper();
		responseAsString = "{\"Drivers\":" + responseAsString + "}";
		ViewDriversGetResponse driversResponse = objMapper.readValue(responseAsString, ViewDriversGetResponse.class);
		System.out.println("res:" + driversResponse);
		driversDetails = driversResponse.getDrivers();
		System.out.println("driversDetails:" + driversDetails);
		for (int i = 0; i < driversDetails.size(); i++) {
			if (driverOID.equals(driversDetails.get(i).getOid())) {
				result = driversDetails.get(i).getFirstName();
				System.out.println("matched first name " + result);
			} else {
				if (i == driversDetails.size() && result.isEmpty()) {
					fail(" <<<< driver oid '" + driverOID
							+ "' is not found in the ViewDrivers PAS Service response . This is not as expected.  >>>>");
				}

			}
		}

		return result;

	}

}
