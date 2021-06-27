import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.Assert;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        plugin = {
                "pretty",
                "html:target/cucumber",
                "json:target/cucumber.json"
        }
)

public class SeeHoursCharged_Test {
        @Given("I am a finance staff")
        public void iAmAFinanceStaff() {
        }

        @When("I want to bill a project")
        public void iWantToBillAProject() {
        }

        @Then("I can visualize the hours of the workers who worked in the project")
        public void iCanVisualizeTheHoursOfTheWorkersWhoWorkedInTheProject() {
        }
}
