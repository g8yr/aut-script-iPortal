package services.rest.apiclients;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


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
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.XMLUnit;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

//import com.cognizant.framework.Status;
//import com.cognizant.supportlibraries.ReusableLibrary;
//import com.cognizant.supportlibraries.ScriptHelper;

public class WebServiceComparator  {
	CloseableHttpClient closeableHttpClient=null;
	public WebServiceComparator(){
	//	super(scriptHelper);	
		getHttpClient();
	}

	public List<?> compareXML(String expectedXML, String actualXML) throws Exception {
        XMLUnit.setIgnoreWhitespace(true);
        XMLUnit.setIgnoreAttributeOrder(true);
        List<?> allDifferences = new ArrayList();
        try
        {
        XMLUnit.compareXML(expectedXML, actualXML);
        DetailedDiff diff = new DetailedDiff(XMLUnit.compareXML(expectedXML, actualXML));
        allDifferences = diff.getAllDifferences();
        }catch(Exception e)
        {
        	e.getMessage();
        }
        return allDifferences;
    }
	public String convertJsonToXML(String jsonData) throws JSONException{
		JSONObject json = new JSONObject(jsonData);
		String xml = XML.toString(json);
		return xml;
	}
//	public String getContext(String contextID) {
//		String UserId =dataTable.getCommonData_v2(contextID, "userId_context");
//		String TransactionType =dataTable.getCommonData_v2(contextID, "transactionType");
//		String Application =dataTable.getCommonData_v2(contextID, "application");
//		String Address= dataTable.getCommonData_v2(contextID, "address");
//		String CorrelationId =dataTable.getCommonData_v2(contextID, "correlationId");
//		
//		String contextString = "";
//		contextString = contextString+"{";
//		if(!UserId.equalsIgnoreCase("")){
//			contextString = contextString+"\"userId\":\""+UserId+"\",";
//		}
//		if(!TransactionType.equalsIgnoreCase("")){
//			contextString = contextString+"\"transactionType\":\""+TransactionType+"\",";
//		}
//		if(!Application.equalsIgnoreCase("")){
//			contextString = contextString+"\"application\":\""+Application+"\",";
//		}
//		if(!Address.equalsIgnoreCase("")){
//			contextString = contextString+"\"address\":\""+Address+"\",";
//		}
//		if(!CorrelationId.equalsIgnoreCase("")){
//			contextString = contextString+"\"correlationId\":\""+CorrelationId+"\",";
//		}
//		contextString = contextString+"}";
//		contextString = contextString.replaceAll("\\,\\}", "\\}");
//		/*"{\"userId\":\""+UserId+"\",\"transactionType\":\""+TransactionType+"\",\"application\":\""
//		+Application+"\",\"address\":\""+Address+"\",\"correlationId\":\""+CorrelationId+"\"}";*/
//		return contextString;
//	}
	
	public CloseableHttpClient getHttpClient(){
		try {
			SSLContextBuilder builder = new SSLContextBuilder();
			builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build(),
					SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create()
					.register("http", new PlainConnectionSocketFactory()).register("https", sslsf).build();
			PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
			cm.setMaxTotal(2000);

			closeableHttpClient = HttpClients.custom().setSSLSocketFactory(sslsf).setConnectionManager(cm).build();
		} catch (KeyStoreException | NoSuchAlgorithmException | KeyManagementException e) {
			//report.updateTestLog("CloseableHttpClient", styleMsg(e.getLocalizedMessage(), "error"), Status.FAIL);
		} 
		return closeableHttpClient;
	}
}
