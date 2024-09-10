package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"classpath:features"}, glue = {"stepDefinitions"},
        tags = "@regression", monochrome = true, dryRun = true,
        plugin = {"pretty", "html:target/cucumber"})
public class MainRunners extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
