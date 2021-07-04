package Clases;

import java.util.ArrayList;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;

//@Entity
public class Persona {
    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private ArrayList<String> proyectos;
    private ArrayList<String> tareas;
    private String nombre;

    public Persona(){
    }

    public Persona(String name){
        this.nombre = name;
        proyectos = new ArrayList<>();
        tareas = new ArrayList<>();
    }

    public void assignToProject(String project){
        proyectos.add(project);
    }

    public void assignTask(String project, String task){
        tareas.add(project + " - " + task);
    }

    public boolean belongsToProject(String project){
        return proyectos.contains(project);
    }

    public boolean taskBelongsToHim(String project, String task){
        //este metodo se corre siempre luego de verificar que la persona pertenece al proyecto,
        //no hace falta validar de nuevo
        return tareas.contains(project + " - " + task);
    }

    public Long getId(){
        return id;
    }
}
