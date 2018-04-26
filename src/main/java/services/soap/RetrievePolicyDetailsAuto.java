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

import junit.framework.Assert;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONException;




//import com.cognizant.framework.Status;
//import com.cognizant.supportlibraries.ReusableLibrary;
//import com.cognizant.supportlibraries.ScriptHelper;
/*
import webservice.soap.retrieveBillingHistory.AAANCNU_Common_version2.com.aaancnuit.www.ErrorInfo;
import webservice.soap.retrievePolicydetail.retrieveAutoPolicydetail.AAANCNU_Common_version2.com.aaancnuit.www.ApplicationContext;
import webservice.soap.retrievePolicydetail.retrieveAutoPolicydetail.AAANCNU_WSDL_RetrieveAutoPolicyDetail_version2.com.aaancnuit.www.RetrieveAutoPolicyDetailProxy;
import webservice.soap.retrievePolicydetail.retrieveAutoPolicydetail.AAANCNU_WSDL_RetrieveAutoPolicyDetail_version2.com.aaancnuit.www.RetrieveAutoPolicyDetailRequest;
import webservice.soap.retrievePolicydetail.retrieveAutoPolicydetail.AAANCNU_WSDL_RetrieveAutoPolicyDetail_version2.com.aaancnuit.www.RetrieveAutoPolicyDetailResponse;
import webservice.soap.retrievePolicydetail.retrieveAutoPolicydetail.AAANCNU_WSDL_RetrieveAutoPolicyDetail_version2.com.aaancnuit.www.RetrieveAutoPolicyDetailSOAPBindingStub;
*/
import AAANCNU_Common_version2.com.aaancnuit.www.*;
import AAANCNU_WSDL_RetrieveAutoPolicyDetail_version2.com.aaancnuit.www.RetrieveAutoPolicyDetailRequest;
import AAANCNU_WSDL_RetrieveAutoPolicyDetail_version2.com.aaancnuit.www.RetrieveAutoPolicyDetailResponse;
import AAANCNU_WSDL_RetrieveAutoPolicyDetail_version2.com.aaancnuit.www.RetrieveAutoPolicyDetailSOAPBindingStub;
import AAANCNU_WSDL_RetrieveAutoPolicyDetail_version2.com.aaancnuit.www.RetrieveAutoPolicyDetailProxy;

public class RetrievePolicyDetailsAuto {
    public boolean isRESTResponseValid = false;
    RetrieveAutoPolicyDetailRequest detailRequest = null;
    RetrieveAutoPolicyDetailProxy autoPolicyDetailProxy = null;
    RetrieveAutoPolicyDetailResponse autoPolicyDetailResponse = null;
    RetrieveAutoPolicyDetailSOAPBindingStub autoPolicyDetailSOAPBindingStub = null;
    HttpClient httpClient;
    URI uri;
    HttpGet get;
    HttpResponse response;
    String soap_XMLAsString = null;
    String restAsXMLString = null;
    String restResponseString = null;

    String UserId = "eczarog";
    String TransactionType = "REALTIME";
    String Application = "MRM_IG";
    String Address = "10.37.128.170";
    String CorrelationId = "eczarog";

    public RetrievePolicyDetailsAuto() {
	super();
    }

    public void soap_SetRequest(String policyNumber, String sourceSystem) {

	ApplicationContext applicationContext = new ApplicationContext();
	applicationContext.setUserId(UserId);
	applicationContext.setTransactionType(TransactionType);
	applicationContext.setApplication(Application);
	applicationContext.setAddress(Address);
	applicationContext.setCorrelationId(CorrelationId);

	detailRequest = new RetrieveAutoPolicyDetailRequest();
	detailRequest.setPolicyNumber(policyNumber);
	detailRequest.setSourceSystem(sourceSystem);
    }

    public void soap_getResponse() throws URISyntaxException, MalformedURLException {
	//String strEndPoint = dataTable.getCommonData_v2(properties.getProperty("Environment"), "SOAP_EndPointUrl");
	String strEndPoint = "https://soaqa3.tent.trt.csaa.pri/";
	strEndPoint = strEndPoint + "RetrieveAutoPolicyDetailV2";
	//String currentTestDescription = testParameter.getCurrentTestDescription();
	//testParameter.setCurrentTestDescription("<strong>Endpoint: </strong>/RetrieveAutoPolicyDetailV2<br>" + currentTestDescription);
	URL url = new URL(strEndPoint);
	URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
	url = uri.toURL();
	try {
	   // report.updateTestLog("SOAP Endpoint", url.toString(), Status.DONE);
		
	    autoPolicyDetailSOAPBindingStub = new RetrieveAutoPolicyDetailSOAPBindingStub(url, null);
	    autoPolicyDetailResponse = autoPolicyDetailSOAPBindingStub.retrieveAutoPolicyDetail(detailRequest);
	} catch (ErrorInfo e) {
	    String errCode = e.getErrorCode();
	    String errText = e.getErrorMessageText();
	    String errMessage = e.getMessage();
	    if (!errCode.equalsIgnoreCase("SOAB101")) {
	    	fail("Error Code: " + errCode + "</br>" + errText + "</br>" + errMessage);
		//report.updateTestLog("soap_getResponse", styleMsg("Error Code: " + errCode + "</br>" + errText + "</br>" + errMessage, "error"), Status.FAIL);
	    }
	} catch (RemoteException e) {
	    String errText = e.getMessage();
	    if (errText != null) {
	    	fail("Exception in soap response" + errText);
		//report.updateTestLog("soap_getResponse", "Exception in soap response" + errText, Status.FAIL);
	    } else {
		try {
		    String response = autoPolicyDetailSOAPBindingStub._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString();
		    if (!response.contains("errorCode>SOAB101<")) {
		    	fail("soap_getResponse : Exception in soap response Service Issue");
		//	report.updateTestLog("soap_getResponse", "Exception in soap response Service Issue", Status.FAIL);
		    }
		} catch (Exception e1) {
		    e1.printStackTrace();
		}
	    }
	}
	try {
		Assert.assertTrue("Request" + autoPolicyDetailSOAPBindingStub._getCall().getMessageContext().getRequestMessage().getSOAPPartAsString(), true);
		
		Assert.assertTrue("Response" + autoPolicyDetailSOAPBindingStub._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString(),true);
		System.out.println("Soap Request " + styleMsg(autoPolicyDetailSOAPBindingStub._getCall().getMessageContext().getRequestMessage().getSOAPPartAsString(), "xml"));
		System.out.println("Soap Response " + styleMsg(autoPolicyDetailSOAPBindingStub._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString(), "xml"));
	   // report.updateTestLog("Soap Request ", styleMsg(autoPolicyDetailSOAPBindingStub._getCall().getMessageContext().getRequestMessage().getSOAPPartAsString(), "xml"),		    Status.DONE);
	   // report.updateTestLog("Soap Response ", styleMsg(autoPolicyDetailSOAPBindingStub._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString(), "xml"),		    Status.DONE);
	} catch (Exception e1) {
	    e1.printStackTrace();
	}
    }

    public void compareAutoPolicyDetailsResponse()
	    throws URISyntaxException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, MalformedURLException, UnsupportedEncodingException, JSONException {
//    String policyNumber = dataTable.getData(testParameter, "RetrievePolicyDetails", "PolicyNumber");
//	String sourceType = dataTable.getData(testParameter, "RetrievePolicyDetails", "DataSource");
//	String typeCd = dataTable.getData(testParameter, "RetrievePolicyDetails", "PolicyType");
    String policyNumber = "VASS900028913";
    String sourceType = "PAS";
    String typeCd = "PA";
    //String policyNumber = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "policyNumber");// "WVS2938148";
	//String sourceType = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "sourceType");// "SIS";
	//String typeCd = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "typeCd");// "PA";
	//report.updateTestLog("Test Data", "policyNumber:" + policyNumber + " sourceType:" + sourceType + " typeCd:" + typeCd, Status.DONE);
	soap_SetRequest(policyNumber, sourceType);
	soap_getResponse();
    }
    
    public String styleMsg(String msg, String option) {
		switch (option) {
			case "error" :
				msg = "<span style=\"font-size:12px; color:red;font-family:Courier New;\">" + msg + "</span>";
				break;
			case "xml" :
				msg = "<textarea style=\"font-size:11px; font-family:Courier New;\"rows=\"4\" cols=\"105\" readonly>" + msg + "</textarea>";
				break;

		}
		return msg;
	}
}
