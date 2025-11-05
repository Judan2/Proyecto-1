package logica;

/**
 * Representa un CLIENTE del sistema
 * 
 * DATOS:
 * - Login y contraseña
 * - Nombre
 * - Email
 * - Saldo disponible
 */
public class Cliente {
    private String login;
    private String password;
    private String nombre;
    private String email;
    private double saldo;

    // CONSTRUCTOR
    public Cliente(String login, String password, String nombre, String email) {
        this.login = login;
        this.password = password;
        this.nombre = nombre;
        this.email = email;
        this.saldo = 0.0; // Saldo inicial es 0
    }

    // GETTERS
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public double getSaldo() {
        return saldo;
    }

    // SETTERS
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSaldo(double nuevoSaldo) {
        this.saldo = nuevoSaldo;
    }

    // MÉTODOS
    
    /**
     * Agrega dinero al saldo (cuando vende tiquetes)
     */
    public void agregarSaldo(double cantidad) {
        this.saldo += cantidad;
    }

    /**
     * Resta dinero del saldo (cuando compra tiquetes)
     */
    public boolean restarSaldo(double cantidad) {
        if (this.saldo < cantidad) {
            return false; // Saldo insuficiente
        }
        this.saldo -= cantidad;
        return true;
    }

    /**
     * Verifica si tiene saldo suficiente
     */
    public boolean tieneSaldoSuficiente(double cantidad) {
        return this.saldo >= cantidad;
    }

    /**
     * Compara dos clientes por su login
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Cliente)) return false;
        
        Cliente otro = (Cliente) obj;
        return this.login.equals(otro.login);
    }

    /**
     * Hash basado en el login
     */
    @Override
    public int hashCode() {
        return this.login.hashCode();
    }

    @Override
    public String toString() {
        return String.format(
            "Cliente[%s] - Nombre: %s - Email: %s - Saldo: $%.2f",
            login, nombre, email, saldo
        );
    }
}