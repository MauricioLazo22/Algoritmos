public class GestorDeTareas<T> {
    private ListaEnlazada<T> lista;

    public GestorDeTareas(ListaEnlazada<T> lista) {
        this.lista = lista;
    }
}