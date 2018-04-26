package services.rest.apiclients;

import static org.testng.Assert.fail;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;



//import webservice.rest.customerSearch.customerSearchResponseQuote.MembershipList;


//import webservice.rest.customerSearch.customerSearchResponseQuote.*;
import webservice.rest.policySearch.policySearchResponse.*;
public class PolicySearch  {

		

	public void getPolicyDetails() {
		String policyNumber = "VASS900028913";
		String cusPrefix = null, cusSuffix = null, cusFirstName = null, cusLastName = null, cusAge = null, cusGender = null, cusPhone = null, cusResidentalAddress = null,
				cusMailingAddress = null, cusDOB = null, cusMartialStatus = null, cusEmail = null, cusMembership = null, MemberSince = null, policyInsureddate = null,
				membersincedate = null, cusMiddleName = null;

		PolicySearchService css = new PolicySearchService();
		try {
			css.rest_getResponse(policyNumber);
			try {
				PolicySearchResponse policySrhResponse = css.getResposne();
				if (policySrhResponse == null) {
				//	report.updateTestLog("getPolicyDetails", "Error while retrieving", Status.FAIL);
					fail("getPolicyDetails - Error while retrieving");
				//	throw new FrameworkException("Unable to retreive expected data via API. Terminating test case.");
				}

				String policynum = policySrhResponse.getResponseJson().getSearchedPolicies().get(0).getPolicyNumber();

				if (policynum.equals(policyNumber)) {
					String evaluestatus = policySrhResponse.getResponseJson().getSearchedPolicies().get(0).getEValue();
					if (evaluestatus.equalsIgnoreCase("N")) {
						System.out.println("Evalue Status - Evalue status is displayed as " + evaluestatus);
					} else {
						System.out.println("Evalue Status - Evalue status is displayed as " + evaluestatus);
					}

				}

			} catch (UnsupportedOperationException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
/*
	public void getPolicyDetailseValue() {
		String policyNumber = dataTable.getData(testParameter, "PhoneCall", "SimpleSearch");
		String cusPrefix = null, cusSuffix = null, cusFirstName = null, cusLastName = null, cusAge = null, cusGender = null, cusPhone = null, cusResidentalAddress = null,
				cusMailingAddress = null, cusDOB = null, cusMartialStatus = null, cusEmail = null, cusMembership = null, MemberSince = null, policyInsureddate = null,
				membersincedate = null, cusMiddleName = null;

		PolicySearchService css = new PolicySearchService(scriptHelper);
		try {
			css.rest_getResponse(policyNumber);
			try {
				PolicySearchResponse policySrhResponse = css.getResposne();
				if (policySrhResponse == null) {
					report.updateTestLog("getPolicyDetails", "Error while retrieving", Status.FAIL);
					throw new FrameworkException("Unable to retreive expected data via API. Terminating test case.");
				}

				String policynum = policySrhResponse.getResponseJson().getSearchedPolicies().get(0).getPolicyNumber();

				if (policynum.equals(policyNumber)) {
					String evaluestatus = policySrhResponse.getResponseJson().getSearchedPolicies().get(0).getEValue();
					if (evaluestatus != null) {
						if (evaluestatus.equalsIgnoreCase("N")) {
							report.updateTestLog("Evalue Status", "Evalue status is displayed as " + evaluestatus, Status.PASS);
						} else {
							report.updateTestLog("Evalue Status", "Evalue status is displayed as " + evaluestatus, Status.PASS);
						}
					}
				}
				int partlist = policySrhResponse.getResponseJson().getSearchedPolicies().get(0).getPartyList().size();
				String licensePolicySearch = policySrhResponse.getResponseJson().getSearchedPolicies().get(0).getPartyList().get(0).getDriversLicenseNumber();
				String FirstName = policySrhResponse.getResponseJson().getSearchedPolicies().get(0).getPartyList().get(0).getFirstName();
				String LasttName = policySrhResponse.getResponseJson().getSearchedPolicies().get(0).getPartyList().get(0).getLastName();
				String Dob = policySrhResponse.getResponseJson().getSearchedPolicies().get(0).getPartyList().get(0).getDob();
				String AdddressLine = policySrhResponse.getResponseJson().getSearchedPolicies().get(0).getPartyList().get(0).getPartyAddress().get(0).getAddress1();
				String zipcode = policySrhResponse.getResponseJson().getSearchedPolicies().get(0).getPartyList().get(0).getPartyAddress().get(0).getZipCode();
				String city = policySrhResponse.getResponseJson().getSearchedPolicies().get(0).getPartyList().get(0).getPartyAddress().get(0).getCity();
				String state = "VA";

				// String
				// licensePolicySearch=policySrhResponse.getResponseJson().getSearchedPolicies().get(0).getPartyList().get(0).getDriversLicenseNumber();
				if (licensePolicySearch != null) {
					dataTable.putData("Evalue_CreateQuote", "DL1", licensePolicySearch);
					dataTable.putData("Evalue_CreateQuote", "DriverFName1", FirstName);
					dataTable.putData("Evalue_CreateQuote", "DriverLName1", LasttName);

					dataTable.putData("Evalue_CreateQuote", "Address_Line1", AdddressLine);
					dataTable.putData("Evalue_CreateQuote", "Zip_code", zipcode);
					dataTable.putData("Evalue_CreateQuote", "City", city);
					dataTable.putData("Evalue_CreateQuote", "State", state);

				}

			} catch (UnsupportedOperationException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
*/
}
