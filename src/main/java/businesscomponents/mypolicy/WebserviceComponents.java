package businesscomponents.mypolicy;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import services.rest.apiclients.GetPolicyPreference;
import services.rest.apiclients.UpdatePolicyPreference;
import services.soap.RecordWalletV2;
import services.soap.RetrieveWalletDetailV2;

public class WebserviceComponents {
	

	UpdatePolicyPreference updatePolicyPreference = new UpdatePolicyPreference();
	GetPolicyPreference getPolicyPreference= new GetPolicyPreference();
	public void setRecordWalletWS(String Iteration) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, MalformedURLException, UnsupportedEncodingException, URISyntaxException, JSONException {
		RecordWalletV2 recordwallet = new RecordWalletV2();
		recordwallet.setRecordWalletV2(Iteration);
	}
	
	public void callRecordWalletWS() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, MalformedURLException, UnsupportedEncodingException, URISyntaxException, JSONException {
		RecordWalletV2 recordwallet = new RecordWalletV2();
		recordwallet.callRecordWalletV2();
	}
	
	public void setRetrieveWalletWS(String Iteration) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, MalformedURLException, UnsupportedEncodingException, URISyntaxException, JSONException {
		RetrieveWalletDetailV2 retrievewallet = new RetrieveWalletDetailV2();
		retrievewallet.setRetrieveWalletDetailsV2(Iteration);
	}
	
	public void callRetrieveWalletWS() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, MalformedURLException, UnsupportedEncodingException, URISyntaxException, JSONException {
		RetrieveWalletDetailV2 retrievewallet = new RetrieveWalletDetailV2();
		retrievewallet.callRetrieveWalletDetailsV2();
	}
	public void setUpdatePreferenceWS(String Iteration) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, URISyntaxException, JSONException, IOException {
		
		updatePolicyPreference.setRequest();
	}
	
	public void callUpdatePreferenceWS() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, URISyntaxException, JSONException, ClientProtocolException, IOException {

		updatePolicyPreference.invokeWebApi();
	}

	public void setGetPreferenceWS(String iteration) throws ClientProtocolException, IOException {
		getPolicyPreference.setRequest();
		
	}

	public void callGetPreferenceWS() throws ClientProtocolException, IOException {
		getPolicyPreference.invokeWebApi();
		
	}
}
