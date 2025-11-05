package Excepciones;

public class UsuarioNoIdentificadoException extends Exception{
    public UsuarioNoIdentificadoException(String mensaje) {
    	super(mensaje);
    }

}