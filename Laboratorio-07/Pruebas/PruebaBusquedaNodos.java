package Pruebas;
import bstreelinklistinterfgeneric.LinkedBST;

public class PruebaBusquedaNodos {
    public static void main(String[] args) {
        try {
            LinkedBST<Integer> arbol = new LinkedBST<>();
            
            // Insertar valores
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(70);
            arbol.insert(20);
            arbol.insert(80);
            
            // Probar con métodos públicos existentes
            System.out.println("Mínimo valor: " + arbol.findMinNode(arbol.root));
            System.out.println("Máximo valor: " + arbol.findMaxNode(arbol.root));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}