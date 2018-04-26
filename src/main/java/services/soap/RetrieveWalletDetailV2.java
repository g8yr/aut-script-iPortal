package services.soap;

import static org.testng.Assert.fail;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.rmi.RemoteException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONException;

import AAANCNU_Common_version2.com.aaancnuit.www.ApplicationContext;
import AAANCNU_Common_version2.com.aaancnuit.www.ErrorInfo;
import AAANCNU_WSDL_RetrieveWalletDetail_version2.com.aaancnuit.www.RetrieveWalletDetailRequest;
import AAANCNU_WSDL_RetrieveWalletDetail_version2.com.aaancnuit.www.RetrieveWalletDetailSOAPBindingStub;

import com.galenframework.reports.nodes.TestReportNode.Status;

public class RetrieveWalletDetailV2  {

    static RetrieveWalletDetailRequest request = null;
    RetrieveWalletDetailSOAPBindingStub stub = null;

    HttpClient httpClient;
    URI uri;
    HttpGet get;
    HttpResponse response;

    String CorrelationId = "C12345";
    String UserId = "test";
    String TransactionType = "online";
    String Address = "127.0.0.1";
    String Application = "EZPAY";
    String subSystem ="CUSTPRL";


    public void soap_SetRequest() {

	String ownerID ="Q2AZTESTAZS876";
	ApplicationContext applicationContext = new ApplicationContext();
	applicationContext.setUserId(UserId);
	applicationContext.setTransactionType(TransactionType);
	applicationContext.setApplication(Application);
	applicationContext.setSubSystem(subSystem);
	applicationContext.setAddress(Address);
	applicationContext.setCorrelationId(CorrelationId);
	request = new RetrieveWalletDetailRequest();
	request.setApplicationContext(applicationContext);
	request.setOwnerID(ownerID);
    }

    public void soap_getResponse() throws URISyntaxException, MalformedURLException {
	String strEndPoint = "https://soaqa3.tent.trt.csaa.pri/";
	strEndPoint = strEndPoint + "RetrieveWalletDetailV2";
	URL url = new URL(strEndPoint);
	URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
	url = uri.toURL();
	try {
	System.out.println("SOAP Endpoint" +url.toString());
	    stub = new RetrieveWalletDetailSOAPBindingStub(url, null);
	    stub.retrieveWalletDetail(request);
	} catch (ErrorInfo e) {
	    String errCode = e.getErrorCode();
	    String errText = e.getErrorMessageText();
	    String errMessage = e.getMessage();
	    if (!errCode.equalsIgnoreCase("SOAB101")) {
	    	fail("Error Code: " + errCode + "</br>" + errText + "</br>" + errMessage);
	//	report.updateTestLog("soap_getResponse", styleMsg("Error Code: " + errCode + "</br>" + errText + "</br>" + errMessage, "error"), Status.FAIL);
	    }
	} catch (RemoteException e) {
	    String errText = e.getMessage();
	    if (errText != null) {
	    	fail("Exception in soap response" + errText);

	//	report.updateTestLog("soap_getResponse", "Exception in soap response" + errText, Status.FAIL);
	    } else {
		try {
		    String response = stub._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString();
		    if (!response.contains("errorCode>SOAB101<")) {
		    	fail("Exception in soap response Service Issue");
	//		report.updateTestLog("soap_getResponse", "Exception in soap response Service Issue", Status.FAIL);
		    }
		} catch (Exception e1) {
		    e1.printStackTrace();
		}
	    }
	}
	try {
		System.out.println("Soap Request "+stub._getCall().getMessageContext().getRequestMessage().getSOAPPartAsString());
		System.out.println("Soap Response "+stub._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString());
	} catch (Exception e1) {
	    e1.printStackTrace();
	}
    }

    public void setRetrieveWalletDetailsV2(String iteration)
	    throws URISyntaxException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, MalformedURLException, UnsupportedEncodingException, JSONException {

	soap_SetRequest();
	
    }
    public void callRetrieveWalletDetailsV2() throws MalformedURLException, URISyntaxException
    {
    soap_getResponse();
    }
}
