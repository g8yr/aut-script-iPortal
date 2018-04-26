package services.rest.apiclients;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicHeader;



import businesscomponents.iportal.WebserviceComponents;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.json.stub.UpdatePreferenceResponse.UpdatePreferenceResponse;
import com.json.stub.preference.Notification;
import com.json.stub.preference.PolicyPreference;
import com.json.stub.preference.PreferenceType;
import com.json.stub.preference.UpdatepreferenceNew;

import preferenceresponse.AvailablePreference;
import services.rest.commons.HTTP_ContentType;
import services.rest.commons.HTTP_RequestType;
import services.rest.commons.REST_Client;

public class UpdatePolicyPreference extends REST_Client {

	String scheme = null;
	String host = null;
	String resource = null;
	HashMap<String, String> urlParameterMap = null;
	static public UpdatePreferenceResponse updatepreferenceresponse;


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

	private void constructAndSet_Request_JSON() throws IOException {
		setRequest_Content(construct_Request_JSON());
	}

	private String construct_Request_JSON() {

		String policyNumber = "VASS900028986";
		String preferenceAction_PAYMENT_CONFIRMATION = "OPT_IN";
		String preferenceAction_PAYMENT_REMINDER = "OPT_IN";
		String preferenceAction_BILL_NOTIFICATION = "OPT_IN";
		String preferenceAction_POLICY_DOCUMENTS = "OPT_IN";

		String policySourceSystem = "PAS";
		String preferenceId = "0987654334";
		String preferenceType = "BILL_NOTIFICATION";
		String preferenceAction = "OPT_IN_PENDING";
		String reason = "AGENT_INTERACTION";
		String updatedByCustomerReferenceId = "1202007006030";
		String updatedBy = "3CM";
		String sourceUpdatedBy ="PAS";
		String channelValue = "evaluetestingdemo+100@gmail.com";
		String channelType ="EMAIL";
		String preferenceGroup ="ELECTRONIC_BILLING_COMMUNICATION";

		UpdatepreferenceNew updatepref_req = new UpdatepreferenceNew();

		// _3CMUpdatePreference updatepref_req = new _3CMUpdatePreference();

		updatepref_req.setPolicyNumber(policyNumber);
		updatepref_req.setPolicySourceSystem(policySourceSystem);
		updatepref_req.setPreferenceId(preferenceId);

		ArrayList<PolicyPreference> policyPreferences = new ArrayList<PolicyPreference>();

		PolicyPreference updatepolicyprefElement = new PolicyPreference();
		updatepolicyprefElement.setPreferenceGroup(preferenceGroup);

		List<Notification> notification = new ArrayList<Notification>();
		Notification updatenotifyElement = new Notification();

		updatenotifyElement.setChannelType(channelType);

		updatenotifyElement.setChannelValue(channelValue);

		List<PreferenceType> preferenceTypes = new ArrayList<PreferenceType>();
		// PreferenceType updateprefElement = new PreferenceType();

		PreferenceType[] preferenceTypeElement = new PreferenceType[4];
		preferenceTypeElement[0] = new PreferenceType();
		// preferenceTypeElement[0].setPreferenceType(preferenceType);
		preferenceTypeElement[0].setPreferenceType("PAYMENT_CONFIRMATION");
		preferenceTypeElement[0].setPreferenceAction(preferenceAction_PAYMENT_CONFIRMATION);
		preferenceTypeElement[0].setReason(reason);
		preferenceTypeElement[0].setUpdatedByCustomerReferenceId(updatedByCustomerReferenceId);
		preferenceTypeElement[0].setUpdatedBy(updatedBy);
		preferenceTypeElement[0].setSourceUpdatedBy(sourceUpdatedBy);
		preferenceTypes.add(0, preferenceTypeElement[0]);
		updatenotifyElement.setPreferenceTypes(preferenceTypes);

		preferenceTypeElement[1] = new PreferenceType();
		preferenceTypeElement[1].setPreferenceType("PAYMENT_REMINDER");
		preferenceTypeElement[1].setPreferenceAction(preferenceAction_PAYMENT_REMINDER);
		preferenceTypeElement[1].setReason(reason);
		preferenceTypeElement[1].setUpdatedByCustomerReferenceId(updatedByCustomerReferenceId);
		preferenceTypeElement[1].setUpdatedBy(updatedBy);
		preferenceTypeElement[1].setSourceUpdatedBy(sourceUpdatedBy);
		preferenceTypes.add(1, preferenceTypeElement[1]);
		updatenotifyElement.setPreferenceTypes(preferenceTypes);

		preferenceTypeElement[2] = new PreferenceType();
		preferenceTypeElement[2].setPreferenceType("BILL_NOTIFICATION");
		preferenceTypeElement[2].setPreferenceAction(preferenceAction_BILL_NOTIFICATION);
		preferenceTypeElement[2].setReason(reason);
		preferenceTypeElement[2].setUpdatedByCustomerReferenceId(updatedByCustomerReferenceId);
		preferenceTypeElement[2].setUpdatedBy(updatedBy);
		preferenceTypeElement[2].setSourceUpdatedBy(sourceUpdatedBy);
		preferenceTypes.add(2, preferenceTypeElement[2]);
		updatenotifyElement.setPreferenceTypes(preferenceTypes);

		preferenceTypeElement[3] = new PreferenceType();
		preferenceTypeElement[3].setPreferenceType("POLICY_DOCUMENTS");
		preferenceTypeElement[3].setPreferenceAction(preferenceAction_POLICY_DOCUMENTS);
		preferenceTypeElement[3].setReason(reason);
		preferenceTypeElement[3].setUpdatedByCustomerReferenceId(updatedByCustomerReferenceId);
		preferenceTypeElement[3].setUpdatedBy(updatedBy);
		preferenceTypeElement[3].setSourceUpdatedBy(sourceUpdatedBy);
		preferenceTypes.add(3, preferenceTypeElement[3]);
		updatenotifyElement.setPreferenceTypes(preferenceTypes);

		notification.add(0, updatenotifyElement);
		updatepolicyprefElement.setNotification(notification);
		policyPreferences.add(0, updatepolicyprefElement);

		updatepref_req.setPolicyPreferences(policyPreferences);

		// Code that converts data encapsulate in java classes to string
		ObjectMapper objMapper = new ObjectMapper();
		String strRequest = null;
		try {
			strRequest = objMapper.writeValueAsString(updatepref_req);
		} catch (Exception e) {
	//		report.updateTestLog("rest_request", "Problem in rest request", Status.FAIL);
		}
		strRequest = "[ " + strRequest + " ]";
		return strRequest;
	}

	/*
	 * updateprefElement.setPreferenceType(preferenceType);
	 * updateprefElement.setPreferenceAction(preferenceAction);
	 * updateprefElement.setReason(reason);
	 * updateprefElement.setUpdatedByCustomerReferenceId(
	 * updatedByCustomerReferenceId); updateprefElement.setUpdatedBy(updatedBy);
	 * updateprefElement.setSourceUpdatedBy(sourceUpdatedBy);
	 * updateprefElement.setLastUpdatedTime(lastUpdatedTime);
	 * 
	 * preferenceTypes.add(0, updateprefElement);
	 * 
	 * updatenotifyElement.setPreferenceTypes(preferenceTypes);
	 * notification.add(0, updatenotifyElement);
	 * updatepolicyprefElement.setNotification(notification);
	 * policyPreferences.add(0, updatepolicyprefElement);
	 * updatepref_req.setPolicyPreferences(policyPreferences);
	 * 
	 * // Code that converts data encapsulate in java classes to string
	 * ObjectMapper objMapper = new ObjectMapper(); String strRequest = null;
	 * try { strRequest = objMapper.writeValueAsString(updatepref_req); } catch
	 * (Exception e) { report.updateTestLog("rest_request",
	 * "Problem in rest request", Status.FAIL); }
	 * 
	 * return strRequest;
	 */

	private void construct_URI() {
		// TODO Auto-generated method stub

		scheme = "https";
		host="3cm-e2e.tent.trt.csaa.pri:8445/ss-preference-api";
		resource = "/preferenceService/v1/updatePreferences";

	}

	private void constructAndSet_Request_Header() {
		String auth_token =WebserviceComponents.token;

		BasicHeader[] apiHeader = new BasicHeader[2];
		
		apiHeader[0] = new BasicHeader("X-Client-Id", "cc_PAS");
		apiHeader[1] = new BasicHeader("Authorization", auth_token);
		setRequest_Header(apiHeader);
	}

	private void constructAndSet_URI_Parameters() {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		construct_URI();
		construct_Parameters();
		setURI_Path(scheme, host, resource, urlParameterMap);

	}

	private void construct_Parameters() {
		// TODO Auto-generated method stub
		urlParameterMap = null;
	}

	protected String getContextAsJSON(String contextID) {
		/*
		String UserId = dataTable.getCommonData_v2(properties.getProperty("Environment") + "_3CMPreferences","userId_context");
		String TransactionType = dataTable.getCommonData_v2(properties.getProperty("Environment") + "_3CMPreferences" ,"transactionType");
		String Application = dataTable.getCommonData_v2(properties.getProperty("Environment") + "_3CMPreferences", "application");
		String Address = dataTable.getCommonData_v2(properties.getProperty("Environment") + "_3CMPreferences", "address");
		String CorrelationId = dataTable.getCommonData_v2(properties.getProperty("Environment") + "_3CMPreferences", "correlationId");
		String SubSystem =dataTable.getCommonData_v2(properties.getProperty("Environment") + "_3CMPreferences", "subsystem");
		*/
		String UserId="eczarog";
		String TransactionType ="REALTIME";
		String Application ="MRM_IG";
		String Address ="10.37.128.170";
		String CorrelationId ="eczarog";
		String SubSystem ="";
		
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

		this.updatepreferenceresponse = objMapper.readValue(responseAsString, UpdatePreferenceResponse.class);
		List<AvailablePreference> avai_preference = new ArrayList<AvailablePreference>();
		AvailablePreference avai_preference_each = new AvailablePreference();
		avai_preference_each.getPreferenceGroup();
		avai_preference_each.getPreferenceGroupDisplayName();
		avai_preference.add(0, avai_preference_each);

		String policyNumber = updatepreferenceresponse.getExistingPreferences().get(0).getPolicyNumber();
		String sourceSystem = updatepreferenceresponse.getExistingPreferences().get(0).getPolicySourceSystem();
		String preferenceID = updatepreferenceresponse.getExistingPreferences().get(0).getPreferenceId();
		//report.updateTestLog("Update Preferences", "Policy Preferences is updated successfully", Status.PASS);
		System.out.println(policyNumber);
		System.out.println(sourceSystem);
		String Preference_Action_PAYMENT_CONFIRMATION = updatepreferenceresponse.getExistingPreferences().get(0).getPolicyPreferences().get(0).getNotification().get(0)
				.getPreferenceTypes().get(0).getPreferenceAction();
		String Preference_Action_PAYMENT_REMINDER = updatepreferenceresponse.getExistingPreferences().get(0).getPolicyPreferences().get(0).getNotification().get(0)
				.getPreferenceTypes().get(1).getPreferenceAction();
		String Preference_Action_BILL_NOTIFICATION = updatepreferenceresponse.getExistingPreferences().get(0).getPolicyPreferences().get(0).getNotification().get(0)
				.getPreferenceTypes().get(2).getPreferenceAction();
		String Preference_Action_POLICY_DOCUMENTS = updatepreferenceresponse.getExistingPreferences().get(0).getPolicyPreferences().get(0).getNotification().get(0)
				.getPreferenceTypes().get(3).getPreferenceAction();
		System.out.println("Payment Confirmation preference status is " + Preference_Action_PAYMENT_CONFIRMATION);
		System.out.println("Payment Reminder preference status is " + Preference_Action_PAYMENT_REMINDER);
		System.out.println("Bill Notification preference status is " + Preference_Action_BILL_NOTIFICATION);
		System.out.println("Policy Documents preference status is " + Preference_Action_POLICY_DOCUMENTS);

	}



}
