package logica.eventos;

import java.util.*;
import logica.eventos.Venue;

public class Evento {
    private String id;
    private String nombre;
    private String fecha;
    private String hora;
    private String tipo;
    private String estado;
    private Venue venue;
    private List<Localidad> localidades = new ArrayList<>();

    public Evento(String id, String nombre, String fecha, String hora, String tipo,
            String estado, Venue venue) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;
        this.tipo = tipo;
        this.estado = estado;
        this.venue = venue;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Venue getVenue() {
        return venue;
    }

    public void asociarLocalidad(Localidad l) {
        localidades.add(l);
    }

    public List<Localidad> getLocalidades() {
        return localidades;
    }

    public int getTotalCapacidad() {
        return localidades.stream().mapToInt(Localidad::getCapacidad).sum();
    }

    public int getTotalVendidos() {
        return localidades.stream().mapToInt(Localidad::getVendidos).sum();
    }

    @Override
    public String toString() {
        return nombre + " (" + tipo + ") - " + fecha + " a las " + hora + " en "+ venue.getNombre() + " [" + estado + "]";
    }
}