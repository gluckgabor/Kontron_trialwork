package stepDefinitions;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import utilities.BaseClass;
import utilities.MyCustomListener;

public class Hooks {

	/*
	 * We can create many Before & After's with help order. 0 is the default value.
	 * Add conditions like "@After("@browser and not @headless")".
	 * Pass "Scenario" as parameter to any tag.
	 * 
	 * BeforeStep hook is executed the AfterStep hooks will also be executed regardless of the result of the step.
	 * If a step did not pass, the following step and its hooks will be skipped.
	 */

	private static PrintStream apiLogs;
	private static String featureName;
	private static Logger logger = LogManager.getLogger(Hooks.class);

	
	@After
	public void afterScenario(Scenario scenario) {
		
		System.out.println("********** " + scenario.getName() + " is completed *********");
		
		MyCustomListener.getTestStatusLog(scenario.getName(), scenario.getStatus().toString());
		logger.info("Entered after scenario");
	}
	
	@BeforeAll
	public static void beforeAll() throws FileNotFoundException {
	    // Runs before all scenarios
		
		apiLogs= new PrintStream(new FileOutputStream(System.getProperty("user.dir")+"//logs//Automation.log"));
		logger.info("******************* Test Suit Started **********************");
		
		System.out.println("******************* Run Started **********************");
	}
	
	@AfterAll
	public static void afterAll() {
	    // Runs after all scenarios
		
		System.out.println("******************* Run Completed **********************");
		apiLogs.close();
		logger.info("******************* Test Suit Completed **********************");
	}
}
