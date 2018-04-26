package services.rest.apiclients;

import static org.testng.Assert.fail;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.ClientProtocolException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



import webservice.rest.customerSearch.customerSearchResponse.MembershipList;
import webservice.rest.customerSearch.customerSearchResponse.PartyAdress;
import webservice.rest.customerSearch.customerSearchResponse.PolicyList;
import webservice.rest.customerSearch.customerSearchResponse.RoleList;
import webservice.rest.customerSearch.customerSearchResponse.RoleList_;
import services.rest.pojo.customerSearchService;

public class CustomerSearch {

	public void getCustomerDetails() {
		//String policyNumber = dataTable.getData(testParameter, "PhoneCall", "SimpleSearch");
		 String policyNumber = "VASS900032689";
		String cusPrefix = null, cusSuffix = null, cusFirstName = null, cusLastName = null, cusAge = null, cusGender = null, cusPhone = null, cusResidentalAddress = null,
				cusMailingAddress = null, cusDOB = null, cusMartialStatus = null, cusEmail = null, cusMembership = null, MemberSince = null, policyInsureddate = null,
				membersincedate = null, cusMiddleName = null;
		
		customerSearchService css = new customerSearchService();
		try {
			css.rest_getResponse(policyNumber);
			try {
				webservice.rest.customerSearch.customerSearchResponse.CustomerSearchResponse customerSrhResponse = css.getResposne();
				if (customerSrhResponse == null) {
				//	report.updateTestLog("getCustomerDetails", "Error while retrieving", Status.FAIL);
					fail("getCustomerDetails - Error while retrieving");
				//	throw new FrameworkException("Unable to retreive expected data via API. Terminating test case.");
				}
				try {
					if (customerSrhResponse.getCustomers().get(0).getPrefix() != null)
						cusPrefix = customerSrhResponse.getCustomers().get(0).getPrefix();
				} catch (Exception e) {
					cusPrefix = "";
				}
				try {
					if (customerSrhResponse.getCustomers().get(0).getSuffix() != null)
						cusSuffix = customerSrhResponse.getCustomers().get(0).getSuffix();
				} catch (Exception e) {
					cusSuffix = "";
				}
				cusFirstName = customerSrhResponse.getCustomers().get(0).getFirstName();
				cusLastName = customerSrhResponse.getCustomers().get(0).getLastName();
				try {
					cusMiddleName = customerSrhResponse.getCustomers().get(0).getMiddleName();
				} catch (Exception e) {
					cusMiddleName = "";
				}
				cusDOB = customerSrhResponse.getCustomers().get(0).getDob();
				cusGender = customerSrhResponse.getCustomers().get(0).getGender();
				cusMartialStatus = customerSrhResponse.getCustomers().get(0).getMaritalStatus();
				cusMembership = customerSrhResponse.getCustomers().get(0).getMemNum();
				int totalPhoneList = customerSrhResponse.getCustomers().get(0).getPhoneList().size();
				if (totalPhoneList == 1)
					cusPhone = customerSrhResponse.getCustomers().get(0).getPhoneList().get(0).getPhoneFullNumber();
				else {
					for (int i = 0; i < totalPhoneList; i++) {
						String phoneType = customerSrhResponse.getCustomers().get(0).getPhoneList().get(i).getPhoneType();
						if (phoneType.equalsIgnoreCase("primaryphonenumber")) {
							cusPhone = customerSrhResponse.getCustomers().get(0).getPhoneList().get(i).getPhoneFullNumber();
							break;
						}
					}
				}
				cusEmail = customerSrhResponse.getCustomers().get(0).getEmailId();
				/*
				 * if(customerSrhResponse.getCustomers().get(0).
				 * getMembershipList().size()==1) MemberSince =
				 * customerSrhResponse.getCustomers().get(0).getMembershipList()
				 * .get(0).getPolicyInceptionDate(); else
				 * if(customerSrhResponse.getCustomers().get(0).
				 * getMembershipList().size()>1) { int
				 * memberlist=customerSrhResponse.getCustomers().get(0).
				 * getMembershipList().size(); List<String> listTodateSort = new
				 * ArrayList<String>(); for(int i=0;i<memberlist;i++) {
				 * if(customerSrhResponse.getCustomers().get(0).
				 * getMembershipList().get(i).getPolicyStatus().equals("ACTIVE")
				 * ){ membersincedate =
				 * customerSrhResponse.getCustomers().get(0).getMembershipList()
				 * .get(i).getPolicyInceptionDate();
				 * listTodateSort.add(membersincedate);} else
				 * if(customerSrhResponse.getCustomers().get(0).
				 * getMembershipList().get(i).getPolicyStatus().equals(
				 * "CONVERTED")){ membersincedate =
				 * customerSrhResponse.getCustomers().get(0).getMembershipList()
				 * .get(i).getPolicyInceptionDate();
				 * listTodateSort.add(membersincedate);} }
				 * 
				 * Collections.sort(listTodateSort);
				 * if(!listTodateSort.isEmpty())
				 * MemberSince=listTodateSort.get(0);
				 * 
				 * }
				 */
				MemberSince = customerSrhResponse.getCustomers().get(0).getMemberSince();

				int policylist = customerSrhResponse.getCustomers().get(0).getPolicyList().size();
				List<String> listTodateSort = new ArrayList<String>();
				for (int i = 0; i < policylist; i++) {
					if (customerSrhResponse.getCustomers().get(0).getPolicyList().get(i).getPolicyStatus().equals("ACTIVE")) {
						policyInsureddate = customerSrhResponse.getCustomers().get(0).getPolicyList().get(i).getTermEffectiveDate();
						listTodateSort.add(policyInsureddate);
					}
				}
				Collections.sort(listTodateSort);
				if (!listTodateSort.isEmpty())
					policyInsureddate = listTodateSort.get(0);
				int totalAddsList = customerSrhResponse.getCustomers().get(0).getPartyAdresses().size();
				for (int i = 0; i < totalAddsList; i++) {
					String addsType = customerSrhResponse.getCustomers().get(0).getPartyAdresses().get(i).getAddressType();
					if (addsType.equalsIgnoreCase("home")) {
						String address2 = customerSrhResponse.getCustomers().get(0).getPartyAdresses().get(i).getAddress2();
						if (address2 == null)
							cusResidentalAddress = customerSrhResponse.getCustomers().get(0).getPartyAdresses().get(i).getAddress1() + ", "
									+ customerSrhResponse.getCustomers().get(0).getPartyAdresses().get(i).getCity() + ", "
									+ customerSrhResponse.getCustomers().get(0).getPartyAdresses().get(i).getState() + ", "
									+ customerSrhResponse.getCustomers().get(0).getPartyAdresses().get(i).getZipCode();
						else
							cusResidentalAddress = customerSrhResponse.getCustomers().get(0).getPartyAdresses().get(i).getAddress1() + ", " + address2 + ", "
									+ customerSrhResponse.getCustomers().get(0).getPartyAdresses().get(i).getCity() + ", "
									+ customerSrhResponse.getCustomers().get(0).getPartyAdresses().get(i).getState() + ", "
									+ customerSrhResponse.getCustomers().get(0).getPartyAdresses().get(i).getZipCode();

					}
					if (addsType.equalsIgnoreCase("mailing")) {
						String address2 = customerSrhResponse.getCustomers().get(0).getPartyAdresses().get(i).getAddress2();
						if (address2 == null)
							cusMailingAddress = customerSrhResponse.getCustomers().get(0).getPartyAdresses().get(i).getAddress1() + ", "
									+ customerSrhResponse.getCustomers().get(0).getPartyAdresses().get(i).getCity() + ", "
									+ customerSrhResponse.getCustomers().get(0).getPartyAdresses().get(i).getState() + ", "
									+ customerSrhResponse.getCustomers().get(0).getPartyAdresses().get(i).getZipCode();
						else if (address2 != null) {
							cusMailingAddress = customerSrhResponse.getCustomers().get(0).getPartyAdresses().get(i).getAddress1() + ", " + address2 + ", "
									+ customerSrhResponse.getCustomers().get(0).getPartyAdresses().get(i).getCity() + ", "
									+ customerSrhResponse.getCustomers().get(0).getPartyAdresses().get(i).getState() + ", "
									+ customerSrhResponse.getCustomers().get(0).getPartyAdresses().get(i).getZipCode();
					//		dataTable.putData("RetriveCustomerSearchDetails", "Street2", address2);
						}
					}
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");
				Date dobDate = null;
				try {
					dobDate = sdf.parse(cusDOB);
					cusDOB = sdf2.format(dobDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Calendar dob = Calendar.getInstance();
				dob.setTime(dobDate);
				Calendar today = Calendar.getInstance();
				int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
				if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR))
					age--;
				cusAge = Integer.toString(age);

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
/*
		dataTable.putData("RetriveCustomerSearchDetails", "Prefix", cusPrefix);
		dataTable.putData("RetriveCustomerSearchDetails", "Suffix", cusSuffix);
		dataTable.putData("RetriveCustomerSearchDetails", "MiddleName", cusMiddleName);
		dataTable.putData("RetriveCustomerSearchDetails", "MailingAddress", cusMailingAddress);
		dataTable.putData("RetriveCustomerSearchDetails", "Age", cusAge);
		dataTable.putData("RetriveCustomerSearchDetails", "Gender", cusGender);
		dataTable.putData("RetriveCustomerSearchDetails", "PhoneNumber", cusPhone);
		dataTable.putData("RetriveCustomerSearchDetails", "PrimaryPhone", cusPhone);
		dataTable.putData("RetriveCustomerSearchDetails", "PrimaryResidentialAddress", cusResidentalAddress);
		dataTable.putData("RetriveCustomerSearchDetails", "DOB", cusDOB);
		dataTable.putData("RetriveCustomerSearchDetails", "MartialStatus", cusMartialStatus);
		dataTable.putData("RetriveCustomerSearchDetails", "Email", cusEmail);
		dataTable.putData("RetriveCustomerSearchDetails", "Membership", cusMembership);
		dataTable.putData("RetriveCustomerSearchDetails", "MemberSince", MemberSince);
		dataTable.putData("RetriveCustomerSearchDetails", "InsuredSince", policyInsureddate);
		*/
		if (cusMailingAddress != null && !cusMailingAddress.equals("")) {

			int strlen = cusMailingAddress.split(",").length;
		//	dataTable.putData("RetriveCustomerSearchDetails", "Street", cusMailingAddress.split(",")[0].trim());
		//	dataTable.putData("RetriveCustomerSearchDetails", "City", cusMailingAddress.split(",")[strlen - 3].trim());
		//	dataTable.putData("RetriveCustomerSearchDetails", "State", cusMailingAddress.split(",")[strlen - 2].trim());
			// dataTable.putData("RetriveCustomerSearchDetails",
			// "Zipcode",cusResidentalAddress.split(",")[strlen-1].trim());
			if (cusDOB != null) {
				System.out.println("REST Response - DOB of customer -" + cusDOB);
				System.out.println("REST Response - Customer details are retrived successfully");
				
			} else {
				fail("REST Response - Error in Rretrieve Customer Details servic");

			}

		}
	}

}