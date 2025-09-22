package cl.kibernumacademy;

public class Tarea {
    private String titulo;
    private String descripcion;
    private boolean estado;

    public Tarea(String titulo, String descripcion, boolean estado) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /*
    *Sirve para generar log y depurar en caso de ser necesario porque sino al tratar de mostrar la tarea daria el nombre de la clase
    y un espacio en memoria
    */
    @Override
    public String toString() {
        return "Tarea{" +
                "titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado=" + estado +
                '}';
    }

}
