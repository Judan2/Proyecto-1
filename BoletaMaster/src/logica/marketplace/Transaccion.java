package logica.marketplace;

import logica.Cliente;
import logica.tickets.Tiquete;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Representa una transacción completada en el marketplace
 */
public class Transaccion {
    private String id;
    private Oferta oferta;
    private Cliente comprador;
    private double precioFinal;
    private String tipo; // "compra_directa", "contraoferta_aceptada"
    private String estado; // "completada", "cancelada", "pendiente"
    private LocalDateTime fecha;

    public Transaccion(Oferta oferta, Cliente comprador, double precioFinal, String tipo) {
        this.id = UUID.randomUUID().toString().substring(0, 12);
        this.oferta = oferta;
        this.comprador = comprador;
        this.precioFinal = precioFinal;
        this.tipo = tipo;
        this.estado = "completada";
        this.fecha = LocalDateTime.now();
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

    public double getPrecioFinal() {
        return precioFinal;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEstado() {
        return estado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Tiquete getTiquete() {
        return oferta.getTiquete();
    }

    public Cliente getVendedor() {
        return oferta.getVendedor();
    }

    public void cancelar() {
        this.estado = "cancelada";
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("Transaccion[%s] - Vendedor: %s, Comprador: %s - Precio: $%.2f - Tipo: %s - Fecha: %s",
                id.substring(0, 6), oferta.getVendedor().getLogin(), comprador.getLogin(),
                precioFinal, tipo, fecha.format(formatter));
    }
}