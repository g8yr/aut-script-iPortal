package services.rest.apiclients;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;



import businesscomponents.iportal.WebserviceComponents;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import webservice.rest.policySearch.policySearchResponse.PolicySearchResponse;
import webservice.rest.retrieveCustomerCentricSearch.retrieveCustomerCentricSearchRequest.CustomerCentricSearch;
import webservice.rest.retrieveCustomerCentricSearch.retrieveCustomerCentricSearchRequest.Header;
import webservice.rest.retrieveCustomerCentricSearch.retrieveCustomerCentricSearchRequest.RequestParam;
import webservice.rest.retrieveCustomerCentricSearch.retrieveCustomerCentricSearchRequest.SearchRequest;
import services.rest.apiclients.RestApiWraper;
import services.rest.pojo.WebServiceComparator;

public class PolicySearchService extends WebServiceComparator {

	public PolicySearchService() {
		
	}

	CustomerCentricSearch request = null;
	PolicySearchResponse response = null;
	SearchRequest srhRequest = null;
	RequestParam reqParam = null;
	Header reqHeader = null;

	String soap_XMLAsString = null;
	String restAsXMLString = null;
	// HttpClient httpClient;
	URI uri;
	HttpPost post;
	HttpResponse httpresponse;
	HttpResponse response1;
	String restResponseAsString = null;

	public void rest_getResponse(String policyNumber) throws URISyntaxException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
		/*
		 * SSLContextBuilder builder = new SSLContextBuilder();
		 * builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
		 * SSLConnectionSocketFactory sslsf = new
		 * SSLConnectionSocketFactory(builder.build(),
		 * SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		 * Registry<ConnectionSocketFactory> registry =
		 * RegistryBuilder.<ConnectionSocketFactory> create().register("http",
		 * new PlainConnectionSocketFactory()) .register("https",
		 * sslsf).build(); PoolingHttpClientConnectionManager cm = new
		 * PoolingHttpClientConnectionManager(registry); cm.setMaxTotal(2000);
		 * CloseableHttpClient httpClient =
		 * HttpClients.custom().setSSLSocketFactory(sslsf).setConnectionManager(
		 * cm).build();
		 */
		URI mrmUri = null;
		String mrmScheme = "https";
		// String mrmSetHost =
		// dataTable.getCommonData_v2(properties.getProperty("Environment"),
		// "REST_HOSTNAME");
		String mrmSetHost = "soaqa3.tent.trt.csaa.pri:8469";
		String mrmSetPath = "/ess/policy/search";
		String mrmPath = "soaqa3";
		
			mrmSetPath = String.format(mrmSetPath, mrmPath);

		try {
			mrmUri = new URIBuilder().setScheme(mrmScheme).setHost(mrmSetHost).setPath(mrmSetPath).build();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}

		// uri = new
		// URIBuilder().setScheme("https").setHost("soamrmqa1:2073").setPath("/ent/qa/csaa/v3/search/customerCentric").build();
		post = new HttpPost(mrmUri);
		String auth_token =WebserviceComponents.token;
        post.addHeader("Authorization", "Bearer "+auth_token);
		post.addHeader("X-ApplicationContext", getContext());
		// post.addHeader("X-ApplicationContext", "{\"transactionType\":
		// \"INCL_ALL_ELIG\",\"application\": \"IEWMS\",\"address\":
		// \"192.162.85.85\"}");
		StringEntity input = null;
		try {
			input = new StringEntity(rest_Request(policyNumber));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		input.setContentType("application/json");
		post.setEntity(input);
	//	System.out.println("rest_getRequest" + _getCall().getMessageContext().getRequestMessage().getSOAPPartAsString());

		try {
			httpresponse = closeableHttpClient.execute(post);
			System.out.println("Response-Content" + (EntityUtils.toString(httpresponse.getEntity(), "UTF-8")));
		//	report.updateTestLog("Response-Content", styleMsg(EntityUtils.toString(httpresponse.getEntity(), "UTF-8"), "xml"), Status.DONE);	
			new RestApiWraper().validateResponse(httpresponse, "customerSearchService");
			
			
		} catch (ClientProtocolException e) {
		//	report.updateTestLog("rest_getResponse", "Problem in rest reponse", Status.FAIL);
		} catch (IOException e) {
		//	report.updateTestLog("rest_getResponse", "Problem in rest response", Status.FAIL);
		}
	}

	public String rest_Request(String searchString) {
		String strRequest = null;

		request = new CustomerCentricSearch();
		srhRequest = new SearchRequest();
		reqParam = new RequestParam();
		reqHeader = new Header();

		reqHeader.setChannelType("DSU");
		reqHeader.setAgencyCode("null");
		reqHeader.setRequestType("null");
		reqHeader.setAgentid("null");

		reqParam.setSearch(searchString);

		srhRequest.setRequestParam(reqParam);
		srhRequest.setHeader(reqHeader);
		request.setSearchRequest(srhRequest);

		ObjectMapper objMapper = new ObjectMapper();
		try {
			strRequest = objMapper.writeValueAsString(request);
		//	report.updateTestLog("Request-Content", styleMsg(strRequest, "xml"), Status.DONE);
			// System.out.println(strRequest);
		} catch (Exception e) {
		//	report.updateTestLog("rest_request", "Problem in rest request", Status.FAIL);
		}
		return strRequest;
	}

	public String rest_Request(String searchString, String policyNumber, String firstName, String lastName, String zipCode, String mdmId) {
		String strRequest = null;

		request = new CustomerCentricSearch();
		srhRequest = new SearchRequest();
		reqParam = new RequestParam();
		reqHeader = new Header();

		reqHeader.setChannelType("DSU");
		reqHeader.setAgencyCode("null");
		reqHeader.setRequestType("null");
		reqHeader.setAgentid("null");

		reqParam.setSearch(searchString);
		if (!policyNumber.equals("") && !policyNumber.isEmpty())
			reqParam.setPolicyNumber(policyNumber);
		if (!firstName.equals("") && !firstName.isEmpty())
			reqParam.setFirstName(firstName);
		if (!lastName.equals("") && !lastName.isEmpty())
			reqParam.setLastName(lastName);
		if (!mdmId.equals("") && !mdmId.isEmpty())
			reqParam.setMdmId(mdmId);

		srhRequest.setRequestParam(reqParam);
		srhRequest.setHeader(reqHeader);
		request.setSearchRequest(srhRequest);

		ObjectMapper objMapper = new ObjectMapper();
		try {
			strRequest = objMapper.writeValueAsString(request);
			// System.out.println(strRequest);
		} catch (Exception e) {
		//	report.updateTestLog("rest_request", "Problem in rest request", Status.FAIL);
		}
		return strRequest;
	}

	public PolicySearchResponse getResposne() throws JsonParseException, JsonMappingException, UnsupportedOperationException, IOException {
		ObjectMapper objMapper = new ObjectMapper();
		PolicySearchResponse res = null;
		if (restResponseAsString != null) {
			res = objMapper.readValue(restResponseAsString, PolicySearchResponse.class);
		} else {
			res = objMapper.readValue(new InputStreamReader(httpresponse.getEntity().getContent()), PolicySearchResponse.class);
		}
		PolicySearchResponse responseJson = res;
		ObjectMapper mapper = new ObjectMapper();
		JSONObject json = null;
		try {
			json = new JSONObject(mapper.writeValueAsString(responseJson));
			//// System.out.println("*********
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return responseJson;
	}

	
}