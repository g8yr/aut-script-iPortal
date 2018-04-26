/**
 * 
 */
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
import AAANCNU_RetrievePolicySummaries_version2.com.aaancnuit.www.PolicySource;
import AAANCNU_WSDL_RetrievePolicySummaries_version2.com.aaancnuit.www.RetrievePolicySummariesRequest;
import AAANCNU_WSDL_RetrievePolicySummaries_version2.com.aaancnuit.www.RetrievePolicySummariesResponse;
import AAANCNU_WSDL_RetrievePolicySummaries_version2.com.aaancnuit.www.RetrievePolicySummariesSOAPBindingStub;

/**
 * @author Vinod Balavel
 *
 */
public class RetrievePolicySummaries  {

	public RetrievePolicySummaries() {
		super();
		// TODO Auto-generated constructor stub
	}

	RetrievePolicySummariesRequest request = null;
	RetrievePolicySummariesSOAPBindingStub stub = null;
	RetrievePolicySummariesResponse response1 = null;

	HttpClient httpClient;
	URI uri;
	HttpGet get;
	HttpResponse response;
/*
	 	String CorrelationId = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "correlationId");
	    String UserId = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "userId");
	    String TransactionType = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "transactionType");
	    String Address = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "IP_Address");
	    String Application = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "application");
	    String subSystem = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "subSystem");
	
	    String policyNumber = dataTable.getData(testParameter, "RetrievePolicyDetails", "PolicyNumber");
	    String sourceType = dataTable.getData(testParameter, "RetrievePolicyDetails", "DataSource");
	    String typeCd = dataTable.getData(testParameter, "RetrievePolicyDetails", "PolicyType");
	    */
	String CorrelationId = "";
    String UserId = "";
    String TransactionType = "";
    String Address = "";
    String Application = "AGNTPRTL";
    String subSystem = "";

    String policyNumber = "VASS900028986";
    String sourceType = "PAS";
    String typeCd = "PA";

	public void soap_SetRequest() {

		ApplicationContext applicationContext = new ApplicationContext();
		applicationContext.setUserId(UserId);
		applicationContext.setTransactionType(TransactionType);
		applicationContext.setApplication(Application);
		applicationContext.setSubSystem(subSystem);
		applicationContext.setAddress(Address);
		applicationContext.setCorrelationId(CorrelationId);
		request = new RetrievePolicySummariesRequest();
		request.setApplicationContext(applicationContext);
		PolicySource policys = new PolicySource();
		policys.setDataSource(sourceType);
		policys.setPolicyNumber(policyNumber);
		policys.setType(typeCd);
		PolicySource[] policy = new PolicySource[1];
		policy[0] = policys;
		request.setPolicy(policy);
	}

	public void soap_getResponse() throws URISyntaxException, MalformedURLException {
		String strEndPoint = "https://soaqa3.tent.trt.csaa.pri";
		strEndPoint = strEndPoint + "/RetrievePolicySummariesV2";
		//String currentTestDescription = testParameter.getCurrentTestDescription();
		// testParameter.setCurrentTestDescription("<strong>Endpoint:
		// </strong>/RetrievePolicySummariesV2<br>" + currentTestDescription);
		URL url = new URL(strEndPoint);
		URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
		url = uri.toURL();
		try {
			//report.updateTestLog("SOAP Endpoint", url.toString(), Status.DONE);
			stub = new RetrievePolicySummariesSOAPBindingStub(url, null);
			response1 = stub.retrievePolicySummaries(request);

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
//			report.updateTestLog("Soap Request ", styleMsg(stub._getCall().getMessageContext().getRequestMessage().getSOAPPartAsString(), "xml"), Status.PASS);
//			report.updateTestLog("Soap Response ", styleMsg(stub._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString(), "xml"), Status.PASS);
//			report.updateTestLog("Soap Response ", "eValue status is retrived successfully" + response1.getPolicySummary(1).getPolicy().geteValueInfo().getenrollmentStatus(),
//					Status.PASS);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void compareRetrievePolicySummariesV2()
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
