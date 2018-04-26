package services.rest.apiclients;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.rmi.RemoteException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
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
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.XMLUnit;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.openqa.selenium.JavascriptExecutor;

import services.rest.pojo.validateEndorsement.validateEndorsementResponse.json.ValidateEndorsementResponse;
import com.Cucumber.supportLibraries.StaticValue;

import services.rest.commons.oAuthClientDetails;
import services.rest.pojo.validateEndorsement.validateEndorsementResponse.json.RuleSet;
//import webservices.RestApiWraper;
/*
import com.cognizant.framework.Status;
import com.cognizant.supportlibraries.ReusableLibrary;
import com.cognizant.supportlibraries.ScriptHelper;
*/
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



public class ValidateEndorsement
{
	//HttpClient httpClient;
	private CloseableHttpClient closeableHttpClient;
	URI uri;
	HttpGet get;
	HttpResponse response;
	String responsemessage;
	String restAsXMLString = null;
	String restResponseString = null;
	public boolean isRESTResponseValid=false;
	public boolean isValidResponse = false;
	public static Logger logger = LogManager.getLogger();
	String result = StaticValue.Empty;
	PolicyLockService policyLockService = new PolicyLockService();
	OAuthTokenPOST oAuthTokenPOST = new OAuthTokenPOST();
	oAuthClientDetails oauthClientDetails = new oAuthClientDetails();
	GenerateXApplicationContext generateXApplicationContext = new GenerateXApplicationContext();
	
	public ValidateEndorsement() {
		
		// TODO Auto-generated constructor stub
	}
	
	public void rest_getResponse(String PolicyNumber,String effectiveDate, String ExpectedStatus) throws URISyntaxException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, ParseException, IOException{
		SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build(), SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create().register("http", new PlainConnectionSocketFactory())
                     .register("https", sslsf).build();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
        cm.setMaxTotal(2000);
        closeableHttpClient = HttpClients.custom().setSSLSocketFactory(sslsf).setConnectionManager(cm).build();
        closeableHttpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
       
        String scheme =  "https";
        String setHost = "pasqa101.apps.prod.pdc.digital.csaa-insurance.aaa.com";
        String setPath = "/api/v1/policies/%s/start-endorsement-info";
        //effectiveDate="2018-08-08";
        setPath = String.format(setPath,PolicyNumber);
        //URI uri = new URIBuilder().setScheme(scheme).setHost(setHost).setPath(setPath).setParameter("endorsementDate", effectiveDate).build();
        URI uri = new URIBuilder().setScheme(scheme).setHost(setHost).setPath(setPath).build();
        System.out.println("Service URL:" + uri );
        get = new HttpGet(uri);		
        get.addHeader("Content-Type", "application/json");
        //get.addHeader("X-ApplicationContext", oauthClientDetails.ApplicationContextDetails);
        String CurrentXApplicationContext;
        CurrentXApplicationContext = generateXApplicationContext.getXApplicationContext();
        System.out.println("CurrentXApplicationContext" + CurrentXApplicationContext);
        get.addHeader("X-ApplicationContext", CurrentXApplicationContext);
     
         Header[] allHeaders = get.getAllHeaders();
      	String headerMsg=uri.toString();
      	for (Header header : allHeaders) {
      		headerMsg = headerMsg +"\n"+header;
 		}
      	Assert.assertTrue("Request"+uri,true);
    //	report.updateTestLog("REST Request", styleMsg(headerMsg, "xml"), Status.PASS);
       
  
      	
      	String tokenGenerated = StaticValue.Empty;      	
      	tokenGenerated = oAuthTokenPOST.invokeWebApi();
      	System.out.println("tokenGenerated for ValidateEndoresement:" + tokenGenerated + ":");
      	
      	setRequest_Authentication("Authorization",tokenGenerated);
      	//setRequest_Authentication("admin","admin");
    	
      	
      	try {
			response = closeableHttpClient.execute(get);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	isRESTResponseValid = validateResponse(response, "validate Endorsement");
    	if(!isRESTResponseValid){
    		try {
				String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
	//			report.updateTestLog("http response verification",styleMsg(responseString, "error"), Status.FAIL);
				fail();
			} catch (org.apache.http.ParseException | IOException e) {
				e.printStackTrace();
			}
    	}
    	HttpEntity entity = response.getEntity();
		responsemessage = EntityUtils.toString(entity);
		Assert.assertTrue("Request"+responsemessage,true);
		String Result = getResponse();
		if (!Result.equals(ExpectedStatus)) {
			fail("<<<<  Validation of PAS Endorsement Eligibility is expected to '" + ExpectedStatus + "' But, PAS Endorsement Eligibility is '" + Result + "' >>>>");
		}
		
		//to lock the policy in PAS
	//	policyLockService.rest_getResponse(PolicyNumber, StaticValue.Pass);
	}
	
	public String getResponse() throws JsonParseException, JsonMappingException, UnsupportedOperationException, IOException 
	{
		ObjectMapper objMapper = new ObjectMapper();		
		ValidateEndorsementResponse res = objMapper.readValue(responsemessage, ValidateEndorsementResponse.class);		
		
		List<String> allowedEndorsements = res.getAllowedEndorsements();
		List<RuleSet> ruleSets = res.getRuleSets();
		System.out.println("ruleSets:"+ruleSets);
		System.out.println("ruleSets:"+ruleSets.get(1));
		if(allowedEndorsements.contains(StaticValue.UpdateVehicle)) {
			System.out.println("PAS Validation: 'UpdateVehicle' found");
			Assert.assertTrue("Make Policy Changes Elligibility Met for policy ",true);	
			result = StaticValue.Pass;
		}
		else
		{
			System.out.println("<<< PAS StartEndorsementInfo Validation: 'UpdateVehicle' not found in AllowedEnodrsements Tag >>>");
			result = StaticValue.Fail;
		}
		return result;
	}

	public boolean validateResponse(HttpResponse response,String service){
		this.response = response;
		int responseCode = response.getStatusLine().getStatusCode();
		 if(!(responseCode>199 && responseCode<299)){
			isValidResponse = false;
			fail(" PAS Service issue while calling Endorsement Eligibility");
			logger.info(response.toString());
		}else{
			isValidResponse = true;
		}
		return isValidResponse;
	}
	
	public String getReponseString(){
		String responseString="";
		try {
			responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
			restResponseString = responseString;
			
		} catch (org.apache.http.ParseException | IOException e) {
			e.printStackTrace();
		}
		return responseString;
	}

	public String getRestResponseStringFromWraper()
	{
		return restResponseString;
	}
	
	protected void setRequest_Authentication(String userName, String password) {

		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, password));
		closeableHttpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).build();

	}
}
