package Recursos;

public class Persona {
    private int Id;
    private String Nombre;
    private int Legajo;
    private Rol Rol;

    public Persona(Rol rol_const, int id_const, int legajo_const, String nombre_const){
        Rol = rol_const;
        Id = id_const;
        Legajo = legajo_const;
        Nombre = nombre_const;
    }

    // Getter Methods


    public Rol getRol() {
        return Rol;
    }

    public int getId() {
        return Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public int getLegajo() {
        return Legajo;
    }

    // Setter Methods

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setLegajo(int Legajo) {
        this.Legajo = Legajo;
    }

    public void setRol(Rol Rol) {
        this.Rol = Rol;
    }
}