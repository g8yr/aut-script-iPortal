
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

import com.Cucumber.supportLibraries.custom.ReadCSVFileFunctions;

import AAANCNU_Common_version2.com.aaancnuit.www.ApplicationContext;
import AAANCNU_Common_version2.com.aaancnuit.www.ErrorInfo;
import AAANCNU_RecordWallet_version2.com.aaancnuit.www.FinacialAccountRefs;
import AAANCNU_RecordWallet_version2.com.aaancnuit.www.FinancialAccountRef;
import AAANCNU_RecordWallet_version2.com.aaancnuit.www.Wallet;
import AAANCNU_WSDL_RecordWallet_version2.com.aaancnuit.www.RecordWalletRequest;
import AAANCNU_WSDL_RecordWallet_version2.com.aaancnuit.www.RecordWalletSOAPBindingStub;



public class RecordWalletV2  {

    RecordWalletSOAPBindingStub stub = null;
    static RecordWalletRequest request = null;

    HttpClient httpClient;
    URI uri;
    HttpGet get;
    HttpResponse response;
    ReadCSVFileFunctions readCSVfilefunctions = new ReadCSVFileFunctions();
	

    String CorrelationId = "eczarog";
    String UserId ="test";
    String TransactionType = "online";
    String Address = "10.37.128.170";
    String Application = "EZPAY";
  

    public RecordWalletV2() {
	
    }

    public void soap_SetRequest(String Iteration) {
    	/*
    	readCSVfilefunctions.readCsvFile(StaticValue.PolicyNumber, Iteration );
    	//policynumber = readCSVfilefunctions.SingleVehicleData.get(StaticValue.PolicyNumber);
    	policynumber = GenFns.ReadDataFromHashMap(currentTestParameters.getScenarioName(), StaticValue.PolicyNumber);
    	String PolicyDate = readCSVfilefunctions.readCsvFile(StaticValue.PolicyDate, Iteration );
    	readCSVfilefunctions.readCsvFile(StaticValue.VehiclePurchaseDate, Iteration );
    	readCSVfilefunctions.readCsvFile(StaticValue.VehicleVIN, Iteration);
*/
	String transactionType = "online";
	String ownerID = "Q2AZTESTAZS876";
	String paymentAccountToken = "DAAF18B1-B5AC-5426-FF17-B983891F9FB8";
	String shortName = "VISA LOGO";
	String isPreferred = "false";
	String subSystem ="B2C";

	ApplicationContext applicationContext = new ApplicationContext();
	applicationContext.setUserId(UserId);
	applicationContext.setTransactionType(TransactionType);
	applicationContext.setApplication(Application);
	applicationContext.setSubSystem(subSystem);
	applicationContext.setAddress(Address);
	applicationContext.setCorrelationId(CorrelationId);
	request = new RecordWalletRequest();
	request.setApplicationContext(applicationContext);
	request.setTransactionType(transactionType);
	;
	Wallet wallet = new Wallet();
	wallet.setOwnerID(ownerID);
	FinacialAccountRefs accounts = new FinacialAccountRefs();
	FinancialAccountRef account = new FinancialAccountRef();
	account.setPaymentAccountToken(paymentAccountToken);
	account.setShortName(shortName);
	FinancialAccountRef[] accountarr = new FinancialAccountRef[1];
	accountarr[0] = account;
	account.setIsPreferred(Boolean.valueOf(isPreferred));
	accounts.setPaymentAccount(accountarr);

	wallet.setAccounts(accounts);
	request.setWallet(wallet);

    }

    public void soap_getResponse() throws URISyntaxException, MalformedURLException {
	//String strEndPoint = dataTable.getCommonData_v2(properties.getProperty("Environment"), "SOAP_EndPointUrl");
    	String strEndPoint="http://sit-soaservices.tent.trt.csaa.pri:42000/" ;
	strEndPoint = strEndPoint + "RecordWalletV2";
	URL url = new URL(strEndPoint);
	URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
	url = uri.toURL();
	try {
	  System.out.println("SOAP Endpoint" +url.toString());
	    stub = new RecordWalletSOAPBindingStub(url, null);
	    stub.recordWallet(request);
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
		System.out.println("SOAP Request - "+stub._getCall().getMessageContext().getRequestMessage().getSOAPPartAsString());
		System.out.println("SOAP Request - "+stub._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString());
		Assert.assertTrue("Request" + stub._getCall().getMessageContext().getRequestMessage().getSOAPPartAsString(), true);
		Assert.assertTrue("Response" + stub._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString(),true);
	//	report.updateTestLog("Soap Request ", styleMsg(stub._getCall().getMessageContext().getRequestMessage().getSOAPPartAsString(), "xml"), Status.PASS);
	//    report.updateTestLog("Soap Response ", styleMsg(stub._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString(), "xml"), Status.PASS);
	} catch (Exception e1) {
	    e1.printStackTrace();
	}
    }
   

    public void setRecordWalletV2(String Iteration)
	    throws URISyntaxException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, MalformedURLException, UnsupportedEncodingException, JSONException {

	soap_SetRequest(Iteration);
	
    }
    public void callRecordWalletV2()
    	    throws URISyntaxException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, MalformedURLException, UnsupportedEncodingException, JSONException {

    	soap_getResponse();
    	
        }
    
}
