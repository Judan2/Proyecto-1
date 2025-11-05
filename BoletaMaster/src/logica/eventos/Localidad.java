package logica.eventos;

public class Localidad {
    private String id;
    private String nombre;
    private double precioBase;
    private boolean numerada;
    private int capacidad;
    private int vendidos = 0;

    public Localidad(String id, String nombre, double precioBase, boolean numerada,
            int capacidad) {
        this.id = id;
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.numerada = numerada;
        this.capacidad = capacidad;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public boolean isNumerada() {
        return numerada;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getCapacidadDisponibles() {
        return capacidad - vendidos;
    }

    public int getVendidos() {
        return vendidos;
    }

    public void marcarVendido() {
        if (vendidos < capacidad) {
            vendidos++;
        }
    }

    public double getPorcentajeVenta() {
        return (double) vendidos / capacidad * 100;
    }

    @Override
    public String toString() {
        return nombre + " (precio=$" + precioBase + ", vendidos=" + vendidos + "/" + capacidad
                + ")";
    }
}