import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Libro {
    private String titulo;
    private String autor;
    private String identificador;
    private String categoria;
    private int edadRecomendada;
    private int copiasDisponibles;

    public Libro(String titulo, String autor, String identificador, String categoria, int edadRecomendada,
            int copiasDisponibles) {
        this.titulo = titulo;
        this.autor = autor;
        this.identificador = identificador;
        this.categoria = categoria;
        this.edadRecomendada = edadRecomendada;
        this.copiasDisponibles = copiasDisponibles;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getEdadRecomendada() {
        return edadRecomendada;
    }

    public int getCopiasDisponibles() {
        return copiasDisponibles;
    }

    public void prestar() {
        if (copiasDisponibles > 0) {
            copiasDisponibles--;
            System.out.println("Libro prestado: " + titulo);
        } else {
            System.out.println("No hay copias disponibles de este libro: " + titulo);
        }
    }

    public void devolver() {
        copiasDisponibles++;
        System.out.println("Libro devuelto: " + titulo);
    }
}

class Usuario {
    private String nombre;
    private String apellidos;
    private String fechaNacimiento;
    private String dni;

    public Usuario(String nombre, String apellidos, String fechaNacimiento, String dni) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getDni() {
        return dni;
    }
}

class Biblioteca {
    private List<Libro> libros;
    private List<Usuario> usuarios;
    private static final String LIBROS_FILE = "libros.txt";
    private static final String USUARIOS_FILE = "usuarios.txt";

    public Biblioteca() {
        libros = new ArrayList<>();
        usuarios = new ArrayList<>();
        cargarLibros();
        cargarUsuarios();
    }

    public void altaLibro(String titulo, String autor, String identificador, String categoria, int edadRecomendada,
            int copiasDisponibles) {
        Libro libro = new Libro(titulo, autor, identificador, categoria, edadRecomendada, copiasDisponibles);
        libros.add(libro);
        guardarLibros();
    }

    public void bajaLibro(String identificador) {
        Libro libro = buscarLibro(identificador);
        if (libro != null) {
            libros.remove(libro);
            guardarLibros();
            System.out.println("Libro eliminado: " + libro.getTitulo());
        } else {
            System.out.println("No se encontró un libro con ese identificador: " + identificador);
        }
    }

    public void altaUsuario(String nombre, String apellidos, String fechaNacimiento, String dni) {
        Usuario usuario = new Usuario(nombre, apellidos, fechaNacimiento, dni);
        usuarios.add(usuario);
        guardarUsuarios();
    }

    public void bajaUsuario(String dni) {
        Usuario usuario = buscarUsuario(dni);
        if (usuario != null) {
            usuarios.remove(usuario);
            guardarUsuarios();
            System.out.println("Usuario eliminado: " + usuario.getNombre() + " " + usuario.getApellidos());
        } else {
            System.out.println("No se encontró un usuario con ese DNI: " + dni);
        }
    }

    public void prestarLibro(String identificador, String dni) {
        Libro libro = buscarLibro(identificador);
        Usuario usuario = buscarUsuario(dni);
        if (libro != null && usuario != null) {
            if (usuarioEsApto(usuario, libro)) {
                libro.prestar();
                guardarLibros();
            } else {
                System.out.println("El usuario no cumple con la edad recomendada para este libro.");
            }
        } else {
            System.out.println("No se encontró el libro o el usuario.");
        }
    }

    public void devolverLibro(String identificador) {
        Libro libro = buscarLibro(identificador);
        if (libro != null) {
            libro.devolver();
            guardarLibros();
        } else {
            System.out.println("No se encontró el libro.");
        }
    }

    public void listarLibros() {
        System.out.println("Lista de libros:");
        for (Libro libro : libros) {
            System.out.println("Título: " + libro.getTitulo());
            System.out.println("Autor: " + libro.getAutor());
            System.out.println("Identificador: " + libro.getIdentificador());
            System.out.println("Categoría: " + libro.getCategoria());
            System.out.println("Edad recomendada: " + libro.getEdadRecomendada());
            System.out.println("Copias disponibles: " + libro.getCopiasDisponibles());
            System.out.println("-----------------------------");
        }
    }

    public void listarLibrosPorCategoria(String categoria) {
        System.out.println("Lista de libros en la categoría '" + categoria + "':");
        for (Libro libro : libros) {
            if (libro.getCategoria().equalsIgnoreCase(categoria)) {
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Autor: " + libro.getAutor());
                System.out.println("Identificador: " + libro.getIdentificador());
                System.out.println("Edad recomendada: " + libro.getEdadRecomendada());
                System.out.println("Copias disponibles: " + libro.getCopiasDisponibles());
                System.out.println("-----------------------------");
            }
        }
    }

    public void listarLibrosPrestados() {
        System.out.println("Lista de libros prestados:");
        for (Libro libro : libros) {
            if (libro.getCopiasDisponibles() < libro.getCopiasDisponibles()) {
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Autor: " + libro.getAutor());
                System.out.println("Identificador: " + libro.getIdentificador());
                System.out.println("Edad recomendada: " + libro.getEdadRecomendada());
                System.out.println("-----------------------------");
            }
        }
    }

    public void listarLibrosDisponibles() {
        System.out.println("Lista de libros disponibles:");
        for (Libro libro : libros) {
            if (libro.getCopiasDisponibles() > 0) {
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Autor: " + libro.getAutor());
                System.out.println("Identificador: " + libro.getIdentificador());
                System.out.println("Edad recomendada: " + libro.getEdadRecomendada());
                System.out.println("-----------------------------");
            }
        }
    }

    public void listarLibrosPorUsuario(String dni) {
        Usuario usuario = buscarUsuario(dni);
        if (usuario != null) {
            System.out
                    .println("Lista de libros prestados a " + usuario.getNombre() + " " + usuario.getApellidos() + ":");
            for (Libro libro : libros) {
                if (libro.getCopiasDisponibles() < libro.getCopiasDisponibles()) {
                    System.out.println("Título: " + libro.getTitulo());
                    System.out.println("Autor: " + libro.getAutor());
                    System.out.println("Identificador: " + libro.getIdentificador());
                    System.out.println("Edad recomendada: " + libro.getEdadRecomendada());
                    System.out.println("-----------------------------");
                }
            }
        } else {
            System.out.println("No se encontró el usuario.");
        }
    }

    private void cargarLibros() {
        try {
            File file = new File(LIBROS_FILE);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String titulo = parts[0];
                String autor = parts[1];
                String identificador = parts[2];
                String categoria = parts[3];
                int edadRecomendada = Integer.parseInt(parts[4]);
                int copiasDisponibles = Integer.parseInt(parts[5]);
                Libro libro = new Libro(titulo, autor, identificador, categoria, edadRecomendada, copiasDisponibles);
                libros.add(libro);
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error al cargar los libros: " + e.getMessage());
        }
    }

    private void guardarLibros() {
        try {
            FileWriter fileWriter = new FileWriter(LIBROS_FILE);
            for (Libro libro : libros) {
                String linea = libro.getTitulo() + "," + libro.getAutor() + "," + libro.getIdentificador() + "," +
                        libro.getCategoria() + "," + libro.getEdadRecomendada() + "," + libro.getCopiasDisponibles()
                        + "\n";
                fileWriter.write(linea);
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error al guardar los libros: " + e.getMessage());
        }
    }

    private void cargarUsuarios() {
        try {
            File file = new File(USUARIOS_FILE);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String nombre = parts[0];
                String apellidos = parts[1];
                String fechaNacimiento = parts[2];
                String dni = parts[3];
                Usuario usuario = new Usuario(nombre, apellidos, fechaNacimiento, dni);
                usuarios.add(usuario);
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error al cargar los usuarios: " + e.getMessage());
        }
    }

    private void guardarUsuarios() {
        try {
            FileWriter fileWriter = new FileWriter(USUARIOS_FILE);
            for (Usuario usuario : usuarios) {
                String linea = usuario.getNombre() + "," + usuario.getApellidos() + "," + usuario.getFechaNacimiento()
                        + "," +
                        usuario.getDni() + "\n";
                fileWriter.write(linea);
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error al guardar los usuarios: " + e.getMessage());
        }
    }

    private Libro buscarLibro(String identificador) {
        for (Libro libro : libros) {
            if (libro.getIdentificador().equals(identificador)) {
                return libro;
            }
        }
        return null;
    }

    private Usuario buscarUsuario(String dni) {
        for (Usuario usuario : usuarios) {
            if (usuario.getDni().equals(dni)) {
                return usuario;
            }
        }
        return null;
    }

    private boolean usuarioEsApto(Usuario usuario, Libro libro) {
        int edadUsuario = calcularEdad(usuario.getFechaNacimiento());
        return edadUsuario >= 8 && edadUsuario >= libro.getEdadRecomendada();
    }

    private int calcularEdad(String fechaNacimiento) {
        // Implementación para calcular la edad a partir de la fecha de nacimiento
        // ...
        return 0;
    }
}

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        biblioteca.altaLibro("El Principito", "Antoine de Saint-Exupéry", "0001", "Aventuras", 6, 3);
        biblioteca.altaLibro("1984", "George Orwell", "0002", "Ciencia Ficción", 12, 5);
        biblioteca.altaLibro("Romeo y Julieta", "William Shakespeare", "0003", "Romántica", 14, 2);

        biblioteca.altaUsuario("Juan", "Pérez", "01/01/1990", "12345678A");
        biblioteca.altaUsuario("María", "González", "15/05/2005", "87654321B");

        biblioteca.prestarLibro("0001", "12345678A");
        biblioteca.prestarLibro("0002", "87654321B");

        biblioteca.listarLibros();
        biblioteca.listarLibrosPorCategoria("Aventuras");
        biblioteca.listarLibrosPrestados();
        biblioteca.listarLibrosDisponibles();
        biblioteca.listarLibrosPorUsuario("12345678A");
    }
}