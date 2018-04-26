/**
 * 
 */
package services.soap;

import static org.testng.Assert.fail;

import java.net.URI;
import java.net.URL;
import java.rmi.RemoteException;

import junit.framework.Assert;
import rcdv2.AAANCNU_Common_version2.com.aaancnuit.www.ApplicationContext;
import rcdv2.AAANCNU_Common_version2.com.aaancnuit.www.ErrorInfo;
import rcdv2.AAANCNU_WSDL_RetrieveCustomerDetails_version2.com.aaancnuit.www.RetrieveCustomerDetailsRequest;
import rcdv2.AAANCNU_WSDL_RetrieveCustomerDetails_version2.com.aaancnuit.www.RetrieveCustomerDetailsResponse;
import rcdv2.AAANCNU_WSDL_RetrieveCustomerDetails_version2.com.aaancnuit.www.RetrieveCustomerDetailsSOAPBindingStub;

//import com.cognizant.framework.Status;
//import com.cognizant.supportlibraries.ReusableLibrary;
//import com.cognizant.supportlibraries.ScriptHelper;

/**
 * @author Vinod Balavel
 *
 */
public class RetrieveCustomerDetailsV2point2   {

    RetrieveCustomerDetailsRequest retrieveCustomerDetailsRequest = null;
    RetrieveCustomerDetailsResponse retrieveCustomerDetailsResponse = null;
    RetrieveCustomerDetailsSOAPBindingStub RetrieveCustomerDetailsStub = null;
/*
    String correlationId = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "correlationId");
    String userId = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "userId");
    String transactionType = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "transactionType");
    String address = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "IP_Address");
    String application = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "application");

    String subSystem = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "subSystem");// "Auto"
    String enterpriseCustomerId = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "enterpriseCustomerId");// "1196730763000"
*/
    String CorrelationId = "B2b_12345";
    String UserId = "";
    String TransactionType = "test";
    String Address = "";
    String application = "IEWMS";
    String subSystem = "DA";
    String enterpriseCustomerId = "1289900593361";
    public RetrieveCustomerDetailsV2point2() {
	super();
	// TODO Auto-generated constructor stub
    }

    public void soap_SetRequest() {
	try {
	    /*retrieveCustomerDetailsRequest = new RetrieveCustomerDetailsRequest();
	    ApplicationContext applicationContext = new ApplicationContext();

	    applicationContext.setUserId(userId);
	    applicationContext.setTransactionType(transactionType);
	    applicationContext.setApplication(application);
	    applicationContext.setSubSystem(subSystem);
	    applicationContext.setAddress(address);
	    applicationContext.setCorrelationId(correlationId);

	    retrieveCustomerDetailsRequest.setApplicationContext(applicationContext);
	    retrieveCustomerDetailsRequest.setEnterpriseCustomerID(enterpriseCustomerId);*/
		
		retrieveCustomerDetailsRequest = new RetrieveCustomerDetailsRequest();
		
		ApplicationContext applicationContext = new ApplicationContext();
		applicationContext.setApplication(application);
		retrieveCustomerDetailsRequest.setApplicationContext(applicationContext);
		retrieveCustomerDetailsRequest.setEnterpriseCustomerID(enterpriseCustomerId);

	    // enterpriseCustomerID
	    // .setEnterpriseCustomerId(enterpriseCustomerId);
	    // retrieveCustomerDetailsRequest.setEnterpriseCustomerID(enterpriseCustomerID);

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void soap_getResponse() throws Exception {
//	String strEndPoint = dataTable.getCommonData_v2(properties.getProperty("Environment"), "SOAP_EndPointUrl");
    String strEndPoint = "https://soaqa3.tent.trt.csaa.pri/";
	//String currentTestDescription = testParameter.getCurrentTestDescription();
	//testParameter.setCurrentTestDescription("<strong>Endpoint: </strong>/2.2/RetrieveCustomerDetails<br>" + currentTestDescription);
	strEndPoint = strEndPoint + "2.2/RetrieveCustomerDetails";
	URL url = new URL(strEndPoint);
	URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
	url = uri.toURL();
	//report.updateTestLog("SOAP Endpoint", url.toString(), Status.DONE);
	try {
	    RetrieveCustomerDetailsStub = new RetrieveCustomerDetailsSOAPBindingStub(url, null);
	    retrieveCustomerDetailsResponse = RetrieveCustomerDetailsStub.retrieveCustomerDetails(retrieveCustomerDetailsRequest);
	} catch (ErrorInfo e) {
	    String errCode = e.getErrorCode();
	    String errText = e.getErrorMessageText();
	    String errMessage = e.getMessage();
	    fail("Error Code: " + errCode + "</br>" + errText + "</br>" + errMessage);
	   // report.updateTestLog("soap_getResponse", styleMsg("Error Code: " + errCode + "</br>" + errText + "</br>" + errMessage, "error"), Status.FAIL);
	   // report.updateTestLog("soap_getResponse", "Exception in soap response" + e.getMessage(), Status.FAIL);
	}

	catch (RemoteException e) {
	    String errText = e.getMessage();
	    if (errText != null) {
	    	fail("soap_getResponse: Exception in soap response" +  errText );
		//report.updateTestLog("soap_getResponse", "Exception in soap response" + errText, Status.FAIL);
	    } else {
		try {
		    String response = RetrieveCustomerDetailsStub._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString();
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
		Assert.assertTrue("Request" + RetrieveCustomerDetailsStub._getCall().getMessageContext().getRequestMessage().getSOAPPartAsString(), true);
		Assert.assertTrue("Response" + RetrieveCustomerDetailsStub._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString(),true);
		System.out.println("Soap Request " + styleMsg(RetrieveCustomerDetailsStub._getCall().getMessageContext().getRequestMessage().getSOAPPartAsString(), "xml"));
		System.out.println("Soap Response " + styleMsg(RetrieveCustomerDetailsStub._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString(), "xml"));
//	    report.updateTestLog("Soap Request ", styleMsg(RetrieveCustomerDetailsStub._getCall().getMessageContext().getRequestMessage().getSOAPPartAsString(), "xml"),
//		    Status.DONE);
//	    report.updateTestLog("Soap Response ", styleMsg(RetrieveCustomerDetailsStub._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString(), "xml"),
//		    Status.DONE);
	} catch (Exception e1) {
	    e1.printStackTrace();
//	    report.updateTestLog("Soap Response ", styleMsg("Error while trying to report. request and response", "error"), Status.DONE);
//	    report.updateTestLog("Soap Response ", styleMsg(e1.getLocalizedMessage(), "xml"), Status.DONE);
	}
    }

    public void verifyRetrieveCustomerDetailsV2point2() throws Exception {
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
