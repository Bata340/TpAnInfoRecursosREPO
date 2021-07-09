package com.aninfo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Recurso {
    private Long Legajo;
    private String Nombre;
    private String Apellido;


    public void setLegajo(Long legajo) {
        this.Legajo = legajo;
    }

    @Id
    public Long getLegajo() {
        return Legajo;
    }
    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setApellido(String apellido) {
        this.Apellido = apellido;
    }

    public String getApellido() {
        return Apellido;
    }
}
