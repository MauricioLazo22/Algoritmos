package InterfacesClasesGenericas;

public class Prueba {
	public static void main(String[] args) {
		OperacionesMatBInteger operacionesMatInteger = new OperacionesMatBInteger();
		System.out.println(operacionesMatInteger.suma(1, 100));
		System.out.println(operacionesMatInteger.resta(100, 1));
		System.out.println(operacionesMatInteger.producto(1, 100));
		System.out.println(operacionesMatInteger.division(1, 100));
		OperacionesMat<Integer> operacionesMatB = new OperacionesMat<>(operacionesMatInteger);
		System.out.println(operacionesMatB.suma(1, 100));
	}
}
