public class GestorTareas<T> {
    private ListaEnlazada<T> lista;

    public GestorTareas(ListaEnlazada<T> lista) {
        this.lista = lista;
    }
    
    public void agregarTarea(T tarea) {
        lista.insertarPrimero(tarea);
    }

    public void mostrarTareas() {
        lista.recorrerListaEnlazada();
    }
    
}
