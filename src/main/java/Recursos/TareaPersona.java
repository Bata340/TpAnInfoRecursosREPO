package Recursos;

public class TareaPersona {
    private int Id;
    private int IdTarea;
    private int IdPersona;



    public TareaPersona(int id, int idTarea, int idPersona) {
        this.IdPersona = idPersona;
        this.Id = id;
        this.IdTarea = idTarea;
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
}