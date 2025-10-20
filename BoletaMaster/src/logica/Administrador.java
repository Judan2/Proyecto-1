package logica;

import logica.eventos.Venue;
import logica.eventos.Evento;
import logica.finanzas.ConfiguracionCobros;

public class Administrador extends Usuario {
    public Administrador(String login, String pwd) {
        super(login, pwd);
    }

    public void aprobarVenue(Venue v) {
        v.setAprobado(true);
        //System.out.println("[ADMIN " + login + "] Venue aprobado: " + v.getNombre());
    }

    public void cancelarEvento(Evento e) {
        e.setEstado("cancelado");
        //System.out.println("[ADMIN " + login + "] Evento cancelado: " + e.getNombre());
    }

    public void configurarCobros(ConfiguracionCobros cfg) {
        System.out.println("[ADMIN " + login + "] Configuración de cobros actualizada.");
    }

    public void autorizarReembolso(Cliente cliente, double monto) {
        cliente.acreditar(monto);
        //System.out.println("[ADMIN " + login + "] Reembolso de $" + monto + " acreditado a " + cliente.getLogin());
    }
}