package services.rest.apiclients;

import static org.testng.Assert.fail;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;
//
//import com.cognizant.framework.Status;
//import com.cognizant.supportlibraries.ReusableLibrary;
//import com.cognizant.supportlibraries.ScriptHelper;
public class oauthTokenGeneration extends WebServiceComparator{
	
	URI uri;
	HttpPost post;
	HttpResponse httpresponse;
	HttpResponse response1;
	String restResponseAsString = null;
	String token = null;
	
	public void tokenGeneration() 
	{
			URI mrmUri= null;
//	        String mrmScheme = dataTable.getCommonData_v2(properties.getProperty("Environment"), "REST_Scheme");
//	        if(mrmScheme.equalsIgnoreCase("http"))
			String mrmScheme = "https";
//			String mrmSetHost = dataTable.getCommonData_v2(properties.getProperty("Environment")+"_API_Token", "REST_HOSTNAME");
	        String mrmSetHost = "qa-sso.tent.trt.csaa.pri";
	       // String mrmSetHost = "wiremock-master.apps.prod.pdc.digital.csaa-insurance.aaa.com";
			String mrmSetPath = "/as/token.oauth2";
						
			try {
				mrmUri = new URIBuilder().setScheme(mrmScheme).setHost(mrmSetHost).setPath(mrmSetPath).build();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
			
//	        uri = new URIBuilder().setScheme("https").setHost("soamrmqa1:2073").setPath("/ent/qa/csaa/v3/search/customerCentric").build();
			//report.updateTestLog("rest_endpoint", mrmUri.toString(), Status.DONE);
			post = new HttpPost(mrmUri);
	       // post.addHeader("Authorization", "Basic "+dataTable.getCommonData_v2(properties.getProperty("Environment")+"_API_Token", "REST_Authorization"));
			 post.addHeader("Authorization", "Basic Y2NfbXJtOkNkYlpYcWFuS3UweDdxREdET0JXV1l1QThFMXFPMmRmd0lCVnBlRGg4N2R6bXZOckNlc01mMWI0WVk5cm95SHg=");
	        post.addHeader("Content-Type", "application/x-www-form-urlencoded");

	        StringEntity input = null;
			try {
				input = new StringEntity("grant_type=client_credentials&scope=");
				for (Header header : post.getAllHeaders()) {
				      
				      if (header.getValue() != null) {
				    	  
				    	 // report.updateTestLog("rest_request Headers", header.getName() +" : "+header.getValue(), Status.DONE);
				      }
				} 
				
				//report.updateTestLog("rest_request", "grant_type=client_credentials&scope=", Status.DONE);
			} catch (UnsupportedEncodingException e) {
				fail("rest_request Problem in rest request: " + input.toString() );
				//report.updateTestLog("rest_request", "Problem in rest request: "+input.toString(), Status.FAIL);
				e.printStackTrace();
			}
	    	input.setContentType("application/json");
	    	post.setEntity(input);
	    	try {
	    		httpresponse = closeableHttpClient.execute(post);
				//new RestApiWraper(scriptHelper).validateResponse(httpresponse,"Oauth Token");
	    		new RestApiWraper().validateResponse(httpresponse,"Oauth Token");
			} catch (ClientProtocolException e) {
				fail("rest_getResponse : Problem in rest reponse");
				//report.updateTestLog("rest_getResponse", "Problem in rest reponse", Status.FAIL);
			} catch (IOException e) {
				fail("rest_getResponse : Problem in rest reponse");
				//report.updateTestLog("rest_getResponse", "Problem in rest response", Status.FAIL);
			}
	    	
	    	String responseJson = "";
			if (httpresponse == null) {
				fail("REST Exception : Exception while posting request");
				//report.updateTestLog("REST Exception", "Exception while posting request", Status.FAIL);
			} else {
				//RestApiWraper restWrapper = (new RestApiWraper(scriptHelper));
				RestApiWraper restWrapper = (new RestApiWraper());
				boolean isValidResponse = restWrapper.validateResponse(httpresponse, "Oauth Token");
				if (isValidResponse) {
					responseJson = restWrapper.getReponseString();
				}
			}
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(responseJson);
				token = jsonObject.getString("access_token");
				System.out.println("Token"+token);
				// report.updateTestLog("Oauth Token", token+" token generated successfully", Status.PASS);
			} catch (JSONException e) {
				fail("Oauth Token : Exception while generating token");
				//report.updateTestLog("Oauth TokenOauth Token", "Exception while generating token", Status.FAIL);
				e.printStackTrace();
			}
			//updateTokenInCommonData(token);
	}
	
	public String getToken() {
		// TODO Auto-generated method stub
		return token;
	}
	
//	public void updateTokenInCommonData(String token)
//	{
//		if(token!=null) {
//		String env = properties.getProperty("Environment");
//		String TD_ID = env+"_Authorization_Token";
//		dataTable.putCommonData(TD_ID, "REST_Authorization",token);
//		//report.updateTestLog("Oauth Token", "Token updated successfully in test data table", Status.PASS);
//		}
//		else
//			//report.updateTestLog("Oauth Token", "Null token", Status.FAIL);
//	}
}
