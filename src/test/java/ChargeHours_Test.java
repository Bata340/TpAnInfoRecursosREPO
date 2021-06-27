import Exceptions.*;
import Recursos.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.Assert;
import org.junit.runner.RunWith;

import Clases.CargaDeHoras;

import java.util.HashMap;
import java.util.Map;

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
        private Persona otra = new Persona(Rol.User, 2, 2, "Esteban Peña");
        private Proyecto proyecto;
        private Tarea tarea1;
        private Tarea tarea;
        private Tarea tar_aux;
        private int horasACargar;
        private CargaDeHoras horas;

        private Map<String, Tarea> tareas = new HashMap<>();
        private Map<String, Proyecto> proyectos = new HashMap<>();


        @Given("I am a developer")
        public void iAmADeveloper() {
                persona = new Persona(Rol.User, 1, 1, "Agustin Hejeu");
        }

        @And("Project {word} exists")
        public void projectStopifyExists(String project) {
                proyecto = new Proyecto(1, project, Estado.Activo, "Music plataform");
                proyectos.put(project, proyecto);
        }

        @And("Task {word} exists in project {word}")
        public void taskDevelopmentExists(String task, String project) {
                tarea = new Tarea(1, 1, task, Estado.Activo, "", -1);
                Proyecto proy = proyectos.get(project);
                proy.addTarea(tarea);
                proyecto = proy;
                tareas.put(task, tarea);

        }

        @And("Task {word} exists in project {word} and belongs to me")
        public void taskDevelopmentExistsAndBelongsToMe(String task, String project) throws Throwable {
                tarea = new Tarea(2, 1, task, Estado.Activo, "", -1);
                Proyecto proy = proyectos.get(project);
                proy.addTarea(tarea);
                proyecto = proy;
                tareas.put(task, tarea);
                proy.addResource(persona, tarea);
        }

        @And("Task {word} exists")
        public void taskDevelopmentExists(String task) {
                tarea1 = new Tarea(2, -1, task, Estado.Activo, "", -1);
                tareas.put(task, tarea1);

        }
        @When("I complete project {word}, task {word}, and {int} hours correctly")
        public void iCompleteProjectNameTaskNameAndHoursCorrectly(String project, String task, int hours) throws Throwable {
                Proyecto proj = proyectos.get(project);
                Tarea tar = tareas.get(task);
                proj.addResource(persona, tar);
                Assert.assertEquals(proyecto, proj);
                Assert.assertEquals(tarea, tar);
                proyecto = proj;
                horasACargar = hours;

        }

        @Then("I get a message that the hours have been charged correctly.")
        public void iGetAMessageThatTheHoursHaveBeenChargedCorrectly() throws Throwable {
                TareaPersona tareaPersona = proyecto.getRecursosAsociados().stream()
                        .filter(t -> t.getIdPersona() == persona.getId()).findFirst().orElse(null);
                Assert.assertNotEquals(null, tareaPersona);
                assert tareaPersona != null;
                Assert.assertEquals(tarea.getId(), tareaPersona.getIdTarea());
                horas = new CargaDeHoras();
                Assert.assertTrue(horas.Cargar(proyecto, tarea, horasACargar, tareaPersona.getPersona()));
        }

        @When("I complete project {word}, task {word}, and {int} hours but I do not belong to the project")
        public void iCompleteProjectNameTaskNameAndHoursButIDoNotBelongToTheProject(String project, String task, int hours) {
                Proyecto proj = proyectos.get(project);
                Tarea tar = tareas.get(task);
                Assert.assertEquals(proyecto, proj);
                Assert.assertEquals(tarea, tar);
                proyecto = proj;
                horasACargar = hours;
        }

        @Then("I get an error message telling me that I do not belong to that project")
        public void iGetAnErrorMessageTellingMeThatIDoNotBelongToThatProject() {
                TareaPersona tareaPersona = proyecto.getRecursosAsociados().stream()
                        .filter(t -> t.getIdPersona() == persona.getId()).findFirst().orElse(null);
                Assert.assertNull(tareaPersona);
                horas = new CargaDeHoras();
                Assert.assertThrows(ResourceNotExistentException.class,() -> {horas.Cargar(proyecto, tarea, horasACargar, persona);});
        }

        @When("I complete project {word}, task {word}, and {int} hours but the task does not belong to the project")
        public void iCompleteProjectNameTaskNameAndHoursButTheTaskDoesNotBelongToTheProject(String project, String task, int hours) throws Throwable  {
                Proyecto proj = proyectos.get(project);
                proj.addResource(persona, null);
                Tarea tar = tareas.get(task);
                Assert.assertEquals(proyecto, proj);
                Assert.assertNull(proj.getTareas().stream()
                        .filter(t -> t.getId() == tar.getId()).findFirst().orElse(null));
                proyecto = proj;
                horasACargar = hours;
        }

        @Then("I get an error message telling me that the task does not belong to the project")
        public void iGetAnErrorMessageTellingMeThatTheTaskDoesNotBelongToTheProject() {
                TareaPersona tareaPersona = proyecto.getRecursosAsociados().stream()
                        .filter(t -> t.getIdPersona() == persona.getId()).findFirst().orElse(null);
                horas = new CargaDeHoras();
                Assert.assertThrows(TaskInvalidException.class,() -> {horas.Cargar(proyecto, tarea1, horasACargar, persona);});
        }

        @When("I complete project {word}, task {word}, and {int} hours but the task does not belong to me")
        public void iCompleteProjectNameTaskNameAndHoursButTheTaskDoesNotBelongToMe(String project, String task, int hours) throws Throwable {
                Proyecto proj = proyectos.get(project);
                tar_aux = tareas.get(task);
                proj.addResource(otra, tar_aux);
                proyecto = proj;
                horasACargar = hours;
        }

        @Then("I get an error message telling me that the task does not belong to me")
        public void iGetAnErrorMessageTellingMeThatTheTaskDoesNotBelongToMe() {
                //TareaPersona tareaPersona = proyecto.getRecursosAsociados().stream()
                //        .filter(t -> t.getIdTarea() == tar_aux.getId()).findFirst().orElse(null);
                horas = new CargaDeHoras();

                Assert.assertThrows(TaskNotFromResource.class,() -> {horas.Cargar(proyecto, tar_aux, horasACargar, persona);});
        }

        @When("I complete project {word}, task {word}, and {int} hours but the hours are not greater than zero")
        public void iCompleteProjectTaskAndHoursButTheHoursAreNotGreaterThanZero(String project, String task, int hours) throws Throwable {
                Proyecto proj = proyectos.get(project);
                Tarea tar = tareas.get(task);
                proj.addResource(persona, tar);
                Assert.assertEquals(proyecto, proj);
                proyecto = proj;
                horasACargar = hours;
        }

        @Then("I get an error message telling me that the amount of hours is not valid")
        public void iGetAnErrorMessageTellingMeThatTheAmountOfHoursIsNotValid() throws Throwable {
                horas = new CargaDeHoras();
                Assert.assertFalse(horas.Cargar(proyecto, tarea, horasACargar, persona));
        }



}
