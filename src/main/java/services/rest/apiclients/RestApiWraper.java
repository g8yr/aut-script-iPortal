package services.rest.apiclients;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import com.cognizant.framework.Status;
//import com.cognizant.supportlibraries.ReusableLibrary;
//import com.cognizant.supportlibraries.ScriptHelper;

public class RestApiWraper //extends ReusableLibrary
{
	public boolean isValidResponse = false;
	public static Logger logger = LogManager.getLogger();
	private HttpResponse response =null;
	private static String restResponseString =null;
	public RestApiWraper() {
		//super(scriptHelper);
	}
	public RestApiWraper(HttpResponse response) {
		//super(scriptHelper);
		this.response = response;
	}
	public boolean validateResponse(HttpResponse response,String service){
		this.response = response;
		int responseCode = response.getStatusLine().getStatusCode();
		if(service.equalsIgnoreCase("Record Payment Enrollment") && responseCode==400){
			String resString = getReponseString();
			if(resString.contains("Policy provided in WS not on AutoPay")) {
				//report.updateTestLog("Error response",styleMsg(resString, "Data Issue"), Status.PASS);
				isValidResponse = true;
			}
		}
		else if(service.equalsIgnoreCase("Issue direct Payment") && responseCode==400){
			String resString = getReponseString();
			if(resString.contains("Payment Token is invalid or not present")) {
				//report.updateTestLog("Error response",styleMsg(resString, "Data Issue"), Status.PASS);
				isValidResponse = true;
			}
		}
		else if(service.equalsIgnoreCase("RetrievePaymentEnrollmentStatus") && responseCode==400){
			String resString = getReponseString();
			if(resString.contains("ENROLLMENT LIST IS EMPTY")) {
				//report.updateTestLog("Error response",styleMsg(resString, "ENROLLMENT LIST IS EMPTY"), Status.PASS);
				isValidResponse = true;
			}
		}
		else if(!(responseCode>199 && responseCode<299)){
			isValidResponse = false;
			logger.info(response.toString());
//			report.updateTestLog("http response",styleMsg("UnExpected Error : "+response.getStatusLine(), "error"), Status.FAIL);
//			report.updateTestLog("Error response",styleMsg(getReponseString(), "error"), Status.FAIL);
//			//throw new FrameworkException("Exception in "+service+" rest api response "+response.getStatusLine());
		}else{
			isValidResponse = true;
			//report.updateTestLog("http response","REST Response Status : "+response.getStatusLine(), Status.PASS);
		}
		return isValidResponse;
	}
	
	public String getReponseString(){
		String responseString="";
		try {
			responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
			restResponseString = responseString;
			//report.updateTestLog("http response data",styleMsg(responseString, "xml"), Status.DONE);
			
		} catch (org.apache.http.ParseException | IOException e) {
			e.printStackTrace();
		}
		return responseString;
	}

	public String getRestResponseStringFromWraper()
	{
		return restResponseString;
	}
}
