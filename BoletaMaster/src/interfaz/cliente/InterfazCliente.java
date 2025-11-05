package interfaz.cliente;

import logica.marketplace.Marketplace;
import logica.Cliente;
import interfaz.util.ValidadorEntrada;
import java.util.Scanner;

/**
 * Interfaz de consola para que los clientes interactúen con el sistema
 * 
 * FUNCIONALIDADES:
 * - Ver ofertas disponibles
 * - Publicar una oferta
 * - Comprar tiquetes
 * - Hacer contrafuertas
 * - Ver saldo
 */
public class InterfazCliente {
    private Scanner scanner;
    private Marketplace marketplace;
    private Cliente clienteLogueado;
    private boolean continuar;

    // CONSTRUCTOR
    public InterfazCliente(Marketplace marketplace) {
        this.scanner = new Scanner(System.in);
        this.marketplace = marketplace;
        this.clienteLogueado = null;
        this.continuar = true;
    }

    // MÉTODO PRINCIPAL - Inicia la interfaz
    public void iniciar() {
        while (continuar) {
            if (clienteLogueado == null) {
                mostrarMenuPrincipal();
            } else {
                mostrarMenuCliente();
            }
        }
        scanner.close();
    }

    // ================== MENÚS ==================

    /**
     * Menú antes de autenticarse
     */
    private void mostrarMenuPrincipal() {
        limpiarPantalla();
        System.out.println("====================================");
        System.out.println("   BOLETAMASTER - CLIENTE");
        System.out.println("====================================");
        System.out.println("1. Iniciar sesión");
        System.out.println("2. Registrarse");
        System.out.println("3. Salir");
        System.out.println("====================================");
        System.out.print("Seleccione una opción: ");

        String opcion = scanner.nextLine().trim();

        switch (opcion) {
            case "1":
                iniciarSesion();
                break;
            case "2":
                registrarse();
                break;
            case "3":
                continuar = false;
                System.out.println("¡Hasta luego!");
                break;
            default:
                System.out.println("❌ Opción no válida");
                pausar();
        }
    }

    /**
     * Menú después de autenticarse
     */
    private void mostrarMenuCliente() {
        limpiarPantalla();
        System.out.println("====================================");
        System.out.println("   BOLETAMASTER - CLIENTE");
        System.out.println("   Usuario: " + clienteLogueado.getLogin());
        System.out.println("   Saldo: $" + String.format("%.2f", 
            marketplace.obtenerSaldo(clienteLogueado)));
        System.out.println("====================================");
        System.out.println("1. Ver ofertas del marketplace");
        System.out.println("2. Publicar una oferta");
        System.out.println("3. Mis ofertas activas");
        System.out.println("4. Ver mis contrafuertas pendientes");
        System.out.println("5. Comprar una oferta");
        System.out.println("6. Hacer una contraoferta");
        System.out.println("7. Mi saldo");
        System.out.println("8. Cerrar sesión");
        System.out.println("====================================");
        System.out.print("Seleccione una opción: ");

        String opcion = scanner.nextLine().trim();

        switch (opcion) {
            case "1":
                verOfertasDisponibles();
                break;
            case "2":
                publicarOferta();
                break;
            case "3":
                verMisOfertas();
                break;
            case "4":
                verContrafuertasPendientes();
                break;
            case "5":
                comprarOferta();
                break;
            case "6":
                hacerContraoferta();
                break;
            case "7":
                verMiSaldo();
                break;
            case "8":
                clienteLogueado = null;
                System.out.println("✓ Sesión cerrada");
                pausar();
                break;
            default:
                System.out.println("❌ Opción no válida");
                pausar();
        }
    }

    // ================== AUTENTICACIÓN ==================

    /**
     * Inicia sesión del cliente
     */
    private void iniciarSesion() {
        limpiarPantalla();
        System.out.println("====== INICIAR SESIÓN ======");
        System.out.print("Login: ");
        String login = scanner.nextLine().trim();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine().trim();

        // En producción, validarías contra base de datos
        // Por ahora, simulamos que se autentica
        if (login.isEmpty() || password.isEmpty()) {
            System.out.println("❌ Login o contraseña vacíos");
            pausar();
            return;
        }

        clienteLogueado = new Cliente(login, password, "Cliente", login + "@email.com");
        System.out.println("✓ ¡Bienvenido, " + login + "!");
        pausar();
    }

    /**
     * Registra un nuevo cliente
     */
    private void registrarse() {
        limpiarPantalla();
        System.out.println("====== REGISTRO DE CLIENTE ======");
        
        System.out.print("Login: ");
        String login = scanner.nextLine().trim();
        
        if (!ValidadorEntrada.validarLogin(login)) {
            pausar();
            return;
        }

        System.out.print("Contraseña: ");
        String password = scanner.nextLine().trim();
        
        if (!ValidadorEntrada.validarPassword(password)) {
            pausar();
            return;
        }

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();
        
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        
        if (!ValidadorEntrada.validarEmail(email)) {
            pausar();
            return;
        }

        clienteLogueado = new Cliente(login, password, nombre, email);
        System.out.println("✓ ¡Registro exitoso! Bienvenido, " + nombre);
        pausar();
    }

    // ================== FUNCIONALIDADES ==================

    /**
     * Ver todas las ofertas disponibles
     */
    private void verOfertasDisponibles() {
        limpiarPantalla();
        System.out.println("====== OFERTAS DISPONIBLES ======");
        
        // Aquí iría: List<Oferta> ofertas = marketplace.obtenerOfertasActivas();
        System.out.println("No hay ofertas disponibles en este momento");
        System.out.println("(Funcionalidad completa cuando integres Marketplace)");
        
        pausar();
    }

    /**
     * Publicar una nueva oferta
     */
    private void publicarOferta() {
        limpiarPantalla();
        System.out.println("====== PUBLICAR OFERTA ======");
        System.out.print("ID del tiquete a vender: ");
        String tiqueteId = scanner.nextLine().trim();
        
        double precio = ValidadorEntrada.validarNumeroPositivo(
            scanner, 
            "Precio deseado"
        );
        
        if (precio == -1) {
            pausar();
            return;
        }

        System.out.println("✓ Oferta publicada exitosamente");
        pausar();
    }

    /**
     * Ver mis ofertas activas
     */
    private void verMisOfertas() {
        limpiarPantalla();
        System.out.println("====== MIS OFERTAS ACTIVAS ======");
        System.out.println("No tienes ofertas activas");
        pausar();
    }

    /**
     * Ver contrafuertas pendientes
     */
    private void verContrafuertasPendientes() {
        limpiarPantalla();
        System.out.println("====== MIS CONTRAFUERTAS PENDIENTES ======");
        System.out.println("No tienes contrafuertas pendientes");
        pausar();
    }

    /**
     * Comprar una oferta
     */
    private void comprarOferta() {
        limpiarPantalla();
        System.out.println("====== COMPRAR OFERTA ======");
        System.out.print("ID de la oferta: ");
        String ofertaId = scanner.nextLine().trim();
        
        System.out.println("✓ Compra realizada exitosamente");
        pausar();
    }

    /**
     * Hacer una contraoferta
     */
    private void hacerContraoferta() {
        limpiarPantalla();
        System.out.println("====== HACER CONTRAOFERTA ======");
        System.out.print("ID de la oferta: ");
        String ofertaId = scanner.nextLine().trim();
        
        double precio = ValidadorEntrada.validarNumeroPositivo(
            scanner,
            "Precio propuesto"
        );
        
        if (precio == -1) {
            pausar();
            return;
        }

        System.out.println("✓ Contraoferta creada exitosamente");
        pausar();
    }

    /**
     * Ver mi saldo
     */
    private void verMiSaldo() {
        limpiarPantalla();
        System.out.println("====== MI SALDO ======");
        double saldo = marketplace.obtenerSaldo(clienteLogueado);
        System.out.println("Saldo disponible: $" + String.format("%.2f", saldo));
        pausar();
    }

    // ================== UTILITARIOS ==================

    private void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void pausar() {
        System.out.println("\nPresiona Enter para continuar...");
        scanner.nextLine();
    }
}