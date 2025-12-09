import java.io.*;   // Importo escaner pa leer el teclado
import java.util.ArrayList; // Importo listas dinamicas
import java.util.Scanner;           // Importo cosas de archivos (File input/output)

/**
 * @author Rafael Avila Campos
 * @version 1.0
 */

public class Main {

    // --- VARIABLES GLOBALES (STATIC) ---
    // Creo lista pa guardar todos los cursos
    static ArrayList<Curso> listaCursos = new ArrayList<>();
    // Creo lista pa guardar todos los profes
    static ArrayList<Profesor> listaProfesores = new ArrayList<>();
    // Creo lista pa guardar todos los alumnos
    static ArrayList<Alumno> listaAlumnos = new ArrayList<>();
    
    // Creo el scanner pa leer lo q escribe el usuario
    static Scanner scanner = new Scanner(System.in);
    // Defino el nombre del archivo donde se guardara todo
    static final String NOMBRE_ARCHIVO = "Universidad_gustambo.dat";

    // --- ARREGLOS CON OPCIONES FIJAS (PA AYUDAR AL USUARIO) ---
    static final String[] OPCIONES_CATEGORIAS = {
        "Computacion", "Matematicas", "Fisica", "Quimica", "Biologia", 
        "Economia", "Deporte", "Literatura", "Filosofia", "Historia", "Otro"
    };
    static final String[] OPCIONES_NIVELES = { "Preparatoria", "Licenciatura", "Posgrado" };
    static final String[] OPCIONES_TITULOS = { "Bachiller", "Tecnico", "Licenciatura", "Maestria", "Doctorado" };

    // ==========================================
    // MAIN (AQUI EMPIEZA TODO EL PROGRAMA)
    // ==========================================
    public static void main(String[] args) {
        cargarDatosSeguro(); // Llamo funcion pa cargar datos del archivo al arrancar
        
        boolean sistemaActivo = true; // Bandera pa mantener el ciclo vivo

        // Ciclo while: Mientras sistemaActivo sea true, el programa sigue corriendo
        while (sistemaActivo) {
            try { // Intento hacer esto...
                mostrarMenu(); // Muestro las letras del menu
                // Pido q elija opcion (funcion segura q no truena con letras)
                int opcion = leerEntero("Selecciona una opción: ", 1, 10);

                // Switch pa ver a donde voy segun el numero
                switch (opcion) {
                    case 1 -> registrarCurso();
                    // Ir a registrar curso
                    case 2 -> registrarProfesor();
                    // Ir a registrar profe
                    case 3 -> registrarAlumno();
                    // Ir a registrar alumno
                    case 4 -> asignarProfesor(); 
                    // Ir a asignar profe
                    case 5 -> inscribirAlumno();
                    // Ir a inscribir alumno
                    case 6 -> verReportes();
                    // Ver listas
                    case 7 -> {
                        guardarDatosSeguro(); // Guardar en disco duro
                        sistemaActivo = false; // Apago la bandera pa salir del while
                        System.out.println(">> Sistema cerrado y guardado. bye.");
                    }
                    case 8 -> editarAlumno();
                    // Ir a editar alumno
                    case 9 -> editarProfesor();
                    // Ir a editar profe
                    case 10 -> editarCurso();
                    // Ir a editar curso
                }
                // --- ZONA DE EDICIÓN ---
                            } catch (Exception e) { // Si pasa algo raro en el try, caigo aqui
                System.out.println("\n!!! Error inesperado pero lo cachamos: " + e.getMessage());
                e.printStackTrace(); // Imprimo el error tecnico pa saber q paso
            }
        }
    }

    // Funcion solo pa imprimir texto del menu
    static void mostrarMenu() {
        System.out.println("\n==========================================");
        System.out.println("   SISTEMA DE GESTION V3.0 (SUPER ROBUSTO)   ");
        System.out.println("==========================================");
        System.out.println("1. Registrar Curso");
        System.out.println("2. Registrar Profesor");
        System.out.println("3. Registrar Alumno");
        System.out.println("4. Asignar Profesor a Curso");
        System.out.println("5. Inscribir Alumno a Curso");
        System.out.println("6. Ver Reportes Generales");
        System.out.println("7. GUARDAR Y SALIR");
        System.out.println("--- EDICION ---");
        System.out.println("8. Editar Alumno");
        System.out.println("9. Editar Profesor");
        System.out.println("10. Editar Curso (ojo con la regla del cupo)");
        System.out.println("==========================================");
    }

    // ==========================================
    // HERRAMIENTAS ANTIBALAS
    // ==========================================

    // Funcion pa leer texto y asegurar q no este vacio
    static String leerTexto(String mensaje) {
        while (true) { // Ciclo infinito hasta q lo haga bien
            System.out.print(mensaje); // Imprimo el mensaje
            String entrada = scanner.nextLine().trim(); // Leo y kito espacios (.trim)
            if (!entrada.isEmpty()) return entrada; // Si no esta vacio, regreso el texto
            System.out.println(">> Error: no dejes vacio el campo bro."); // Si ta vacio, regaño
        }
    }

    // Funcion pa leer enteros de forma segura
    static int leerEntero(String mensaje, int min, int max) {
        while (true) { // Ciclo infinito
            System.out.print(mensaje);
            try {
                String entrada = scanner.nextLine().trim(); // Leo como texto primero
                int numero = Integer.parseInt(entrada); // Intento convertir a numero
                // Si es numero, checo si esta en el rango permitido
                if (numero >= min && numero <= max) return numero; // Si cumple, regreso numero
                System.out.println(">> Error: El numero tiene q tar entre " + min + " y " + max);
            } catch (NumberFormatException e) { // Si falla la conversion (era letra), caigo aqui
                System.out.println(">> Error: mete numeros no letras, no inventes.");
            }
        }
    }

    // Funcion pa editar: muestra valor actual y permite dejarlo igual con Enter
    static String leerTextoEditar(String etiqueta, String valorActual) {
        System.out.println("   " + etiqueta + " [Actual: " + valorActual + "]"); // Muestro actual
        System.out.print("   (Enter pa dejar igual, o escribe nuevo): ");
        String entrada = scanner.nextLine().trim(); // Leo entrada
        if (entrada.isEmpty()) return valorActual; // Si dio Enter vacio, regreso el valor viejo
        return entrada; // Si escribio algo, regreso lo nuevo
    }

    // Funcion pa imprimir un arreglo de opciones bonito
    static void mostrarOpciones(String titulo, String[] opciones) {
        System.out.println("\n   --- " + titulo + " ---");
        System.out.print("   [ ");
        for (String op : opciones) System.out.print(op + " | "); // Recorro arreglo e imprimo
        System.out.println("]");
    }

    // ==========================================
    // FUNCIONES DE REGISTRO
    // ==========================================

    static void registrarCurso() {
        System.out.println("\n--- NUEVO CURSO ---");
        String id = leerTexto("ID unico del curso: "); // Pido ID
        if (buscarCurso(id) != null) { System.out.println(">> Ese ID ya existe men."); return; } // Valido duplicado

        String nombre = leerTexto("Nombre del curso: ");
        
        mostrarOpciones("Categorias", OPCIONES_CATEGORIAS); // Muestro opciones
        String cat = leerTexto("Escribe la categoria tal cual: ");

        mostrarOpciones("Niveles", OPCIONES_NIVELES); // Muestro niveles
        String niv = leerTexto("Escribe el nivel: ");
        
        String plat = leerTexto("Plataforma (Zoom/Meet): ");
        int hInicio = leerEntero("Hora Inicio (7-20): ", 7, 20); // Pido hora validada
        int hFin = leerEntero("Hora Fin ("+(hInicio+1)+"-21): ", hInicio+1, 21); // Pido hora fin validada
        int cupo = leerEntero("Cupo Maximo (1-100): ", 1, 100); // Pido cupo

        // Creo el objeto Curso con todo lo q pedi
        Curso c = new Curso(id, nombre, cat, niv, plat, hInicio, hFin, cupo);
        // Pido fechas y las guardo
        c.setFechasInscripcion(leerTexto("Fecha Inicio: "), leerTexto("Fecha Fin: "));
        
        listaCursos.add(c); // Agrego a la lista global
        System.out.println(">> Curso guardado.");
    }

    static void registrarProfesor() {
        System.out.println("\n--- NUEVO PROFE ---");
        String id = leerTexto("ID Trabajador: ");
        if (buscarProfesor(id) != null) { System.out.println(">> ID repetido."); return; }

        String nombre = leerTexto("Nombre Completo: ");
        mostrarOpciones("Titulos", OPCIONES_TITULOS);
        String titulo = leerTexto("Titulo: ");
        int anio = leerEntero("Año Titulacion (1950-2025): ", 1950, 2025);

        // Creo el profe basico
        Profesor p = new Profesor(nombre, id, titulo, anio);

        // Ciclo pa meter materias
        mostrarOpciones("Materias", OPCIONES_CATEGORIAS);
        System.out.println("Escribe materias ('fin' pa salir):");
        while(true) {
            String dato = leerTexto("   > Materia: ");
            if (dato.equalsIgnoreCase("fin")) break; // Si escribe fin, rompo ciclo
            p.agregarCategoria(dato); // Agrego materia al profe
        }

        // Ciclo pa meter niveles
        mostrarOpciones("Niveles", OPCIONES_NIVELES);
        System.out.println("Escribe niveles ('fin' pa salir):");
        while(true) {
            String dato = leerTexto("   > Nivel: ");
            if (dato.equalsIgnoreCase("fin")) break;
            p.agregarNivel(dato); // Agrego nivel al profe
        }

        listaProfesores.add(p); // Guardo profe en lista global
        System.out.println(">> Profe guardado.");
    }

    static void registrarAlumno() {
        System.out.println("\n--- NUEVO ALUMNO ---");
        String id = leerTexto("Num Cuenta: ");
        if (buscarAlumno(id) != null) { System.out.println(">> ID repetido."); return; }

        String nombre = leerTexto("Nombre: ");
        int edad = leerEntero("Edad (10-99): ", 10, 99);
        String escuela = leerTexto("Prepa de origen: ");

        // Creo alumno y lo guardo directo en la lista
        listaAlumnos.add(new Alumno(nombre, edad, escuela, id));
        System.out.println(">> Alumno listo.");
    }

    // ==========================================
    // OPERACIONES
    // ==========================================

    static void asignarProfesor() {
        String idC = leerTexto("ID Curso: ");
        Curso c = buscarCurso(idC); // Busco curso
        if (c == null) { System.out.println(">> No existe el curso."); return; } // Si es null, adios
        if (c.tieneProfesor()) { System.out.println(">> Ya tiene profe, no inventes."); return; } // Si ya tiene, error

        String idP = leerTexto("ID Profe: ");
        Profesor p = buscarProfesor(idP); // Busco profe
        if (p == null) { System.out.println(">> No existe el profe."); return; }

        // Valido si el profe sabe dar esa clase (metodo del profe)
        if (p.puedeDarClase(c.getNivel(), c.getCategoria())) {
            c.asignarProfesor(p); // Si sabe, lo asigno
            System.out.println(">> Asignacion chida, todo bien.");
        } else {
            // Si no sabe, imprimo error y digo que necesita
            System.out.println(">> Error: El profe no sabe dar esa clase.");
            System.out.println("   Requiere: " + c.getCategoria() + " / " + c.getNivel());
        }
    }

    static void inscribirAlumno() {
        String idC = leerTexto("ID Curso: ");
        Curso c = buscarCurso(idC); // Busco curso
        if (c == null) { System.out.println(">> No existe curso."); return; }

        String idA = leerTexto("ID Alumno: ");
        Alumno a = buscarAlumno(idA); // Busco alumno
        if (a == null) { System.out.println(">> No existe alumno."); return; }

        if (!c.hayCupo()) { System.out.println(">> Ta lleno el curso."); return; } // Valido cupo del curso
        if (!a.puedeInscribir()) { System.out.println(">> Ya tiene 6 materias, q no abuse."); return; } // Valido carga del alumno

        // Intento inscribir (la funcion regresa true o false)
        if (c.inscribirAlumno(a)) {
            a.agregarCurso(c.getIdCurso()); // Si pudo, agrego ID del curso al alumno tambien
            System.out.println(">> Inscrito!");
        } else {
            System.out.println(">> Ya estaba inscrito, pon atencion.");
        }
    }

    // ==========================================
    // EDICION AVANZADA
    // ==========================================

    static void editarAlumno() {
        String id = leerTexto("ID Alumno a editar: ");
        Alumno a = buscarAlumno(id); // Busco alumno
        if (a == null) { System.out.println(">> No ta."); return; }

        // Uso setters con la herramienta de edicion inteligente
        a.setNombre(leerTextoEditar("Nombre", a.getNombre()));
        a.setEscuela(leerTextoEditar("Escuela", a.getEscuela()));
        System.out.println(">> Alumno actualizado.");
    }

    static void editarProfesor() {
        String id = leerTexto("ID Profe a editar: ");
        Profesor p = buscarProfesor(id); // Busco profe
        if (p == null) { System.out.println(">> No ta."); return; }

        p.setNombre(leerTextoEditar("Nombre", p.getNombre())); // Edito nombre
        
        mostrarOpciones("Titulos Sugeridos", OPCIONES_TITULOS);
        p.setTitulo(leerTextoEditar("Titulo", p.getTitulo())); // Edito titulo

        // Logica pa Editar Materias
        System.out.println("\n--- MATERIAS: " + p.getCategorias() + " ---");
        System.out.print("¿Cambiar materias? (si/no): ");
        // Si escribe "si", entro a editar
        if (scanner.nextLine().trim().equalsIgnoreCase("si")) {
            p.limpiarCategorias(); // Borro viejas
            mostrarOpciones("Opciones", OPCIONES_CATEGORIAS);
            System.out.println("Pon las nuevas ('fin' pa acabar):");
            while(true) {
                String mat = leerTexto(" > ");
                if (mat.equalsIgnoreCase("fin")) break;
                p.agregarCategoria(mat); // Agrego nuevas
            }
        }

        // Logica pa Editar Niveles (Misma idea q arriba)
        System.out.println("\n--- NIVELES: " + p.getNiveles() + " ---");
        System.out.print("¿Cambiar niveles? (si/no): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("si")) {
            p.limpiarNiveles(); 
            mostrarOpciones("Opciones", OPCIONES_NIVELES);
            System.out.println("Pon los nuevos ('fin' pa acabar):");
            while(true) {
                String niv = leerTexto(" > ");
                if (niv.equalsIgnoreCase("fin")) break;
                p.agregarNivel(niv);
            }
        }
        System.out.println(">> Profe actualizado.");
    }

    static void editarCurso() {
        String id = leerTexto("ID Curso a editar: ");
        Curso c = buscarCurso(id); // Busco curso
        if (c == null) { System.out.println(">> No ta."); return; }

        // REGLA DEL JEFE: solo editar si hay mas de 3 alumnos
        if (c.getCantidadAlumnos() <= 3) {
            System.out.println(">> BLOQUEADO: Tienen q haber mas de 3 chavos pa editar.");
            System.out.println("   Ahorita hay: " + c.getCantidadAlumnos());
            return; // Me salgo, no dejo editar
        }

        System.out.println(">> Regla cumplida. Editando...");
        c.setNombre(leerTextoEditar("Nombre", c.getNombre())); // Edito nombre
        
        mostrarOpciones("Opciones", OPCIONES_CATEGORIAS);
        c.setCategoria(leerTextoEditar("Categoria", c.getCategoria())); // Edito categoria

        c.setNivel(leerTextoEditar("Nivel", c.getNivel())); // Edito nivel
        System.out.println(">> Curso actualizado.");
    }

    static void verReportes() {
        System.out.println("\n--- REPORTE ---");
        System.out.println("Cursos: " + listaCursos.size());
        for(Curso c : listaCursos) System.out.println("  " + c); // Imprimo cada curso
        
        System.out.println("\nProfes: " + listaProfesores.size());
        for(Profesor p : listaProfesores) System.out.println("  " + p); // Imprimo cada profe

        System.out.println("\nAlumnos: " + listaAlumnos.size());
        for(Alumno a : listaAlumnos) System.out.println("  " + a); // Imprimo cada alumno
    }

    // ==========================================
    // BUSQUEDAS Y ARCHIVOS
    // ==========================================

    // Funcion buscar Curso x ID
    static Curso buscarCurso(String id) {
        for(Curso c : listaCursos) if (c.getIdCurso().equalsIgnoreCase(id)) return c; // Comparo ID
        return null; // Si no encuentro nada regreso null
    }
    // Funcion buscar Profe x ID
    static Profesor buscarProfesor(String id) {
        for(Profesor p : listaProfesores) if (p.getNumTrabajador().equalsIgnoreCase(id)) return p;
        return null;
    }
    // Funcion buscar Alumno x ID
    static Alumno buscarAlumno(String id) {
        for(Alumno a : listaAlumnos) if (a.getNumeroCuenta().equalsIgnoreCase(id)) return a;
        return null;
    }

    // Funcion Guardar
    static void guardarDatosSeguro() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(NOMBRE_ARCHIVO))) {
            out.writeObject(listaCursos);      // Escribo la lista de cursos
            out.writeObject(listaProfesores);  // Escribo la lista de profes
            out.writeObject(listaAlumnos);     // Escribo la lista de alumnos
            System.out.println(">> Guardado en el disco duro.");
        } catch (IOException e) {
            System.out.println(">> Error guardando: " + e.getMessage());
        }
    }

    // Funcion Cargar (con un warning rarito q ignoramos con suppresswarnings)
    @SuppressWarnings("unchecked")
    static void cargarDatosSeguro() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(NOMBRE_ARCHIVO))) {
            listaCursos = (ArrayList<Curso>) in.readObject();      // Leo cursos
            listaProfesores = (ArrayList<Profesor>) in.readObject(); // Leo profes
            listaAlumnos = (ArrayList<Alumno>) in.readObject();     // Leo alumnos
            System.out.println(">> Datos cargados chido.");
        } catch (Exception e) {
            // Si falla (pq no hay archivo aun), inicializo listas vacias
            System.out.println(">> No habia archivo o estaba dañado, empezamos de cero.");
            listaCursos = new ArrayList<>();
            listaProfesores = new ArrayList<>();
            listaAlumnos = new ArrayList<>();
        }
    }
}