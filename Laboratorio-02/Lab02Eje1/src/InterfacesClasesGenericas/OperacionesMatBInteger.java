package InterfacesClasesGenericas;

public class OperacionesMatBInteger implements Operable<Integer>{
	@Override
	public Integer suma(Integer operando1, Integer operando2) {
		return operando1 + operando2;
	}
	
	@Override
	public Integer resta(Integer operando1, Integer operando2) {
		return operando1 - operando2;
	}
	
	@Override
	public Integer producto(Integer operando1, Integer operando2) {
		return operando1 * operando2;
	}
	
	@Override
	public Integer division(Integer operando1, Integer operando2) {
		return operando1 / operando2;
	}
	
	@Override
	public Integer potencia(Integer base, Integer exponente) {
		return (int) Math.pow(base, exponente);  //para poder retornar el numero debemos convertirlo a int
	}
	
	@Override
	public Integer raizCuadrada(Integer operando) {
		return (int) Math.sqrt(operando); //para poder retornar el numero debemos convertirlo a int
	}
	
	@Override
	public Integer raizCubica(Integer operando) {
		return (int) Math.cbrt(operando); //para poder retornar el numero debemos convertirlo a int
	}
}
