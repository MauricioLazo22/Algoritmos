package Ejercicio2;

import java.util.Random;

public class KEsimoElemento {
    public static int encontrarKEsimo(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            throw new IllegalArgumentException("k debe estar entre 1 y el tamaño del arreglo");
        }
        return quickSelect(arr.clone(), 0, arr.length - 1, k - 1);
    }
    
    private static int quickSelect(int[] arr, int izquierda, int derecha, int k) {
        // Caso base: cuando el subarreglo tiene un solo elemento
        if (izquierda == derecha) {
        return arr[izquierda];
        }

        int indicePivote = particionAleatoria(arr, izquierda, derecha);// indicePivote = 4

        if (k == indicePivote) { 
            return arr[k];
        } 
        else if (k < indicePivote) {
            return quickSelect(arr, izquierda, indicePivote - 1,k);
        } else {
            return quickSelect(arr, indicePivote + 1, derecha, k);
        }
    }
    
    private static int particionAleatoria(int[] arr, int izquierda, int derecha) {
        // Seleccionar un pivote aleatorio
        Random rand = new Random();
        int indicePivote = izquierda + rand.nextInt(derecha – izquierda + 1); 
        swap(arr, indicePivote, derecha); 
        int pivote = arr[derecha]; 
        int i = izquierda; 

        for (int j = izquierda; j < derecha; j++) { // 0,4
            if (arr[j] <= pivote) { // 4 < 6
                swap(arr, i, j); // {4, 2, 1, 4, 6}
                i++;
            }
        }
        swap(arr, i, derecha);
        return i;
    }

}
