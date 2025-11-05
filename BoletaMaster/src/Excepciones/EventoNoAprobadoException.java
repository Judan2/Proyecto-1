package Excepciones;

public class EventoNoAprobadoException extends Exception {
    public EventoNoAprobadoException(String mensaje) {
        super(mensaje);
    }
}