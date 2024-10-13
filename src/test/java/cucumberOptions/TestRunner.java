package cucumberOptions;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/resources/features/PetClinic_CRUD_API_Tests.feature",
		glue="stepDefinitions",
		monochrome=true,
		tags="@CRUD and @AddOwner and @UpdateOwner and @DeleteOwner",
		plugin={"utilities.MyCustomListener",
				"pretty",
				"html:reports/htmlReports/cucumber.html",
				"json:reports/jsonReports/cucumber.json",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}

		)
public class TestRunner {

}
