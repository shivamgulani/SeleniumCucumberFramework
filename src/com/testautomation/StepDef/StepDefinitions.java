package com.testautomation.StepDef;

import java.util.Properties;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.testautomation.Listeners.ExtentReportListener;
import com.testautomation.Utility.BrowserUtility;

import cucumber.api.java.en.Given;

public class StepDefinitions extends ExtentReportListener
 {
    @Given("I have {int} cukes in my belly")
    public void I_have_cukes_in_my_belly(int cukes) {
        Belly belly = new Belly();
        belly.eat(cukes);
        ExtentTest logInfo=null;
		try {
			test = extent.createTest(Feature.class, "Cake Validation");							
			test=test.createNode(Scenario.class, "Cake Validation");						
			logInfo=test.createNode(new GherkinKeyword("Given"), "I have {int} cukes in my belly");
			
			logInfo.pass("Cakes Eaten");
			
		} catch (AssertionError | Exception e) {
			testStepHandlewithoutScreenshot("FAIL",logInfo,e);			
		}		
    }
}
