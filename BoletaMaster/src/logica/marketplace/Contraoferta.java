package logica.marketplace;

import logica.Cliente;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Representa una contraoferta de un comprador sobre una oferta existente
 */
public class Contraoferta {
    private String id;
    private Oferta oferta;
    private Cliente comprador;
    private double precioProponido;
    private String estado; // "pendiente", "aceptada", "rechazada"
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaRespuesta;

    public Contraoferta(Oferta oferta, Cliente comprador, double precioProponido) {
        this.id = UUID.randomUUID().toString().substring(0, 12);
        this.oferta = oferta;
        this.comprador = comprador;
        this.precioProponido = precioProponido;
        this.estado = "pendiente";
        this.fechaCreacion = LocalDateTime.now();
        this.fechaRespuesta = null;
    }

    // Getters
    public String getId() {
        return id;
    }

    public Oferta getOferta() {
        return oferta;
    }

    public Cliente getComprador() {
        return comprador;
    }

    public double getPrecioProponido() {
        return precioProponido;
    }

    public String getEstado() {
        return estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getFechaRespuesta() {
        return fechaRespuesta;
    }

    // Setters
    public void aceptar() {
        this.estado = "aceptada";
        this.fechaRespuesta = LocalDateTime.now();
    }

    public void rechazar() {
        this.estado = "rechazada";
        this.fechaRespuesta = LocalDateTime.now();
    }

    public boolean estaPendiente() {
        return "pendiente".equals(estado);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("Contraoferta[%s] - Comprador: %s - Precio propuesto: $%.2f - Estado: %s - Creada: %s",
                id.substring(0, 6), comprador.getLogin(), precioProponido, estado,
                fechaCreacion.format(formatter));
    }
}