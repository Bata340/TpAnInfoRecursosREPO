package Recursos;

import Exceptions.ResourceNotExistentException;
import Exceptions.TareaNotExistentException;

import java.util.ArrayList;
import java.util.Collection;

public class Proyecto {
    private int Id;
    private String Codigo;
    private Estado Estado;
    private String Descripcion;
    private ArrayList<TareaPersona> RecursosAsociados;
    private ArrayList<Tarea> Tareas;
    //private ArrayList<Persona> recursosAsociados;


    // Getter Methods

    public Proyecto(int id, String codigo, Estado estado, String descripcion){
        RecursosAsociados = new ArrayList<>();
        Tareas = new ArrayList<>();
        this.Id = id;
        this.Codigo = codigo;
        this.Estado = estado;
        this.Descripcion = descripcion;
    }

    public int getId() {
        return Id;
    }

    public String getCodigo() {
        return Codigo;
    }

    public Estado getEstado() {
        return Estado;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    // Setter Methods

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public void setEstado(Estado Estado) {
        this.Estado = Estado;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public void addResource(Persona recurso, Tarea tarea) throws ResourceNotExistentException, TareaNotExistentException {
        if(recurso == null)
            throw new ResourceNotExistentException("Recurso no encontrado");
        if (tarea == null) {
            RecursosAsociados.add(new TareaPersona(1, -1, recurso.getId(), recurso));
            return;
        }
        Tarea ret = Tareas.stream().filter(t -> t.getId() == tarea.getId()).findFirst().orElse(null);
        if (ret == null)
            throw new TareaNotExistentException("No se encontro la tarea en el proyecto");


    }

    public void addTarea(Tarea tarea) {
        Tareas.add(tarea);
    }

    public ArrayList<TareaPersona> getRecursosAsociados(){
        return RecursosAsociados;
    }
    public ArrayList<Tarea> getTareas(){
        return Tareas;
    }


    public void removeResource(Persona recurso) throws ResourceNotExistentException {
        TareaPersona tareaPersona = RecursosAsociados.stream().filter(p -> p.getIdPersona() == recurso.getId()).findFirst().orElse(null);
        if (tareaPersona == null)
            throw new ResourceNotExistentException("No existe esa Recurso en el proyecto");
        RecursosAsociados.remove(tareaPersona);
    }

}