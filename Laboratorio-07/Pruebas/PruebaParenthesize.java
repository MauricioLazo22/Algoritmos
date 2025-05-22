package Pruebas;

import Exceptions.ItemDuplicated;
import bstreelinklistinterfgeneric.LinkedBST;

public class PruebaParenthesize {
    public static void main(String[] args) {
        try {
            LinkedBST<String> arbol = new LinkedBST<>();
            
            arbol.insert("Sales");
            arbol.insert("Domestic");
            arbol.insert("International");
            arbol.insert("Canada");
            arbol.insert("S. America");
            arbol.insert("Overseas");
            arbol.insert("Africa");
            arbol.insert("Europe");
            arbol.insert("Asia");
            arbol.insert("Australia");
            
            System.out.println("Representación con paréntesis:");
            arbol.parenthesize();
            
        } catch (ItemDuplicated e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
