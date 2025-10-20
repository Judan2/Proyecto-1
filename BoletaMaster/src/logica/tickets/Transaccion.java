package logica.tickets;

import java.util.*;
import java.util.UUID;

public class Transaccion {
    private String id;
    private String fecha;
    private String estado;
    private double total;
    private String tipoPago;
    private List<Tiquete> tiquetes = new ArrayList<>();

    public Transaccion(double total, String tipoPago) {
        this.id = UUID.randomUUID().toString().substring(0, 12);
        this.fecha = java.time.LocalDateTime.now().toString();
        this.estado = "completada";
        this.total = total;
        this.tipoPago = tipoPago;
    }

    public void agregarTiquete(Tiquete t) { tiquetes.add(t); }
    public String getId() { return id; }
    public String getFecha() { return fecha; }
    public String getEstado() { return estado; }
    public double getTotal() { return total; }
    public String getTipoPago() { return tipoPago; }
    public List<Tiquete> getTiquetes() { return new ArrayList<>(tiquetes); }
    public int getCantidadTiquetes() { return tiquetes.size(); }

    @Override
    public String toString() {
        return String.format("Transaccion[%s] - %d tiquetes - $%.2f - pago: %s - estado: %s",
                id.substring(0, 6), tiquetes.size(), total, tipoPago, estado);
    }
}
