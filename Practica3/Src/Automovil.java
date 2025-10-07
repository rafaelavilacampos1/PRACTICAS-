public class Automovil {
    private MarcaAutomovil marca;
    private String transmision;
    private String tipo;
    private int anio;
    private Chasis chasis;
    private boolean peliculaAntiAsalto;
    private Llanta llanta;
    private String tipoFrenos;
    private int precioFrenos;
    private SistemaElectronico sistemaElectronico;
    private int precioTotal;

    public Automovil(MarcaAutomovil marca, String transmision, String tipo, int anio, Chasis chasis,
                     boolean peliculaAntiAsalto, Llanta llanta, String tipoFrenos, SistemaElectronico sistemaElectronico) {

        this.marca = marca;
        this.transmision = transmision;
        this.tipo = tipo;
        this.anio = anio;
        this.chasis = chasis;
        this.peliculaAntiAsalto = peliculaAntiAsalto;
        this.llanta = llanta;
        this.tipoFrenos = tipoFrenos;
        this.sistemaElectronico = sistemaElectronico;

        calcularPrecio();
    }

    private void calcularPrecio() {
        precioTotal = 0;
        // Precio por marca
        precioTotal += marca.getPrecioImportacion();

        // Año
        if (anio == 2023) precioTotal += 30000;
        else if (anio == 2024) precioTotal += 40000;
        else if (anio == 2025) precioTotal += 50000;

        // Transmisión
        if (transmision.equalsIgnoreCase("Manual")) precioTotal += 40000;
        else precioTotal += 80000;

        // Tipo de auto
        switch (tipo.toLowerCase()) {
            case "compacto" -> precioTotal += 40000;
            case "semicompacto" -> precioTotal += 80000;
            case "sedán" -> precioTotal += 120000;
            case "deportivo" -> precioTotal += 200000;
        }

        // Chasis
        precioTotal += chasis.getPrecio();

        // Película
        if (peliculaAntiAsalto) precioTotal += 4000;

        // Llantas
        precioTotal += llanta.getPrecio();

        // Frenos
        if (tipoFrenos.equalsIgnoreCase("Disco")) precioFrenos = 3200;
        else precioFrenos = 2100;
        precioTotal += precioFrenos;

        // Sistema electrónico
        precioTotal += sistemaElectronico.getPrecioTotal();
    }

    public int getPrecioTotal() {
        return precioTotal;
    }

    @Override
    public String toString() {
        return "----- Detalles del Automóvil -----\n" +
                marca + "\n" +
                "Transmisión: " + transmision + "\n" +
                "Tipo de Auto: " + tipo + "\n" +
                "Año: " + anio + "\n" +
                chasis + "\n" +
                "Película Anti-Asalto: " + (peliculaAntiAsalto ? "Sí ($4000)" : "No") + "\n" +
                llanta + "\n" +
                "Frenos: " + tipoFrenos + " ($" + precioFrenos + ")\n" +
                sistemaElectronico + "\n" +
                "TOTAL A PAGAR: $" + precioTotal + "\n";
    }
}
