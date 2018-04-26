package webelementrepository.MyPolicy;

import org.openqa.selenium.By;

import stepDefinitions.common.MasterStepDefs;

public class AddVehiclePage extends MasterStepDefs {

	
	public By AddVehicleCollapseArrow =By.xpath("//div[@class='policies-row add-vehicle-container']//*[name()='svg']//*[name()='use']");

	public By VechicleListTitle = By.xpath( "//div[contains(@class,'vehicle-list')]//div[@class='card-title']//h3");

	
	
	
	public By AddVehicleCancelButtonVisible = By.xpath("//div[@class='interview-section']//span[@class='cancel-button']");
	public By AddVehicleBackButtonVisible = By.xpath("//div[@class='interview-section']//input[@class='back-button']");
	public By AddVehicleActionBlockTitle = By.xpath( "//div[@class='interview-section']//span[@class='title-text']");
	public By AddVehicleActionBlockSubTitle = By.xpath("//div[@class='interview-section']//span[@class='sub-text']");
	public By AddVehicleDrawerViewText = By.xpath("//div[contains(@class,'interview-section')]//div[@class='add-vehicle-lower-section']//strong");
	public By AddVehicleOwnershipTypeQuestionOptionsOwned = By.xpath("//div[contains(@class,'interview-section')]//li[@data-analytics-label='OWN-ListButton']");
	public By AddVehicleOwnershipTypeQuestionOptionsFinanced = By.xpath("//div[contains(@class,'interview-section')]//li[@data-analytics-label='FNC-ListButton']");
	public By AddVehicleOwnershipTypeQuestionOptionsLeased = By.xpath("//div[contains(@class,'interview-section')]//li[@data-analytics-label='LSD-ListButton']");
	public By AddVehicleDrawerViewYesText = By.xpath("//div[contains(@class,'interview-section')]//div[@class='add-vehicle-lower-section']//button[contains(@value,'YES')]");
	public By AddVehicleDrawerViewNoText = By.xpath("//div[contains(@class,'interview-section')]//div[@class='add-vehicle-lower-section']//button[contains(@value,'NO')]");	
	
	
	public By AddVehicleSummaryPageActionBlockTitle = By.xpath("//div[@class='summary-section']//span[@class='title-text']");
	public By AddVehicleSummaryPageActionBlockSubTitle = By.xpath("//div[@class='summary-section']//span[@class='sub-text']");
	public By AddVehicleSummarypageCancelButtonVisible = By.xpath("//div[@class='summary-section']//span[@class='cancel-button']");
	public By AddVehicleSummarypageBackButtonVisible = By.xpath("//div[@class='summary-section']//input[@class='back-button']");
	public By AddVehicleQuestionSummaryOwnershipTypeLabel = By.xpath("//div[@class='summary-section']//div[@class='form'][1]//div[@class='show-inline']");
	public By AddVehicleQuestionSummaryOwnershipTypeValue = By.xpath("//div[@class='summary-section']//div[@class='form'][1]//div[@class='show-inline right']");
	public By AddVehicleQuestionSummaryUsageTypeLabel = By.xpath("//div[@class='summary-section']//div[@class='form'][2]//div[@class='show-inline']");
	public By AddVehicleQuestionSummaryUsageTypeValue = By.xpath("//div[@class='summary-section']//div[@class='form'][2]//div[@class='show-inline right']");
	public By AddVehicleQuestionSummaryLocatedLabel = By.xpath("//div[@class='summary-section']//div[@class='form'][3]//div[@class='show-inline']");
	public By AddVehicleQuestionSummaryLocatedValue = By.xpath("//div[@class='summary-section']//div[@class='form'][3]//div[@class='show-inline right']");
	public By AddVehicleQuestionSummarySalvagedLabel = By.xpath("//div[@class='summary-section']//div[@class='form'][4]//div[@class='show-inline']");
	public By AddVehicleQuestionSummarySalvagedValue = By.xpath("//div[@class='summary-section']//div[@class='form'][4]//div[@class='show-inline right']");
	public By AddVehicleQuestionSummaryAllRegisteredOwnersLabel = By.xpath("//div[@class='summary-section']//div[@class='form'][5]//div[@class='show-inline']");
	public By AddVehicleQuestionSummaryAllRegisteredOwnersValue = By.xpath("//div[@class='summary-section']//div[@class='form'][5]//div[@class='show-inline right']");
	public By AddVehicleQuestionSummaryAntiTheftLabel = By.xpath("//div[@class='summary-section']//div[@class='form'][6]//div[@class='show-inline']");
	public By AddVehicleQuestionSummaryAntiTheftValue = By.xpath("//div[@class='summary-section']//div[@class='form'][6]//div[@class='show-inline right']");
	public By AddVehicleQuestionSummaryAddContinueButton = By.xpath("//button[@id='addAndContinueButton']");
	public By AddVehicleContinueButton = By.xpath("//button[@id='continueButton']");
	public String EndorsementPendingElm = "//div[contains(@class,'aaaie-badge--message')]";
	
	
	
	

	
	
	
	

}