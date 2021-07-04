import Exceptions.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.Assert;
import org.junit.runner.RunWith;

import Clases.*;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/ChargeHours.feature",
        plugin = {
                "pretty",
                "html:target/cucumber",
                "json:target/cucumber.json"
        }
)

public class ChargeHoursTest {
    private Persona persona;
    private CargaDeHoras carga;
    private boolean doesNotBelongToProject;
    private boolean taskDoesNotBelong;
    private boolean hoursInvalid;

    @Given("developer named {word} working on {word} task {word}")
    public void developerNamedWorkingOnTask(String name, String project, String task) {
        persona = new Persona(name);
        persona.assignToProject(project);
        persona.assignTask(project, task);
    }

    @When("he charges {int} hours into {word} task {word}")
    public void heChargesHoursIntoStopifyTaskDevelopment(int hours, String project, String task) throws Throwable {
        try {
            carga = new CargaDeHoras(project, task, this.persona, hours);
        }
        catch (DoesNotBelongToProject e) {
            carga = null;
            doesNotBelongToProject = true;
        }
        catch (TaskNotFromResource e) {
            carga = null;
            taskDoesNotBelong = true;
        }
        catch (HoursNotValid e) {
            carga = null;
            hoursInvalid = true;
        }
    }

    @Then("charge is successful")
    public void chargeIsSuccessful()  {
        Assert.assertNotNull(carga);
    }

    @Then("he gets a message saying that he does not belong to the project")
    public void heGetsAMessageSayingThatHeDoesNotBelongToTheProject() {
        Assert.assertTrue(doesNotBelongToProject);
        Assert.assertNull(carga);
    }

    @Then("he gets a message saying that the task does not belong to him")
    public void heGetsAMessageSayingThatTheTaskDoesNotBelongToHim() {
        Assert.assertTrue(taskDoesNotBelong);
        Assert.assertNull(carga);
    }

    @Then("he gets a message saying that the amount of hours is invalid")
    public void heGetsAMessageSayingThatTheAmountOfHoursIsInvalid() {
        Assert.assertTrue(hoursInvalid);
        Assert.assertNull(carga);
    }
}
