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

import java.text.SimpleDateFormat;
import java.util.Date;

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
    private boolean dateInvalid;

    @Given("developer named {word} working on {word} task {word}")
    public void developerNamedWorkingOnTask(String name, String project, String task) {
        persona = new Persona(name);
        persona.assignToProject(project);
        persona.assignTask(project, task);
    }

    @When("he charges {int} hours into {word} task {word} on {word}")
    public void heChargesHoursIntoStopifyTaskDevelopment(int hours, String project, String task, String date) throws Throwable {
        try {
            SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");
            carga = new CargaDeHoras(project, task, this.persona, hours, sdformat.parse(date));
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
        catch (DateNotValid e){
            carga = null;
            dateInvalid = true;
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

    @Then("he gets a message saying that the date is not valid")
    public void heGetsAMessageSayingThatTheDateIsNotValid(){
        Assert.assertTrue(dateInvalid);
        Assert.assertNull(carga);
    }

    @Then ("he can see his charge date is correct and it is {word}")
    public void heCanSeeHisChargeDateIsCorrect(String date){
        SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = null;
        try {
            fecha = sdformat.parse(date);
        }catch(java.text.ParseException ignored){}
        Assert.assertNotNull(carga);
        Assert.assertEquals(carga.getDate(), fecha);
    }
}
