import Exceptions.ResourceNotExistentException;
import Exceptions.TareaNotExistentException;
import Recursos.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.Assert;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/ChargeHours.feature",
        plugin = {
                "pretty",
                "html:target/cucumber",
                "json:target/cucumber.json"
        }
)

public class ChargeHours_Test {

        private Persona persona;
        private Proyecto proyecto;
        private Tarea tarea;

        @Given("I am a developer")
        public void iAmADeveloper() throws ResourceNotExistentException, TareaNotExistentException {
                persona = new Persona(Rol.User, 1, 1, "Agustin Hejeu");
                proyecto = new Proyecto(1, "Stopify", Estado.Activo, "Music plataform");
                tarea = new Tarea(1, 1, "Development", Estado.Activo, "", -1);
                proyecto.addResource(persona, tarea);

        }

        @When("I complete project name, task name, and hours correctly")
        public void iCompleteProjectNameTaskNameAndHoursCorrectly(Proyecto project, Tarea task, int hours) {

        }

        @Then("I get a message that the hours have been charged correctly.")
        public void iGetAMessageThatTheHoursHaveBeenChargedCorrectly() {
        }

        @When("I complete project name, task name, and hours but I do not belong to the project")
        public void iCompleteProjectNameTaskNameAndHoursButIDoNotBelongToTheProject() {
        }

        @Then("I get an error message telling me that I do not belong to that project")
        public void iGetAnErrorMessageTellingMeThatIDoNotBelongToThatProject() {
        }

        @When("I complete project name, task name, and hours but the task does not belong to the project")
        public void iCompleteProjectNameTaskNameAndHoursButTheTaskDoesNotBelongToTheProject() {
        }

        @Then("I get an error message telling me that the task does not belong to the project")
        public void iGetAnErrorMessageTellingMeThatTheTaskDoesNotBelongToTheProject() {
        }

        @When("I complete project name, task name, and hours but the task does not belong to me")
        public void iCompleteProjectNameTaskNameAndHoursButTheTaskDoesNotBelongToMe() {
        }

        @Then("I get an error message telling me that the task does not belong to me")
        public void iGetAnErrorMessageTellingMeThatTheTaskDoesNotBelongToMe() {
        }

        @When("I complete project name, task name, and hours but the hours are not greater than zero")
        public void iCompleteProjectNameTaskNameAndHoursButTheHoursAreNotGreaterThanZero() {
        }

        @Then("I get an error message telling me that the amount of hours is not valid")
        public void iGetAnErrorMessageTellingMeThatTheAmountOfHoursIsNotValid() {
        }

}
