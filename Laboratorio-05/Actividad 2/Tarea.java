public class Tarea{

    private String titulo;
    private int prioridad;

    public Tarea(String titulo, int prioridad) {
        this.titulo = titulo;
        this.prioridad = prioridad;
    }

    private void setTitulo(String titulo){
        this.titulo = titulo;
    }
    
    private void setPrioridad(int prioridad){
        this.prioridad = prioridad;
    }

    private String getTitulo(){
        return this.titulo;
    }

    private int getPrioridad(){
        return this.prioridad;
    }

    @Override
    public String toString() {
        return titulo + " (Prioridad: " + prioridad + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Tarea)) return false;
        Tarea otra = (Tarea) obj;
        return this.titulo.equals(otra.titulo) && this.prioridad == otra.prioridad;
    }
}