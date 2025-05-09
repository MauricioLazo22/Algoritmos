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

    public T obtenerTareaMasPrioritaria() {
        T tareaMasPrioritaria = null;
        int maxPrioridad = Integer.MIN_VALUE;
        Nodaso<?> aux = lista.getPrimerito();

        while (aux != null) {
            T tarea = (T) aux.getDato();
            if (tarea instanceof Tarea) {
                Tarea t = (Tarea) tarea;
                if (t.getPrioridad() > maxPrioridad) {
                    maxPrioridad = t.getPrioridad();
                    tareaMasPrioritaria = tarea;
                }
            }
            aux = aux.getElQueSigue();
        }
        return tareaMasPrioritaria;
    }

    
}   