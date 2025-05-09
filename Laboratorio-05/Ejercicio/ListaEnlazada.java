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
	
	public static <T> ListaEnlazada<T> invertirLista(ListaEnlazada<T> original) {
		ListaEnlazada<T> invertida = new ListaEnlazada<>(null);
		Nodaso<?> aux = original.getPrimerito();
		while (aux != null) {
			T dato = (T) aux.getDato();
			invertida.insertarPrimero(dato);
			aux = aux.getElQueSigue();
		}
		return invertida;
	}

	public static <T> Nodaso<T> insertarAlFinal(Nodaso<T> head, T valor) {
		Nodaso<T> nuevo = new Nodaso<>(valor);
	
		if (head == null) {
			return nuevo;
		}
	
		Nodaso<?> actual = head;
		while (actual.getElQueSigue() != null) {
			actual = actual.getElQueSigue();
		}
	
		actual.setElQueSigue(nuevo);
		return head;
	}	
	
	public static <T> int contarNodos(Nodaso<T> head) {
		int contador = 0;
		Nodaso<?> actual = head;
		while (actual != null) {
			contador++;
			actual = actual.getElQueSigue();
		}
		return contador;
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