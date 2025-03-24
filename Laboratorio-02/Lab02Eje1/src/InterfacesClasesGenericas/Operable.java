package InterfacesClasesGenericas;

public interface Operable<N extends Number> {
	public N suma(N operando1, N operando2);
	public N resta(N operando1, N operando2);
	public N producto(N operando1, N operando2);
	public N division(N operando1, N operando2);
	public N potencia(N base, N exponente);
	public N raizCuadrada(N operando1);
	public N raizCubica(N operando1);
}
