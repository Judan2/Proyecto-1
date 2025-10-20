package logica.eventos;

public class Oferta {
    private String id;
    private double porcentaje;
    private long inicio;
    private long fin;
    private boolean activa;

    public Oferta(String id, double porcentaje, long inicio, long fin, boolean activa) {
        this.id = id;
        this.porcentaje = porcentaje;
        this.inicio = inicio;
        this.fin = fin;
        this.activa = activa;
    }

    public String getId() {
        return id;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public boolean aplicaAhora() {
        long now = System.currentTimeMillis();
        return activa && now >= inicio && now <= fin;
    }

    public double aplicarDescuento(double precio) {
        if (aplicaAhora()) {
            return precio * (1 - porcentaje / 100.0);
        }
        return precio;
    }

    @Override
    public String toString() {
        return "Oferta[" + id + "] - " + porcentaje + "% descuento - activa: " + activa;
    }
}