package logica;

public abstract class Usuario {
    protected String login;
    protected String passwordHash;
    protected boolean activo = true;

    public Usuario(String login, String password) {
        this.login = login;
        this.passwordHash = hash(password);
    }

    private String hash(String pwd) {
        return Integer.toHexString((pwd == null) ? 0 : pwd.hashCode());
    }

    public boolean autenticar(String password) {
        return activo && hash(password).equals(passwordHash);
    }

    public String getLogin() {
        return login;
    }

    public void desactivar() {
        activo = false;
    }

    public boolean isActivo() {
        return activo;
    }
}