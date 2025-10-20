package logica;

import excepciones.*;
import logica.tickets.*;
import logica.eventos.*;
import java.util.*;

public class Cliente extends Usuario {
    private double saldo;
    private Map<String, Tiquete> tiquetes = new HashMap<>();
    private List<Transaccion> transacciones = new ArrayList<>();
    private static final int MAX_TICKETS_PER_TX = 10;

    public Cliente(String login, String password, double saldoInicial) {
        super(login, password);
        this.saldo = saldoInicial;
    }

    public double getSaldo() {
        return saldo;
    }

    public void acreditar(double monto) {
        this.saldo += monto;
    }

    public void debitar(double monto) throws SaldoInsuficienteException {
        if (saldo < monto) {
            throw new SaldoInsuficienteException(
                "Saldo insuficiente: saldo=" + saldo + ", costo=" + monto);
        }
        this.saldo -= monto;
    }

    public Collection<Tiquete> getTiquetes() {
        return tiquetes.values();
    }

    public Transaccion comprarTiquetes(Evento evento, Localidad localidad, int cantidad,
            double precioUnitario) throws SaldoInsuficienteException, LimiteTiquetesException,
            EventoNoAprobadoException {

        if (!evento.getVenue().isAprobado()) {
            throw new EventoNoAprobadoException("El venue del evento no está aprobado.");
        }

        if (cantidad <= 0) {
            throw new IllegalArgumentException("Cantidad debe ser > 0.");
        }

        if (cantidad > MAX_TICKETS_PER_TX) {
            throw new LimiteTiquetesException(
                "Se supera el límite de tiquetes por transacción: " + MAX_TICKETS_PER_TX);
        }

        if (localidad.getCapacidadDisponibles() < cantidad) {
            throw new IllegalStateException("No hay disponibilidad suficiente en la localidad.");
        }

        double total = precioUnitario * cantidad;
        if (saldo < total) {
            throw new SaldoInsuficienteException(
                "Saldo insuficiente: saldo=" + saldo + ", total=" + total);
        }

        Transaccion trx = new Transaccion(total, "SALDO");
        for (int i = 0; i < cantidad; i++) {
            Tiquete t = new Tiquete(localidad, precioUnitario, this);
            trx.agregarTiquete(t);
            tiquetes.put(t.getId(), t);
            localidad.marcarVendido();
        }

        transacciones.add(trx);
        debitar(total);
        return trx;
    }

    public Transferencia transferirTiquete(String idTiquete, Cliente destino, String pwd)
            throws UsuarioNoAutenticadoException, TiqueteNoTransferibleException {

        if (!this.autenticar(pwd)) {
            throw new UsuarioNoAutenticadoException("Contraseña incorrecta para usuario " + login);
        }

        Tiquete t = tiquetes.get(idTiquete);
        if (t == null) {
            throw new IllegalArgumentException("Tiquete no encontrado: " + idTiquete);
        }

        if (!t.isTransferible()) {
            throw new TiqueteNoTransferibleException("Tiquete no transferible: " + idTiquete);
        }

        if (t.isUsado()) {
            throw new IllegalStateException("Tiquete ya usado: " + idTiquete);
        }

        tiquetes.remove(idTiquete);
        t.setPropietario(destino);
        destino.recibirTiquete(t);

        return new Transferencia(this.login, destino.getLogin());
    }

    public void recibirTiquete(Tiquete t) {
        tiquetes.put(t.getId(), t);
    }

    public void imprimirTiquetes() {
        System.out.println("\n--- Tiquetes de " + login + " ---");
        if (tiquetes.isEmpty()) {
            System.out.println("  (ninguno)");
            return;
        }
        for (Tiquete t : tiquetes.values()) {
            System.out.println("  " + t);
        }
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }
}