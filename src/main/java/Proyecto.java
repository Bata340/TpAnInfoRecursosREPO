import java.util.ArrayList;

public class Proyecto {
    private int Id;
    private String Codigo;
    private Estado Estado;
    private String Descripcion;
    private ArrayList<Persona> recursosAsociados;


    // Getter Methods

    public Proyecto(int id, String codigo, Estado estado, String descripcion){
        recursosAsociados = new ArrayList<Persona>();
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

    public void addResource(Persona recurso){
        recursosAsociados.add(recurso);
    }

    public ArrayList<Persona> getRecursosAsociados(){
        return recursosAsociados;
    }

    public void removeResource(Persona recurso){
        recursosAsociados.remove(recurso);
    }
}