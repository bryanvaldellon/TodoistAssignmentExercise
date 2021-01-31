package JUnitApplicationRunner;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/cucumberReport",
                "json:target/cucumber.json"
        },
        features = "src/test/resources/features",
        glue = "StepDefinitions",
        tags = "@completeTodo"
)
@RunWith(Cucumber.class)
public class TodoistUITest {
}
