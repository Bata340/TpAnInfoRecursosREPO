package Exceptions;

public class ResourceNotExistentException extends Exception{
    public ResourceNotExistentException(String mensaje){
        super(mensaje);
    }
}
