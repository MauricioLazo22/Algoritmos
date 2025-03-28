package Ejercicio2;

public class Principal {
	public static void main(String[] args) {
		System.out.println("Bolsa de Golosinas");
		Bolsa < Golosina > bolsaGolo = new Bolsa < Golosina > (3);
		Golosina g = new Golosina("Chocman", 3);
		Golosina g1 = new Golosina("Nick", 6);
		Golosina g2 = new Golosina("Gaseosa", 9);
		bolsaGolo.add(g);
		bolsaGolo.add(g1);
		bolsaGolo.add(g2);
		
		for (Golosina golosina : bolsaGolo) {
			System.out.println(golosina.getNombre());
			System.out.print(golosina.getPeso());
			System.out.println();	
        }
	}
}