public class SistemaElectronico {
    private boolean pantallaDigital;
    private boolean espejosElectronicos;
    private boolean camaraReversa;
    private boolean sensorReversa;
    private int precioTotal;

    public SistemaElectronico(boolean pantallaDigital, boolean espejosElectronicos, boolean camaraReversa, boolean sensorReversa) {
        this.pantallaDigital = pantallaDigital;
        this.espejosElectronicos = espejosElectronicos;
        this.camaraReversa = camaraReversa;
        this.sensorReversa = sensorReversa;
        calcularPrecio();
    }

    private void calcularPrecio() {
        if (pantallaDigital) precioTotal += 3000;
        if (espejosElectronicos) precioTotal += 5000;
        if (camaraReversa) precioTotal += 7000;
        if (sensorReversa) precioTotal += 4000;
    }

    public int getPrecioTotal() {
        return precioTotal;
    }

    @Override
    public String toString() {
        return "Sistema Electrónico:\n" +
               "  Pantalla Digital: " + pantallaDigital + "\n" +
               "  Espejos Electrónicos: " + espejosElectronicos + "\n" +
               "  Cámara de Reversa: " + camaraReversa + "\n" +
               "  Sensor de Reversa: " + sensorReversa + "\n" +
               "  Precio Total: $" + precioTotal;
    }
}













