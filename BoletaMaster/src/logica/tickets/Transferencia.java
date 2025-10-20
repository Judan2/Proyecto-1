package logica.tickets;

import java.util.UUID;

public class Transferencia {
    private String id;
    private String fecha;
    private String estado;
    private String fromLogin;
    private String toLogin;

    public Transferencia(String fromLogin, String toLogin) {
        this.id = UUID.randomUUID().toString().substring(0, 12);
        this.fecha = java.time.LocalDateTime.now().toString();
        this.estado = "completada";
        this.fromLogin = fromLogin;
        this.toLogin = toLogin;
    }

    public String getId() { return id; }
    public String getFecha() { return fecha; }
    public String getEstado() { return estado; }
    public String getFromLogin() { return fromLogin; }
    public String getToLogin() { return toLogin; }

    @Override
    public String toString() {
        return String.format("Transferencia[%s] - de '%s' a '%s' - estado: %s",
                id.substring(0, 6), fromLogin, toLogin, estado);
    }
}