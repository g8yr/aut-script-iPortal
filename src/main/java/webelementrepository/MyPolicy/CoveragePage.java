package webelementrepository.MyPolicy;

import org.openqa.selenium.By;

import stepDefinitions.common.MasterStepDefs;

public class CoveragePage extends MasterStepDefs {

	public By CoverageSummaryText =By.xpath("//div[contains(@class,'coverage-title')][1]/span[1]");
	public By CoverageSummarySubText =By.xpath("//div[contains(@class,'coverage-title')][1]/span[2]");
	

	public static final String CoverageBlockGroup = "//div[@class='coverage-wrapper clearfix']";
	public static final String CoverageDescriptionBlockTitleText = "//div[contains(@class,'coverage-description-block')]//span[contains(@class,'coverage-title-text')]";
	public static final String CoverageDescriptionBlockSubText = "//div[contains(@class,'coverage-description-block')]//span[contains(@class,'coverage-sub-text')]";
	public static final String CoverageValueBlockTitleText = "//div[contains(@class,'coverage-value-block')]//span[contains(@class,'coverage-title-text')]";
	public static final String CoverageValueBlockSubText  = "//div[contains(@class,'coverage-value-block')]//span[contains(@class,'coverage-sub-text')]";
	
	public static final String CoverageVehicleModel = "//div[contains(@class,'coverage-container')]//h2";
	public static final String CoverageVehicleVIN = "//div[contains(@class,'coverage-container')]//span[contains(@class,'vin-box sub-text')]";
	
	public static final String CoveragePendingTag = "//div[contains(@class,'coverage-container')]//div[contains(@class,'aaaie-badge--message')]";
 
	
	public By CoverageExcessElectrionicEquipment =By.xpath("??");
	public By CoverageNewCarAdded =By.xpath("??");
	public By CoverageCustomizedEquipment =By.xpath("??");
	
	public By CoverageContinueButton =By.xpath("//button[@id='continueButton']");
	
	
	
	

	
	
	
	

}