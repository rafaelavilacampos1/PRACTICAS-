/**
 * Clase que representa un automóvil configurado por el usuario,
 * incluyendo todos sus componentes y el cálculo del precio total.
 */
public class Automovil {

    // Marca del automóvil (incluye marca, país y precio de importación)
    private MarcaAutomovil marca;

    // Tipo de transmisión (Manual o Automática)
    private String transmision;

    // Tipo de auto (Compacto, Semicompacto, Sedán, Deportivo)
    private String tipo;

    // Año del automóvil (2023, 2024, 2025)
    private int anio;

    // Objeto que representa el chasis (color, acabado y precio)
    private Chasis chasis;

    // Indica si el vehículo incluye película anti-asalto
    private boolean peliculaAntiAsalto;

    // Objeto que representa las llantas del vehículo
    private Llanta llanta;

    // Tipo de frenos (Disco o Tambor)
    private String tipoFrenos;

    // Precio de los frenos
    private int precioFrenos;

    // Objeto que representa el sistema electrónico del automóvil
    private SistemaElectronico sistemaElectronico;

    // Precio total calculado del vehículo
    private int precioTotal;

    /**
     * Constructor del automóvil. Recibe todos los componentes seleccionados
     * y realiza el cálculo del precio total.
     */
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

        // Llama al método para calcular el precio total
        calcularPrecio();
    }

    /**
     * Método privado que calcula el precio total del automóvil
     * sumando los precios de cada componente y configuración.
     */
    private void calcularPrecio() {
        precioTotal = 0;

        // Precio de importación según la marca y país
        precioTotal += marca.getPrecioImportacion();

        // Precio adicional según el año
        if (anio == 2023) precioTotal += 30000;
        else if (anio == 2024) precioTotal += 40000;
        else if (anio == 2025) precioTotal += 50000;

        // Precio por tipo de transmisión
        if (transmision.equalsIgnoreCase("Manual")) precioTotal += 40000;
        else precioTotal += 80000;

        // Precio según el tipo de auto
        switch (tipo.toLowerCase()) {
            case "compacto" -> precioTotal += 40000;
            case "semicompacto" -> precioTotal += 80000;
            case "sedán" -> precioTotal += 120000;
            case "deportivo" -> precioTotal += 200000;
        }

        // Precio del chasis (acabado)
        precioTotal += chasis.getPrecio();

        // Precio por incluir película anti-asalto
        if (peliculaAntiAsalto) precioTotal += 4000;

        // Precio de las llantas (marca + rin + material)
        precioTotal += llanta.getPrecio();

        // Precio de los frenos según el tipo
        if (tipoFrenos.equalsIgnoreCase("Disco")) precioFrenos = 3200;
        else precioFrenos = 2100;
        precioTotal += precioFrenos;

        // Precio de los elementos electrónicos (pantalla, cámara, sensores, etc.)
        precioTotal += sistemaElectronico.getPrecioTotal();
    }

    /**
     * Devuelve el precio total del automóvil.
     * @return precio total en pesos
     */
    public int getPrecioTotal() {
        return precioTotal;
    }

    /**
     * Representación en texto de todos los detalles del automóvil,
     * incluyendo los componentes y el precio total.
     */
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
