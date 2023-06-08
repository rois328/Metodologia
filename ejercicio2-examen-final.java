class Libro {
    String titulo;
    String autor;
    String id;
    String categoria;
    int edadrecomendada;
    
    Libro(String titulo, String autor, String id, String categoria, int edadrecomendada) {
      this.titulo = titulo;
      this.autor = autor;
      this.id = id;
      this.categoria = categoria;
      this.edadrecomendada = edadrecomendada;
    }
  }
  
  class Usuario {
    String nombre;
    String apellido1;
    String apellido2;
    String dni;
    int edad;
    
    Usuario(String nombre, String apellido1, String apellido2, String dni, int edad) {
      this.nombre = nombre;
      this.apellido1 = apellido1;
      this.apellido2 = apellido2;