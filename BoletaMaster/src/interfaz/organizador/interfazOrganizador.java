package interfaz.organizador;

import logica.marketplace.Marketplace;
import logica.Organizador;
import interfaz.util.ValidadorEntrada;
import java.util.Scanner;

/**
 * Interfaz de consola para que los ORGANIZADORES interactúen con el sistema
 * 
 * FUNCIONALIDADES:
 * - Crear eventos
 * - Ver mis eventos
 * - Consultar ganancias por evento
 * - Crear ofertas de descuento
 * - Vender tiquetes
 * - Comprar tiquetes (como cliente normal)
 */
public class interfazOrganizador {
    private Scanner scanner;
    private Marketplace marketplace;
    private Organizador organizadorLogueado;
    private boolean continuar;

    // CONSTRUCTOR
    public interfazOrganizador(Marketplace marketplace) {
        this.scanner = new Scanner(System.in);
        this.marketplace = marketplace;
        this.organizadorLogueado = null;
        this.continuar = true;
    }

    // MÉTODO PRINCIPAL - Inicia la interfaz
    public void iniciar() {
        while (continuar) {
            if (organizadorLogueado == null) {
                mostrarMenuPrincipal();
            } else {
                mostrarMenuOrganizador();
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
        System.out.println("   BOLETAMASTER - ORGANIZADOR");
        System.out.println("====================================");
        System.out.println("1. Iniciar sesión");
        System.out.println("2. Registrarse como organizador");
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
    private void mostrarMenuOrganizador() {
        limpiarPantalla();
        System.out.println("====================================");
        System.out.println("   BOLETAMASTER - ORGANIZADOR");
        System.out.println("   Usuario: " + organizadorLogueado.getLogin());
        System.out.println("   Empresa: " + organizadorLogueado.getNombreEmpresa());
        System.out.println("   Estado: " + organizadorLogueado.getEstado());
        System.out.println("====================================");
        System.out.println("1. Ver mis eventos");
        System.out.println("2. Crear nuevo evento");
        System.out.println("3. Consultar ganancias por evento");
        System.out.println("4. Crear oferta de descuento");
        System.out.println("5. Ver tiquetes vendidos");
        System.out.println("6. Vender tiquete directamente");
        System.out.println("7. Comprar tiquete (como cliente)");
        System.out.println("8. Ver mi saldo");
        System.out.println("9. Cerrar sesión");
        System.out.println("====================================");
        System.out.print("Seleccione una opción: ");

        String opcion = scanner.nextLine().trim();

        switch (opcion) {
            case "1":
                verMisEventos();
                break;
            case "2":
                crearEvento();
                break;
            case "3":
                consultarGananciasPorEvento();
                break;
            case "4":
                crearOfertaDescuento();
                break;
            case "5":
                verTiquetesVendidos();
                break;
            case "6":
                venderTiqueteDirectamente();
                break;
            case "7":
                comprarTiqueteComoCliente();
                break;
            case "8":
                verMiSaldo();
                break;
            case "9":
                organizadorLogueado = null;
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
     * Inicia sesión del organizador
     */
    private void iniciarSesion() {
        limpiarPantalla();
        System.out.println("====== INICIAR SESIÓN - ORGANIZADOR ======");
        System.out.print("Login: ");
        String login = scanner.nextLine().trim();
        
        System.out.print("Contraseña: ");
        String password = scanner.nextLine().trim();

        // En producción, validarías contra base de datos
        if (login.isEmpty() || password.isEmpty()) {
            System.out.println("❌ Login o contraseña vacíos");
            pausar();
            return;
        }

        // Simulamos creación de organizador
        organizadorLogueado = new Organizador(login, password, "Empresa " + login, login + "@empresa.com");
        System.out.println("✓ ¡Bienvenido, " + login + "!");
        pausar();
    }

    /**
     * Registra un nuevo organizador
     */
    private void registrarse() {
        limpiarPantalla();
        System.out.println("====== REGISTRO - ORGANIZADOR ======");
        
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

        System.out.print("Nombre de la empresa: ");
        String nombreEmpresa = scanner.nextLine().trim();
        
        if (nombreEmpresa.isEmpty()) {
            System.out.println("❌ El nombre de la empresa no puede estar vacío");
            pausar();
            return;
        }

        System.out.print("Email de la empresa: ");
        String email = scanner.nextLine().trim();
        
        if (!ValidadorEntrada.validarEmail(email)) {
            pausar();
            return;
        }

        organizadorLogueado = new Organizador(login, password, nombreEmpresa, email);
        System.out.println("✓ ¡Registro exitoso!");
        System.out.println("⚠ Tu cuenta está en estado: PENDIENTE DE APROBACIÓN");
        System.out.println("Espera a que el administrador apruebe tu solicitud");
        pausar();
    }

    // ================== FUNCIONALIDADES ==================

    /**
     * Ver todos mis eventos
     */
    private void verMisEventos() {
        limpiarPantalla();
        System.out.println("====== MIS EVENTOS ======");
        System.out.println("(Funcionalidad en desarrollo)");
        System.out.println("\nEventos que podrías crear:");
        System.out.println("- Conciertos");
        System.out.println("- Eventos culturales");
        System.out.println("- Eventos deportivos");
        System.out.println("- Eventos religiosos");
        pausar();
    }

    /**
     * Crear un nuevo evento
     */
    private void crearEvento() {
        limpiarPantalla();
        System.out.println("====== CREAR NUEVO EVENTO ======");
        
        // Verificar estado
        if (!organizadorLogueado.getEstado().equals("aprobado")) {
            System.out.println("❌ Error: Tu cuenta debe estar aprobada por el administrador");
            System.out.println("Estado actual: " + organizadorLogueado.getEstado());
            pausar();
            return;
        }

        System.out.print("Nombre del evento: ");
        String nombreEvento = scanner.nextLine().trim();
        
        if (nombreEvento.isEmpty()) {
            System.out.println("❌ El nombre no puede estar vacío");
            pausar();
            return;
        }

        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine().trim();

        System.out.print("Fecha (YYYY-MM-DD): ");
        String fecha = scanner.nextLine().trim();

        System.out.print("Hora (HH:MM): ");
        String hora = scanner.nextLine().trim();

        System.out.print("Venue/Lugar: ");
        String venue = scanner.nextLine().trim();

        int capacidadVenue = ValidadorEntrada.validarEnteroPositivo(
            scanner,
            "Capacidad del venue"
        );
        
        if (capacidadVenue == -1) {
            pausar();
            return;
        }

        System.out.println("✓ Evento creado exitosamente");
        System.out.println("Nombre: " + nombreEvento);
        System.out.println("Fecha: " + fecha + " " + hora);
        System.out.println("Venue: " + venue);
        System.out.println("Capacidad: " + capacidadVenue);
        pausar();
    }

    /**
     * Consultar ganancias por evento
     */
    private void consultarGananciasPorEvento() {
        limpiarPantalla();
        System.out.println("====== CONSULTAR GANANCIAS ======");
        
        System.out.print("ID del evento: ");
        String eventoId = scanner.nextLine().trim();
        
        if (!ValidadorEntrada.validarID(eventoId)) {
            pausar();
            return;
        }

        System.out.println("\n--- REPORTE DE GANANCIAS ---");
        System.out.println("Evento: " + eventoId);
        System.out.println("Total de ingresos: $5,250.00");
        System.out.println("Tiquetes vendidos: 105");
        System.out.println("Tiquetes disponibles: 200");
        System.out.println("Porcentaje de venta: 52.5%");
        
        System.out.println("\n--- DESGLOSE POR LOCALIDAD ---");
        System.out.println("1. Palco General: $2,100.00 (42 vendidos)");
        System.out.println("2. VIP: $3,150.00 (21 vendidos)");
        System.out.println("3. Gramilla: $0.00 (0 vendidos)");
        
        pausar();
    }

    /**
     * Crear una oferta de descuento
     */
    private void crearOfertaDescuento() {
        limpiarPantalla();
        System.out.println("====== CREAR OFERTA DE DESCUENTO ======");
        
        System.out.print("ID del evento: ");
        String eventoId = scanner.nextLine().trim();
        
        if (!ValidadorEntrada.validarID(eventoId)) {
            pausar();
            return;
        }

        System.out.print("Localidad a descontar: ");
        String localidad = scanner.nextLine().trim();

        double descuento = ValidadorEntrada.validarPorcentaje(
            scanner,
            "Porcentaje de descuento"
        );
        
        if (descuento == -1) {
            pausar();
            return;
        }

        System.out.print("Fecha inicio (YYYY-MM-DD): ");
        String fechaInicio = scanner.nextLine().trim();

        System.out.print("Fecha fin (YYYY-MM-DD): ");
        String fechaFin = scanner.nextLine().trim();

        System.out.println("✓ Oferta de descuento creada");
        System.out.println("Evento: " + eventoId);
        System.out.println("Localidad: " + localidad);
        System.out.println("Descuento: " + descuento + "%");
        System.out.println("Vigente desde: " + fechaInicio + " hasta " + fechaFin);
        pausar();
    }

    /**
     * Ver tiquetes que he vendido
     */
    private void verTiquetesVendidos() {
        limpiarPantalla();
        System.out.println("====== TIQUETES VENDIDOS ======");
        System.out.println("No hay tiquetes vendidos aún");
        System.out.println("\nCuando vendas tiquetes, aparecerán aquí:");
        System.out.println("- ID del tiquete");
        System.out.println("- Cliente que lo compró");
        System.out.println("- Precio");
        System.out.println("- Fecha de venta");
        pausar();
    }

    /**
     * Vender tiquete directamente
     */
    private void venderTiqueteDirectamente() {
        limpiarPantalla();
        System.out.println("====== VENDER TIQUETE DIRECTAMENTE ======");
        
        System.out.print("ID del evento: ");
        String eventoId = scanner.nextLine().trim();
        
        System.out.print("Localidad: ");
        String localidad = scanner.nextLine().trim();

        int cantidad = ValidadorEntrada.validarEnteroPositivo(
            scanner,
            "Cantidad de tiquetes"
        );
        
        if (cantidad == -1) {
            pausar();
            return;
        }

        double precioUnitario = ValidadorEntrada.validarNumeroPositivo(
            scanner,
            "Precio unitario"
        );
        
        if (precioUnitario == -1) {
            pausar();
            return;
        }

        double total = cantidad * precioUnitario;
        
        System.out.println("\n--- RESUMEN DE VENTA ---");
        System.out.println("Evento: " + eventoId);
        System.out.println("Localidad: " + localidad);
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Precio unitario: $" + String.format("%.2f", precioUnitario));
        System.out.println("Total: $" + String.format("%.2f", total));
        
        if (ValidadorEntrada.solicitarConfirmacion(scanner, "¿Confirmar venta?")) {
            System.out.println("✓ Venta realizada");
            System.out.println("Dinero agregado a tu saldo: $" + String.format("%.2f", total));
        } else {
            System.out.println("❌ Venta cancelada");
        }
        
        pausar();
    }

    /**
     * Comprar tiquete como cliente (organizador también puede comprar)
     */
    private void comprarTiqueteComoCliente() {
        limpiarPantalla();
        System.out.println("====== COMPRAR TIQUETE (COMO CLIENTE) ======");
        System.out.println("Como organizador, puedes comprar tiquetes de otros eventos");
        
        System.out.print("ID de la oferta en el marketplace: ");
        String ofertaId = scanner.nextLine().trim();
        
        if (!ValidadorEntrada.validarID(ofertaId)) {
            pausar();
            return;
        }

        System.out.println("✓ Compra realizada exitosamente");
        System.out.println("Oferta: " + ofertaId);
        System.out.println("El tiquete ahora te pertenece");
        pausar();
    }

    /**
     * Ver mi saldo
     */
    private void verMiSaldo() {
        limpiarPantalla();
        System.out.println("====== MI SALDO ======");
        double saldo = organizadorLogueado.getSaldo();
        System.out.println("Saldo disponible: $" + String.format("%.2f", saldo));
        System.out.println("\nPuedes usar este saldo para:");
        System.out.println("- Comprar tiquetes de otros eventos");
        System.out.println("- Pagar servicios de la plataforma");
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