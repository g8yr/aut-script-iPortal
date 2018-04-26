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
import services.rest.pojo.ViewCoveragesGetResponse.ViewCoveragesGetResponse;
import services.rest.pojo.ViewCoveragesGetResponse.Coverage;
import services.rest.pojo.ViewCoveragesGetResponse.PolicyCoverage;
import services.rest.pojo.ViewCoveragesGetResponse.VehicleLevelCoverage;

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

public class ViewCoveragesGet extends REST_Client {
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
	VehicleLevelCoverage VehicleCoverage = null;
	Coverage result = null;
	List<Coverage> coverageDetails;
	List<Coverage> coverageDetailsReturn;
	List<VehicleLevelCoverage> vehicleLevelCoverageDetails;
	ViewCoveragesGetResponse vehicleCoverageResp = null;
	String vehoid = StaticValue.Empty;
	

	public List<Coverage> invokeWebApi(String ScenarioName, String VehOID) {
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
			coverageDetailsReturn = getResponse_Class(VehOID);
		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException | IOException e) {
			e.printStackTrace();
			fail(" <<<< Issue while calling ViewCoverage PAS Service for the scenario name '" + ScenarioName + "' . This is not as expected.  >>>>");
		}
		return coverageDetailsReturn;

	}

	public void constructAndSet_URI_Parameters(String ScenarioName) {
		construct_URI(ScenarioName);
		construct_Parameters();
		setURI_Path(scheme, host, resource, urlParameterMap);

	}

	public void construct_URI(String ScenarioName) {
		scheme = "https";
		host = "pasqa101.apps.prod.pdc.digital.csaa-insurance.aaa.com";
		resource = "/api/v1/policies/%s/endorsement/coverages";
		PolicyNumber = StaticValue.Empty;
		PolicyNumber = GenFns.ReadDataFromHashMap(ScenarioName, StaticValue.PolicyNumber);
		resource = String.format(resource, PolicyNumber);
	}

	private void construct_Parameters() {
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

	public List<Coverage> getResponse_Class(String VehOID) throws JsonParseException, JsonMappingException, IOException {
		// Code that applies the string to Java Class
		ObjectMapper objMapper = new ObjectMapper();
		ViewCoveragesGetResponse coverageResponse = objMapper.readValue(responseAsString,
				ViewCoveragesGetResponse.class);
		vehicleLevelCoverageDetails = coverageResponse.getVehicleLevelCoverages();
		if (coverageResponse.getVehicleLevelCoverages().size() == 0) {
			fail(" <<<< No vehicleLevelCoverages found in the ViewCoverage PAS Service response . This is not as expected.  >>>>");
		}
		for (int i = 0; i < vehicleLevelCoverageDetails.size(); i++) {
			if (VehOID.equals(vehicleLevelCoverageDetails.get(i).getOid())) {
				coverageDetails = vehicleLevelCoverageDetails.get(i).getCoverages();
			}
			
		}
		
		System.out.println("res:" + coverageResponse);
		return coverageDetails;

	}

}
