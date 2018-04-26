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

import webservice.rest.retrievePolicyTransactionDetails.RetrievePolicyTransactionsDetails;
import webservice.rest.retrievePolicyTransactionDetails.RetrievePolicyTransactionsResponse;
import webservice.rest.retrievePolicyTransactionDetails.Transaction;
import webservice.rest.retrievePolicyTransactionDetails.Transactions;
import webservice.soap.retrieveBillingHistory.AAANCNU_Common_version2.com.aaancnuit.www.ErrorInfo;
import webservice.soap.retrievePolicyTransactionDetails.AAANCNU_Common_version2.com.aaancnuit.www.ApplicationContext;
import webservice.soap.retrievePolicyTransactionDetails.AAANCNU_WSDL_RetrievePolicyTransactions_version2.com.aaancnuit.www.RetrievePolicyTransactionsProxy;
import webservice.soap.retrievePolicyTransactionDetails.AAANCNU_WSDL_RetrievePolicyTransactions_version2.com.aaancnuit.www.RetrievePolicyTransactionsRequest;
import webservice.soap.retrievePolicyTransactionDetails.AAANCNU_WSDL_RetrievePolicyTransactions_version2.com.aaancnuit.www.RetrievePolicyTransactionsSOAPBindingStub;
import services.rest.apiclients.RestApiWraper;

public class RetrievePolicyTransactionDetail {

	RetrievePolicyTransactionsRequest policyTransactionsRequest =null; 
	RetrievePolicyTransactionsProxy policyTransactionsProxy =null;
	webservice.soap.retrievePolicyTransactionDetails.AAANCNU_WSDL_RetrievePolicyTransactions_version2.com.aaancnuit.www.RetrievePolicyTransactionsResponse policyTransactionsResponse =null;
	RetrievePolicyTransactionsSOAPBindingStub bindingStub = null;
	HttpClient httpClient;
	URI uri;
	HttpGet get;
	HttpResponse response;
	String soap_XMLAsString = null;
	String restAsXMLString = null;
	String restResponseString = null;
	public boolean isRESTResponseValid=false;
	
	String UserId ="eczarog";
	String TransactionType ="REALTIME";
	String Application ="MRM_IG";
	String Address ="10.37.128.170";
	String CorrelationId ="eczarog";
	
	/*
	String CorrelationId = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "correlationId");
    String UserId = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "userId");
    String TransactionType = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "transactionType");
    String Address = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "IP_Address");
    String Application = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "application");
    String subSystem = dataTable.getData(testParameter, "Webservice_" + properties.getProperty("Environment"), "subSystem");
*/
	
	
	public void soap_SetRequest(String policyNumber,String sourceSystem,String productCode){
		
		ApplicationContext applicationContext = new ApplicationContext();
		applicationContext.setUserId(UserId);
		applicationContext.setTransactionType(TransactionType);
		applicationContext.setApplication(Application);
		applicationContext.setAddress(Address);
		applicationContext.setCorrelationId(CorrelationId);
		
		BigInteger priorTermCount  = new BigInteger("5");
		policyTransactionsRequest = new RetrievePolicyTransactionsRequest();
		webservice.soap.retrievePolicyTransactionDetails.AAANCNU_RetrievePolicyTransactions_version2.com.aaancnuit.www.PolicySource policy = new webservice.soap.retrievePolicyTransactionDetails.AAANCNU_RetrievePolicyTransactions_version2.com.aaancnuit.www.PolicySource();
		policy.setPolicyNumber(policyNumber);
		policy.setDataSource(sourceSystem);
		policy.setProductCode(productCode);
		policyTransactionsRequest.setPolicy(policy);
		policyTransactionsRequest.setPriorTermCount(priorTermCount);
		policyTransactionsRequest.setIncludeAll(true);
	}
	
	public void soap_getResponse() throws MalformedURLException, URISyntaxException{
		//String strEndPoint = dataTable.getCommonData_v2(properties.getProperty("Environment"), "SOAP_EndPointUrl");


		String strEndPoint="";	
//		if (properties.getProperty("dynamicUrl").equalsIgnoreCase("true")) {
//			strEndPoint = properties.getProperty("ApplicationURL");
//		} else {
			strEndPoint = "https://soaqa3.tent.trt.csaa.pri/";
//		}

		
		strEndPoint = strEndPoint+"RetrievePolicyTransactionsV2";
		URL url = new URL(strEndPoint);
		URI uri = new URI(url.getProtocol(), url.getUserInfo(),
				url.getHost(), url.getPort(), url.getPath(),
				url.getQuery(), url.getRef());
		url = uri.toURL();
		try {
			System.out.println("SOAP Endpoint - "+url.toString());
		//	report.updateTestLog("SOAP Endpoint", url.toString(), Status.DONE);
			bindingStub = new RetrievePolicyTransactionsSOAPBindingStub(url,null);
			policyTransactionsResponse = bindingStub.retrievePolicyTransactions(policyTransactionsRequest);
			setSoap_XMLAsString(bindingStub._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString());
		} catch (ErrorInfo e) {
			String errCode = e.getErrorCode();
			String errText = e.getErrorMessageText();
			String errMessage = e.getMessage();
	//		report.updateTestLog("soap_getResponse",
	//				styleMsg("Error Code: " + errCode + "</br>" + errText + "</br>" + errMessage, "error"), Status.FAIL);
	//		report.updateTestLog("soap_getResponse", "Exception in soap response"+e.getMessage(), Status.FAIL);
		} catch (RemoteException e) {
	//		report.updateTestLog("soap_getResponse", "Exception in soap response"+e.getMessage(), Status.FAIL);
		}
		try {
			System.out.println("SOAP Request - "+bindingStub._getCall().getMessageContext().getRequestMessage().getSOAPPartAsString());
			System.out.println("SOAP Response - "+bindingStub._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString());
	//		report.updateTestLog("Soap Request ",styleMsg(bindingStub._getCall().getMessageContext().getRequestMessage().getSOAPPartAsString(),"xml"),Status.DONE);
	//		report.updateTestLog("Soap Response ",styleMsg(bindingStub._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString(),"xml"),Status.DONE);
		} catch (Exception e1) {e1.printStackTrace();}
	}
	
	public void rest_getResponse(String policyNumber,String sourceSystem,String productCode) throws URISyntaxException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, UnsupportedEncodingException{
		SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build(), SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create().register("http", new PlainConnectionSocketFactory())
                     .register("https", sslsf).build();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
        cm.setMaxTotal(2000);
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).setConnectionManager(cm).build();
        String scheme =  "https";
        String setHost = "soaqa3.tent.trt.csaa.pri:2073";
        String setPath = "/ent/%s/csaa/v1/policies/transactions";
        String setPriortermcount ="5";
        String setincludeAll ="true";
        String setHeadername="Authorization";
        String auth_token =WebserviceComponents.token;
        String setHeadervalue="Bearer "+auth_token;
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
        URI uri = new URIBuilder().setScheme(scheme).setHost(setHost).setPath(setPath).setParameter("policyNumber", policyNumber)
                .setParameter("priorTermCount", setPriortermcount).setParameter("includeAll", setincludeAll).setParameter("sourceSystem", sourceSystem).setParameter("productCode", productCode).build();
         get = new HttpGet(uri);
         get.addHeader(setHeadername,setHeadervalue);
         get.addHeader("X-ApplicationContext", "{\"userId\":\""+UserId+"\",\"transactionType\":\""+TransactionType+"\",\"application\":\""+Application+"\",\"address\":\""+Address+"\",\"correlationId\":\""+CorrelationId+"\"}");
         
         Header[] allHeaders = get.getAllHeaders();
      	String headerMsg=uri.toString();
      	for (Header header : allHeaders) {
      		headerMsg = headerMsg +"\n"+header;
 		}
      	System.out.println("REST request - " + headerMsg);
     // 	report.updateTestLog("REST Request", styleMsg(headerMsg, "xml"), Status.DONE);
         
    	try {
			response = httpClient.execute(get);
		} catch (ClientProtocolException e) {
		//	report.updateTestLog("rest_getResponse", "Exception in rest response", Status.FAIL);
		} catch (IOException e) {
		//	report.updateTestLog("rest_getResponse", "Exception in rest response", Status.FAIL);
		}
    	isRESTResponseValid = (new RestApiWraper()).validateResponse(response, "Issue direct Payment");
    	if(!isRESTResponseValid){
    		try {
				String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
				fail("http response verification - error");
		//		report.updateTestLog("http response verification",styleMsg(responseString, "error"), Status.FAIL);
				
			} catch (org.apache.http.ParseException | IOException e) {
				e.printStackTrace();
			}
    	}
	}
	
	public void convertJsonToXML() throws JSONException{
		HttpEntity entity = response.getEntity();
		String responseString = null;
		try {
			responseString = EntityUtils.toString(entity, "UTF-8");
			setRestResponseString(responseString);
		} catch (org.apache.http.ParseException | IOException e) {
			// TODO Auto-generated catch block
	//		report.updateTestLog("Json to XML convertion", "Problem in converting Json to XML", Status.FAIL);
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
	public void comparePolicyTransactionResponse() throws JsonParseException, JsonMappingException, KeyManagementException, UnsupportedOperationException, NoSuchAlgorithmException, KeyStoreException, IOException, URISyntaxException, JSONException{
//		String policyNumber = dataTable.getData(testParameter, "Webservice_"+properties.getProperty("Environment"), "policyNumber");
//		String sourceType = dataTable.getData(testParameter, "Webservice_"+properties.getProperty("Environment"), "sourceType");
//		String productCode= dataTable.getData(testParameter, "Webservice_"+properties.getProperty("Environment"), "productCode");
		
		//Code updated for Digital Service
		String policyNumber = "VASS900028913";
		String sourceType = "PAS";
		String productCode = "PA";
		
	//	report.updateTestLog("Test Data", "policyNumber:"+policyNumber+" sourceType:"+sourceType+" productCode:"+productCode, Status.DONE);
		soap_SetRequest(policyNumber, sourceType, productCode);
		soap_getResponse();
		rest_getResponse(policyNumber,sourceType,productCode);
		convertJsonToXML();
		/*List<?> difference = null;
		try {
			difference = compareXML(getSoap_XMLAsString(), getRestAsXMLString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			report.updateTestLog("Compare XML", "Exception in comparing xml", Status.FAIL);
		}
		int diffCount = difference.size();
		if(diffCount==0){
			report.updateTestLog("Webservice Validation", "Rest and Soap response are equal", Status.PASS);
		}else{
			for(int index=0;index<diffCount;index++){
				report.updateTestLog("Webservice Validation", "[Expected: SOAP][Actual: REST] "+difference.get(index), Status.FAIL);
			}
		}*/
	}
	
	
	public List<?> compareXML(String expectedXML, String actualXML) throws Exception {
        XMLUnit.setIgnoreWhitespace(true);
        XMLUnit.setIgnoreAttributeOrder(true);
        //System.out.println("*********
        //System.out.println("*********
        expectedXML = expectedXML.replaceAll("aa_1:", "").replaceAll("aa:", "").replaceAll("out2:", "").replaceAll("out3:", "").replaceAll("out:", "");
        expectedXML = "<retrievePolicyTransactionsResponse>"+expectedXML.substring(expectedXML.indexOf("<transactions>"), expectedXML.indexOf("</soapenv:Body>"));
        if(actualXML.contains("<policyNumber>"))
        	actualXML = "<retrievePolicyTransactionsResponse>"+actualXML.substring(actualXML.indexOf("<transactions>"));
        
        DetailedDiff diff = new DetailedDiff(XMLUnit.compareXML(expectedXML, actualXML));
        List<?> allDifferences = diff.getAllDifferences();
        return allDifferences;
    }
	
	public void soap_getSampleResponse() throws MalformedURLException, URISyntaxException{
		String policyNumber = "CTS3183646";
		String sourceType = "SIS";
		String productCode = "HO";
		soap_SetRequest(policyNumber, sourceType, productCode);
		soap_getResponse();
		System.out.println();
	}
	public void rest_getSampleResponse() throws JsonParseException, JsonMappingException, UnsupportedOperationException, IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, URISyntaxException{
		String policyNumber = "HO33520990";
		String sourceType = "SIS";
		String productCode= "HO";
		rest_getResponse(policyNumber,sourceType,productCode);
		ObjectMapper obj = new ObjectMapper();
		RetrievePolicyTransactionsDetails res = obj.readValue(new InputStreamReader(response.getEntity().getContent()), RetrievePolicyTransactionsDetails.class);
		RetrievePolicyTransactionsResponse resDetails = res.getRetrievePolicyTransactionsResponse();
		//System.out.println("*********
		Transactions transactions = resDetails.getTransactions();
		List<Transaction> policytransaction = transactions.getTransaction();
		int count = policytransaction.size();
		for( Transaction txn :policytransaction){
		//System.out.println("*********
		//System.out.println("*********
		//System.out.println("*********
		}
	}
	
	public webservice.rest.retrievePolicyTransactionDetails.RetrievePolicyTransactionsResponse getResponse() throws JsonParseException, JsonMappingException, UnsupportedOperationException, IOException {
		ObjectMapper objMapper = new ObjectMapper();	
		RetrievePolicyTransactionsDetails res = objMapper.readValue(new InputStreamReader(response.getEntity().getContent()), RetrievePolicyTransactionsDetails.class);
		RetrievePolicyTransactionsResponse resDetails = res.getRetrievePolicyTransactionsResponse();
		return resDetails;
		
	}
	
}
