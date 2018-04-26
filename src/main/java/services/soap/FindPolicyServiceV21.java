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





import AAANCNU_Common_version2.com.aaancnuit.www.ApplicationContext;
import AAANCNU_Common_version2.com.aaancnuit.www.ErrorInfo;
import fpFNOLV2.AAANCNU_FindPolicy_version2.com.aaancnuit.www.PolicySearchCriteria;
import fpFNOLV2.AAANCNU_WSDL_FindPolicy_version2.com.aaancnuit.www.FindPolicyRequest;
import fpFNOLV2.AAANCNU_WSDL_FindPolicy_version2.com.aaancnuit.www.FindPolicySOAPBindingStub;

public class FindPolicyServiceV21  {

    FindPolicySOAPBindingStub findpolicyStub = null;
    FindPolicyRequest findPolicyRequest = null;

    HttpClient httpClient;
    URI uri;
    HttpGet get;
    HttpResponse response;

    String subSystem = "Sales";
    /*
    String CorrelationId = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "correlationId");
    String UserId = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "userId");
    String TransactionType = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "transactionType");
    String Address = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "IP_Address");
    String Application = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "application");
    String policyNumber = dataTable.getData(testParameter, "RetrievePolicyDetails", "PolicyNumber");
    */
    String CorrelationId ="";
    String UserId = "";
    String TransactionType = "";
    String Address = "";
    String Application = "";
    String policyNumber = "VASS202130324";
    
    //  String policyNumber = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "policyNumber");

    public FindPolicyServiceV21() {
	super();
	//testParameter.setCurrentTestDescription("PolicyNumber: " + policyNumber);// Changed
										 // Test
										 // Description
										 // From
										 // "testDescription(TransactionType,Application)"
    }

    public void soap_SetRequest(String policyNumber) {

	ApplicationContext applicationContext = new ApplicationContext();
	applicationContext.setUserId(UserId);
	applicationContext.setTransactionType(TransactionType);
	applicationContext.setApplication(Application);
	applicationContext.setSubSystem(subSystem);
	applicationContext.setAddress(Address);
	applicationContext.setCorrelationId(CorrelationId);
	findPolicyRequest = new FindPolicyRequest();
	PolicySearchCriteria searchCriteria = new PolicySearchCriteria();
	searchCriteria.setPolicyNumber(policyNumber);
	findPolicyRequest.setSearchCriteria(searchCriteria);

    }

    public void soap_getResponse() throws URISyntaxException, MalformedURLException {
	//String strEndPoint = dataTable.getCommonData_v2(properties.getProperty("Environment"), "SOAP_EndPointUrl");
    String strEndPoint = "https://soaqa3.tent.trt.csaa.pri/";
	strEndPoint = strEndPoint + "FindPolicyV2.1";
	//String currentTestDescription = testParameter.getCurrentTestDescription();
	//testParameter.setCurrentTestDescription("<strong>Endpoint: </strong>/FindPolicyV2.1<br>" + currentTestDescription);
	URL url = new URL(strEndPoint);
	URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
	url = uri.toURL();
	//report.updateTestLog("SOAP Endpoint", url.toString(), Status.DONE);
	try {
	    findpolicyStub = new FindPolicySOAPBindingStub(url, null);
	    findpolicyStub.findPolicy(findPolicyRequest);
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
	    	fail("soap_getResponse: Exception in soap response" +  errText );
		//report.updateTestLog("soap_getResponse", "Exception in soap response" + errText, Status.FAIL);
	    } else {
		try {
		    String response = findpolicyStub._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString();
		    if (!response.contains("errorCode>SOAB101<")) {
		    	fail("soap_getResponse : Exception in soap response Service Issue");
			//report.updateTestLog("soap_getResponse", "Exception in soap response Service Issue", Status.FAIL);
		    }
		} catch (Exception e1) {
		    e1.printStackTrace();
		}
	    }
	}
	try {
		Assert.assertTrue("Request" + findpolicyStub._getCall().getMessageContext().getRequestMessage().getSOAPPartAsString(), true);
		Assert.assertTrue("Response" + findpolicyStub._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString(),true);
		System.out.println("Soap Request " + styleMsg(findpolicyStub._getCall().getMessageContext().getRequestMessage().getSOAPPartAsString(), "xml"));
		System.out.println("Soap Response " + styleMsg(findpolicyStub._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString(), "xml"));
//	    report.updateTestLog("Soap Request ", styleMsg(findpolicyStub._getCall().getMessageContext().getRequestMessage().getSOAPPartAsString(), "xml"), Status.PASS);
//	    report.updateTestLog("Soap Response ", styleMsg(findpolicyStub._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString(), "xml"), Status.PASS);
	} catch (Exception e1) {
	    e1.printStackTrace();
	}
    }

    public void verifyFindPolicy()
	    throws URISyntaxException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, MalformedURLException, UnsupportedEncodingException, JSONException {

	//report.updateTestLog("Test Data", "policyNumber:" + policyNumber, Status.DONE);

	soap_SetRequest(policyNumber);
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
