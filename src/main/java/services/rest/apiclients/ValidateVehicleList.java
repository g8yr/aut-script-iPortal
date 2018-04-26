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
import webelementrepository.MyPolicy.MakePolicyChangesPage;
import MyPolicyContentSpec.VehiclesPageContent;
import businesscomponents.mypolicy.GeneralFunctions;

import com.Cucumber.supportLibraries.StaticValue;
import com.Cucumber.supportLibraries.Webaction;
//import webservices.RestApiWraper;
/*
import com.cognizant.framework.Status;
import com.cognizant.supportlibraries.ReusableLibrary;
import com.cognizant.supportlibraries.ScriptHelper;
*/
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ValidateVehicleList {
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
	//EndorsementPageContent endorsementpagecontent = new EndorsementPageContent();
	VehiclesPageContent vehiclespagecontent = new VehiclesPageContent();
	oAuthClientDetails oauthClientDetails = new oAuthClientDetails();
	GenerateXApplicationContext generateXApplicationContext = new GenerateXApplicationContext();
	public static HashMap<String, Integer> VehicleDataMap = new HashMap<String, Integer>();
	// MakePolicyChangesFunctions makepolicychangesfunctions= new
	// MakePolicyChangesFunctions();
	public boolean isRESTResponseValid = false;
	public boolean isValidResponse = false;
	Webaction action = new Webaction();
	public static Logger logger = LogManager.getLogger();
	public static int totalTransactions;
	//Map<String, HashMap<String, String>> VehicleDataMap = new HashMap<String, HashMap<String, String>>();
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
	String VINUI;
	String Vehicledetails;

	public void rest_getResponse(String PolicyNumber) throws URISyntaxException, NoSuchAlgorithmException,
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
		String setPath = "/api/v1/policies/%s/vehicles";

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
		// report.updateTestLog("REST Request", styleMsg(headerMsg, "xml"),
		// Status.PASS);
      	String tokenGenerated = StaticValue.Empty;      	
      	tokenGenerated = oAuthTokenPOST.invokeWebApi();
      	System.out.println("tokenGenerated for ValidateVehicleList:" + tokenGenerated + ":");
      	
      	setRequest_Authentication("Authorization",tokenGenerated);
      	//setRequest_Authentication("admin","admin");
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
				// report.updateTestLog("http response verification",styleMsg(responseString,
				// "error"), Status.FAIL);
				// fail();
			} catch (org.apache.http.ParseException | IOException e) {
				e.printStackTrace();
			}
		}
		HttpEntity entity = response.getEntity();
		responsemessage = EntityUtils.toString(entity);
		responsemessage = "{\"Vehicles\":" + responsemessage + "}";
		Assert.assertTrue("Request" + responsemessage, true);
		// String Result = getResponse();
		// if (!Result.equals(ExpectedStatus)) {
		// fail("Validation of PAS Endorsement Eligibility is not as Expected");
		// }
		getResponse();

		// report.updateTestLog("REST Response", styleMsg(responsemessage, "xml"),
		// Status.PASS);
	}

	public void getResponse()
			throws JsonParseException, JsonMappingException, UnsupportedOperationException, IOException {
		ObjectMapper objMapper = new ObjectMapper();
		ValidateVehiclesResponse res = objMapper.readValue(responsemessage, ValidateVehiclesResponse.class);
		System.out.println("res:" + res);
		resDetails = res.getVehicles();
		System.out.println("resDetails:" + resDetails);
		totalTransactions = res.getVehicles().size();
		VehicleDataMap.put(StaticValue.totalTransactions, totalTransactions);
		for (int i = 0; i < resDetails.size(); i++) {
			vehoid = resDetails.get(i).getOid();
			VehModelYear = resDetails.get(i).getModelYear();
			Vehmanufacturer = resDetails.get(i).getManufacturer();
			Vehseries = resDetails.get(i).getSeries();
			Vehmodel = resDetails.get(i).getModel();
			VehbodyStyle = resDetails.get(i).getBodyStyle();
			VehIdentificationNo = resDetails.get(i).getVehIdentificationNo();
			VehvehicleStatus = resDetails.get(i).getVehicleStatus();

			// VIN validation
			// VINUI =
			// action.GetText(By.xpath(String.format(makepolicychangepage.VechicleVIN,i+1)));
			int iterator = StaticValue.zero;
			iterator = i + 1;
			String ActualVinWebElm = StaticValue.Empty;
			ActualVinWebElm = makepolicychangepage.VechicleBlockGroup + "[" + iterator + "]"
					+ makepolicychangepage.VechicleVIN;
			VINUI = action.GetText(By.xpath(String.format(ActualVinWebElm)));
			String[] vinUISplit = VINUI.split(" ");
			GenFns.chkTheContent(vinUISplit[0], vehiclespagecontent.VehiclesPageVINCaption, StaticValue.VehiclePageVINCaption);
			GenFns.chkTheContent(vinUISplit[1], VehIdentificationNo, StaticValue.VehiclePageVINNumber);
			
			
			// Model, Model year and Manufacturer validations
			// Vehicledetails=action.GetText(By.xpath(String.format(makepolicychangepage.VechicleModel,i+1)));
			ActualVinWebElm = StaticValue.Empty;
			ActualVinWebElm = makepolicychangepage.VechicleBlockGroup + "[" + iterator + "]"
					+ makepolicychangepage.VechicleModel;
			Vehicledetails = action.GetText(By.xpath(String.format(ActualVinWebElm)));
			String[] VehicledetailsSplit = Vehicledetails.split(" ");
			// chkTheContent(VehModelYear, VehModelYear, StaticValue.VehiclePageYear);
			GenFns.chkTheContent(VehicledetailsSplit[0], VehModelYear, StaticValue.VehiclePageYear);
			GenFns.chkTheContent(VehicledetailsSplit[1], Vehmanufacturer, StaticValue.VehiclePageManufacturer);
			GenFns.chkTheContent(VehicledetailsSplit[2], Vehmodel, StaticValue.VehiclePageModel);
		}
		// StaticValue.setVehicleData(VehicleDataMap);
		ValidateAddVehicleLink(totalTransactions);

	}

	// Function to Validate the AddVehicleHyperlinkText
	public void ValidateAddVehicleLink(int VehicleCountPAS) {
		int VechicleCountUI = StaticValue.zero;
		String AddVehicleDescription, AddVehicleCaption = StaticValue.Empty;
		VechicleCountUI = action.fnExistOfElm(makepolicychangepage.VehicleCount, 3);
		System.out.println("VechicleCountUI:" + VechicleCountUI + ":");
		if ((VehicleCountPAS <= StaticValue.MaximumVehicleDisplayCount) && (VehicleCountPAS == VechicleCountUI)
				&& !(VehicleCountPAS == StaticValue.zero)) {
			if ((VehicleCountPAS < StaticValue.MaximumVehicleDisplayCount)
					&& (action.IsObjectPresent(makepolicychangepage.AddVehicleHyperlink, "AddVehicleHyperlink"))) {
				System.out.println("Chk add vechicle text");

				AddVehicleDescription = action.GetText(makepolicychangepage.AddVehicleHyperlinktext);
				GenFns.chkTheContent(AddVehicleDescription, vehiclespagecontent.AddVehicleLinkText,
						StaticValue.VehiclePageAddVehicleDescription);

				AddVehicleCaption = action.GetText(makepolicychangepage.AddVehicleHyperlink);
				GenFns.chkTheContent(AddVehicleCaption, vehiclespagecontent.AddVehicleLinkCaption,
						StaticValue.VehiclePageAddVehicleCaption);

			}
			// Throws a fail statement while Policy have 7 Vehicle List 'Add Vehicle' Link
			// should not get displayed
			else if ((VehicleCountPAS == StaticValue.MaximumVehicleDisplayCount)
					&& (action.IsObjectPresent(makepolicychangepage.AddVehicleHyperlink, "AddVehicleHyperlink"))) {
				fail(" <<<< 'ADD VEHICLE' Link is displayed for the Policy though it reached the allowed maximum Vehicle Count "
						+ StaticValue.MaximumVehicleDisplayCount + "  >>>> ");
			}
		}

		// Throws a fail statement while Policy have Zero Vehicle List
		else if ((VehicleCountPAS == 0) && (VechicleCountUI == 0)) {
			fail(" <<<< PAS Current Term Vehicles Service is not added with any Vehicle. Issue in PAS service call >>>> ");
		}

		// Throws a fail statement while vehicles diaplyed in UI after PAS Vehicle List
		// Service with no vehicle
		else if ((VehicleCountPAS == 0) && (VechicleCountUI == 1)) {
			fail(" <<<< PAS Current Term Vehicles Service is not added with any Vehicle. However vehicles are displayed in My Policy Portal. Issue in MyPolicy portal itself >>>> ");
		}

		// Throws a fail statement while No vehicles in UI after success PAS Vehicle
		// List Service
		else if ((VehicleCountPAS == 1) && (VechicleCountUI == 0)) {
			fail(" <<<< PAS Current Term Vehicles Service is added with Vehicle. However some vehicles are not displayed in My Policy Portal. Issue in MyPolicy portal itself >>>> ");
		}

		// Throws a fail statement while Number of vehicles in UI is more than PAS
		// Vehicle List Service
		else if ((VechicleCountUI > VehicleCountPAS) && !(VehicleCountPAS > StaticValue.MaximumVehicleDisplayCount)) {
			fail(" <<<< PAS Current Term Vehicles Service is added with Vehicle. HoweverThere is a mismatch in the count of vehicles displayed in MyPolicy Portal. Issue in MyPolicy portal itself >>>> ");
		}
		// Throws a fail statement while Number of vehicles in UI is less than PAS
		// Vehicle List Service
		else if ((VechicleCountUI < VehicleCountPAS) && !(VehicleCountPAS > StaticValue.MaximumVehicleDisplayCount)) {
			fail(" <<<< PAS Current Term Vehicles Service is added with Vehicle. HoweverThere is a mismatch in the count of vehicles displayed in MyPolicy Portal. Issue in MyPolicy portal itself >>>> ");
		}
		// Throws a fail statement while Policy have more than 7 Vehicle List
		else {
			fail(" <<<< PAS Current Term Vehicles Service retrieves Vehicle List more than the allowed maximum Vehicle Count "
					+ StaticValue.MaximumVehicleDisplayCount + "  >>>> ");
		}

	}

	public boolean validateResponse(HttpResponse response, String service) {
		this.response = response;
		int responseCode = response.getStatusLine().getStatusCode();
		if (!(responseCode > 199 && responseCode < 299)) {
			isValidResponse = false;
			fail(" <<<< PAS Service call issue while calling Current Term Vehicles Service >>>> ");
			logger.info(response.toString());
			// report.updateTestLog("http response",styleMsg("UnExpected Error :
			// "+response.getStatusLine(), "error"), Status.FAIL);
			// report.updateTestLog("Error response",styleMsg(getReponseString(), "error"),
			// Status.FAIL);
			// throw new FrameworkException("Exception in "+service+" rest api response
			// "+response.getStatusLine());
		} else {
			isValidResponse = true;
			// report.updateTestLog("http response","REST Response Status :
			// "+response.getStatusLine(), Status.PASS);
		}
		return isValidResponse;
	}

	public String getReponseString() {
		String responseString = "";
		try {
			responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
			restResponseString = responseString;
			// report.updateTestLog("http response data",styleMsg(responseString, "xml"),
			// Status.DONE);

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
