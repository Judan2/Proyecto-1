package logica.finanzas;

import java.util.UUID;

public class ReporteFinanciero {
    private String idReporte;
    private String tipo;
    private String fechaGeneracion;
    private String datos;
    private double totalIngresos;
    private double totalComisiones;

    public ReporteFinanciero(String tipo, String datos) {
        this.idReporte = UUID.randomUUID().toString().substring(0, 12);
        this.tipo = tipo;
        this.datos = datos;
        this.fechaGeneracion = java.time.LocalDateTime.now().toString();
        this.totalIngresos = 0;
        this.totalComisiones = 0;
    }

    public String getIdReporte() {
        return idReporte;
    }

    public String getTipo() {
        return tipo;
    }

    public String getFechaGeneracion() {
        return fechaGeneracion;
    }

    public String getDatos() {
        return datos;
    }

    public double getTotalIngresos() {
        return totalIngresos;
    }

    public void setTotalIngresos(double monto) {
        this.totalIngresos = monto;
    }

    public double getTotalComisiones() {
        return totalComisiones;
    }

    public void setTotalComisiones(double monto) {
        this.totalComisiones = monto;
    }

    @Override
    public String toString() {
        return String.format(
                "ReporteFinanciero[%s] - Tipo: %s - Fecha: %s - Ingresos: $%.2f - Comisiones: $%.2f",
                idReporte.substring(0, 6), tipo, fechaGeneracion, totalIngresos,
                totalComisiones);
    }
}
