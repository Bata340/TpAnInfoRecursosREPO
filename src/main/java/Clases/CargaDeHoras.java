package Clases;

import Exceptions.ResourceNotExistentException;
import Exceptions.TaskInvalidException;
import Recursos.Persona;
import Recursos.Proyecto;
import Recursos.Tarea;
import Recursos.TareaPersona;

import java.util.ArrayList;

public class CargaDeHoras {

    public boolean Cargar(Proyecto project, Tarea task, int hours, Persona persona) throws Throwable {
        Tarea tarea = project.getTareas().stream()
                .filter(t -> t.getId() == task.getId()).findFirst().orElse(null);
        if (tarea == null) {
            throw new TaskInvalidException("Tarea no valido en el proyecto");
        }
        TareaPersona tareaPersona = project.getRecursosAsociados().stream()
                .filter(t -> t.getIdPersona() == persona.getId()).findFirst().orElse(null);
        if (tareaPersona == null) {
            throw new ResourceNotExistentException("Persona no valida en el proyecto");
        }
        return hours >= 0;
    }
}
