package com.Cucumber.supportLibraries.custom;


public class CsvParameterFileLayoutPage {

	public String ScenarioName;
	public String BrowserName;
	public String BrowserVersion;
	public String PlatformName;
	
	

	public String getScenarioName() {
		return ScenarioName;
	}

	public void setScenarioName(String scenarioName) {
		ScenarioName = scenarioName;
	}

	

	public String getBrowserName() {
		return BrowserName;
	}

	public void setBrowserName(String browserName) {
		BrowserName = browserName;
	}

	
	
	public String getBrowserVersion() {
		return BrowserVersion;
	}

	public void setBrowserVersion(String browserVersion) {
		BrowserVersion = browserVersion;
	}

	public String getPlatformName() {
		return PlatformName;
	}

	public void setPlatformName(String platformName) {
		PlatformName = platformName;
	}


}