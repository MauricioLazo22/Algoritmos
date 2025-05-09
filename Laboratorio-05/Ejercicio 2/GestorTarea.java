public class GestorDeTareas<T> {
    private ListaEnlazada<T> lista;

    public GestorDeTareas(ListaEnlazada<T> lista) {
        this.lista = lista;
    }

    public void agregarTarea(T tarea) {
        lista.insertarUltimo(tarea);
    }

    public boolean eliminarTarea(T tarea) {
        int posicion = lista.buscar(tarea);
        if (posicion != -1) {
            lista.borrarNodo(tarea);
            return true;
        }
        return false;
    }

    public boolean contieneTarea(T tarea) {
        return lista.buscar(tarea) != -1;
    }

    public void imprimirTareas() {
        lista.recorrerListaEnlazada();
    }

    public int contarTareas() {
        return lista.longitudDeLista();
    }

    
}   