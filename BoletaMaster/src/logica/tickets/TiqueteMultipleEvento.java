package logica.tickets;
import logica.eventos.Localidad;
import logica.Cliente;

public class TiqueteMultipleEvento extends Tiquete {
    private int cantidadEntradas;

    public TiqueteMultipleEvento(Localidad localidad, double precioFinal, Cliente propietario,
            int cantidad) {
        super(localidad, precioFinal, propietario);
        this.cantidadEntradas = cantidad;
        this.transferible = true;
    }

    public int getCantidadEntradas() { return cantidadEntradas; }

    @Override
    public String toString() {
        return String.format("TiqueteMultiple[%s] - %s x%d - estado=%s - $%.2f",
                id.substring(0, 6), localidad.getNombre(), cantidadEntradas, estado, precioFinal);
    }
}
