package logica.finanzas;

public class ConfiguracionCobros {
    private double pctMusical;
    private double pctDeportivo;
    private double pctCultural;
    private double pctReligioso;
    private double cargoEmision;

    public ConfiguracionCobros(double musical, double deportivo, double cultural,
            double religioso, double cargoEmision) {
        this.pctMusical = musical;
        this.pctDeportivo = deportivo;
        this.pctCultural = cultural;
        this.pctReligioso = religioso;
        this.cargoEmision = cargoEmision;
    }

    public double getPctMusical() { return pctMusical; }
    public double getPctDeportivo() { return pctDeportivo; }
    public double getPctCultural() { return pctCultural; }
    public double getPctReligioso() { return pctReligioso; }
    public double getCargoEmision() { return cargoEmision; }

    public double calcularComision(double precioBase, String tipoEvento) {
        double pct = 0;
        switch (tipoEvento.toLowerCase()) {
            case "musical": pct = pctMusical; break;
            case "deportivo": pct = pctDeportivo; break;
            case "cultural": pct = pctCultural; break;
            case "religioso": pct = pctReligioso; break;
            default: pct = pctCultural; break;
        }
        return precioBase * pct / 100.0 + cargoEmision;
    }
}