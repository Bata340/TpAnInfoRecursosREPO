public class Tarea {
    private int Id;
    private int IdProyecto;
    private String Codigo;
    private Estado Estado;
    private String Descripcion;
    private int IdTarea;


    // Getter Methods

    public Tarea(int id, int idProyecto, String codigo, Estado estado, String Descripcion, int idTarea) {

    }


    public int getId() {
        return Id;
    }

    public int getIdProyecto() {
        return IdProyecto;
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

    public int getIdTarea() {
        return IdTarea;
    }

    // Setter Methods

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setIdProyecto(int IdProyecto) {
        this.IdProyecto = IdProyecto;
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

    public void setIdTarea(int IdTarea) {
        this.IdTarea = IdTarea;
    }
}