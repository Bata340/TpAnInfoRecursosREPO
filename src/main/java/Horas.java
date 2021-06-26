import java.util.Date;


public class Horas {
    private int Id;
    private Date Fecha;
    private int IdPersona;
    private int IdProyecto;
    private int IdTarea;
    private int Cantidad;


    public Horas(int id, Date fecha, int idPersona, int idProyecto, int idTarea, int cantidad) {
        this.Id = id;
        this.Fecha = fecha;
        this.IdPersona = idPersona;
        this.IdProyecto = idProyecto;
        this.IdTarea = idTarea;
        this.Cantidad = cantidad;
    }

    // Getter Methods

    public int getId() {
        return Id;
    }

    public Date getFecha() {
        return Fecha;
    }

    public int getIdPersona() {
        return IdPersona;
    }

    public int getIdProyecto() {
        return IdProyecto;
    }

    public int getIdTarea() {
        return IdTarea;
    }

    public int getCantidad() {
        return Cantidad;
    }

    // Setter Methods

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public void setIdPersona(int IdPersona) {
        this.IdPersona = IdPersona;
    }

    public void setIdProyecto(int IdProyecto) {
        this.IdProyecto = IdProyecto;
    }

    public void setIdTarea(int IdTarea) {
        this.IdTarea = IdTarea;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }
}

