package logica.marketplace;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Mantiene un registro de log de todos los eventos que ocurren en el marketplace
 */
public class LogMarketplace {
    private List<RegistroLog> registros;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LogMarketplace() {
        this.registros = new ArrayList<>();
    }

    /**
     * Registra la creación de una oferta
     */
    public void registrarCreacionOferta(Oferta oferta) {
        String mensaje = String.format("OFERTA CREADA: %s - Vendedor: %s - Tiquete: %s - Precio: $%.2f",
                oferta.getId().substring(0, 6), oferta.getVendedor().getLogin(),
                oferta.getTiquete().getId().substring(0, 6), oferta.getPrecioOferta());
        registros.add(new RegistroLog("OFERTA_CREADA", mensaje));
    }

    /**
     * Registra la cancelación de una oferta por el vendedor
     */
    public void registrarCancelacionOfertaPorVendedor(Oferta oferta) {
        String mensaje = String.format("OFERTA CANCELADA POR VENDEDOR: %s - Vendedor: %s - Precio original: $%.2f",
                oferta.getId().substring(0, 6), oferta.getVendedor().getLogin(), oferta.getPrecioOferta());
        registros.add(new RegistroLog("OFERTA_CANCELADA_VENDEDOR", mensaje));
    }

    /**
     * Registra la cancelación de una oferta por el administrador
     */
    public void registrarCancelacionOfertaPorAdmin(Oferta oferta, String adminLogin) {
        String mensaje = String.format("OFERTA CANCELADA POR ADMIN: %s - Admin: %s - Vendedor: %s",
                oferta.getId().substring(0, 6), adminLogin, oferta.getVendedor().getLogin());
        registros.add(new RegistroLog("OFERTA_CANCELADA_ADMIN", mensaje));
    }

    /**
     * Registra una contraoferta propuesta
     */
    public void registrarContraoferta(Contraoferta contraoferta) {
        String mensaje = String.format("CONTRAOFERTA PROPUESTA: %s - Oferta: %s - Comprador: %s - Precio propuesto: $%.2f",
                contraoferta.getId().substring(0, 6), contraoferta.getOferta().getId().substring(0, 6),
                contraoferta.getComprador().getLogin(), contraoferta.getPrecioProponido());
        registros.add(new RegistroLog("CONTRAOFERTA_PROPUESTA", mensaje));
    }

    /**
     * Registra la aceptación de una contraoferta
     */
    public void registrarAceptacionContraoferta(Contraoferta contraoferta) {
        String mensaje = String.format("CONTRAOFERTA ACEPTADA: %s - Vendedor: %s - Comprador: %s - Precio final: $%.2f",
                contraoferta.getId().substring(0, 6), contraoferta.getOferta().getVendedor().getLogin(),
                contraoferta.getComprador().getLogin(), contraoferta.getPrecioProponido());
        registros.add(new RegistroLog("CONTRAOFERTA_ACEPTADA", mensaje));
    }

    /**
     * Registra el rechazo de una contraoferta
     */
    public void registrarRechazoContraoferta(Contraoferta contraoferta) {
        String mensaje = String.format("CONTRAOFERTA RECHAZADA: %s - Vendedor: %s - Comprador: %s",
                contraoferta.getId().substring(0, 6), contraoferta.getOferta().getVendedor().getLogin(),
                contraoferta.getComprador().getLogin());
        registros.add(new RegistroLog("CONTRAOFERTA_RECHAZADA", mensaje));
    }

    /**
     * Registra una transacción completada
     */
    public void registrarTransaccion(Transaccion transaccion) {
        String mensaje = String.format("TRANSACCION COMPLETADA: %s - Vendedor: %s - Comprador: %s - Precio: $%.2f - Tipo: %s",
                transaccion.getId().substring(0, 6), transaccion.getVendedor().getLogin(),
                transaccion.getComprador().getLogin(), transaccion.getPrecioFinal(), transaccion.getTipo());
        registros.add(new RegistroLog("TRANSACCION_COMPLETADA", mensaje));
    }

    /**
     * Registra el cambio de propietario de un tiquete
     */
    public void registrarTransferenciaTiquete(String tiqueteId, String vendedorLogin, String compradorLogin) {
        String mensaje = String.format("TIQUETE TRANSFERIDO: %s - De: %s - A: %s",
                tiqueteId.substring(0, 6), vendedorLogin, compradorLogin);
        registros.add(new RegistroLog("TIQUETE_TRANSFERIDO", mensaje));
    }

    /**
     * Obtiene todos los registros del log (solo para administrador)
     */
    public List<RegistroLog> obtenerTodosLosRegistros() {
        return new ArrayList<>(registros);
    }

    /**
     * Obtiene registros filtrados por tipo
     */
    public List<RegistroLog> obtenerRegistrosPorTipo(String tipo) {
        List<RegistroLog> filtrados = new ArrayList<>();
        for (RegistroLog registro : registros) {
            if (registro.getTipo().equals(tipo)) {
                filtrados.add(registro);
            }
        }
        return filtrados;
    }

    /**
     * Obtiene la cantidad de registros
     */
    public int obtenerCantidadRegistros() {
        return registros.size();
    }

    /**
     * Clase interna para representar un registro del log
     */
    public static class RegistroLog {
        private String tipo;
        private String mensaje;
        private LocalDateTime fecha;

        public RegistroLog(String tipo, String mensaje) {
            this.tipo = tipo;
            this.mensaje = mensaje;
            this.fecha = LocalDateTime.now();
        }

        public String getTipo() {
            return tipo;
        }

        public String getMensaje() {
            return mensaje;
        }

        public LocalDateTime getFecha() {
            return fecha;
        }

        @Override
        public String toString() {
            return String.format("[%s] %s - %s", fecha.format(formatter), tipo, mensaje);
        }
    }

    /**
     * Imprime todos los registros del log de manera legible
     */
    public void imprimirLog() {
        System.out.println("\n=== LOG DEL MARKETPLACE ===");
        System.out.println("Total de registros: " + registros.size());
        System.out.println("=====================================\n");
        for (RegistroLog registro : registros) {
            System.out.println(registro);
        }
        System.out.println("\n=====================================");
    }
}