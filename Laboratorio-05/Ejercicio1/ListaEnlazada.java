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
    
}