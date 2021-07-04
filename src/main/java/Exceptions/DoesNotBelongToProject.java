package Exceptions;

public class DoesNotBelongToProject extends Exception{
    public DoesNotBelongToProject(String mensaje){
        super(mensaje);
    }
}
