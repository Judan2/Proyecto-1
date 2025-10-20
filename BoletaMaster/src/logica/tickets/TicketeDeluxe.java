package logica.tickets;

import logica.eventos.Localidad;
import logica.Cliente;

public class TiqueteDeluxe extends Tiquete {
    private String beneficios;
    private boolean incluyeAdicionales;

    public TiqueteDeluxe(Localidad localidad, double precioFinal, Cliente propietario,
            String beneficios, boolean incluyeAdicionales) {
        super(localidad, precioFinal, propietario);
        this.beneficios = beneficios;
        this.incluyeAdicionales = incluyeAdicionales;
        this.transferible = false;
    }

    public String getBeneficios() { return beneficios; }
    public boolean incluyeAdicionales() { return incluyeAdicionales; }

    @Override
    public String toString() {
        return String.format("TiqueteDeluxe[%s] - %s - Beneficios: %s - NO TRANSFERIBLE - $%.2f",
                id.substring(0, 6), localidad.getNombre(), beneficios, precioFinal);
    }
}
