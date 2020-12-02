package com.testautomation.StepDef;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.testautomation.Listeners.ExtentReportListener;

import cucumber.api.java.en.Given;

public class CalcStepDefination extends ExtentReportListener{

	@Given("I want to add two numbers")
	public void i_want_to_add_two_numbers() {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println("Add");
	    ExtentTest logInfo=null;
	    try {
			test = extent.createTest(Feature.class, "I want to Add Two numbers");							
			test=test.createNode(Scenario.class, "i_want_to_add_two_numbers");						
			logInfo=test.createNode(new GherkinKeyword("Given"), "i_want_to_add_two_numbers");
			
			logInfo.pass("Numbers Added");
			
		} catch (AssertionError | Exception e) {
			testStepHandlewithoutScreenshot("FAIL",logInfo,e);			
		}	
	}


@Given("I want to multiply two numbers")
public void i_want_to_multiply_two_numbers() {
    // Write code here that turns the phrase above into concrete actions
    System.out.println("Multiply");
    ExtentTest logInfo=null;
    try {
		test = extent.createTest(Feature.class, "I want to Multiply Two numbers");							
		test=test.createNode(Scenario.class, "i_want_to_multiply_two_numbers");						
		logInfo=test.createNode(new GherkinKeyword("Given"), "i_want_to_multiply_two_numbers");
		
		logInfo.pass("Numbers Multiplied");
		
	} catch (AssertionError | Exception e) {
		testStepHandlewithoutScreenshot("FAIL",logInfo,e);			
	}	
}


	
	
}
