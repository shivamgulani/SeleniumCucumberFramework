package com.testautomation.TestRunner;

public class TestClassTemplate {
	
	
	private String TestCaseName="";
	private String BDD="";
	private String FeatureFile="";
	private	String Execute="";
	private String DataSheet="";
	private String Scenario="";
	public String getTestCaseName() {
		return TestCaseName;
	}
	public void setTestCaseName(String testCaseName) {
		TestCaseName = testCaseName;
	}
	public String getBDD() {
		return BDD;
	}
	public void setBDD(String bDD) {
		BDD = bDD;
	}
	public String getFeatureFile() {
		return FeatureFile;
	}
	public void setFeatureFile(String featureFile) {
		FeatureFile = featureFile;
	}
	public String getExecute() {
		return Execute;
	}
	public void setExecute(String execute) {
		Execute = execute;
	}
	public String getDataSheet() {
		return DataSheet;
	}
	public void setDataSheet(String dataSheet) {
		DataSheet = dataSheet;
	}
	public String getScenario() {
		return Scenario;
	}
	public void setScenario(String scenario) {
		Scenario = scenario;
	}

}
