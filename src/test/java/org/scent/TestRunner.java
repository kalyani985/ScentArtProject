package org.scent;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features =  "src/test/resources" , // or wherever your feature files are
		glue = "org.scent", // Ensure this matches the package of your step definitions
		monochrome = true, dryRun = false)
public class TestRunner {

}
