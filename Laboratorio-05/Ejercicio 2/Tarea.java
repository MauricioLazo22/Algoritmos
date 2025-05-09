public class Tarea{

    private String titulo;
    private int prioridad;

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
}