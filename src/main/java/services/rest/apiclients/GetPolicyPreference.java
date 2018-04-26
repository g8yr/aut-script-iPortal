package services.rest.apiclients;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicHeader;




import businesscomponents.iportal.WebserviceComponents;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.json.stub.getpreference.GetPreference;

import preferenceresponse.AvailablePreference;
import preferenceresponse.PreferenceResponse;
import services.rest.commons.HTTP_ContentType;
import services.rest.commons.HTTP_RequestType;
import services.rest.commons.REST_Client;

public class GetPolicyPreference extends REST_Client {

	String scheme = null;
	String host = null;
	String resource = null;
	HashMap<String, String> urlParameterMap = null;
	static public PreferenceResponse preferenceresponse;

	public void setRequest() throws ClientProtocolException, IOException {

		setRequestType(HTTP_RequestType.POST);
		setContentType(HTTP_ContentType.JSON);
		constructAndSet_URI_Parameters();
		constructAndSet_Request_Header();
		constructAndSet_Request_JSON();
	}
public void invokeWebApi() throws ClientProtocolException, IOException {

		
		invokeService();
	getResponse_Class();
	}
	public void constructAndSet_URI_Parameters() {

		// TODO Auto-generated method stub
		construct_URI();
		construct_Parameters();
		setURI_Path(scheme, host, resource, urlParameterMap);

	}

	public void construct_URI() {
		/*
		 * scheme = "https"; host = "sit-soaservices.tent.trt.csaa.pri:2073";
		 * resource = "/ent/qa/csaa/v1/policy/preferences/update";
		 * 
		 * scheme = "http"; host = "n01apl4375.tent.trt.csaa.pri:8082"; resource
		 * = "/ss-preference-api/preferenceService/v1/getPreferences";
		 */
		/*
		 * scheme = "https"; host =
		 * "preference-api-e2e.apps.prod.pdc.digital.csaa-insurance.aaa.com";
		 * resource = "/preferenceService/v1/getPreferences";
		 */
		scheme = "https";
		host = "3cm-e2e.tent.trt.csaa.pri:8445/ss-preference-api";
		resource = "/preferenceService/v1/getPreferences";

	}

	private void construct_Parameters() {
		// TODO Auto-generated method stub
		urlParameterMap = null;
	}

	private void constructAndSet_Request_Header() {
		// introduce appropriate header - below code is a sample
		// String auth_token="mgNyc5MzUPbYXZVO8cWt1zWLDB08";
		// String auth_token= dataTable.getDataIndependent("Common_Testdata",
		// "TD_ID", "QA_API_Token", "REST_Authorization");
		String auth_token =WebserviceComponents.token;
		System.out.println("Token" +auth_token);
		//String auth_token ="3fEThq4IBjXgEmH6s71FlhzP4PSu";
		BasicHeader[] apiHeader = new BasicHeader[3];
		// apiHeader[0] = new BasicHeader("X-Client-Id",
		// dataTable.getCommonData_v2(properties.getProperty("Environment") +
		// "_API_Search", "REST_XClientID"));
		// apiHeader[0] = new BasicHeader("X-Client-Id",
		// "9AF11739-7B36-0D23-4A4F-639469401DDF");
		apiHeader[0] = new BasicHeader("X-Client-Id", "ss_client");
		apiHeader[1] = new BasicHeader("X-ApplicationContext", getContextAsJSON("X-ApplicationContext"));
		/*
		 * apiHeader[1] = new BasicHeader( "Authorization", "Bearer "
		 * +auth_token);
		 */
		apiHeader[2] = new BasicHeader("Authorization", auth_token);
		setRequest_Header(apiHeader);
	}

	private void constructAndSet_Request_JSON() throws UnsupportedEncodingException {
		setRequest_Content(construct_Request_JSON());
	}

	private String construct_Request_JSON() {
		// TODO Auto-generated method stub

		GetPreference getpref_request = new GetPreference();
		String policyNumber = "VASS900028913";
		String policySourceSystem = "PAS";
		getpref_request.setPolicyNumber(policyNumber);
		getpref_request.setPolicySourceSystem(policySourceSystem);

		// Code that converts data encapsulate in java classes to string
		ObjectMapper objMapper = new ObjectMapper();
		String strRequest = null;
		strRequest = "[";
		try {
			strRequest = objMapper.writeValueAsString(getpref_request);
		} catch (Exception e) {
	//		report.updateTestLog("rest_request", "Problem in rest request", Status.FAIL);
		}
		strRequest = "[ " + strRequest + " ]";

		return strRequest;
	}

	protected String getContextAsJSON(String contextID) {
		String UserId="eczarog";
		String TransactionType ="realtime";
		String Application ="3CM";
		String Address ="10.37.128.170";
		String CorrelationId ="guiid";
		String SubSystem ="sales";
		

		String contextString = "";

		contextString = contextString + "{";
		if (!UserId.equalsIgnoreCase("")) {
			contextString = contextString + "\"userId\":\"" + UserId + "\",";
		}
		if (!TransactionType.equalsIgnoreCase("")) {
			contextString = contextString + "\"transactionType\":\"" + TransactionType + "\",";
		}
		if (!Application.equalsIgnoreCase("")) {
			contextString = contextString + "\"application\":\"" + Application + "\",";
		}
		if (!SubSystem.equalsIgnoreCase("")) {
			contextString = contextString + "\"subSystem\":\"" + SubSystem + "\",";
		}
		if (!CorrelationId.equalsIgnoreCase("")) {
			contextString = contextString + "\"correlationId\":\"" + CorrelationId + "\",";
		}
		contextString = contextString + "}";
		contextString = contextString.replaceAll("\\,\\}", "\\}");
		/*
		 * "{\"userId\":\""+UserId+"\",\"transactionType\":\""+TransactionType+
		 * "\",\"application\":\""
		 * +Application+"\",\"address\":\""+Address+"\",\"correlationId\":\""+
		 * CorrelationId+"\"}";
		 */
		return contextString;
	}

	public void getResponse_Class() throws JsonParseException, JsonMappingException, IOException {
		// Code that applies the string to Java Class
		ObjectMapper objMapper = new ObjectMapper();
		// this.preferenceresponse = objMapper.readValue(responseAsString,
		// PreferenceResponse.class);
		this.preferenceresponse = objMapper.readValue(responseAsString, PreferenceResponse.class);
		List<AvailablePreference> avai_preference = new ArrayList<AvailablePreference>();
		AvailablePreference avai_preference_each = new AvailablePreference();
		avai_preference_each.getPreferenceGroup();
		avai_preference_each.getPreferenceGroupDisplayName();
		avai_preference.add(0, avai_preference_each);

		String preferenceGroup = preferenceresponse.getAvailablePreferences().get(0).getPreferenceGroup();
		String PreferenceGroupDisplayName = preferenceresponse.getAvailablePreferences().get(0).getPreferenceGroupDisplayName();
		String Preference_Action_PAYMENT_CONFIRMATION = preferenceresponse.getExistingPreferences().get(0).getPolicyPreferences().get(0).getNotification().get(0)
				.getPreferenceTypes().get(0).getPreferenceAction();
		String Preference_Action_PAYMENT_REMINDER = preferenceresponse.getExistingPreferences().get(0).getPolicyPreferences().get(0).getNotification().get(0).getPreferenceTypes()
				.get(1).getPreferenceAction();
		String Preference_Action_BILL_NOTIFICATION = preferenceresponse.getExistingPreferences().get(0).getPolicyPreferences().get(0).getNotification().get(0).getPreferenceTypes()
				.get(2).getPreferenceAction();
		String Preference_Action_POLICY_DOCUMENTS = preferenceresponse.getExistingPreferences().get(0).getPolicyPreferences().get(0).getNotification().get(0).getPreferenceTypes()
				.get(3).getPreferenceAction();
	//	report.updateTestLog("Payment Confirmation", "Payment Confirmation preference status is " + Preference_Action_PAYMENT_CONFIRMATION, Status.PASS);
	//	report.updateTestLog("Payment Reminder", "Payment Reminder preference status is " + Preference_Action_PAYMENT_REMINDER, Status.PASS);
	//	report.updateTestLog("Bill Notification", "Bill Notification preference status is " + Preference_Action_BILL_NOTIFICATION, Status.PASS);
	//	report.updateTestLog("Policy Documents", "Policy Documents preference status is " + Preference_Action_POLICY_DOCUMENTS, Status.PASS);
		System.out.println(preferenceGroup);
		System.out.println(PreferenceGroupDisplayName);
	}

	

}
