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
	
	public static <T> ListaEnlazada<T> inv1ertirLista(ListaEnlazada<T> original) {
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

    public void sertarPrimero(T dato) {
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

	public static <T> boolean sonIguales(Nodaso<T> head1, Nodaso<T> head2) {
		Nodaso<?> actual1 = head1;
		Nodaso<?> actual2 = head2;
	
		while (actual1 != null && actual2 != null) {
			T dato1 = (T) actual1.getDato();
			T dato2 = (T) actual2.getDato();
			if (!dato1.equals(dato2)) {
				return false;
			}
			actual1 = actual1.getElQueSigue();
			actual2 = actual2.getElQueSigue();
		}
	
		return actual1 == null && actual2 == null;
	}	

	public static <T> ListaEnlazada<T> concatenarListas(ListaEnlazada<T> lista1, ListaEnlazada<T> lista2) {
		ListaEnlazada<T> listaConcatenada = new ListaEnlazada<>(null);
		
		Nodaso<?> aux1 = lista1.getPrimerito();
		while (aux1 != null) {
			listaConcatenada.insertarUltimo((T) aux1.getDato());
			aux1 = aux1.getElQueSigue();
		}
	
		Nodaso<?> aux2 = lista2.getPrimerito();
		while (aux2 != null) {
			listaConcatenada.insertarUltimo((T) aux2.getDato());
			aux2 = aux2.getElQueSigue();
		}
	
		return listaConcatenada;
	}
	
	public void insertarUltimo(T dato) {
		Nodaso<?> nuevoUltimo = new Nodaso<T>(dato);
		Nodaso<?> aux = primerito;
		
		if (aux == null) {
			primerito = nuevoUltimo;  // Si la lista está vacía, el nuevo nodo será el primer nodo
		} else {
			while (aux.getElQueSigue() != null) {
				aux = aux.getElQueSigue();
			}
			aux.setElQueSigue(nuevoUltimo);  // Si la lista no está vacía, añadimos al final
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