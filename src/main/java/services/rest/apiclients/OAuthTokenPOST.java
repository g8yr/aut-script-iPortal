package services.rest.apiclients;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import com.Cucumber.supportLibraries.StaticValue;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import businesscomponents.mypolicy.GeneralFunctions;
import services.rest.commons.HTTP_ContentType;
import services.rest.commons.HTTP_RequestType;
import services.rest.commons.REST_Client;
import services.rest.commons.oAuthClientDetails;
import services.rest.pojo.OAuthTokenPostResponse;

public class OAuthTokenPOST extends REST_Client {
	private String scheme = null;
	private String host = null;
	private String resource = null;
	private HashMap<String, String> urlParameterMap = null;

	private OAuthTokenPostResponse oAuthTokenPostResponse = new OAuthTokenPostResponse();

	public String GeneratedAuthToken = StaticValue.Empty;
	GeneralFunctions GenFns = new GeneralFunctions();
	
	// private String VIN = StaticValue.Empty;

	public String invokeWebApi() throws ClientProtocolException, IOException {
		setRequestType(HTTP_RequestType.POST);
		setContentType(HTTP_ContentType.X_FORM_URLENCODED);
		constructAndSet_URI_Parameters();
		constructAndSet_Request_Header();
		constructAndSet_Request_UrlEncodedParms();
		invokeService();
		getResponse_Class();
		return GeneratedAuthToken;
	}

	public void constructAndSet_URI_Parameters() {
		construct_URI();
		construct_Parameters();
		setURI_Path(scheme, host, resource, urlParameterMap);
	}

	public void construct_URI() {
		scheme = "https";
		//host = "wiremock-master.apps.prod.pdc.digital.csaa-insurance.aaa.com";
		host = "qa-sso.tent.trt.csaa.pri";
		resource = "/as/token.oauth2";
	}

	public void construct_Parameters() {
		urlParameterMap = null;
	}

	private void constructAndSet_Request_Header() {
		// introduce appropriate header - below code is a sample
		BasicHeader[] apiHeader = new BasicHeader[2];
		apiHeader[0] = new BasicHeader("Content-Type", "application/x-www-form-urlencoded");
		apiHeader[1] = new BasicHeader("Authorization", "Basic Y2NfaWV3bXM6alpNZG1teE1GS0F3amRhYWFWc1gzU0N1b3NodFRUUlJXd2xiQUdKN3BtSFU3cDlMdzhPellwVFVKYVJnczBpNQ==");

		setRequest_Header(apiHeader);
	}

	private void constructAndSet_Request_UrlEncodedParms() throws UnsupportedEncodingException {
		oAuthClientDetails oauthClientDetails = new oAuthClientDetails();
		List<NameValuePair> requestParams = new ArrayList<NameValuePair>();
		requestParams.add(new BasicNameValuePair("grant_type", oauthClientDetails.grantTypeDetails));
		//requestParams.add(new BasicNameValuePair("client_Id", oauthClientDetails.clientIdDetails));
		//requestParams.add(new BasicNameValuePair("client_Secret", oauthClientDetails.clientSecretDetails));
		setRequest_Content_ForURLEncoded(requestParams);

	}

	public void getResponse_Class() throws JsonParseException, JsonMappingException, IOException {
		GeneratedAuthToken = StaticValue.Empty;
		ObjectMapper objMapper = new ObjectMapper();
		this.oAuthTokenPostResponse = objMapper.readValue(responseAsString, OAuthTokenPostResponse.class);
		if (oAuthTokenPostResponse.getAccessToken().isEmpty()) {
			fail(" <<<< Token not generated and Empty.  issue while calling OAuthToken Service >>>>");
		}
		GeneratedAuthToken = oAuthTokenPostResponse.getTokenType() + " " + oAuthTokenPostResponse.getAccessToken();
		System.out.println("Actual Token:" + GeneratedAuthToken + ":");
		
		
	}
}
