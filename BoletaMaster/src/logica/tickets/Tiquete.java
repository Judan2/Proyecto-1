package logica.tickets;

import logica.eventos.Localidad;
import logica.Cliente;
import java.util.UUID;

public class Tiquete {
    protected String id;
    protected double precioFinal;
    protected String fechaCompra;
    protected String estado;
    protected boolean transferible;
    protected Cliente propietario;
    protected Localidad localidad;
    protected boolean usado = false;

    public Tiquete(Localidad localidad, double precioFinal, Cliente propietario) {
        this.id = UUID.randomUUID().toString().substring(0, 12);
        this.localidad = localidad;
        this.precioFinal = precioFinal;
        this.fechaCompra = java.time.LocalDateTime.now().toString();
        this.estado = "valido";
        this.transferible = true;
        this.propietario = propietario;
    }

    public String getId() {
        return id;
    }

    public double getPrecioFinal() {
        return precioFinal;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public String getEstado() {
        return estado;
    }

    public boolean isTransferible() {
        return transferible;
    }

    public void setTransferible(boolean t) {
        transferible = t;
    }

    public boolean isUsado() {
        return usado;
    }

    public void marcarUsado() {
        usado = true;
        estado = "usado";
    }

    public void setPropietario(Cliente c) {
        propietario = c;
    }

    public Cliente getPropietario() {
        return propietario;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    @Override
    public String toString() {
        return String.format("Tiquete[%s] - %s - estado=%s - precio=$%.2f - propietario=%s",
                id.substring(0, 6), localidad.getNombre(), estado, precioFinal,
                propietario.getLogin());
    }
}













