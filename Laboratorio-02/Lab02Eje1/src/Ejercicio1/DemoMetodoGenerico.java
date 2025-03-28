package Ejercicio1;

class DemoMetodoGenerico {
	static <T> boolean exist(T[] x, T y) {
        for (T elemento : x) {
            if (elemento.equals(y))
                return true;
        }
        return false;
    }
	
	public static void main(String[] args) {
		Golosina[] v = {new Golosina("Chocman", 1), new Golosina("Nick", 0.5)};
		Chocolatina[] w = {new Chocolatina("milky"), new Chocolatina("triangulo")};
		
		System.out.println(exist(v, new Golosina("Chocman", 1)));
		System.out.println(exist(w, new Chocolatina("milky")));
		System.out.println(exist(w, new Chocolatina("Nick")));
//		System.out.println(exist(w, new Golosina("Nick")));      // DA ERROR
	}
}