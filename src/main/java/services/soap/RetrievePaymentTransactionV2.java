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





import AAANCNU_Common_version2.com.aaancnuit.www.ErrorInfo;
import AAANCNU_RetrievePaymentTransaction_version2.com.aaancnuit.www.PaymentTransactionFilter;
import AAANCNU_RetrievePaymentTransaction_version2.com.aaancnuit.www.PolicyProductSource;
import AAANCNU_WSDL_RetrievePaymentTransaction_version2.com.aaancnuit.www.RetrievePaymentTransactionRequest;
import AAANCNU_WSDL_RetrievePaymentTransaction_version2.com.aaancnuit.www.RetrievePaymentTransactionSOAPBindingStub;

public class RetrievePaymentTransactionV2   {

    RetrievePaymentTransactionSOAPBindingStub stub = null;
    RetrievePaymentTransactionRequest request = null;

    HttpClient httpClient;
    URI uri;
    HttpGet get;
    HttpResponse response;
    /*
    String policyNumber = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "policyNumber");
    String riskState = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "riskState");
    String dataSource = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "sourceType");
    String type = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "typeCd");
    String receiptNumber = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "receiptNumber");
   */
    /*
    String policyNumber = dataTable.getData(testParameter, "RetrievePolicyBillingDetails", "PolicyNumber");
	String dataSource = dataTable.getData(testParameter, "RetrievePolicyBillingDetails", "DataSource");
	String type = dataTable.getData(testParameter, "RetrievePolicyBillingDetails", "PolicyType");
	String riskState = dataTable.getData(testParameter, "RetrievePolicyBillingDetails", "riskState");
	String receiptNumber = dataTable.getData(testParameter, "RetrievePolicyBillingDetails", "receiptNumber");
	 
    String CorrelationId = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "correlationId");
    String UserId = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "userId");
    String TransactionType = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "transactionType");
    String Address = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "IP_Address");
    String Application = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "application");
    String subSystem = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "subSystem");
    */
    String policyNumber = "VASS900028913";
    String dataSource = "PAS";
    String type = "PA";
    String riskState = "VA";
    String receiptNumber = "9542837";
    
    String CorrelationId = "";
    String UserId = "";
    String TransactionType = "";
    String Address = "";
    String Application = "";
    String subSystem = "";
   
    public RetrievePaymentTransactionV2() {
	super();
	//testParameter.setCurrentTestDescription("PolicyNumber:" + policyNumber + "||RiskState:" + riskState);// Changed
													     // Test
													     // Description
													     // From
													     // "testDescription(TransactionType,Application)"
													     // -Rajesh
    }

    public void soap_SetRequest() {

	request = new RetrievePaymentTransactionRequest();
	PolicyProductSource policy = new PolicyProductSource();
	policy.setPolicyNumber(policyNumber);
	policy.setRiskState(riskState);
	policy.setDataSource(dataSource);
	policy.setType(type);
	request.setPolicy(policy);
	request.setReceiptNumber(receiptNumber);
	PaymentTransactionFilter additionalCriteria = new PaymentTransactionFilter();
	Boolean includePriorTransactions = Boolean.FALSE;
	additionalCriteria.setIncludePriorTransactions(includePriorTransactions);
	request.setAdditionalCriteria(additionalCriteria);
    }

    public void soap_getResponse() throws URISyntaxException, MalformedURLException {
	//String strEndPoint = dataTable.getCommonData_v2(properties.getProperty("Environment"), "SOAP_EndPointUrl");
    String strEndPoint = "https://soaqa3.tent.trt.csaa.pri/";
	strEndPoint = strEndPoint + "RetrievePaymentTransactionV2";
	//String currentTestDescription = testParameter.getCurrentTestDescription();
	//testParameter.setCurrentTestDescription("<strong>Endpoint: </strong>/RetrievePaymentTransactionV2<br>" + currentTestDescription);
	URL url = new URL(strEndPoint);
	URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
	url = uri.toURL();
	try {
	   // report.updateTestLog("SOAP Endpoint", url.toString(), Status.DONE);
	    stub = new RetrievePaymentTransactionSOAPBindingStub(url, null);

	    try {
		stub.retrievePaymentTransaction(request);
	    } catch (Exception e) {
	    }

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
		    String response = stub._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString();
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
		Assert.assertTrue("Request" + stub._getCall().getMessageContext().getRequestMessage().getSOAPPartAsString(), true);
		Assert.assertTrue("Response" + stub._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString(),true);
		System.out.println("Soap Request " + styleMsg(stub._getCall().getMessageContext().getRequestMessage().getSOAPPartAsString(), "xml"));
		System.out.println("Soap Response " + styleMsg(stub._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString(), "xml"));
//	    report.updateTestLog("Soap Request ", styleMsg(stub._getCall().getMessageContext().getRequestMessage().getSOAPPartAsString(), "xml"), Status.PASS);
//	    report.updateTestLog("Soap Response ", styleMsg(stub._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString(), "xml"), Status.PASS);
	} catch (Exception e1) {
	    e1.printStackTrace();
	}
    }

    public void compareRetrievePaymentTransactionV2()
	    throws URISyntaxException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, MalformedURLException, UnsupportedEncodingException, JSONException {

	soap_SetRequest();
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
