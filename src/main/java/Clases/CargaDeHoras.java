package Clases;

import Exceptions.*;
import java.util.Date;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;

//@Entity
public class CargaDeHoras {
    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCarga;
    private String tarea;
    private String proyecto;
    private Persona persona;
    private Date fecha;
    private int horas;

    public CargaDeHoras(){
    }

    public CargaDeHoras(String project, String task, Persona person, int hours, Date date) throws Throwable{
        if (! person.belongsToProject(project) ) { throw new DoesNotBelongToProject("El Recurso no pertenece al Proyecto"); }
        if (! person.taskBelongsToHim(project, task) ) { throw new TaskNotFromResource("La tarea no pertenece al Proyecto"); }
        if (hours <= 0) { throw new HoursNotValid("La cantidad de horas ingresada no es valida"); }
        if (date.compareTo(new java.util.Date(System.currentTimeMillis())) > 0){
            throw new DateNotValid("La fecha ingresada es mayor que el día de hoy");
        }

        this.tarea = task;
        this.proyecto = project;
        this.persona = person;
        this.horas = hours;
        this.fecha = date;
    }

    public void modifyProject(String project) throws Throwable {
        if (! this.persona.belongsToProject(project) ) { throw new DoesNotBelongToProject("El Recurso no pertenece al Proyecto"); }
        this.proyecto = project;
    }

    public void modifyTask(String task) throws Throwable {
        if (! this.persona.taskBelongsToHim(this.proyecto, task) ) { throw new TaskNotFromResource("La tarea no pertenece al Proyecto"); }
        this.tarea = task;
    }

    public void modifyHours(int hours) throws Throwable {
        if ( hours <= 0 ) { throw new HoursNotValid("La cantidad de horas ingresada no es valida"); }
        this.horas = hours;
    }

    public void modifyPerson(Persona person) throws Throwable {
        if (! person.belongsToProject(this.proyecto) ) { throw new DoesNotBelongToProject("El Recurso no pertenece al Proyecto"); }
        if (! person.taskBelongsToHim(this.proyecto, this.tarea) ) { throw new TaskNotFromResource("La tarea no pertenece al Proyecto"); }
        this.persona = person;
    }

    public Long getId(){
        return idCarga;
    }

    public Date getDate(){
        return fecha;
    }
}
