package logica.marketplace;

import logica.tickets.Tiquete;
import logica.Cliente;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Representa una oferta de reventa de un tiquete en el marketplace
 */
public class Oferta {
    private String id;
    private Tiquete tiquete;
    private Cliente vendedor;
    private double precioOferta;
    private String estado; // "activa", "vendida", "cancelada", "contraoferta"
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;

    public Oferta(Tiquete tiquete, Cliente vendedor, double precioOferta) {
        this.id = UUID.randomUUID().toString().substring(0, 12);
        this.tiquete = tiquete;
        this.vendedor = vendedor;
        this.precioOferta = precioOferta;
        this.estado = "activa";
        this.fechaCreacion = LocalDateTime.now();
        this.fechaModificacion = LocalDateTime.now();
    }

    // Getters
    public String getId() {
        return id;
    }

    public Tiquete getTiquete() {
        return tiquete;
    }

    public Cliente getVendedor() {
        return vendedor;
    }

    public double getPrecioOferta() {
        return precioOferta;
    }

    public String getEstado() {
        return estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    // Setters
    public void setPrecioOferta(double precio) {
        this.precioOferta = precio;
        this.fechaModificacion = LocalDateTime.now();
    }

    public void setEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
        this.fechaModificacion = LocalDateTime.now();
    }

    public boolean estaActiva() {
        return "activa".equals(estado);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("Oferta[%s] - Vendedor: %s - Tiquete: %s - Precio: $%.2f - Estado: %s - Creada: %s",
                id.substring(0, 6), vendedor.getLogin(), tiquete.getId().substring(0, 6),
                precioOferta, estado, fechaCreacion.format(formatter));
    }
}