package logica.tickets;

import logica.eventos.Localidad;
import logica.Cliente;

public class TiqueteIndividual extends Tiquete {
    private String numeroAsiento;

    public TiqueteIndividual(Localidad localidad, double precioFinal, Cliente propietario,
            String numeroAsiento) {
        super(localidad, precioFinal, propietario);
        this.numeroAsiento = numeroAsiento;
    }

    public String getNumeroAsiento() { return numeroAsiento; }

    @Override
    public String toString() {
        return String.format("TiqueteIndividual[%s] - %s - Asiento %s - estado=%s - $%.2f",
                id.substring(0, 6), localidad.getNombre(), numeroAsiento, estado, precioFinal);
    }
}