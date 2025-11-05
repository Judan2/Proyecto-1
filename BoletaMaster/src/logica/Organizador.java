package logica;

/**
 * Representa un ORGANIZADOR DE EVENTOS en el sistema
 * 
 * DATOS:
 * - Login y contraseña
 * - Nombre de la empresa
 * - Email de la empresa
 * - Estado (pendiente, aprobado, rechazado)
 * - Saldo
 */
public class Organizador {
    private String login;
    private String password;
    private String nombreEmpresa;
    private String email;
    private String estado; // pendiente, aprobado, rechazado
    private double saldo;

    // CONSTRUCTOR
    public Organizador(String login, String password, String nombreEmpresa, String email) {
        this.login = login;
        this.password = password;
        this.nombreEmpresa = nombreEmpresa;
        this.email = email;
        this.estado = "pendiente"; // Al registrarse, está pendiente de aprobación
        this.saldo = 0.0;
    }

    // GETTERS
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public String getEmail() {
        return email;
    }

    public String getEstado() {
        return estado;
    }

    public double getSaldo() {
        return saldo;
    }

    // SETTERS
    public void setNombreEmpresa(String nombre) {
        this.nombreEmpresa = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public void setSaldo(double nuevoSaldo) {
        this.saldo = nuevoSaldo;
    }

    // MÉTODOS
    
    /**
     * Agrega dinero al saldo
     */
    public void agregarSaldo(double cantidad) {
        this.saldo += cantidad;
    }

    /**
     * Resta dinero del saldo
     */
    public boolean restarSaldo(double cantidad) {
        if (this.saldo < cantidad) {
            return false;
        }
        this.saldo -= cantidad;
        return true;
    }

    /**
     * Verifica si está aprobado
     */
    public boolean estaAprobado() {
        return "aprobado".equals(this.estado);
    }

    /**
     * Compara dos organizadores
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Organizador)) return false;
        
        Organizador otro = (Organizador) obj;
        return this.login.equals(otro.login);
    }

    @Override
    public String toString() {
        return String.format(
            "Organizador[%s] - Empresa: %s - Estado: %s - Email: %s",
            login, nombreEmpresa, estado, email
        );
    }
}