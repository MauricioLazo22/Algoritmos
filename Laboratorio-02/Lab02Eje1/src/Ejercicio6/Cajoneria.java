package Ejercicio6;
import java.util.ArrayList;
import java.util.Iterator;

public class Cajoneria<T> implements Iterable<Caja<T>> {
    private ArrayList<Caja<T>> lista = new ArrayList<>();
    private int tope;

    public Cajoneria(int tope) {
        this.tope = tope;
    }

    public void add(Caja<T> caja) {
        if (lista.size() < tope) {
            lista.add(caja);
        } else {
            throw new RuntimeException("No caben más cajas en la cajonería.");
        }
    }

    public String search(T elemento) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getContenido().equals(elemento)) {
                return "Elemento encontrado en la posición " + i + ", en una caja de color " + lista.get(i).getColor();
            }
        }
        return "Elemento no encontrado en la cajonería.";
    }

    public T delete(T elemento) {
        Iterator<Caja<T>> iter = lista.iterator();
        while (iter.hasNext()) {
            Caja<T> caja = iter.next();
            if (caja.getContenido().equals(elemento)) {
                iter.remove();
                return caja.getContenido();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-10s %-12s %-20s\n", "Posicion", "Color Caja", "Objeto"));
        sb.append("------------------------------------------------\n");
        
        for (int i = 0; i < lista.size(); i++) {
            Caja<T> caja = lista.get(i);
            sb.append(String.format("%-10d %-12s %-20s\n", i + 1, caja.getColor(), caja.getContenido()));
        }

        return sb.toString();
    }


    @Override
    public Iterator<Caja<T>> iterator() {
        return lista.iterator();
    }
}