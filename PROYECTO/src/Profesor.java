/**
 * Clase que representa a un profesor y sus características profesionales.
 * Implementa {@link Serializable} para permitir guardar objetos en archivos.
 * Contiene información personal, académica y las listas de niveles y categorías
 * que puede impartir.
 */
import java.io.Serializable; // Pa guardar en archivo
import java.util.ArrayList;  // Pa usar listas

// Clase del profe, tambien serializable pa guardarse
public class Profesor implements Serializable {
    private static final long serialVersionUID = 1L; // Version del archivo

    // --- DATOS DEL PROFE ---
    /** Nombre completo del profesor. */
    private String nombre;          // Nombre del maestro

    /** Número de trabajador (ID único del profesor). */
    private final String numTrabajador;   // Su ID de chamba

    /** Título académico del profesor (Lic, Ing, Dr, etc.). */
    private String titulo;          // Si es Lic, Ing, Dr...

    /** Año en que obtuvo su título académico. */
    private int anioTitulo;         // En que año se graduo
    
    // --- LISTAS DE LO QUE SABE HACER ---
    /** Lista de niveles que puede impartir (ej. Prepa, Uni). */
    private final ArrayList<String> nivelesQueDa;    // Lista de niveles (Prepa, Uni...)

    /** Lista de categorías o materias que puede impartir (ej. Mate, Física). */
    private final ArrayList<String> categoriasQueDa; // Lista de materias (Mate, Fisica...)

    // --- CONSTRUCTOR ---
    /**
     * Crea un nuevo profesor con datos básicos y listas inicializadas.
     *
     * @param nombre        Nombre del profesor
     * @param numTrabajador ID único del trabajador
     * @param titulo        Título académico del profesor
     * @param anioTitulo    Año en que obtuvo el título
     */
    public Profesor(String nombre, String numTrabajador, String titulo, int anioTitulo) {
        this.nombre = nombre;             // Asigno nombre
        this.numTrabajador = numTrabajador; // Asigno ID
        this.titulo = titulo;             // Asigno titulo
        this.anioTitulo = anioTitulo;     // Asigno año
        this.nivelesQueDa = new ArrayList<>();    // Inicializo lista vacia de niveles
        this.categoriasQueDa = new ArrayList<>(); // Inicializo lista vacia de materias
    }

    // --- GETTERS (Pa leer datos desde afuera) ---
    /** @return Número de trabajador del profesor. */
    public String getNumTrabajador() { return numTrabajador; } // Dame ID

    /** @return Nombre del profesor. */
    public String getNombre() { return nombre; }               // Dame Nombre

    /** @return Título académico del profesor. */
    public String getTitulo() { return titulo; }               // Dame Titulo

    /** @return Año en que obtuvo su título académico. */
    public int getAnioTitulo() { return anioTitulo; }          // Dame Año

    /** @return Lista de niveles que puede impartir. */
    public ArrayList<String> getNiveles() { return nivelesQueDa; }       // Dame lista niveles

    /** @return Lista de categorías que puede impartir. */
    public ArrayList<String> getCategorias() { return categoriasQueDa; } // Dame lista materias

    // --- SETTERS (Pa editar datos) ---
    /** Actualiza el nombre del profesor. */
    public void setNombre(String nombre) { this.nombre = nombre; } // Actualiza nombre

    /** Actualiza el título académico del profesor. */
    public void setTitulo(String titulo) { this.titulo = titulo; } // Actualiza titulo

    /** Actualiza el año del título académico. */
    public void setAnioTitulo(int anio) { this.anioTitulo = anio; } // Actualiza año

    // --- METODOS PA BORRAR LISTAS (Usados al editar) ---
    /** Elimina todos los niveles registrados. */
    public void limpiarNiveles() { this.nivelesQueDa.clear(); }      // Borra todos los niveles

    /** Elimina todas las categorías registradas. */
    public void limpiarCategorias() { this.categoriasQueDa.clear(); } // Borra todas las materias

    // --- METODOS PA LLENAR LISTAS ---
    /**
     * Agrega un nivel que el profesor puede impartir, evitando duplicados.
     *
     * @param nivel Nivel que se desea agregar
     */
    public void agregarNivel(String nivel) {
        // Si la lista NO contiene este nivel...
        if (!nivelesQueDa.contains(nivel)) {
             nivelesQueDa.add(nivel); // ...entonces agregalo
        }
    }

    /**
     * Agrega una categoría/materia que el profesor puede impartir, evitando duplicados.
     *
     * @param cat Categoría o materia a agregar
     */
    public void agregarCategoria(String cat) {
        // Si la lista NO contiene esta materia...
        if (!categoriasQueDa.contains(cat)) {
            categoriasQueDa.add(cat); // ...entonces agregala
        }
    }
    
    // --- LOGICA IMPORTANTE ---
    
    /**
     * Verifica si el profesor cumple con los requisitos para impartir un curso,
     * comparando tanto el nivel como la categoría requerida.
     *
     * @param nivelRequerido     Nivel solicitado para la clase
     * @param categoriaRequerida Categoría/materia solicitada
     * @return {@code true} si el profesor cumple con ambos requisitos; de lo contrario, {@code false}.
     */
    public boolean puedeDarClase(String nivelRequerido, String categoriaRequerida) {
        boolean tieneNivel = false; // Asumo q no tiene el nivel
        boolean tieneCat = false;   // Asumo q no tiene la materia

        // Recorro su lista de niveles uno por uno
        for(String n : nivelesQueDa) {
            // Si encuentro el nivel (ignorando mayusculas)...
            if(n.equalsIgnoreCase(nivelRequerido)) tieneNivel = true; // ...entonces si lo tiene!
        }
        
        // Recorro su lista de materias una por una
        for(String c : categoriasQueDa) {
            // Si encuentro la materia...
            if(c.equalsIgnoreCase(categoriaRequerida)) tieneCat = true; // ...entonces si la tiene!
        }
        
        // Regreso TRUE solo si tiene AMBAS cosas (nivel Y materia)
        return tieneNivel && tieneCat;
    }
}
