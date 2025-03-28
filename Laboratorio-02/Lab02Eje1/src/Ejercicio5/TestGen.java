package Ejercicio5;

public class TestGen {
    public static void main(String[] args) {
        Cajoneria<Golosina> cajoneria = new Cajoneria<>(5);
        
        Caja<Golosina> caja1 = new Caja<>("Rojo", new Golosina("Chocman", 50));
        Caja<Golosina> caja2 = new Caja<>("Azul", new Golosina("Snickers", 45));
        Caja<Golosina> caja3 = new Caja<>("Verde", new Golosina("MilkyWay", 40));
        Caja<Golosina> caja4 = new Caja<>("Amarillo", new Golosina("Twix", 42));
        Caja<Golosina> caja5 = new Caja<>("Blanco", new Golosina("KitKat", 48));

        cajoneria.add(caja1);
        cajoneria.add(caja2);
        cajoneria.add(caja3);
        cajoneria.add(caja4);
        cajoneria.add(caja5);

        System.out.println("Estado inicial de la cajonería:");
        System.out.println(cajoneria);

        System.out.println("\nBuscando 'Snickers (45g)':");
        System.out.println(cajoneria.search(new Golosina("Snickers", 45)));

        System.out.println("\nBuscando 'M&M's (30g)':");
        System.out.println(cajoneria.search(new Golosina("M&M's", 30)));

        System.out.println("\nEliminando 'MilkyWay (40g)':");
        System.out.println("Eliminado: " + cajoneria.delete(new Golosina("MilkyWay", 40)));

        System.out.println("\nEstado final de la cajonería:");
        System.out.println(cajoneria);
    }
}