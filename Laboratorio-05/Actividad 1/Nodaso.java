public class Nodaso<T> {
    
	private T dato;
	private Nodaso<T> elQueSigue;

    public Nodaso(T dato) {
		this.dato = dato;
		this.elQueSigue = null;
	}
	
	public T getDato() {
		return dato;
	}
	
	public Nodaso<?> getElQueSigue(){
		return this.elQueSigue;
	}
	
	public void setDato(T dato) {
		this.dato = dato;
	}
	
	public void setElQueSigue(Nodaso<?> elQueSigue){
		this.elQueSigue = elQueSigue;
	}
}