package businesscomponents.mypolicy;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import services.rest.apiclients.ValidateEndorsement;
import services.rest.apiclients.ValidateVehicleList;
import stepDefinitions.common.MasterStepDefs;
import webelementrepository.MyPolicy.MakePolicyChangesPage;
import webelementrepository.MyPolicy.PaymentPage;
import MyPolicyContentSpec.EndorsementPageContent;
import MyPolicyContentSpec.VehiclesPageContent;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.StaticValue;
import com.Cucumber.supportLibraries.Util;
import com.Cucumber.supportLibraries.Webaction;

public class MakePolicyChangesFunctions extends MasterStepDefs {
	static Logger log = Logger.getLogger(PaymentPage.class);
	WebDriver driver = DriverManager.getWebDriver();
	Webaction action = new Webaction();
	PaymentPage paymentpage = new PaymentPage();
	ValidateEndorsement endorsementWebservice = new ValidateEndorsement();
	MakePolicyChangesPage makepolicychangepage = new MakePolicyChangesPage();
	EndorsementPageContent endorsementpagecontent = new EndorsementPageContent();
	VehiclesPageContent vehiclepagecontent = new VehiclesPageContent();
	ValidateVehicleList vehicleslist =new ValidateVehicleList();
	GeneralFunctions GenFns = new GeneralFunctions();
	int TotalNoOfPolicy = StaticValue.zero;
	int TotalNoOfPolicyWithMinimumDue = StaticValue.zero;
	int TotalNoOfPolicyPaidFull = StaticValue.zero;
	int CntOfPolicyWithMinimumDueForGivenInstance = StaticValue.zero;

	// Function to Ensure the Page has the Vehicle details
	public void ValidateTheVehiclePageLanded() {
//		action.waitForElement(makeolicychangepage.CardContentPolicyNumberElm);
//		String policynumberfrommakepolicychange = action.GetText(makeolicychangepage.CardContentPolicyNumberElm);
//		GenFns.chkTheContent(policynumberfrommakepolicychange, "COSS900029141", StaticValue.PolicyNumber);
		action.waitForElement(makepolicychangepage.VehicleModelElm);
		//currentScenario.embed(Util.takeScreenshot(driver), "image/png");
	}

	// Function to Ensure the Endorsement page is displayed as per specifications
	public void ValidateTheEnorsementPageAsPerSpec() {
		ValidateThePageHeaderInEnorsementPage();
		ValidateTheCardQuestionInEnorsementPage();
		ValidateTheAddRemoveIconInEnorsementPage();
		ValidateTheAddRemoveTextInEnorsementPage();
		ValidateTheDescriptionTextInEnorsementPage();
		ValidateTheDescriptionSubTextInEnorsementPage();
		ValidateTheDisclaimerText1InEnorsementPage();
		ValidateTheDisclaimerText2InEnorsementPage();

	}

	// Function to Ensure the Page Header in Endorsement page
	public void ValidateThePageHeaderInEnorsementPage() {
		//currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		action.waitForElement(makepolicychangepage.EnodorsementpageTitleElm);
		String makepolicychangeheader = action.GetText(makepolicychangepage.EnodorsementpageTitleElm);
		GenFns.chkTheContent(makepolicychangeheader, endorsementpagecontent.MAKEPOLICYCHANGESHeader,
				StaticValue.EndorsementPageHeader);

	}

	// Function to Ensure the Card Question in Endorsement page
	public void ValidateTheCardQuestionInEnorsementPage() {
		action.waitForElement(makepolicychangepage.WhatWouldYouLikeToDoTodayElm);
		String WhatWouldYouLikeToDoToday = action.GetText(makepolicychangepage.WhatWouldYouLikeToDoTodayElm);
		GenFns.chkTheContent(WhatWouldYouLikeToDoToday, endorsementpagecontent.CardQuestion,
				StaticValue.EndorsementPageCardQuestion);
	}

	// Function to Ensure the Add Remove Icon in Endorsement page
	public void ValidateTheAddRemoveIconInEnorsementPage() {
		action.waitForElement(makepolicychangepage.AddRemoveVechicleImageElm);
	}

	// Function to Ensure the AddRemoveText in Endorsement page
	public void ValidateTheAddRemoveTextInEnorsementPage() {
		action.waitForElement(makepolicychangepage.AddRemoveVechicleTextElm);
		String addremovetext = action.GetText(makepolicychangepage.AddRemoveVechicleTextElm);
		String Actaddremovetext = addremovetext.replaceAll("(\n)", " ");
		GenFns.chkTheContent(Actaddremovetext, endorsementpagecontent.AddRemoveVehicles, endorsementpagecontent.AddRemoveVehiclesInMobile,
				StaticValue.EndorsementPageAddRemoveVehicle);
	}

	// Function to Ensure the DescriptionText in Endorsement page
	public void ValidateTheDescriptionTextInEnorsementPage() {
		action.waitForElement(makepolicychangepage.AddRemoveVechicleTextElm);
		String DescriptionText = action.GetText(makepolicychangepage.DescriptionTextElm);
		GenFns.chkTheContent(DescriptionText, endorsementpagecontent.DescriptionText1, endorsementpagecontent.DescriptionText1InMobile, 
				StaticValue.EndorsementPageDescription1);

	}

	// Function to Ensure the DescriptionText in Endorsement page
	public void ValidateTheDescriptionSubTextInEnorsementPage() {
		action.waitForElement(makepolicychangepage.AddRemoveVechicleTextElm);
		String DescriptionsubText = action.GetText(makepolicychangepage.DescriptionSubTextElm);
		GenFns.chkTheContent(DescriptionsubText, endorsementpagecontent.DescriptionText2,
				StaticValue.EndorsementPageDescription2);
	}

	// Function to Ensure the DisclaimerText1 in Endorsement page
	public void ValidateTheDisclaimerText1InEnorsementPage() {
		action.waitForElement(makepolicychangepage.AddRemoveVechicleTextElm);
		String DisclaimerText1 = action.GetText(makepolicychangepage.DisclaimerElm1);
		GenFns.chkTheContent(DisclaimerText1, endorsementpagecontent.DisclaimerText1, StaticValue.EndorsementPageDisclaimer1);

	}

	// Function to Ensure the DisclaimerText2 in Endorsement page
	public void ValidateTheDisclaimerText2InEnorsementPage() {
		action.waitForElement(makepolicychangepage.AddRemoveVechicleTextElm);
		String DisclaimerText2 = action.GetText(makepolicychangepage.DisclaimerElm2);
		GenFns.chkTheContent(DisclaimerText2, endorsementpagecontent.DisclaimerText2, StaticValue.EndorsementPageDisclaimer2);
	}



	// Function to Ensure the Vehicles Page is displayed as per specifications
		public void ValidateTheVehcileListAsPerSpec(String PolicyNumber) {
			ValidateThePageHeaderInEnorsementPage();
			ValidatePolicyNumberVehicleDetailsSection(PolicyNumber);
			ValidateTextInVehicleDetailsSection();
			ValidateSubTextInVehicleDetailsSection();
		}
		
		// Function to Ensure the Policy number is displayed in Vehicles Page
		public void ValidatePolicyNumberVehicleDetailsSection(String PolicyNumber) {
			//currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			action.waitForElement(makepolicychangepage.CardContentPolicyNumberElm);
			String policynumberfrommakepolicychange = action.GetText(makepolicychangepage.CardContentPolicyNumberElm);
			GenFns.chkTheContent(policynumberfrommakepolicychange, PolicyNumber, StaticValue.PolicyNumber);
		}
		
		// Function to Ensure the DescriptionText in Vehicles Page
		public void ValidateTextInVehicleDetailsSection() {
			action.waitForElement(makepolicychangepage.VehicleSecDescriptionTextElm);
			String DescriptionText = action.GetText(makepolicychangepage.VehicleSecDescriptionTextElm);
			GenFns.chkTheContent(DescriptionText, vehiclepagecontent.VehiclesDescriptionText1,
					StaticValue.VehiclePageDescription1);
		}
		
		// Function to Ensure the DescriptionSubtextText in Vehicles Page
		public void ValidateSubTextInVehicleDetailsSection() {
			action.waitForElement(makepolicychangepage.VehicleSecDescriptionSubTextElm);
			String DescriptionText = action.GetText(makepolicychangepage.VehicleSecDescriptionSubTextElm);
			GenFns.chkTheContent(DescriptionText, vehiclepagecontent.VehiclesDescriptionSubText,
					StaticValue.VehiclePageDescription2);
		}
		
//		// Function to Ensure Display of Add Vehcile Hyperlink in Vehicles Page
//				public void ValidateAddVehcileHyperlink() {
//					action.waitForElement(makepolicychangepage.AddVehicleHyperlink);
//		}
//		
//		// Function to Click Add Vehcile Hyperlink in Vehicles Page
//				public void ClickAddVehcileHyperlink() {
//					action.ClickElement(makepolicychangepage.AddVehicleHyperlink);
//		}
//		
//		// Function to Ensure the AddVehcileHyperlinkText in Vehicles Page
//				public void ValidateAddVehcileHyperlinkText() {
//					action.waitForElement(makepolicychangepage.AddVehicleHyperlinktext);
//					String DescriptionText = action.GetText(makepolicychangepage.AddVehicleHyperlinktext);
//					GenFns.chkTheContent(DescriptionText, vehiclepagecontent.AddVehicleLinkText,
//							StaticValue.VehiclePageAddVehicleDescription);
//		}
				
				
				
	

		
	// ***********************************************************
	// End of MakePolicyChange PoliciesFunctions
	// ***********************************************************
				
				
}
