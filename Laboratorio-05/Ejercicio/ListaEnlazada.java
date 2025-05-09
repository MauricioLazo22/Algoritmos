public class ListaEnlazada<T> {

	private Nodaso<?> primerito;
	
	public ListaEnlazada(Nodaso<?> primerito) {
		this.primerito = primerito;
	}

    public boolean laListaEsVacia() {
		if(primerito.getElQueSigue() == null) {
			return true;
		} else {
			return false;
		}
	}

    public int longitudDeLista() {
		int longitud = 0;
		Nodaso<?> aux = primerito;
		while (aux != null) {
			longitud++;
			aux = aux.getElQueSigue();
		}
		return longitud;
	}

    public void destruirLista() {
		this.primerito.setElQueSigue(null);
	}

    public int buscarPorPosicion(T dato) {
		Nodaso<?> aux = primerito;
		int posicion = 0;
		while (aux != null) {
			if (aux.getDato() == dato) {
				return posicion;
			}
			aux = aux.getElQueSigue();
			posicion++;
		}
		return -1;
	}

	public static <T> boolean buscarElemento(ListaEnlazada<T> lista, T valor) {
		Nodaso<?> aux = lista.getPrimerito();
		while (aux != null) {
			T dato = (T) aux.getDato();
			if (dato.equals(valor)) {
				return true;
			}
			aux = aux.getElQueSigue();
		}
		return false;
	}
	
    
    public void insertarPrimero(T dato) {
		Nodaso<?> nuevoPrimero = new Nodaso<T>(dato);
		nuevoPrimero.setElQueSigue(primerito);
		primerito = nuevoPrimero;
	}
	
    public void insertarUltimo(T dato) {
		Nodaso<?> nuevoUltimo = new Nodaso<T>(dato);
		Nodaso<?> aux = primerito;
		while (aux != null) {
			aux = aux.getElQueSigue();
			if(aux == null) {
				aux.setElQueSigue(nuevoUltimo);
			}
		}
	}
	
    public void borrarNodo(T dato) {
		Nodaso<?> aux = primerito;
		Nodaso<?> aux2 = aux.getElQueSigue();
		while (aux != null) {
			if(aux2.getDato() == dato) {
				aux.setElQueSigue(aux2.getElQueSigue());
			}
			aux = aux.getElQueSigue();
		}
	}
	
    public void recorrerListaEnlazada() {
		Nodaso<?> aux = primerito;
		while (aux != null) {
			System.out.println(aux.getDato());
			aux = aux.getElQueSigue();
		}
	}
}