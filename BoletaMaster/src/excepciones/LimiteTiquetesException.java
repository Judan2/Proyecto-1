package excepciones;

public class LimiteTiquetesException extends Exception {
    public LimiteTiquetesException(String mensaje) {
        super(mensaje);
    }
}
