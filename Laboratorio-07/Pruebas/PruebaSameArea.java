package Pruebas;
import bstreelinklistinterfgeneric.LinkedBST;

public class PruebaSameArea {

    public static void main(String[] args) {
        // Crear dos árboles
        LinkedBST<Integer> arbol1 = new LinkedBST<>();
        LinkedBST<Integer> arbol2 = new LinkedBST<>();

        try {
            arbol1.insert(400);
            arbol1.insert(100);
            arbol1.insert(700);
            arbol1.insert(50);
            arbol1.insert(200);
            arbol1.insert(75);

            arbol2.insert(300);
            arbol2.insert(150);
            arbol2.insert(600);
            arbol2.insert(120);
            arbol2.insert(170);
            arbol2.insert(50);

            // Verificar si tienen la misma área
            boolean iguales = sameArea(arbol1, arbol2);
            System.out.println("¿Tienen la misma área? " + iguales);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean sameArea(LinkedBST<?> arbol1, LinkedBST<?> arbol2) {
        return arbol1.areaBST() == arbol2.areaBST();
    }
}
