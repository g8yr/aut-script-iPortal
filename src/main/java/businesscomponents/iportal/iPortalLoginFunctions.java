package businesscomponents.iportal;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import webelementrepository.MyPolicy.DashboardPage;
import webelementrepository.iPortal.iPortalLoginPage;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.IPortalStaticValue;
import com.Cucumber.supportLibraries.Util;
import com.Cucumber.supportLibraries.Webaction;
import com.Cucumber.supportLibraries.custom.ReadCSVFileFunctions;

public class iPortalLoginFunctions extends Webaction  {
	WebDriver driver = DriverManager.getWebDriver();
	IPortalStaticValue iportalstaticvalue = new IPortalStaticValue(); 
	boolean result;
	ReadCSVFileFunctions readcsvfilefunctions = new ReadCSVFileFunctions();
	iPortalLoginPage iPortalLoginPage = new iPortalLoginPage();
	Webaction action = new Webaction();
	int iterator1 = 0;
	int iterator2 = 6;

	public void LoadloginPage() throws IOException {
	    //driver.get("https://mypolicy-evalue.digital.pncie.com/login");
	//	driver.get("https://portal-evalue2.apps.prod.pdc.digital.csaa-insurance.aaa.com/dashboard?DIRECT-ACCESS-ID=bdddd157-9835-4852-b506-d7c2eed4f409-1519213470&AUTHORIZATION=eyJhbGciOiJIUzI1NiIsImtpZCI6IjAxIn0.eyJzY29wZSI6WyJudWxsIl0sImNsaWVudF9pZCI6Im51bGwiLCJkaXJlY3RfYWNjZXNzX2lkIjoiYmRkZGQxNTctOTgzNS00ODUyLWI1MDYtZDdjMmVlZDRmNDA5LTE1MTkyMTM0NzAiLCJjZHNfamRiY19tYWlsIjoiZXZhbHVlb2Zmc2hvcmUrOTAxNkBnbWFpbC5jb20iLCJleHAiOjE1MjExMzA4MTN9.0-HaHgkR43h43RAay-Ufrns-h0esvWnxmRxoSRo6C9E&FIRST-NAME=Jim&LAST-NAME=Jose&O-AUTH=eyJhbGciOiJIUzI1NiIsImtpZCI6IjAxIn0.eyJzY29wZSI6WyJudWxsIl0sImNsaWVudF9pZCI6Im51bGwiLCJkaXJlY3RfYWNjZXNzX2lkIjoiYmRkZGQxNTctOTgzNS00ODUyLWI1MDYtZDdjMmVlZDRmNDA5LTE1MTkyMTM0NzAiLCJjZHNfamRiY19tYWlsIjoiZXZhbHVlb2Zmc2hvcmUrOTAxNkBnbWFpbC5jb20iLCJleHAiOjE1MjExMzA4MTN9.0-HaHgkR43h43RAay-Ufrns-h0esvWnxmRxoSRo6C9E");
		driver.get("https://portal-evalue2.apps.prod.pdc.digital.csaa-insurance.aaa.com/dashboard?DIRECT-ACCESS-ID=1404af02-9df5-4eea-8001-5fe356e83fb0-1517815170&AUTHORIZATION=eyJhbGciOiJIUzI1NiIsImtpZCI6IjAxIn0.eyJzY29wZSI6WyJudWxsIl0sImNsaWVudF9pZCI6Im51bGwiLCJkaXJlY3RfYWNjZXNzX2lkIjoiMTQwNGFmMDItOWRmNS00ZWVhLTgwMDEtNWZlMzU2ZTgzZmIwLTE1MTc4MTUxNzAiLCJjZHNfamRiY19tYWlsIjoiZXZhbHVlb2Zmc2hvcmUrODAwNEBnbWFpbC5jb20iLCJleHAiOjE1MjExMTkxNjd9.AFzJTRSVqTUOfoKHzp9VkrnpYpEHv3SOmOtVtDD-1Vw&FIRST-NAME=Jose&LAST-NAME=Miller&O-AUTH=eyJhbGciOiJIUzI1NiIsImtpZCI6IjAxIn0.eyJzY29wZSI6WyJudWxsIl0sImNsaWVudF9pZCI6Im51bGwiLCJkaXJlY3RfYWNjZXNzX2lkIjoiMTQwNGFmMDItOWRmNS00ZWVhLTgwMDEtNWZlMzU2ZTgzZmIwLTE1MTc4MTUxNzAiLCJjZHNfamRiY19tYWlsIjoiZXZhbHVlb2Zmc2hvcmUrODAwNEBnbWFpbC5jb20iLCJleHAiOjE1MjExMTkxNjd9.AFzJTRSVqTUOfoKHzp9VkrnpYpEHv3SOmOtVtDD-1Vw");
	//	driver.get("https://portal-evalue2.apps.prod.pdc.digital.csaa-insurance.aaa.com/dashboard?DIRECT-ACCESS-ID=5468a658-afe0-4ac7-95f1-2bee32b14132-1518153233&AUTHORIZATION=eyJhbGciOiJIUzI1NiIsImtpZCI6IjAxIn0.eyJzY29wZSI6WyJudWxsIl0sImNsaWVudF9pZCI6Im51bGwiLCJkaXJlY3RfYWNjZXNzX2lkIjoiNTQ2OGE2NTgtYWZlMC00YWM3LTk1ZjEtMmJlZTMyYjE0MTMyLTE1MTgxNTMyMzMiLCJjZHNfamRiY19tYWlsIjoiZXZhbHVlb2Zmc2hvcmUrOTAwMUBnbWFpbC5jb20iLCJleHAiOjE1MjExMzEyOTJ9.8HrpW66FDEh2WbOZrVB18pvu8mtO55JTDxGQNZZyIzA&FIRST-NAME=Peter&LAST-NAME=Jose&O-AUTH=eyJhbGciOiJIUzI1NiIsImtpZCI6IjAxIn0.eyJzY29wZSI6WyJudWxsIl0sImNsaWVudF9pZCI6Im51bGwiLCJkaXJlY3RfYWNjZXNzX2lkIjoiNTQ2OGE2NTgtYWZlMC00YWM3LTk1ZjEtMmJlZTMyYjE0MTMyLTE1MTgxNTMyMzMiLCJjZHNfamRiY19tYWlsIjoiZXZhbHVlb2Zmc2hvcmUrOTAwMUBnbWFpbC5jb20iLCJleHAiOjE1MjExMzEyOTJ9.8HrpW66FDEh2WbOZrVB18pvu8mtO55JTDxGQNZZyIzA");
//		driver.get("https://portal-evalue2.apps.prod.pdc.digital.csaa-insurance.aaa.com/dashboard?DIRECT-ACCESS-ID=1404af02-9df5-4eea-8001-5fe356e83fb0-1517815170&AUTHORIZATION=eyJhbGciOiJIUzI1NiIsImtpZCI6IjAxIn0.eyJzY29wZSI6WyJudWxsIl0sImNsaWVudF9pZCI6Im51bGwiLCJkaXJlY3RfYWNjZXNzX2lkIjoiMTQwNGFmMDItOWRmNS00ZWVhLTgwMDEtNWZlMzU2ZTgzZmIwLTE1MTc4MTUxNzAiLCJjZHNfamRiY19tYWlsIjoiZXZhbHVlb2Zmc2hvcmUrODAwNEBnbWFpbC5jb20iLCJleHAiOjE1MjExMTkxNjd9.AFzJTRSVqTUOfoKHzp9VkrnpYpEHv3SOmOtVtDD-1Vw&FIRST-NAME=Jose&LAST-NAME=Miller&O-AUTH=eyJhbGciOiJIUzI1NiIsImtpZCI6IjAxIn0.eyJzY29wZSI6WyJudWxsIl0sImNsaWVudF9pZCI6Im51bGwiLCJkaXJlY3RfYWNjZXNzX2lkIjoiMTQwNGFmMDItOWRmNS00ZWVhLTgwMDEtNWZlMzU2ZTgzZmIwLTE1MTc4MTUxNzAiLCJjZHNfamRiY19tYWlsIjoiZXZhbHVlb2Zmc2hvc");
		//driver.get("https://portal-evalue2.apps.prod.pdc.digital.csaa-insurance.aaa.com/dashboard?DIRECT-ACCESS-ID=0305862a-bf86-44d6-ac70-e52b0ea0b739-1519206069&AUTHORIZATION=eyJhbGciOiJIUzI1NiIsImtpZCI6IjAxIn0.eyJzY29wZSI6WyJudWxsIl0sImNsaWVudF9pZCI6Im51bGwiLCJkaXJlY3RfYWNjZXNzX2lkIjoiMDMwNTg2MmEtYmY4Ni00NGQ2LWFjNzAtZTUyYjBlYTBiNzM5LTE1MTkyMDYwNjkiLCJjZHNfamRiY19tYWlsIjoiZXZhbHVlb2Zmc2hvcmUrOTAxNEBnbWFpbC5jb20iLCJleHAiOjE1MjExMzEzNzB9.KXrEof_IaKCmdzrpAEm5n7acK8DI85RITpKTPAS4vJo&FIRST-NAME=Princy&LAST-NAME=George&O-AUTH=eyJhbGciOiJIUzI1NiIsImtpZCI6IjAxIn0.eyJzY29wZSI6WyJudWxsIl0sImNsaWVudF9pZCI6Im51bGwiLCJkaXJlY3RfYWNjZXNzX2lkIjoiMDMwNTg2MmEtYmY4Ni00NGQ2LWFjNzAtZTUyYjBlYTBiNzM5LTE1MTkyMDYwNjkiLCJjZHNfamRiY19tYWlsIjoiZXZhbHVlb2Zmc2hvcmUrOTAxNEBnbWFpbC5jb20iLCJleHAiOjE1MjExMzEzNzB9.KXrEof_IaKCmdzrpAEm5n7acK8DI85RITpKTPAS4vJo");
		//driver.get("https://portal-evalue2.apps.prod.pdc.digital.csaa-insurance.aaa.com/endorsement?policyNumber=COSS900029141");
		
		//make policy button not visible url
		//driver.get("https://portal-evalue2.apps.prod.pdc.digital.csaa-insurance.aaa.com/dashboard?DIRECT-ACCESS-ID=02ba0070-4048-47fd-a688-ab1b87dde39b-1513854379&AUTHORIZATION=eyJhbGciOiJIUzI1NiIsImtpZCI6IjAxIn0.eyJzY29wZSI6WyJudWxsIl0sImNsaWVudF9pZCI6Im51bGwiLCJkaXJlY3RfYWNjZXNzX2lkIjoiMDJiYTAwNzAtNDA0OC00N2ZkLWE2ODgtYWIxYjg3ZGRlMzliLTE1MTM4NTQzNzkiLCJjZHNfamRiY19tYWlsIjoiZGlnaXRhbHNlcnZpY2VzMzIzMTdAZ21haWwuY29tIiwiZXhwIjoxNTIxMjA4NDU5fQ.2HoEESpMDPXz3Mca76m4m6fKqMchCPelFKFvsbaQMEM&FIRST-NAME=Lee&LAST-NAME=Canton&O-AUTH=eyJhbGciOiJIUzI1NiIsImtpZCI6IjAxIn0.eyJzY29wZSI6WyJudWxsIl0sImNsaWVudF9pZCI6Im51bGwiLCJkaXJlY3RfYWNjZXNzX2lkIjoiMDJiYTAwNzAtNDA0OC00N2ZkLWE2ODgtYWIxYjg3ZGRlMzliLTE1MTM4NTQzNzkiLCJjZHNfamRiY19tYWlsIjoiZGlnaXRhbHNlcnZpY2VzMzIzMTdAZ21haWwuY29tIiwiZXhwIjoxNTIxMjA4NDU5fQ.2HoEESpMDPXz3Mca76m4m6fKqMchCPelFKFvsbaQMEM");
		driver.manage().window().maximize();
		/*Screenshot 
		
		  final Screenshot screenshot = new AShot().shootingStrategy(
	                new ViewportPastingStrategy(500)).takeScreenshot(driver);
	 
	        final BufferedImage image = screenshot.getImage();
	        try {
				ImageIO.write(image, "png", new File(
				        "D://Mar 26//ds-automation-bdd//target//allure-results//"
				                + "AShot_BBC_Entire.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        BufferedImage originalImage = 
                    ImageIO.read(new File("D://Mar 26//ds-automation-bdd//target//allure-results//"
				                + "AShot_BBC_Entire.png"));
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ImageIO.write( originalImage, "png", baos );
	        baos.flush();
	    	byte[] imageInByte = baos.toByteArray();
	    	baos.close();
	  //     save(image);
	       
	      //  imageInByte = image.toByteArray();
	      currentScenario.embed(imageInByte, "image/png");
	       */
		currentScenario.embed(Util.takeScreenshot(driver),
			"image/png");

//		assertTrue(driver.getTitle().contains("My Policy")
//				|| driver.getTitle().contains("Sign-on"));
		
	}
	


//	public void LoadloginPageWithID() {
//			driver.get("https://mypolicy-evalue.digital.pncie.com/login");
//			driver.manage().window().maximize();
//			currentScenario.embed(Util.takeScreenshot(driver),
//					"image/png");
//			assertTrue(driver.getTitle().contains("My Policy"));
//			
//		}
	public void loadiPortalLoginPage() {
		driver.get("https://iportal-evalue.digital.csaa-insurance.aaa.com");
		driver.manage().window().maximize();
		currentScenario.embed(Util.takeScreenshot(driver),
				"image/png");
		//assertTrue(driver.getTitle().contains("User ID"));
		
	}
//	public void entercredential() {
//		
//		ArrayList<CsvInputFileLayoutPage> data = readcsvfilefunctions.readCSVfileIntoArray();
//		System.out.println("Scenario Name is" + currentTestParameters.getScenarioName() + ";");
//		for (CsvInputFileLayoutPage jb : data) {
//			iterator1++;
//			if (iterator1 == iterator2) {
//				System.out.println("scenarioname:" + jb.getScenarioName());
//				System.out.println("userid:" + jb.getUserId());
//				System.out.println("password:" + jb.getPassword());
//				System.out.println("policynumber:" + jb.getPolicyNumber());
//			}
//
//		}
//		
//		
//	}
	
	public void enterLoginDetails(String userName,String password )
	{
		action.waitForElement(iPortalLoginPage.EMAIL);	
		action.EnterText    (iPortalLoginPage.EMAIL , userName);		
		action.EnterText    (iPortalLoginPage.PASSWORD, password);
		action.ClickElement (iPortalLoginPage.SIGNIN);
	}
	
}