package services.rest.commons;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.httpclient.Credentials;
import org.apache.http.NameValuePair;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

//import com.cognizant.framework.FrameworkException;
//import com.cognizant.framework.Status;
//import com.cognizant.supportlibraries.ReusableLibrary;
//import com.cognizant.supportlibraries.ScriptHelper;

public class REST_Client  {
	private CloseableHttpClient closeableHttpClient;
	private HttpGet httpGet;
	private HttpPost httpPost;
	private HttpPut httpPut;
	protected HttpResponse httpResponse;
	private StringEntity requestContentEntity;
	private UrlEncodedFormEntity requestContentEntityForUrlEncoded;
	public String responseAsString;
	private BasicHeader[] headerArr;
	private Credentials credentials;
	private URI apiURI;
	protected HTTP_RequestType currReqType;
	private boolean isValidResponse;
	private String requestContentAsString;
	private HTTP_ContentType currServiceContentType;

	public REST_Client() {
		//super(scriptHelper);
		this.closeableHttpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
	}
	
	protected void setRequestType(HTTP_RequestType reqType) {
		this.currReqType = reqType;
	}

	protected void setContentType(HTTP_ContentType contentType) {
		this.currServiceContentType = contentType;
	}

	protected void setURI_Path(String scheme, String host, String resource, HashMap<String, String> parameters) {
		try {
			URIBuilder uriBuilder = new URIBuilder().setScheme(scheme).setHost(host).setPath(resource);
			if (parameters != null) {
				for (Entry<String, String> entry : parameters.entrySet()) {
					uriBuilder.setParameter(entry.getKey(), entry.getValue());
		//			report.updateTestLog("URI-Parameters", entry.getKey() + " = " + entry.getValue(), Status.DONE);
				}
			}
			this.apiURI = uriBuilder.build();
			System.out.println("apiURI:"+apiURI+":");
		//	report.updateTestLog("API URI", this.apiURI.toString(), Status.DONE);
		} catch (URISyntaxException e) {
		//	throw new FrameworkException("Invalid URI details");
		}
		switch (this.currReqType) {
			case GET :
				this.httpGet = new HttpGet(this.apiURI);
				break;
			case POST :
				this.httpPost = new HttpPost(this.apiURI);
				break;
			case PUT :
				this.httpPut = new HttpPut(this.apiURI);
				break;
			default :
				break;

		}
	}

	protected void setRequest_Header(BasicHeader[] headerArr) {
		this.headerArr = headerArr;
		if (headerArr != null) {
			switch (this.currReqType) {
				case GET :
					for (BasicHeader header : this.headerArr) {
						this.httpGet.addHeader(header);
			//			report.updateTestLog("API Header", header.getName() + ":" + header.getValue(), Status.DONE);
					}
					break;
				case POST :
					for (BasicHeader header : this.headerArr) {
						this.httpPost.addHeader(header);
			//			report.updateTestLog("API Header", header.getName() + ":" + header.getValue(), Status.DONE);
					}
					break;
				case PUT :
					for (BasicHeader header : this.headerArr) {
						this.httpPut.addHeader(header);
			//			report.updateTestLog("API Header", header.getName() + ":" + header.getValue(), Status.DONE);
					}
					break;
				default :
					break;

			}
		}
	}

	protected void setRequest_Authentication(String userName, String password) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, password));
		this.closeableHttpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).build();

	}

	protected void setRequest_Content(String requestContent) {
		this.requestContentAsString = requestContent;
	//	report.updateTestLog("Request-Content", styleMsg(requestContent, "xml"), Status.DONE);
		try {
			this.requestContentEntity = new StringEntity(this.requestContentAsString);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		switch (this.currServiceContentType) {
			case XML :
				this.requestContentEntity.setContentType("application/xml");
				break;
			case JSON :
				this.requestContentEntity.setContentType("application/json");
				break;
			default :
				break;
		}

		switch (this.currReqType) {
			case GET :
				//
				break;
			case POST :
				this.httpPost.setEntity(requestContentEntity);
				break;
			case PUT :
				this.httpPut.setEntity(requestContentEntity);
				break;
			default :
				break;

		}

	}

	protected void setRequest_Content_ForURLEncoded(List<NameValuePair> postParams) {
		try {
			this.requestContentEntityForUrlEncoded = new UrlEncodedFormEntity(postParams);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		switch (this.currReqType) {
			case POST :
				this.httpPost.setEntity(requestContentEntityForUrlEncoded);
				break;
			default :
				break;

		}

	}
	
	protected void invokeService() {
		switch (this.currReqType) {
			case GET :
				try {
					System.out.println("httpGet:"+httpGet+":");
					this.httpResponse = this.closeableHttpClient.execute(this.httpGet);
				} catch (IOException e) {
//					throw new FrameworkException("Exception:" + styleMsg(e.getMessage(), "xml"));
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				validateResponseCode();
				break;
			case POST :
				try {
					System.out.println("post:"+httpPost);
					this.httpResponse = this.closeableHttpClient.execute(this.httpPost);
				} catch (IOException e) {
		//			throw new FrameworkException("Exception:" + styleMsg(e.getMessage(), "xml"));
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				validateResponseCode();
				break;
			case PUT :
				try {
					this.httpResponse = this.closeableHttpClient.execute(this.httpPut);
				} catch (IOException e) {
			//		throw new FrameworkException("Exception:" + styleMsg(e.getMessage(), "xml"));
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				validateResponseCode();
				break;
			default :
				break;

		}
	}

	private boolean validateResponseCode() {
		this.getReponseString();
		int responseCode = this.httpResponse.getStatusLine().getStatusCode();
		if (!(responseCode > 199 && responseCode < 299)) {
			isValidResponse = false;
	//		logger.info(this.httpResponse.toString());
			System.out.println("http response" + this.httpResponse.getStatusLine() +"error");
	//		report.updateTestLog("Error response", styleMsg(this.responseAsString, "error"), Status.FAIL);
	//		throw new FrameworkException("Exception in " + this.apiURI.getPath() + " rest api response " + this.httpResponse.getStatusLine());
		} else {
			isValidResponse = true;
			System.out.println( "REST Response Status : " + this.httpResponse.getStatusLine());
		}
		return isValidResponse;
	}

	private String getReponseString() {
		this.responseAsString = "";
		try {
			this.responseAsString = EntityUtils.toString(this.httpResponse.getEntity(), "UTF-8");
		//	report.updateTestLog("Response-Content", styleMsg(this.responseAsString, "xml"), Status.DONE);

		} catch (org.apache.http.ParseException | IOException e) {
			e.printStackTrace();
		}
		return this.responseAsString;
	}

	protected String getContextAsJSON(String contextID) {
		String UserId = "eczarog";
		String TransactionType = "realtime";
		String Application = "3CM";
		String Address = "";
		String CorrelationId = "guiid";

		String contextString = "";
		contextString = contextString + "{";
		if (!UserId.equalsIgnoreCase("")) {
			contextString = contextString + "\"userId\":\"" + UserId + "\",";
		}
		if (!TransactionType.equalsIgnoreCase("")) {
			contextString = contextString + "\"transactionType\":\"" + TransactionType + "\",";
		}
		if (!Application.equalsIgnoreCase("")) {
			contextString = contextString + "\"application\":\"" + Application + "\",";
		}
		if (!Address.equalsIgnoreCase("")) {
			contextString = contextString + "\"address\":\"" + Address + "\",";
		}
		if (!CorrelationId.equalsIgnoreCase("")) {
			contextString = contextString + "\"correlationId\":\"" + CorrelationId + "\",";
		}
		contextString = contextString + "}";
		contextString = contextString.replaceAll("\\,\\}", "\\}");
		/*
		 * "{\"userId\":\""+UserId+"\",\"transactionType\":\""+TransactionType+
		 * "\",\"application\":\""
		 * +Application+"\",\"address\":\""+Address+"\",\"correlationId\":\""+
		 * CorrelationId+"\"}";
		 */
		return contextString;
	}

}
