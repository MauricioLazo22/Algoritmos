package Actividad3;

public class BinarySearchIterativo {
    public static void main(String[] args) {
        int[] array = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
        int dato = 23;
        
        int resultado = busquedaBinaria(array, dato);
        
        if (resultado == -1) {
            System.out.println("El elemento no está presente en el array");
        } else {
            System.out.println("Elemento encontrado en el índice: " + resultado);
        }
    }

    public static int busquedaBinaria(int[] array, int dato) {
        int inf = 0;
        int sup = array.length - 1;
        
        while (inf <= sup) {
            int cen = inf + (sup - inf) / 2;
            
            if (array[cen] == dato) {
                return cen;
            }
            
            if (array[cen] > dato) {
                sup = cen - 1;
            } else {
                inf = cen + 1;
            }
        }
        
            return -1;
    }
}
