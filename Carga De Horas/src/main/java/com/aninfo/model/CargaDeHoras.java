package com.aninfo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CargaDeHoras {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCarga;
    private Double horas;
    private Long tarea;
    private Long proyecto;
    private Date fecha;
    private Long legajoPersona;

    public CargaDeHoras(){
    }

    public CargaDeHoras(Double hours, Long task, Long project, Date date, Long legajo) {
        this.horas = hours;
        this.fecha = date;
        this.tarea = task;
        this.proyecto = project;
        this.legajoPersona = legajo;
    }

    public Long getIdCarga() {
        return idCarga;
    }

    public void setIdCarga(Long id) {
        this.idCarga = id;
    }

    public Double getHoras() {
        return horas;
    }

    public void setHoras(Double hours) {
        this.horas = hours;
    }

    public Date getFecha(){ return fecha; }

    public void setFecha(Date date) { this.fecha = date; }

    public Long getTarea(){ return tarea; }

    public void setTarea(Long task){ this.tarea = task; }

    public Long getProyecto(){ return proyecto; }

    public void setProyecto(Long project){ this.proyecto = project; }

    public Long getLegajoPersona(){ return legajoPersona; }

    public void setLegajoPersona(Long legajo){ this.legajoPersona = legajo; }

}
