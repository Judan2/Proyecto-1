package logica;

import logica.eventos.*;
import logica.finanzas.ReporteFinanciero;
import java.util.*;

public class Organizador extends Usuario {
    private String nombreEmpresa;
    private List<Evento> eventos = new ArrayList<>();

    public Organizador(String login, String password, String nombreEmpresa) {
        super(login, password);
        this.nombreEmpresa = nombreEmpresa;
    }

    public Evento crearEvento(String id, String nombre, String fecha, String hora,
            String tipo, Venue venue) {
        Evento e = new Evento(id, nombre, fecha, hora, tipo, "pendiente", venue);
        eventos.add(e);
        return e;
    }

    public void sugerirVenue(Venue venue) {
        System.out.println("[ORG " + login + "] Sugiere venue: " + venue);
    }

    public void generarReporte(ReporteFinanciero r) {
        System.out.println("[ORG " + login + "] Genera reporte: " + r.getIdReporte());
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }
}