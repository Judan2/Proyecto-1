package interfaz.util;

import java.util.Scanner;

/**
 * Clase utilitaria con validaciones compartidas
 * 
 * USADO POR:
 * - InterfazCliente
 * - InterfazAdmin
 * - InterfazOrganizador
 * 
 * VENTAJA:
 * - Código reutilizable
 * - Mensajes de error consistentes
 * - Fácil de mantener
 */
public class ValidadorEntrada {

    /**
     * Valida un login
     * Requisitos:
     * - No vacío
     * - Mínimo 3 caracteres
     * - Solo letras, números, guión bajo
     * 
     * @param login texto a validar
     * @return true si es válido
     */
    public static boolean validarLogin(String login) {
        if (login == null || login.trim().isEmpty()) {
            System.out.println("❌ Error: Login no puede estar vacío");
            return false;
        }
        
        if (login.length() < 3) {
            System.out.println("❌ Error: Login debe tener mínimo 3 caracteres");
            return false;
        }
        
        if (!login.matches("[a-zA-Z0-9_]+")) {
            System.out.println("❌ Error: Solo letras, números y guión bajo");
            return false;
        }
        
        return true;
    }

    /**
     * Valida una contraseña
     * Requisitos:
     * - No vacía
     * - Mínimo 6 caracteres
     * 
     * @param password contraseña a validar
     * @return true si es válida
     */
    public static boolean validarPassword(String password) {
        if (password == null || password.isEmpty()) {
            System.out.println("❌ Error: Contraseña no puede estar vacía");
            return false;
        }
        
        if (password.length() < 6) {
            System.out.println("❌ Error: Contraseña debe tener mínimo 6 caracteres");
            return false;
        }
        
        return true;
    }

    /**
     * Valida un email
     * Requisitos:
     * - Contiene @
     * - Contiene .
     * 
     * @param email correo a validar
     * @return true si es válido
     */
    public static boolean validarEmail(String email) {
        if (!email.contains("@") || !email.contains(".")) {
            System.out.println("❌ Error: Email debe tener @ y punto");
            return false;
        }
        
        String[] partes = email.split("@");
        if (partes.length != 2 || partes[0].isEmpty()) {
            System.out.println("❌ Error: Email inválido");
            return false;
        }
        
        return true;
    }

    /**
     * Valida y solicita un número positivo
     * 
     * USO:
     * double precio = ValidadorEntrada.validarNumeroPositivo(scanner, "Precio");
     * if (precio == -1) return; // Hubo error
     * 
     * @param scanner para leer entrada
     * @param mensaje qué mostrar al usuario
     * @return número validado o -1 si inválido
     */
    public static double validarNumeroPositivo(Scanner scanner, String mensaje) {
        System.out.print(mensaje + ": ");
        
        try {
            String input = scanner.nextLine().trim();
            double numero = Double.parseDouble(input);
            
            if (numero <= 0) {
                System.out.println("❌ Error: Debe ser un número positivo");
                return -1;
            }
            
            return numero;
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Ingresa un número válido");
            return -1;
        }
    }

    /**
     * Valida y solicita un número entero positivo
     * 
     * USO:
     * int cantidad = ValidadorEntrada.validarEnteroPositivo(scanner, "Cantidad");
     * if (cantidad == -1) return; // Hubo error
     * 
     * @param scanner para leer entrada
     * @param mensaje qué mostrar al usuario
     * @return número validado o -1 si inválido
     */
    public static int validarEnteroPositivo(Scanner scanner, String mensaje) {
        double resultado = validarNumeroPositivo(scanner, mensaje);
        return (resultado == -1) ? -1 : (int) resultado;
    }

    /**
     * Valida un porcentaje (0-100)
     * 
     * USO:
     * double porcentaje = ValidadorEntrada.validarPorcentaje(scanner, "Descuento");
     * if (porcentaje == -1) return; // Hubo error
     * 
     * @param scanner para leer entrada
     * @param mensaje qué mostrar al usuario
     * @return porcentaje validado o -1 si inválido
     */
    public static double validarPorcentaje(Scanner scanner, String mensaje) {
        System.out.print(mensaje + " (0-100): ");
        
        try {
            String input = scanner.nextLine().trim();
            double porcentaje = Double.parseDouble(input);
            
            if (porcentaje < 0 || porcentaje > 100) {
                System.out.println("❌ Error: Debe estar entre 0 y 100");
                return -1;
            }
            
            return porcentaje;
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Ingresa un número válido");
            return -1;
        }
    }

    /**
     * Solicita confirmación S/N al usuario
     * 
     * USO:
     * if (ValidadorEntrada.solicitarConfirmacion(scanner, "¿Deseas continuar?")) {
     *     // Usuario respondió S
     * } else {
     *     // Usuario respondió N
     * }
     * 
     * @param scanner para leer entrada
     * @param mensaje pregunta para el usuario
     * @return true si dice S, false si dice N
     */
    public static boolean solicitarConfirmacion(Scanner scanner, String mensaje) {
        System.out.print(mensaje + " (S/N): ");
        
        String respuesta = scanner.nextLine().trim().toUpperCase();
        
        while (!respuesta.equals("S") && !respuesta.equals("N")) {
            System.out.print("Por favor, ingresa S o N: ");
            respuesta = scanner.nextLine().trim().toUpperCase();
        }
        
        return respuesta.equals("S");
    }

    /**
     * Valida que un ID no sea vacío
     * 
     * @param id a validar
     * @return true si es válido
     */
    public static boolean validarID(String id) {
        if (id == null || id.trim().isEmpty()) {
            System.out.println("❌ Error: ID no puede estar vacío");
            return false;
        }
        return true;
    }

    /**
     * Pausa la pantalla (muy usado)
     * 
     * USO:
     * ValidadorEntrada.pausar(scanner);
     * 
     * @param scanner para parar
     */
    public static void pausar(Scanner scanner) {
        System.out.println("\nPresiona Enter para continuar...");
        scanner.nextLine();
    }
}