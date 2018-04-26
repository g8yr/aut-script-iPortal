package webelementrepository.MyPolicy;

import org.openqa.selenium.By;

import stepDefinitions.common.MasterStepDefs;

public class DriverVehicleAssignmentPage extends MasterStepDefs {

	public By DriverVehicleAssignmentSummaryText =By.xpath("//div[contains(@class,'vehicle-block')][1]/span[1]");
	public By DriverVehicleAssignmentSummarySubText =By.xpath("//div[contains(@class,'vehicle-block')][1]/span[2]");

	public static final String DriverVechicleAssignmentBlockGroup = "//div[contains(@class,'vehicle-block assignment-section')]";
	public static final String DriverVechicleAssignmentModel = "//span[contains(@class,'title-text vehicle-model')]";
	public static final String DriverVechicleAssignmentDriverDescription = "//span[contains(@class,'driver-details')]";
	public static final String DriverVechicleAssignmentPendingTag = "//div[contains(@class,'all-caps aaaie-badge')]";

	
	public By DriverVehicleAssignmentContinueButton =By.xpath("//button[contains(@class,'continue-button')]	");
	
	
	
	

	
	
	
	

}