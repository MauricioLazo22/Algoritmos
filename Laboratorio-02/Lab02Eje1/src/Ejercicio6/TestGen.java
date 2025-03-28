package Ejercicio6;

public class TestGen {
	public static void main(String[] args) {
        Cajoneria<Chocolatina> cajoneria = new Cajoneria<>(5);

        Caja<Chocolatina> caja1 = new Caja<>("Rojo", new Chocolatina("Milka"));
        Caja<Chocolatina> caja2 = new Caja<>("Azul", new Chocolatina("Ferrero"));
        Caja<Chocolatina> caja3 = new Caja<>("Verde", new Chocolatina("Lindt"));
        Caja<Chocolatina> caja4 = new Caja<>("Amarillo", new Chocolatina("Toblerone"));
        Caja<Chocolatina> caja5 = new Caja<>("Blanco", new Chocolatina("Hershey's"));

        cajoneria.add(caja1);
        cajoneria.add(caja2);
        cajoneria.add(caja3);
        cajoneria.add(caja4);
        cajoneria.add(caja5);

        System.out.println("Estado inicial de la cajonería:");
        System.out.println(cajoneria);

        System.out.println("\nBuscando 'Ferrero':");
        System.out.println(cajoneria.search(new Chocolatina("Ferrero")));

        System.out.println("\nBuscando 'Nestlé':");
        System.out.println(cajoneria.search(new Chocolatina("Nestlé")));

        System.out.println("\nEliminando 'Lindt':");
        System.out.println("Eliminado: " + cajoneria.delete(new Chocolatina("Lindt")));

        System.out.println("\nEstado final de la cajonería:");
        System.out.println(cajoneria);
    }
}