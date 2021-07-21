package com.aninfo.integration.cucumber;

import com.aninfo.exceptions.HoursNotValid;
import com.aninfo.model.CargaDeHoras;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CargaDeHorasOperationsTest extends CargaDeHorasIntegrationServiceTest {

    private CargaDeHoras carga;
    private HoursNotValid hnv;
    Long legajo;
    Long tarea;
    Long proyecto;

    @Before
    public void setup() {
        System.out.println("Before any test execution");
    }

    @Given("^developer (\\d+) working on project (\\d+) task (\\d+)$")
    public void developer_working_on_project_task(Long id, Long proy, Long task)  {
        this.legajo = id;
        this.proyecto = proy;
        this.tarea = task;
    }

    @When("^he charges (\\d+) hours into proyect (\\d+) task (\\d+) on (.*)$")
    public void he_charges_hours_into_proyect_task_on(int hours, int proy, int task, String date) throws Throwable {
        try {
            SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
            carga = createCarga(Double.valueOf(hours), this.tarea, this.proyecto, sdformat.parse(date), this.legajo);
        } catch (HoursNotValid hnv) {
            this.hnv = hnv;
        }
    }

    @Then("^he gets a message saying that the amount of hours is invalid$")
    public void he_gets_a_message_saying_that_the_amount_of_hours_is_invalid() {
        assertNotNull(hnv);
    }

    @Then("^he gets a message saying that the charge is successful$")
    public void he_gets_a_message_saying_that_the_charge_is_successful() {
        assertNotNull(this.carga);
    }

    @After
    public void tearDown() {
        System.out.println("After all test execution");
    }
}
