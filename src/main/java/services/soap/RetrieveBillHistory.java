package services.soap;

import static org.testng.Assert.fail;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.rmi.RemoteException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.XMLUnit;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;





import businesscomponents.iportal.WebserviceComponents;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import webservice.rest.retrieveBillingHistory.AccountTransaction;
import webservice.rest.retrieveBillingHistory.RetrieveBillingHistory;
import webservice.soap.retrieveBillingHistory.AAANCNU_Common_version2.com.aaancnuit.www.ApplicationContext;
import webservice.soap.retrieveBillingHistory.AAANCNU_Common_version2.com.aaancnuit.www.ErrorInfo;
import webservice.soap.retrieveBillingHistory.AAANCNU_RetrievePolicyBillingHistory_version2.com.aaancnuit.www.AccountTransactions;
import webservice.soap.retrieveBillingHistory.AAANCNU_RetrievePolicyBillingHistory_version2.com.aaancnuit.www.PolicySource;
import webservice.soap.retrieveBillingHistory.AAANCNU_WSDL_RetrievePolicyBillingHistory_version2.com.aaancnuit.www.RetrievePolicyBillingHistoryProxy;
import webservice.soap.retrieveBillingHistory.AAANCNU_WSDL_RetrievePolicyBillingHistory_version2.com.aaancnuit.www.RetrievePolicyBillingHistoryRequest;
import webservice.soap.retrieveBillingHistory.AAANCNU_WSDL_RetrievePolicyBillingHistory_version2.com.aaancnuit.www.RetrievePolicyBillingHistoryResponse;
import webservice.soap.retrieveBillingHistory.AAANCNU_WSDL_RetrievePolicyBillingHistory_version2.com.aaancnuit.www.RetrievePolicyBillingHistorySOAPBindingStub;
import services.rest.apiclients.RestApiWraper;

public class RetrieveBillHistory  {
	public boolean isRESTResponseValid = false;
	RetrievePolicyBillingHistoryRequest policyhistoryrequest = null;
	RetrievePolicyBillingHistoryProxy policyhistoryproxy = null;
	RetrievePolicyBillingHistoryResponse policyhistoryresponse = null;
	RetrievePolicyBillingHistorySOAPBindingStub billingHistorySOAPBindingStub = null;
	String soap_XMLAsString = null;
	String restAsXMLString = null;
	String restResponseString = null;
	HttpClient httpClient;
	URI uri;
	HttpGet get;
	HttpResponse response;

	String UserId = "eczarog";
	String TransactionType = "REALTIME";
	String Application = "MRM_IG";
	String Address = "10.37.128.170";
	String CorrelationId = "eczarog";

	public void soap_SetRequest(String policyNumber, String sourceSystem, String[] transactiontype) {

		ApplicationContext applicationContext = new ApplicationContext();
		applicationContext.setUserId(UserId);
		applicationContext.setTransactionType(TransactionType);
		applicationContext.setApplication(Application);
		applicationContext.setAddress(Address);
		applicationContext.setCorrelationId(CorrelationId);

		BigInteger priorTermCount = new BigInteger("1");
		policyhistoryrequest = new RetrievePolicyBillingHistoryRequest();
		PolicySource policySource = new PolicySource();
		policySource.setPolicyNumber(policyNumber);
		policySource.setDataSource(sourceSystem);
		policyhistoryrequest.setPolicy(policySource);
		policyhistoryrequest.setPriorTermCount(priorTermCount);
		policyhistoryrequest.setTransactionType(transactiontype);
		policyhistoryrequest.setApplicationContext(applicationContext);
	}

	public void soap_getResponse() throws URISyntaxException, MalformedURLException {

		String strEndPoint = "";

//		if (properties.getProperty("dynamicUrl").equalsIgnoreCase("true")) {
//			strEndPoint = properties.getProperty("ApplicationURL");
//			} else {
			strEndPoint = "https://soaqa3.tent.trt.csaa.pri/";
//		}
	
//		String strEndPoint = "http://soaqa3.tent.trt.csaa.pri/";
		
		strEndPoint = strEndPoint + "RetrievePolicyBillingHistoryV2";
		System.out.println("Endpoint " +strEndPoint);
		URL url = new URL(strEndPoint);
		URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
		url = uri.toURL();
		try {
		//	report.updateTestLog("SOAP Endpoint", url.toString(), Status.DONE);
			billingHistorySOAPBindingStub = new RetrievePolicyBillingHistorySOAPBindingStub(url, null);
			policyhistoryresponse = billingHistorySOAPBindingStub.retrievePolicyBillingHistory(policyhistoryrequest);
			setSoap_XMLAsString(billingHistorySOAPBindingStub._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString());
		} catch (ErrorInfo e) {
			String errCode = e.getErrorCode();
			String errText = e.getErrorMessageText();
			String errMessage = e.getMessage();
		//	report.updateTestLog("soap_getResponse", styleMsg("Error Code: " + errCode + "</br>" + errText + "</br>" + errMessage, "error"), Status.FAIL);
		//	report.updateTestLog("soap_getResponse", "Exception in soap response" + e.getMessage(), Status.FAIL);
		} catch (RemoteException e) {
		//	report.updateTestLog("soap_getResponse", "Exception in soap response" + e.getMessage(), Status.FAIL);
		}
		try {
			System.out.println("SOAP Request - "+billingHistorySOAPBindingStub._getCall().getMessageContext().getRequestMessage().getSOAPPartAsString());
			System.out.println("SOAP Response - "+billingHistorySOAPBindingStub._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString());
		//	report.updateTestLog("Soap Request ", styleMsg(billingHistorySOAPBindingStub._getCall().getMessageContext().getRequestMessage().getSOAPPartAsString(), "xml"),
		//			Status.DONE);
		//	report.updateTestLog("Soap Response ", styleMsg(billingHistorySOAPBindingStub._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString(), "xml"),
		//			Status.DONE);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void rest_getResponse(String policyNumber, String sourceSystem, String transactiontype) throws URISyntaxException, NoSuchAlgorithmException, KeyStoreException,
			KeyManagementException, UnsupportedEncodingException {
		SSLContextBuilder builder = new SSLContextBuilder();
		builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build(), SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create().register("http", new PlainConnectionSocketFactory())
				.register("https", sslsf).build();
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
		cm.setMaxTotal(2000);
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).setConnectionManager(cm).build();
		String scheme = "https";
		String setHost = "soaqa3.tent.trt.csaa.pri:2073";
		String setPath = "/ent/%s/csaa/v1/policies/billing/history";
		String setPriortermcount = "1";
		String setHeadername = "Authorization";
		String auth_token =WebserviceComponents.token;
		String setHeadervalue = "Bearer "+auth_token;
		String mrmPath = "soaqa3";
       /*
		if(properties.getProperty("Environment").equalsIgnoreCase("qa")||properties.getProperty("Environment").equalsIgnoreCase("qa2")||properties.getProperty("Environment").equalsIgnoreCase("qa4")){
        	if(mrmPath.equals("") || mrmPath.isEmpty())
        		setPath = String.format(setPath, "qa");
			else
				setPath = String.format(setPath, mrmPath);
        }else if(properties.getProperty("Environment").equalsIgnoreCase("perf")||properties.getProperty("Environment").equalsIgnoreCase("demo")||properties.getProperty("Environment").equalsIgnoreCase("brkfix")){
        	if(mrmPath.equals("") || mrmPath.isEmpty())
        		setPath = String.format(setPath, "perf");
			else
				setPath = String.format(setPath, mrmPath);
        }
        else
        */
        	setPath = String.format(setPath, mrmPath);
		uri = new URIBuilder().setScheme(scheme).setHost(setHost).setPath(setPath).setParameter("policyNumber", policyNumber).setParameter("sourceSystem", sourceSystem)
				.setParameter("priorTermCount", setPriortermcount).setParameter("transactionType", transactiontype).build();
		String decoded = URLDecoder.decode(uri.toString(), "UTF-8");
		URI newuri = new URI(decoded);
		get = new HttpGet(newuri);
		get.addHeader(setHeadername, setHeadervalue);
		get.addHeader("X-ApplicationContext", "{\"userId\":\"" + UserId + "\",\"transactionType\":\"" + TransactionType + "\",\"application\":\"" + Application
				+ "\",\"address\":\"" + Address + "\",\"correlationId\":\"" + CorrelationId + "\"}");

		Header[] allHeaders = get.getAllHeaders();
		String headerMsg = uri.toString();
		for (Header header : allHeaders) {
			headerMsg = headerMsg + "\n" + header;
		}
		System.out.println("REST Request " +headerMsg);
	//	report.updateTestLog("REST Request", styleMsg(headerMsg, "xml"), Status.DONE);
		try {
			response = httpClient.execute(get);
		} catch (ClientProtocolException e) {
		//	report.updateTestLog("rest_getResponse", "Exception in rest response", Status.FAIL);
		} catch (IOException e) {
		//	report.updateTestLog("rest_getResponse", "Exception in rest response", Status.FAIL);
		}
		isRESTResponseValid = (new RestApiWraper()).validateResponse(response, "Issue direct Payment");
		if (!isRESTResponseValid) {
			try {
				String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
		//		report.updateTestLog("http response verification", styleMsg(responseString, "error"), Status.FAIL);
				fail("http response verification - error");
			} catch (org.apache.http.ParseException | IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void convertJsonToXML() throws JSONException {
		HttpEntity entity = response.getEntity();
		String responseString = null;
		try {
			responseString = EntityUtils.toString(entity, "UTF-8");
			setRestResponseString(responseString);
		} catch (org.apache.http.ParseException | IOException e) {
			// TODO Auto-generated catch block
		//	report.updateTestLog("Json to XML convertion", "Problem in converting Json to XML", Status.FAIL);
		}
		JSONObject json = new JSONObject(responseString);
		String xml = XML.toString(json);
		setRestAsXMLString(xml);
	}
	public String getSoap_XMLAsString() {
		return soap_XMLAsString;
	}

	public void setSoap_XMLAsString(String soap_XMLAsString) {
		this.soap_XMLAsString = soap_XMLAsString;
	}

	public String getRestAsXMLString() {
		return restAsXMLString;
	}

	public void setRestAsXMLString(String restAsXMLString) {
		this.restAsXMLString = restAsXMLString;
	}

	public String getRestResponseString() {
		return restResponseString;
	}

	public void setRestResponseString(String restResponseString) {
	//	report.updateTestLog("REST Response", styleMsg(restResponseString, "xml"), Status.DONE);
		this.restResponseString = restResponseString;
	}

	public void compareBillingHistoryResponse() throws URISyntaxException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, MalformedURLException,
			UnsupportedEncodingException, JSONException {
//		String policyNumber = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "policyNumber");// "CTS3183646";
//		String sourceType = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "sourceType");// "SIS";
//		String transactionType = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "transactionType");// "BIL,PAY,ADJ";

		//Code updated for Digital Service
		String policyNumber = "VASS900028949";
		String sourceType = "PAS";
		String transactionType = "BIL,PAY,ADJ";
						
	//	report.updateTestLog("Test Data", "policyNumber:" + policyNumber + " sourceType:" + sourceType + " transactionType:" + transactionType, Status.DONE);

		String[] tempArray = transactionType.split(",");
		String[] arraytransactionType = {tempArray[0], tempArray[1], tempArray[2]};
		//soap_SetRequest(policyNumber, sourceType, arraytransactionType);
		//error soap_getResponse();
		rest_getResponse(policyNumber, sourceType, transactionType);
		convertJsonToXML();
		/*List<?> difference = null;
		try {
			difference = compareXML(getSoap_XMLAsString(), getRestAsXMLString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			report.updateTestLog("Compare XML", "Exception in comparing xml", Status.FAIL);
		}
		int diffCount = difference.size();
		if (diffCount == 0) {
			report.updateTestLog("Webservice Validation", "Rest and Soap response are equal", Status.PASS);
		} else {
			for (int index = 0; index < diffCount; index++) {
				report.updateTestLog("Webservice Validation", "[Expected: SOAP][Actual: REST] " + difference.get(index), Status.FAIL);
			}
		}*/
	}

	public List<?> compareXML(String expectedXML, String actualXML) throws Exception {
		XMLUnit.setIgnoreWhitespace(true);
		XMLUnit.setIgnoreAttributeOrder(true);
		// System.out.println("*********
		// System.out.println("*********
		expectedXML = expectedXML.replaceAll("aa_1:", "").replaceAll("aa:", "").replaceAll("out2:", "").replaceAll("out3:", "").replaceAll("out:", "");
		expectedXML = "<retrievePolicyBillingHistoryResponse>" + expectedXML.substring(expectedXML.indexOf("<policyNumber>"), expectedXML.indexOf("</soapenv:Body>"));
		// expectedXML =
		// expectedXML.substring(expectedXML.indexOf("<soapenv:Body>"),
		// expectedXML.indexOf("</soapenv:Body>"));
		DetailedDiff diff = new DetailedDiff(XMLUnit.compareXML(expectedXML, actualXML));
		List<?> allDifferences = diff.getAllDifferences();
		return allDifferences;
	}

	public void soap_getSampleResponse() throws MalformedURLException, URISyntaxException {
		String policyNumber = "CTS3183646";
		String sourceType = "SIS";
		String[] transType = {"BIL", "PAY", "ADJ"};
		soap_SetRequest(policyNumber, sourceType, transType);
		soap_getResponse();
		String billingHistoryResponse = policyhistoryresponse.getPolicyNumber();
		// System.out.println("*********
		AccountTransactions billingHistory = policyhistoryresponse.getAccountTransactions();
		int size = billingHistory.getAccountTransaction().length;
		// System.out.println("*********
		for (int i = 0; i < size; i++) {
			// System.out.println("*********
			// System.out.println("*********
			// System.out.println("*********
			i++;
			// System.out.println("*********

		}
	}

	public void rest_getSampleResponse() throws JsonParseException, JsonMappingException, UnsupportedOperationException, IOException, KeyManagementException,
			NoSuchAlgorithmException, KeyStoreException, URISyntaxException {
		String policyNumber = "CTS3183646";
		String sourceType = "SIS";
		String transType = "BIL,PAY,ADJ";
		rest_getResponse(policyNumber, sourceType, transType);
		ObjectMapper obj = new ObjectMapper();
		RetrieveBillingHistory res = obj.readValue(new InputStreamReader(response.getEntity().getContent()), RetrieveBillingHistory.class);// /*new
																																			// FileReader(new
																																			// File("D:/Nirmal/MRM/workspace/json2pojogen/src/main/resources/schema/RetrievePolicyDetails.json"))*/),
																																			// RetrievePolicyDetails.class);
		webservice.rest.retrieveBillingHistory.RetrievePolicyBillingHistoryResponse resDetails = res.getRetrievePolicyBillingHistoryResponse();
		// System.out.println("*********
		webservice.rest.retrieveBillingHistory.AccountTransactions accounttransactions = resDetails.getAccountTransactions();
		List<AccountTransaction> acctxn = accounttransactions.getAccountTransaction();
		int numoftxn = acctxn.size();
		for (int i = 0; i < numoftxn; i++) {
			// System.out.println("*********
			// System.out.println("*********
			// System.out.println("*********
			i = i + 1;// added to retriev the Next[BILL] Transaction
			// System.out.println("*********
			// System.out.println("*********
			// System.out.println("*********
			// System.out.println("*********

		}
	}

	public webservice.rest.retrieveBillingHistory.RetrievePolicyBillingHistoryResponse getResponse() throws JsonParseException, JsonMappingException,
			UnsupportedOperationException, IOException {
		ObjectMapper objMapper = new ObjectMapper();
		RetrieveBillingHistory res = objMapper.readValue(new InputStreamReader(response.getEntity().getContent()), RetrieveBillingHistory.class);
		webservice.rest.retrieveBillingHistory.RetrievePolicyBillingHistoryResponse resDetails = res.getRetrievePolicyBillingHistoryResponse();
		return resDetails;

	}

}
