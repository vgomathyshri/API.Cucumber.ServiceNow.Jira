package servicenow.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/main/java/servicenow/features",glue="servicenow.steps",publish=true)
public class ChangeRequestRunner extends AbstractTestNGCucumberTests{

	
}
