package Pruebas;

import Exceptions.ItemDuplicated;
import bstreelinklistinterfgeneric.LinkedBST;

public class PruebaInOrder {
    public static void main(String[] args) {
        try {
            // Crear el árbol según la imagen
            LinkedBST<Integer> arbol = new LinkedBST<>();
            
            // Insertar los nodos en el orden adecuado para construir el árbol mostrado
            arbol.insert(400);  // raíz
            arbol.insert(100);  // izquierda de 400
            arbol.insert(700);  // derecha de 400
            arbol.insert(50);   // izquierda de 100
            arbol.insert(200);  // derecha de 100
            arbol.insert(75);   // derecha de 50
            
            // Probar el recorrido in-orden
            System.out.println("Recorrido In-Orden del árbol:");
            arbol.inOrder(arbol.root);
            
            // El resultado esperado es: 50, 75, 100, 200, 400, 700
        } catch (ItemDuplicated e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}