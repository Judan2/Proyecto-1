package test.logica.marketplace;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import logica.marketplace.Marketplace;
import logica.Cliente;

/**
 * Pruebas unitarias para Marketplace
 */
public class MarketplaceTest {

    private Marketplace marketplace;
    private Cliente cliente1;
    private Cliente cliente2;

    @Before
    public void setUp() {
        marketplace = new Marketplace();
        cliente1 = new Cliente("juan", "pass123", "Juan", "juan@email.com");
        cliente2 = new Cliente("pedro", "pass456", "Pedro", "pedro@email.com");
    }

    // ========== PRUEBAS BÁSICAS ==========

    @Test
    public void testMarketplaceCreado() {
        assertNotNull("Marketplace debe crearse", marketplace);
    }

    @Test
    public void testClienteCreado() {
        assertNotNull("Cliente debe crearse", cliente1);
    }

    @Test
    public void testSaldoInicial() {
        double saldo = marketplace.obtenerSaldo(cliente1);
        assertEquals("Saldo inicial debe ser 0", 0.0, saldo, 0.01);
    }

    @Test
    public void testAgregarSaldo() {
        marketplace.agregarSaldo(cliente1, 100.0);
        double saldo = marketplace.obtenerSaldo(cliente1);
        assertEquals("Saldo debe ser 100", 100.0, 0.01);
    }

    @Test
    public void testRestarSaldoExitosa() {
        marketplace.agregarSaldo(cliente1, 100.0);
        boolean resultado = marketplace.restarSaldo(cliente1, 50.0);
        assertTrue("Debe restar exitosamente", resultado);
    }

    @Test
    public void testRestarSaldoInsuficiente() {
        marketplace.agregarSaldo(cliente1, 30.0);
        boolean resultado = marketplace.restarSaldo(cliente1, 50.0);
        assertFalse("No debe permitir restar más que saldo", resultado);
    }

    @Test
    public void testObtenerOfertasActivas() {
        int ofertas = marketplace.obtenerOfertasActivas().size();
        assertEquals("No debe haber ofertas al inicio", 0, ofertas);
    }

    @Test
    public void testLoginCliente() {
        assertEquals("Login debe ser juan", "juan", cliente1.getLogin());
    }

    @Test
    public void testEmailCliente() {
        assertEquals("Email debe ser correcto", "juan@email.com", cliente1.getEmail());
    }

    @Test
    public void testClienteEquals() {
        Cliente otro = new Cliente("juan", "pass123", "Otro", "otro@email.com");
        assertTrue("Clientes con mismo login deben ser iguales", cliente1.equals(otro));
    }

    @Test
    public void testClienteDiferente() {
        assertFalse("Clientes diferentes no deben ser iguales", cliente1.equals(cliente2));
    }
}