package services.rest.apiclients;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

import junit.framework.Assert;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import services.rest.commons.oAuthClientDetails;
import services.rest.pojo.validateVehiclesList.validateVehiclesResponse.ValidateVehiclesResponse;
import services.rest.pojo.validateVehiclesList.validateVehiclesResponse.Vehicle;
import stepDefinitions.common.MasterStepDefs;
import webelementrepository.MyPolicy.AddVehiclePage;
import webelementrepository.MyPolicy.DriverVehicleAssignmentPage;
import webelementrepository.MyPolicy.MakePolicyChangesPage;
import MyPolicyContentSpec.VehiclesPageContent;
import businesscomponents.mypolicy.GeneralFunctions;

import com.Cucumber.supportLibraries.StaticValue;
import com.Cucumber.supportLibraries.Webaction;
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

public class ValidatePendingEndorsementVehicle extends MasterStepDefs {
	GeneralFunctions GenFns = new GeneralFunctions();
	// HttpClient httpClient;
	private CloseableHttpClient closeableHttpClient;
	URI uri;
	HttpGet get;
	HttpResponse response;
	String responsemessage;
	String restAsXMLString = null;
	String restResponseString = null;
	MakePolicyChangesPage makepolicychangepage = new MakePolicyChangesPage();
	AddVehiclePage addvehiclepage = new AddVehiclePage();
	ReadCSVFileFunctions readCSVfilefunctions = new ReadCSVFileFunctions();
	ViewAssignmentsGet viewAssignmentsGet = new ViewAssignmentsGet();
	ViewDriversGet viewDriversGet = new ViewDriversGet();
	VehiclesPageContent vehiclespagecontent = new VehiclesPageContent();
	DriverVehicleAssignmentPage driverVehicleAssignmentPage = new DriverVehicleAssignmentPage();
	oAuthClientDetails oauthClientDetails = new oAuthClientDetails();
	GenerateXApplicationContext generateXApplicationContext = new GenerateXApplicationContext();
	public static HashMap<String, Integer> VehicleDataMap = new HashMap<String, Integer>();
	public boolean isRESTResponseValid = false;
	public boolean isValidResponse = false;
	Webaction action = new Webaction();
	public static Logger logger = LogManager.getLogger();
	public static int totalTransactions;
	// Map<String, HashMap<String, String>> VehicleDataMap = new HashMap<String,
	// HashMap<String, String>>();
	List<Vehicle> resDetails;

	StaticValue StaticValue = new StaticValue();
	public String Year;
	public String Model;
	public String vehoid;
	private String VehModelYear;
	private String Vehmanufacturer;
	private String Vehseries;
	private String Vehmodel;
	private String VehbodyStyle;
	private String VehIdentificationNo;
	private String VehvehicleStatus;
	public String Ownership;
	public String Usage;
	public String Salvaged;
	public String GaragingDifferent;
	public String AntiTheft;
	public String RegisteredOwner;
	

	String VINUI;
	String Vehicledetails;

	public String VehicleOifForDriverAssignements = StaticValue.Empty;

	public ValidateVehiclesResponse rest_getResponse(String PolicyNumber) throws URISyntaxException, NoSuchAlgorithmException,
			KeyStoreException, KeyManagementException, ParseException, IOException {

		OAuthTokenPOST oAuthTokenPOST = new OAuthTokenPOST();
		SSLContextBuilder builder = new SSLContextBuilder();
		builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build(),
				SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", new PlainConnectionSocketFactory()).register("https", sslsf).build();
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
		cm.setMaxTotal(2000);
		closeableHttpClient = HttpClients.custom().setSSLSocketFactory(sslsf).setConnectionManager(cm).build();
		closeableHttpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();

		String scheme = "https";
		String setHost = "pasqa101.apps.prod.pdc.digital.csaa-insurance.aaa.com";
		String setPath = "/api/v1/policies/%s/endorsement/vehicles";

		setPath = String.format(setPath, PolicyNumber);
		URI uri = new URIBuilder().setScheme(scheme).setHost(setHost).setPath(setPath).build();
		get = new HttpGet(uri);
		get.addHeader("Content-Type", "application/json");
		//get.addHeader("X-ApplicationContext", oauthClientDetails.ApplicationContextDetails);
		String CurrentXApplicationContext;
        CurrentXApplicationContext = generateXApplicationContext.getXApplicationContext();
        System.out.println("CurrentXApplicationContext" + CurrentXApplicationContext);
        get.addHeader("X-ApplicationContext", CurrentXApplicationContext);

		Header[] allHeaders = get.getAllHeaders();
		String headerMsg = uri.toString();
		for (Header header : allHeaders) {
			headerMsg = headerMsg + "\n" + header;
		}
		Assert.assertTrue("Request" + uri, true);
		String tokenGenerated = StaticValue.Empty;
		tokenGenerated = oAuthTokenPOST.invokeWebApi();
		System.out.println("tokenGenerated for PendingEndoresement:" + tokenGenerated + ":");

		setRequest_Authentication("Authorization", tokenGenerated);
		// setRequest_Authentication("admin","admin");
		try {
			response = closeableHttpClient.execute(get);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		isRESTResponseValid = validateResponse(response, "validate Vehicle List");
		if (!isRESTResponseValid) {
			try {
				String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
			} catch (org.apache.http.ParseException | IOException e) {
				e.printStackTrace();
			}
		}
		HttpEntity entity = response.getEntity();
		responsemessage = EntityUtils.toString(entity);
		responsemessage = "{\"Vehicles\":" + responsemessage + "}";
		Assert.assertTrue("Request" + responsemessage, true);
		ValidateVehiclesResponse PendingServiceRsponse = getResponse();
		return PendingServiceRsponse;
	}

	public ValidateVehiclesResponse getResponse()
			throws JsonParseException, JsonMappingException, UnsupportedOperationException, IOException {
		ObjectMapper objMapper = new ObjectMapper();
		ValidateVehiclesResponse res = objMapper.readValue(responsemessage, ValidateVehiclesResponse.class);
		System.out.println("res:" + res);	
		return res;

	}
				
	

	public boolean validateResponse(HttpResponse response, String service) {
		this.response = response;
		int responseCode = response.getStatusLine().getStatusCode();
		if (!(responseCode > 199 && responseCode < 299)) {
			isValidResponse = false;
			fail(" <<<< PAS Service call issue while calling PAS Pending Endorsement Vechicles >>>> ");
			logger.info(response.toString());
		} else {
			isValidResponse = true;
		}
		return isValidResponse;
	}

	public String getReponseString() {
		String responseString = "";
		try {
			responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
			restResponseString = responseString;

		} catch (org.apache.http.ParseException | IOException e) {
			e.printStackTrace();
		}
		return responseString;
	}

	public String getRestResponseStringFromWraper() {
		return restResponseString;
	}

	protected void setRequest_Authentication(String userName, String password) {

		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, password));
		closeableHttpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).build();

	}

}
