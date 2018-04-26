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
import org.apache.http.client.methods.HttpPost;
import org.json.JSONException;

//import com.cognizant.framework.Status;
//import com.cognizant.supportlibraries.ReusableLibrary;
//import com.cognizant.supportlibraries.ScriptHelper;





import webservice.soap.retrievePolicyBillingSummaries.AAANCNU_Common_version2.com.aaancnuit.www.ApplicationContext;
import webservice.soap.retrievePolicyBillingSummaries.AAANCNU_Common_version2.com.aaancnuit.www.ErrorInfo;
import webservice.soap.retrievePolicyBillingSummaries.AAANCNU_RetrievePolicyBillingSummaries_version2.com.aaancnuit.www.PolicyProductSource;
import webservice.soap.retrievePolicyBillingSummaries.AAANCNU_WSDL_RetrievePolicyBillingSummaries_version2.com.aaancnuit.www.RetrievePolicyBillingSummariesProxy;
import webservice.soap.retrievePolicyBillingSummaries.AAANCNU_WSDL_RetrievePolicyBillingSummaries_version2.com.aaancnuit.www.RetrievePolicyBillingSummariesRequest;
import webservice.soap.retrievePolicyBillingSummaries.AAANCNU_WSDL_RetrievePolicyBillingSummaries_version2.com.aaancnuit.www.RetrievePolicyBillingSummariesResponse;
import webservice.soap.retrievePolicyBillingSummaries.AAANCNU_WSDL_RetrievePolicyBillingSummaries_version2.com.aaancnuit.www.RetrievePolicyBillingSummariesSOAPBindingStub;

public class RetrievePolicyBillingSummaries  {
    public boolean isRESTResponseValid = false;
    RetrievePolicyBillingSummariesRequest billingSummariesRequest;
    RetrievePolicyBillingSummariesProxy billingSummariesProxy;
    RetrievePolicyBillingSummariesResponse billingSummariesResponse;
    RetrievePolicyBillingSummariesSOAPBindingStub billingSummariesSOAPBindingStub;
    String soap_XMLAsString = null;
    String restAsXMLString = null;
    String restResponseString = null;
    HttpClient httpClient;
    URI uri;
    HttpPost post;
    HttpResponse response;

    // Declared PolicyNumber ,sourceType & productCode as global Variables
    // -Rajesh
/*
    String policyNumber = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "policyNumber");// "WVS2938148";
    String sourceType = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "sourceType");// "SIS";
    String productCode = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "productCode");// "PA";
    */
   /* String policyNumber = dataTable.getData(testParameter, "RetrievePolicyBillingDetails", "PolicyNumber");
	String sourceType = dataTable.getData(testParameter, "RetrievePolicyBillingDetails", "DataSource");
	String productCode = dataTable.getData(testParameter, "RetrievePolicyBillingDetails", "PolicyType");
	String autopay = "";
    String CorrelationId = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "correlationId");
    String UserId = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "userId");
    String TransactionType = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "transactionType");
    String Address = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "IP_Address");
    String Application = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "application");
    String subSystem = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "subSystem");
*/
    String policyNumber = "VASS900028913";
	String sourceType = "PAS";
	String productCode = "PA";
	String autopay = "";
    String CorrelationId = "";
    String UserId = "";
    String TransactionType = "";
    String Address = "";
    String Application = "";
    String subSystem = "";
    public RetrievePolicyBillingSummaries() {
	super();

	//testParameter.setCurrentTestDescription("PolicyNumber:" + policyNumber + "||SourceType:" + sourceType + "||ProductCode:" + productCode);// Changed
																		// Test
																		// Description
																		// From
																		// "testDescription(TransactionType,Application)"

    }

    public void soap_SetRequest(String policyNumber, String sourceSystem, String typeCd) {
	/*
	 * String policyNumber = "0355610";String sourceSystem ="HDES";String
	 * typeCd = "HO";String policyCount ="1";
	 */
	billingSummariesRequest = new RetrievePolicyBillingSummariesRequest();
	/*
	 * ApplicationContext applicationContext = new ApplicationContext();
	 * applicationContext.setTransactionType("INCL_ALL_ELIG");
	 * applicationContext.setAddress("127.0.0.1");
	 */

	ApplicationContext applicationContext = new ApplicationContext();
	applicationContext.setUserId(UserId);
	applicationContext.setTransactionType(TransactionType);
	applicationContext.setApplication(Application);
	applicationContext.setAddress(Address);
	applicationContext.setCorrelationId(CorrelationId);

	billingSummariesRequest.setApplicationContext(applicationContext);
	PolicyProductSource[] arrayPolicySource = new PolicyProductSource[1];
	PolicyProductSource policyProductSource = new PolicyProductSource();
	policyProductSource.setPolicyNumber(policyNumber);
	policyProductSource.setDataSource(sourceSystem);
	policyProductSource.setType(typeCd);
	arrayPolicySource[0] = policyProductSource;
	billingSummariesRequest.setPolicies(arrayPolicySource);
    }

    public void soap_getResponse() throws URISyntaxException, MalformedURLException {
//	String strEndPoint = dataTable.getCommonData_v2(properties.getProperty("Environment"), "SOAP_EndPointUrl");
	String strEndPoint = "https://soaqa3.tent.trt.csaa.pri/";
	//strEndPoint = strEndPoint + "RetrievePolicyBillingSummariesV2";
	strEndPoint = strEndPoint + "4.1/RetrievePolicyBillingSummaries";
	//String currentTestDescription = testParameter.getCurrentTestDescription();
	//testParameter.setCurrentTestDescription("<strong>Endpoint: </strong>/RetrievePolicyBillingSummariesV2<br>" + currentTestDescription);
	URL url = new URL(strEndPoint);
	URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
	url = uri.toURL();
	/*
	 * billingSummariesProxy = new RetrievePolicyBillingSummariesProxy();
	 * billingSummariesProxy.setEndpoint(strEndPoint+
	 * "RetrievePolicyBillingSummariesV2");
	 */
	try {
	   // report.updateTestLog("SOAP Endpoint", url.toString(), Status.DONE);
	    billingSummariesSOAPBindingStub = new RetrievePolicyBillingSummariesSOAPBindingStub(url, null);
	    billingSummariesResponse = billingSummariesSOAPBindingStub.retrievePolicyBillingSummaries(billingSummariesRequest);
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
		    String response = billingSummariesSOAPBindingStub._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString();
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
		Assert.assertTrue("Request" + billingSummariesSOAPBindingStub._getCall().getMessageContext().getRequestMessage().getSOAPPartAsString(), true);
		Assert.assertTrue("Response" + billingSummariesSOAPBindingStub._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString(),true);
		System.out.println("Soap Request " + styleMsg(billingSummariesSOAPBindingStub._getCall().getMessageContext().getRequestMessage().getSOAPPartAsString(), "xml"));
		System.out.println("Soap Response " + styleMsg(billingSummariesSOAPBindingStub._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString(), "xml"));
	   // report.updateTestLog("Soap Request ", styleMsg(billingSummariesSOAPBindingStub._getCall().getMessageContext().getRequestMessage().getSOAPPartAsString(), "xml"),		    Status.DONE);
	   // report.updateTestLog("Soap Response ", styleMsg(billingSummariesSOAPBindingStub._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString(), "xml"),		    Status.DONE);
	} catch (Exception e1) {
	    e1.printStackTrace();
	}
    }

    public void compareBillingSummarryResponse()
	    throws URISyntaxException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, MalformedURLException, UnsupportedEncodingException, JSONException {

	//report.updateTestLog("Test Data", "policyNumber:" + policyNumber + " sourceType:" + sourceType + " productCode:" + productCode, Status.DONE);
	soap_SetRequest(policyNumber, sourceType, productCode);
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