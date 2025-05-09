public class GestorTareas<T> {
    private ListaEnlazada<T> lista;

    public GestorTareas(ListaEnlazada<T> lista) {
        this.lista = lista;
    }
    
    public void agregarTarea(T tarea) {
        lista.insertarPrimero(tarea);
    }

    public void eliminarTarea(T tarea) {
        lista.borrarNodo(tarea);
    }

    public int buscarTarea(T tarea) {
        return lista.buscar(tarea);
    }

    public void mostrarTareas() {
        lista.recorrerListaEnlazada();
    }

    public boolean estaVacia() {
        return lista.laListaEsVacia();
    }

    public int contarTareas() {
        return lista.longitudDeLista();
    }
}
