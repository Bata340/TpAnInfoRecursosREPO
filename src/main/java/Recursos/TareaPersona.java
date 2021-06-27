package Recursos;

public class TareaPersona {
    private int Id;
    private int IdTarea;
    private int IdPersona;
    private Persona Persona;



    public TareaPersona(int id, int idTarea, int idPersona, Persona persona) {
        this.IdPersona = idPersona;
        this.Id = id;
        this.IdTarea = idTarea;
        this.Persona = persona;
    }

    // Getter Methods

    public int getId() {
        return Id;
    }

    public int getIdTarea() {
        return IdTarea;
    }

    public int getIdPersona() {
        return IdPersona;
    }

    public Persona getPersona() { return Persona; }

    // Setter Methods

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setIdTarea(int IdTarea) {
        this.IdTarea = IdTarea;
    }

    public void setIdPersona(int IdPersona) {
        this.IdPersona = IdPersona;
    }

    public void setPersona(Persona persona) { Persona = persona; }
}