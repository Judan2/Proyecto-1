package logica;

public class PaqueteDeluxe {
    private String id;
    private String beneficios;
    private boolean incluyeTiquetesAdicionales;
    private double precio;

    public PaqueteDeluxe(String id, String beneficios, boolean incluyeTiquetesAdicionales,
            double precio) {
        this.id = id;
        this.beneficios = beneficios;
        this.incluyeTiquetesAdicionales = incluyeTiquetesAdicionales;
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public String getBeneficios() {
        return beneficios;
    }

    public boolean incluyeTiquetesAdicionales() {
        return incluyeTiquetesAdicionales;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "PaqueteDeluxe[" + id + "] - " + beneficios + " - $" + precio;
    }
}
