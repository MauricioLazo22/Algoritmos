package Actividad1;

public class Principal {
	public static void main(String[] args) {
		System.out.println("Bolsa de Chocolatina");
		Bolsa < Chocolatina > bolsaCho = new Bolsa < Chocolatina > (0);
		Chocolatina c = new Chocolatina("milka");
		Chocolatina c1 = new Chocolatina("milka");
		Chocolatina c2 = new Chocolatina("ferrero");
		bolsaCho.add(c);
		bolsaCho.add(c1);
		bolsaCho.add(c2);
		
		for (Chocolatina chocolatina: bolsaCho) {
			System.out.println(chocolatina.getMarca());
		}
		
		// ACTIVIDAD 7
		System.out.println("");
		System.out.println("Bolsa de Golosinas");
		Bolsa < Golosina > bolsaGolo = new Bolsa < Golosina > (0);
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
