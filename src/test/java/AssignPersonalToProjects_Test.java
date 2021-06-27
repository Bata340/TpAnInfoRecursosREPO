import Exceptions.ResourceNotExistentException;
import Recursos.Estado;
import Recursos.Persona;
import Recursos.Proyecto;
import Recursos.Rol;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.Assert;
import org.junit.runner.RunWith;

import java.util.ArrayList;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/AssignPersonalToProjects.feature",
        plugin = {
                "pretty",
                "html:target/cucumber",
                "json:target/cucumber.json"
        }
)

public class AssignPersonalToProjects_Test {

    private Persona persona;
    private Proyecto proyecto;

    @Given("^I am a project leader$")
    public void i_am_a_project_leader(){
        persona = new Persona(Rol.Admin, 1, 123, "Leonardo Felici");
    }

    @When("^a project is already created$")
    public void a_project_is_already_created(){
        proyecto = new Proyecto(1, "PSA", Estado.Activo,"Desarrollo CRM PSA");
    }

    @Then("^I can assign personal already existent into the project")
    public void i_can_assign_personal_already_existent_into_the_project() throws ResourceNotExistentException{
        proyecto.addResource(persona);
        ArrayList<Persona> personas = proyecto.getRecursosAsociados();
        Assert.assertEquals(personas.get(0), persona);
        proyecto.removeResource(persona);
    }

    @When ("^there is a person assigned to a project$")
    public void there_is_a_person_assigned_to_a_project() throws ResourceNotExistentException{
        proyecto = new Proyecto(1, "PSA", Estado.Activo,"Desarrollo CRM PSA");
        proyecto.addResource(persona);
    }

    @Then ("^I can remove that person from the project")
    public void i_can_remove_that_person_from_the_project(){
        proyecto.removeResource(persona);
        Assert.assertTrue(proyecto.getRecursosAsociados().isEmpty());
    }

    @When ("^I want to add a person to a project and he does not exist$")
    public void i_want_to_add_a_person_to_a_project_and_he_does_not_exist(){
        persona = null;
        proyecto = new Proyecto(1, "PSA", Estado.Activo,"Desarrollo CRM PSA");
    }

    @Then ("^I get an error message telling me that that person does not exist")
    public void i_get_an_error_message_telling_me_that_that_person_does_not_exist() throws ResourceNotExistentException {
        Assert.assertThrows(ResourceNotExistentException.class, () -> {proyecto.addResource(persona);});
    }

}
