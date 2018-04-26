package com.Cucumber.supportLibraries.custom;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;

import stepDefinitions.common.MasterStepDefs;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.Settings;
import com.Cucumber.supportLibraries.StaticValue;
import com.Cucumber.supportLibraries.Util;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class ReadCSVFileFunctions extends MasterStepDefs {

	// public ReadCSVFileFunctions (HashMap<String, String> SingleVehicleData) {
	// this.SingleVehicleData = SingleVehicleData;
	//
	// }

	// WebDriver driver = DriverManager.getWebDriver();
	StaticValue staticvalue = new StaticValue();


	static Logger log;

	static {
		log = Logger.getLogger(ReadCSVFileFunctions.class);
	}

	public static HashMap<String, String> SingleVehicleData = new HashMap<String, String>();
	ArrayList<CsvInputFileLayoutPage> data;
	ArrayList<CsvParameterFileLayoutPage> parameterfromcsv;	
	public String InputFilePath;
	public static String RunConfiguration;
	static Properties properties = Settings.getInstance();

	public String readCsvFile(String fieldname, String Iteration) {
		String result = StaticValue.Empty;
		String ScenarioName = StaticValue.Empty;
		int currentIteration = StaticValue.zero;
		data = readCSVfileIntoArray();
		currentTestParameters = DriverManager.getTestParameters();
		ScenarioName = currentTestParameters.getScenarioName();
		

		for (CsvInputFileLayoutPage jb : data) {
			currentIteration++;
			String key = StaticValue.Empty;

			// System.out.println("jb.getIteration():"+jb.getIteration()+":");
			// System.out.println("Iteration:"+Iteration+":");
			// System.out.println("jb.getScenarioName():"+jb.getScenarioName()+":");
			// System.out.println("ScenarioName:"+ScenarioName+":");
			// System.out.println("currentIteration):"+currentIteration+":");

			if (result != StaticValue.Empty) {
				break;
			}

			if (ScenarioName.equals(jb.getScenarioName()) && Iteration.equals(jb.getIteration())) {
				switch (fieldname) {
				case StaticValue.UserId:
					result = jb.getUserId();
					key = StaticValue.Empty;
					key = ScenarioName + "_" + StaticValue.UserId;
					SingleVehicleData.put(key, result);
					// SingleVehicleData.put(StaticValue.UserId, result);
					System.out.println("match found in CSV:" + result);
					break;
				case StaticValue.Password:
					result = jb.getPassword();
					key = StaticValue.Empty;
					key = ScenarioName + "_" + StaticValue.Password;
					SingleVehicleData.put(key, result);
					// SingleVehicleData.put(StaticValue.Password, result);
					System.out.println("match found in CSV:" + result);
					break;
				case StaticValue.PolicyNumber:
					result = jb.getPolicyNumber();
					key = StaticValue.Empty;
					key = ScenarioName + "_" + StaticValue.PolicyNumber;
					SingleVehicleData.put(key, result);
					// SingleVehicleData.put(StaticValue.PolicyNumber, result);
					System.out.println("match found in CSV:" + result);
					break;
				case StaticValue.PolicyDate:
					result = jb.getPolicyDate();
					key = StaticValue.Empty;
					key = ScenarioName + "_" + StaticValue.PolicyDate;
					SingleVehicleData.put(key, result);
					SingleVehicleData.put(StaticValue.PolicyDate, result);
					System.out.println("match found in CSV:" + result);
					break;
				case StaticValue.VehiclePurchaseDate:
					result = jb.getVehiclePurchaseDate();
					key = StaticValue.Empty;
					key = ScenarioName + "_" + StaticValue.VehiclePurchaseDate;
					SingleVehicleData.put(key, result);
					//SingleVehicleData.put(StaticValue.VehiclePurchaseDate, result);
					System.out.println("match found in CSV:" + result);
					break;
				case StaticValue.VehicleVIN:
					result = jb.getVehicleVIN();
					key = StaticValue.Empty;
					key = ScenarioName + "_" + StaticValue.VehicleVIN;
					SingleVehicleData.put(key, result);
					//SingleVehicleData.put(StaticValue.VehicleVIN, result);
					System.out.println("match found in CSV:" + result);
					break;
				case StaticValue.VehicleOwnerShipType:
					result = jb.getVehicleOwnerShipType();
					key = StaticValue.Empty;
					key = ScenarioName + "_" + StaticValue.VehicleOwnerShipType;
					SingleVehicleData.put(key, result);
					//SingleVehicleData.put(StaticValue.VehicleOwnerShipType, result);
					System.out.println("match found in CSV:" + result);
					break;
				case StaticValue.VehicleUsageTypeAsBusiness:
					result = jb.getVehicleUsageTypeAsBusiness();
					key = StaticValue.Empty;
					key = ScenarioName + "_" + StaticValue.VehicleUsageTypeAsBusiness;
					SingleVehicleData.put(key, result);
					//SingleVehicleData.put(StaticValue.VehicleUsageTypeAsBusiness, result);
					System.out.println("match found in CSV:" + result);
					break;
				case StaticValue.VehicleLocatedAtYourResidence:
					result = jb.getVehicleLocatedAtYourResidence();
					key = StaticValue.Empty;
					key = ScenarioName + "_" + StaticValue.VehicleLocatedAtYourResidence;
					SingleVehicleData.put(key, result);
					//SingleVehicleData.put(StaticValue.VehicleLocatedAtYourResidence, result);
					System.out.println("match found in CSV:" + result);
					break;
				case StaticValue.VehicleSalvaged:
					result = jb.getVehicleSalvaged();
					key = StaticValue.Empty;
					key = ScenarioName + "_" + StaticValue.VehicleSalvaged;
					SingleVehicleData.put(key, result);
					//SingleVehicleData.put(StaticValue.VehicleSalvaged, result);
					System.out.println("match found in CSV:" + result);
					break;
				case StaticValue.AllRegisteredOwnersInPolicy:
					result = jb.getAllRegisteredOwnersInPolicy();
					key = StaticValue.Empty;
					key = ScenarioName + "_" + StaticValue.AllRegisteredOwnersInPolicy;
					SingleVehicleData.put(key, result);
					//SingleVehicleData.put(StaticValue.AllRegisteredOwnersInPolicy, result);
					System.out.println("match found in CSV:" + result);
					break;
				case StaticValue.AntiTheftDevice:
					result = jb.getAntiTheftDevice();
					key = StaticValue.Empty;
					key = ScenarioName + "_" + StaticValue.AntiTheftDevice;
					SingleVehicleData.put(key, result);
					//SingleVehicleData.put(StaticValue.AntiTheftDevice, result);
					System.out.println("match found in CSV:" + result);
					break;
				case StaticValue.AddRemoveAdditionalVehicle:
					result = jb.getAddRemoveAdditionalVehicle();
					key = StaticValue.Empty;
					key = ScenarioName + "_" + StaticValue.AddRemoveAdditionalVehicle;
					SingleVehicleData.put(key, result);
					//SingleVehicleData.put(StaticValue.AddRemoveAdditionalVehicle, result);
					System.out.println("match found in CSV:" + result);
					break;
				case StaticValue.CardName:
					result = jb.getCardNameonCard();
					key = StaticValue.Empty;
					key = ScenarioName + "_" + StaticValue.CardName;
					SingleVehicleData.put(key, result);
					System.out.println("match found in CSV:" + result);
					break;
				case StaticValue.CreditCardNumber:
					result = jb.getCreditCardNumber();
					key = StaticValue.Empty;
					key = ScenarioName + "_" + StaticValue.CreditCardNumber;
					SingleVehicleData.put(key, result);
					System.out.println("match found in CSV:" + result);
					break;
				case StaticValue.DebitCardNumber:
					result = jb.getDebitCardNumber();
					key = StaticValue.Empty;
					key = ScenarioName + "_" + StaticValue.DebitCardNumber;
					SingleVehicleData.put(key, result);
					System.out.println("match found in CSV:" + result);
					break;
				case StaticValue.CardExpirationDate:
					result = jb.getCardExpirationDate();
					key = StaticValue.Empty;
					key = ScenarioName + "_" + StaticValue.CardExpirationDate;
					SingleVehicleData.put(key, result);
					System.out.println("match found in CSV:" + result);
					break;
				case StaticValue.CardZipcode:
					result = jb.getCardZipCode();
					key = StaticValue.Empty;
					key = ScenarioName + "_" + StaticValue.CardZipcode;
					SingleVehicleData.put(key, result);
					System.out.println("match found in CSV:" + result);
					break;
				case StaticValue.CardNickname:
					result = jb.getCardNickname();
					key = StaticValue.Empty;
					key = ScenarioName + "_" + StaticValue.CardNickname;
					SingleVehicleData.put(key, result);
					System.out.println("match found in CSV:" + result);
					break;
				case StaticValue.AccountHolderName:
					result = jb.getAccountHolderName();
					key = StaticValue.Empty;
					key = ScenarioName + "_" + StaticValue.AccountHolderName;
					SingleVehicleData.put(key, result);
					System.out.println("match found in CSV:" + result);
					break;
				case StaticValue.RoutingNumber:
					result = jb.getRoutingNumber();
					key = StaticValue.Empty;
					key = ScenarioName + "_" + StaticValue.RoutingNumber;
					SingleVehicleData.put(key, result);
					System.out.println("match found in CSV:" + result);
					break;
				case StaticValue.AccountNumber:
					result = jb.getAccountNumber();
					key = StaticValue.Empty;
					key = ScenarioName + "_" + StaticValue.AccountNumber;
					SingleVehicleData.put(key, result);
					System.out.println("match found in CSV:" + result);
					break;
				case StaticValue.AccountNickname:
					result = jb.getAccountNickname();
					key = StaticValue.Empty;
					key = ScenarioName + "_" + StaticValue.AccountNickname;
					SingleVehicleData.put(key, result);
					System.out.println("match found in CSV:" + result);
					break;
				case StaticValue.eValueDiscountApply:
					result = jb.geteValueDiscountApply();
					key = StaticValue.Empty;
					key = ScenarioName + "_" + StaticValue.eValueDiscountApply;
					SingleVehicleData.put(key, result);
					System.out.println("match found in CSV:" + result);
					break;

				}
			} else {
				if (currentIteration == data.size() && result == StaticValue.Empty) {
					fail(" <<<< Could not able to find the Matching Scenario Name   '" + jb.getScenarioName()
							+ "' for the iteration " + jb.getIteration() + " in CSV file : >>>>");
				}
			}

		}
		return result;
	}

	public ArrayList<CsvInputFileLayoutPage> readCSVfileIntoArray() {
		RunConfiguration=properties.getProperty("RunConfiguration");
		
		if (RunConfiguration.equalsIgnoreCase("MyPolicy_Regression")) {
			InputFilePath = currentTestParameters.getRelativePath() + Util.getFileSeparator() + "src"
					+ Util.getFileSeparator() + "test" + Util.getFileSeparator() + "resources" + Util.getFileSeparator()
					+ "test-data" + Util.getFileSeparator() + "MyPolicy_Regression_Data.csv";
			String[] memberFieldsToBindTo = { "ScenarioName", "Iteration", "UserId", "Password", "PolicyNumber",
					"PolicyDate", "VehiclePurchaseDate", "VehicleVIN", "VehicleOwnershipType", "VehicleUsageTypeAsBusiness",
					"VehicleLocatedAtYourResidence", "VehicleSalvaged", "AllRegisteredOwnersInPolicy", "AntiTheftDevice",
					"AddRemoveAdditionalVehicle","CardNameonCard","CreditCardNumber","DebitCardNumber","CardExpirationDate","CardZipCode","CardNickname","AccountHolderName","RoutingNumber","AccountNumber","AccountNickname","eValueDiscountApply"};
		
			Reader reader = null;
			try {
				reader = Files.newBufferedReader(Paths.get(InputFilePath));
			} catch (IOException e) {
				e.printStackTrace();
			}

			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			strategy.setType(CsvInputFileLayoutPage.class);
			/*
			String[] memberFieldsToBindTo = { "ScenarioName", "Iteration", "UserId", "Password", "PolicyNumber",
					"PolicyDate", "VehiclePurchaseDate", "VehicleVIN", "VehicleOwnershipType", "VehicleUsageTypeAsBusiness",
					"VehicleLocatedAtYourResidence", "VehicleSalvaged", "AllRegisteredOwnersInPolicy", "AntiTheftDevice",
					"AddRemoveAdditionalVehicle","CardNameonCard","CreditCardNumber","DebitCardNumber","CardExpirationDate","CardZipCode","CardNickname","AccountHolderName","RoutingNumber","AccountNumber","AccountNickname" };
			
			*/
			
			strategy.setColumnMapping(memberFieldsToBindTo);
			CsvToBean csvToBean = new CsvToBeanBuilder(reader).withMappingStrategy(strategy).withSkipLines(1)
					.withIgnoreLeadingWhiteSpace(true).build();

			ArrayList<CsvInputFileLayoutPage> InputData = (ArrayList<CsvInputFileLayoutPage>) csvToBean.parse();

			return InputData;
		}
		else if(RunConfiguration.equalsIgnoreCase("iPortal_Regression"))
		{
			InputFilePath = currentTestParameters.getRelativePath() + Util.getFileSeparator() + "src"
					+ Util.getFileSeparator() + "test" + Util.getFileSeparator() + "resources" + Util.getFileSeparator()
					+ "test-data" + Util.getFileSeparator() + "iPortal_Regression_Data.csv";
			String[] memberFieldsToBindTo = { "ScenarioName", "Iteration", "UserId", "Password", "PolicyNumber",
					"PolicyDate", "VehiclePurchaseDate", "VehicleVIN", "VehicleOwnershipType", "VehicleUsageTypeAsBusiness",
					"VehicleLocatedAtYourResidence", "VehicleSalvaged", "AllRegisteredOwnersInPolicy", "AntiTheftDevice",
					"AddRemoveAdditionalVehicle","CardNameonCard","CreditCardNumber","DebitCardNumber","CardExpirationDate","CardZipCode","CardNickname","AccountHolderName","RoutingNumber","AccountNumber","AccountNickname"};
		
			Reader reader = null;
			try {
				reader = Files.newBufferedReader(Paths.get(InputFilePath));
			} catch (IOException e) {
				e.printStackTrace();
			}

			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			strategy.setType(CsvInputFileLayoutPage.class);
			/*
			String[] memberFieldsToBindTo = { "ScenarioName", "Iteration", "UserId", "Password", "PolicyNumber",
					"PolicyDate", "VehiclePurchaseDate", "VehicleVIN", "VehicleOwnershipType", "VehicleUsageTypeAsBusiness",
					"VehicleLocatedAtYourResidence", "VehicleSalvaged", "AllRegisteredOwnersInPolicy", "AntiTheftDevice",
					"AddRemoveAdditionalVehicle","CardNameonCard","CreditCardNumber","DebitCardNumber","CardExpirationDate","CardZipCode","CardNickname","AccountHolderName","RoutingNumber","AccountNumber","AccountNickname" };
			
			*/
			
			strategy.setColumnMapping(memberFieldsToBindTo);
			CsvToBean csvToBean = new CsvToBeanBuilder(reader).withMappingStrategy(strategy).withSkipLines(1)
					.withIgnoreLeadingWhiteSpace(true).build();

			ArrayList<CsvInputFileLayoutPage> InputData = (ArrayList<CsvInputFileLayoutPage>) csvToBean.parse();

			return InputData;
		}
		else{
			InputFilePath = currentTestParameters.getRelativePath() + Util.getFileSeparator() + "src"
				+ Util.getFileSeparator() + "test" + Util.getFileSeparator() + "resources" + Util.getFileSeparator()
				+ "test-data" + Util.getFileSeparator() + "Data.csv";
			Reader reader = null;
			try {
				reader = Files.newBufferedReader(Paths.get(InputFilePath));
			} catch (IOException e) {
				e.printStackTrace();
			}

			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			strategy.setType(CsvInputFileLayoutPage.class);
			
			String[] memberFieldsToBindTo = { "ScenarioName", "Iteration", "UserId", "Password", "PolicyNumber",
					"PolicyDate", "VehiclePurchaseDate", "VehicleVIN", "VehicleOwnershipType", "VehicleUsageTypeAsBusiness",
					"VehicleLocatedAtYourResidence", "VehicleSalvaged", "AllRegisteredOwnersInPolicy", "AntiTheftDevice",
					"AddRemoveAdditionalVehicle"};
		
			
			strategy.setColumnMapping(memberFieldsToBindTo);
			CsvToBean csvToBean = new CsvToBeanBuilder(reader).withMappingStrategy(strategy).withSkipLines(1)
					.withIgnoreLeadingWhiteSpace(true).build();

			ArrayList<CsvInputFileLayoutPage> InputData = (ArrayList<CsvInputFileLayoutPage>) csvToBean.parse();

			return InputData;
		}
		

	}

	public String readCsvFileCommonData(String ScenarioName) {
		String result = StaticValue.Empty;
		parameterfromcsv = readCSVCommonDatafileIntoArray();
		for (CsvParameterFileLayoutPage jb : parameterfromcsv) {

			if (ScenarioName.equals(jb.getScenarioName())) {
				log.info("ScenarioName - " + ScenarioName + " , jb.getScenarioName() - " + jb.getScenarioName());
				result = jb.BrowserName + "#" + jb.BrowserVersion + "#" + jb.PlatformName;
				break;
			}
		}
        System.out.println("result:"+result+":");
		return result;
	}

	public ArrayList<CsvParameterFileLayoutPage> readCSVCommonDatafileIntoArray() {
		RunConfiguration=properties.getProperty("RunConfiguration");
		if (RunConfiguration.equalsIgnoreCase("MyPolicy_Regression")) {
		InputFilePath = currentTestParameters.getRelativePath() + Util.getFileSeparator() + "src"
				+ Util.getFileSeparator() + "test" + Util.getFileSeparator() + "resources" + Util.getFileSeparator()
				+ "test-data" + Util.getFileSeparator() + "TestRunner_Browser.csv";
		}
		else if (RunConfiguration.equalsIgnoreCase("iPortal_Regression")) {
			InputFilePath = currentTestParameters.getRelativePath() + Util.getFileSeparator() + "src"
					+ Util.getFileSeparator() + "test" + Util.getFileSeparator() + "resources" + Util.getFileSeparator()
					+ "test-data" + Util.getFileSeparator() + "iPortal_TestRunner_Browser.csv";
			}
			
		else
		{
			InputFilePath = currentTestParameters.getRelativePath() + Util.getFileSeparator() + "src"
					+ Util.getFileSeparator() + "test" + Util.getFileSeparator() + "resources" + Util.getFileSeparator()
					+ "test-data" + Util.getFileSeparator() + "TestRunner_Browser.csv";	
		}
		Reader reader = null;
		try {
			reader = Files.newBufferedReader(Paths.get(InputFilePath));
		} catch (IOException e) {
			e.printStackTrace();
		}

		ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
		strategy.setType(CsvParameterFileLayoutPage.class);
		String[] memberFieldsToBindTo = { "ScenarioName", "BrowserName", "BrowserVersion", "PlatformName" };
		strategy.setColumnMapping(memberFieldsToBindTo);
		CsvToBean csvToBean = new CsvToBeanBuilder(reader).withMappingStrategy(strategy).withSkipLines(1)
				.withIgnoreLeadingWhiteSpace(true).build();

		ArrayList<CsvParameterFileLayoutPage> InputData = (ArrayList<CsvParameterFileLayoutPage>) csvToBean.parse();

		return InputData;

	}

}