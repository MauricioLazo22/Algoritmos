package InterfacesClasesGenericas;

public class OperacionesMat <T extends Number> {
    
    private Operable<T> operaciones;
    
    public OperacionesMat(Operable<T> operaciones) {
        this.operaciones = operaciones;
    }
    
    public T suma(T operando1, T operando2) {
		return operaciones.suma(operando1, operando2);
	}
	
	public T resta(T operando1, T operando2) {
		return operaciones.resta(operando1, operando2);
	}
	
	public T producto(T operando1, T operando2) {
		return operaciones.producto(operando1, operando2);
	}
	
	public T division(T operando1, T operando2) {
		return operaciones.division(operando1, operando2);
	}
	
	public T potencia(T base, T exponente) {
		return operaciones.potencia(base, exponente);
	}
	
	public T raizCuadrada(T operando) {
		return operaciones.raizCuadrada(operando);
	}

	public T raizCubica(T operando) {
		return operaciones.raizCubica(operando);
	}
}