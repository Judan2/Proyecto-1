package logica.marketplace;

import logica.tickets.Tiquete;
import logica.tickets.TiqueteDeluxe;
import logica.Cliente;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Gestiona el marketplace de reventa de tiquetes
 */
public class Marketplace {
    private List<Oferta> ofertas;
    private List<Contraoferta> contrafuertas;
    private List<Transaccion> transacciones;
    private LogMarketplace log;
    private Map<String, Double> saldosUsuarios; // login -> saldo

    public Marketplace() {
        this.ofertas = new ArrayList<>();
        this.contrafuertas = new ArrayList<>();
        this.transacciones = new ArrayList<>();
        this.log = new LogMarketplace();
        this.saldosUsuarios = new HashMap<>();
    }

    /**
     * Un cliente publica una oferta de reventa de un tiquete
     * No se pueden revender tiquetes Deluxe
     */
    public boolean crearOferta(Tiquete tiquete, Cliente vendedor, double precioOferta) {
        // Validar que no sea tiquete Deluxe
        if (tiquete instanceof TiqueteDeluxe) {
            System.out.println("Error: No se pueden revender tiquetes Deluxe");
            return false;
        }

        // Validar que el tiquete pertenezca al vendedor
        if (!tiquete.getPropietario().equals(vendedor)) {
            System.out.println("Error: El tiquete no pertenece al vendedor");
            return false;
        }

        // Validar que no haya una oferta activa del mismo tiquete
        for (Oferta oferta : ofertas) {
            if (oferta.getTiquete().getId().equals(tiquete.getId()) && oferta.estaActiva()) {
                System.out.println("Error: Ya existe una oferta activa para este tiquete");
                return false;
            }
        }

        Oferta nuevaOferta = new Oferta(tiquete, vendedor, precioOferta);
        ofertas.add(nuevaOferta);
        log.registrarCreacionOferta(nuevaOferta);
        System.out.println("Oferta creada exitosamente: " + nuevaOferta.getId());
        return true;
    }

    /**
     * El vendedor cancela su oferta
     */
    public boolean cancelarOfertaPorVendedor(String ofertaId, Cliente vendedor) {
        Oferta oferta = buscarOferta(ofertaId);
        if (oferta == null) {
            System.out.println("Error: Oferta no encontrada");
            return false;
        }

        if (!oferta.getVendedor().equals(vendedor)) {
            System.out.println("Error: Solo el vendedor puede cancelar la oferta");
            return false;
        }

        if (!oferta.estaActiva()) {
            System.out.println("Error: La oferta no está activa");
            return false;
        }

        oferta.setEstado("cancelada");
        log.registrarCancelacionOfertaPorVendedor(oferta);
        System.out.println("Oferta cancelada exitosamente");
        return true;
    }

    /**
     * El administrador cancela una oferta
     */
    public boolean cancelarOfertaPorAdmin(String ofertaId, String adminLogin) {
        Oferta oferta = buscarOferta(ofertaId);
        if (oferta == null) {
            System.out.println("Error: Oferta no encontrada");
            return false;
        }

        oferta.setEstado("cancelada");
        log.registrarCancelacionOfertaPorAdmin(oferta, adminLogin);
        System.out.println("Oferta cancelada por administrador");
        return true;
    }

    /**
     * Un comprador propone una contraoferta
     */
    public boolean crearContraoferta(String ofertaId, Cliente comprador, double precioProponido) {
        Oferta oferta = buscarOferta(ofertaId);
        if (oferta == null) {
            System.out.println("Error: Oferta no encontrada");
            return false;
        }

        if (!oferta.estaActiva()) {
            System.out.println("Error: La oferta no está activa");
            return false;
        }

        // Validar que el comprador no sea el vendedor
        if (oferta.getVendedor().equals(comprador)) {
            System.out.println("Error: No puedes hacer una contraoferta a tu propia oferta");
            return false;
        }

        // Validar que el precio propuesto sea válido
        if (precioProponido <= 0) {
            System.out.println("Error: El precio debe ser mayor a 0");
            return false;
        }

        Contraoferta nuevaContraoferta = new Contraoferta(oferta, comprador, precioProponido);
        contrafuertas.add(nuevaContraoferta);
        log.registrarContraoferta(nuevaContraoferta);
        System.out.println("Contraoferta creada exitosamente: " + nuevaContraoferta.getId());
        return true;
    }

    /**
     * El vendedor acepta una contraoferta
     */
    public boolean aceptarContraoferta(String contraofertaId, Cliente vendedor) {
        Contraoferta contraoferta = buscarContraoferta(contraofertaId);
        if (contraoferta == null) {
            System.out.println("Error: Contraoferta no encontrada");
            return false;
        }

        if (!contraoferta.getOferta().getVendedor().equals(vendedor)) {
            System.out.println("Error: Solo el vendedor puede aceptar la contraoferta");
            return false;
        }

        if (!contraoferta.estaPendiente()) {
            System.out.println("Error: La contraoferta no está pendiente");
            return false;
        }

        // Crear transacción y realizar venta
        contraoferta.aceptar();
        Transaccion transaccion = new Transaccion(contraoferta.getOferta(), 
                contraoferta.getComprador(), contraoferta.getPrecioProponido(), "contraoferta_aceptada");
        transacciones.add(transaccion);

        // Actualizar propietario del tiquete
        contraoferta.getOferta().getTiquete().setPropietario(contraoferta.getComprador());

        // Actualizar saldos
        agregarSaldo(vendedor, contraoferta.getPrecioProponido());
        restarSaldo(contraoferta.getComprador(), contraoferta.getPrecioProponido());

        // Actualizar estado de la oferta
        contraoferta.getOferta().setEstado("vendida");

        log.registrarAceptacionContraoferta(contraoferta);
        log.registrarTransaccion(transaccion);
        log.registrarTransferenciaTiquete(contraoferta.getOferta().getTiquete().getId(),
                vendedor.getLogin(), contraoferta.getComprador().getLogin());

        System.out.println("Contraoferta aceptada y transacción completada");
        return true;
    }

    /**
     * El vendedor rechaza una contraoferta
     */
    public boolean rechazarContraoferta(String contraofertaId, Cliente vendedor) {
        Contraoferta contraoferta = buscarContraoferta(contraofertaId);
        if (contraoferta == null) {
            System.out.println("Error: Contraoferta no encontrada");
            return false;
        }

        if (!contraoferta.getOferta().getVendedor().equals(vendedor)) {
            System.out.println("Error: Solo el vendedor puede rechazar la contraoferta");
            return false;
        }

        if (!contraoferta.estaPendiente()) {
            System.out.println("Error: La contraoferta no está pendiente");
            return false;
        }

        contraoferta.rechazar();
        log.registrarRechazoContraoferta(contraoferta);
        System.out.println("Contraoferta rechazada");
        return true;
    }

    /**
     * Compra directa de una oferta al precio establecido
     */
    public boolean comprarOfertaDirecto(String ofertaId, Cliente comprador) {
        Oferta oferta = buscarOferta(ofertaId);
        if (oferta == null) {
            System.out.println("Error: Oferta no encontrada");
            return false;
        }

        if (!oferta.estaActiva()) {
            System.out.println("Error: La oferta no está activa");
            return false;
        }

        if (oferta.getVendedor().equals(comprador)) {
            System.out.println("Error: No puedes comprar tu propia oferta");
            return false;
        }

        // Crear transacción
        Transaccion transaccion = new Transaccion(oferta, comprador, oferta.getPrecioOferta(), "compra_directa");
        transacciones.add(transaccion);

        // Actualizar propietario del tiquete
        oferta.getTiquete().setPropietario(comprador);

        // Actualizar saldos
        agregarSaldo(oferta.getVendedor(), oferta.getPrecioOferta());
        restarSaldo(comprador, oferta.getPrecioOferta());

        // Actualizar estado de la oferta
        oferta.setEstado("vendida");

        log.registrarTransaccion(transaccion);
        log.registrarTransferenciaTiquete(oferta.getTiquete().getId(),
                oferta.getVendedor().getLogin(), comprador.getLogin());

        System.out.println("Compra realizada exitosamente");
        return true;
    }

    /**
     * Obtiene todas las ofertas activas
     */
    public List<Oferta> obtenerOfertasActivas() {
        return ofertas.stream()
                .filter(Oferta::estaActiva)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene las contrafuertas pendientes de un vendedor
     */
    public List<Contraoferta> obtenerContrafuertasPendientes(Cliente vendedor) {
        return contrafuertas.stream()
                .filter(c -> c.getOferta().getVendedor().equals(vendedor) && c.estaPendiente())
                .collect(Collectors.toList());
    }

    /**
     * Obtiene las ofertas de un vendedor
     */
    public List<Oferta> obtenerOfertasDelVendedor(Cliente vendedor) {
        return ofertas.stream()
                .filter(o -> o.getVendedor().equals(vendedor))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene el saldo de un usuario
     */
    public double obtenerSaldo(Cliente cliente) {
        return saldosUsuarios.getOrDefault(cliente.getLogin(), 0.0);
    }

    /**
     * Agrega saldo a un usuario
     */
    public void agregarSaldo(Cliente cliente, double cantidad) {
        double saldoActual = saldosUsuarios.getOrDefault(cliente.getLogin(), 0.0);
        saldosUsuarios.put(cliente.getLogin(), saldoActual + cantidad);
    }

    /**
     * Resta saldo de un usuario
     */
    public boolean restarSaldo(Cliente cliente, double cantidad) {
        double saldoActual = saldosUsuarios.getOrDefault(cliente.getLogin(), 0.0);
        if (saldoActual < cantidad) {
            System.out.println("Error: Saldo insuficiente");
            return false;
        }
        saldosUsuarios.put(cliente.getLogin(), saldoActual - cantidad);
        return true;
    }

    /**
     * Obtiene el log del marketplace (solo para administrador)
     */
    public LogMarketplace obtenerLog() {
        return log;
    }

    // Métodos auxiliares privados
    private Oferta buscarOferta(String ofertaId) {
        for (Oferta oferta : ofertas) {
            if (oferta.getId().equals(ofertaId)) {
                return oferta;
            }
        }
        return null;
    }

    private Contraoferta buscarContraoferta(String contraofertaId) {
        for (Contraoferta contraoferta : contrafuertas) {
            if (contraoferta.getId().equals(contraofertaId)) {
                return contraoferta;
            }
        }
        return null;
    }

    /**
     * Imprime un resumen del marketplace
     */
    public void imprimirResumen() {
        System.out.println("\n=== RESUMEN DEL MARKETPLACE ===");
        System.out.println("Ofertas activas: " + obtenerOfertasActivas().size());
        System.out.println("Total de ofertas: " + ofertas.size());
        System.out.println("Contrafuertas pendientes: " + contrafuertas.stream()
                .filter(Contraoferta::estaPendiente).count());
        System.out.println("Total de transacciones: " + transacciones.size());
        System.out.println("Registros en el log: " + log.obtenerCantidadRegistros());
        System.out.println("================================\n");
    }
}