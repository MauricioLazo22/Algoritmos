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
        int indicePivote = izquierda + rand.nextInt(derecha - izquierda + 1); 
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

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        // Casos de prueba
        int[] arr1 = {4, 2, 7, 10, 4, 17};
        System.out.println("Caso 1: " + encontrarKEsimo(arr1, 3)); // Debe devolver 4
       
        int[] arr2 = {4, 2, 7, 10, 4, 1, 6};
        System.out.println("Caso 2: " + encontrarKEsimo(arr2, 5)); // Debe devolver 6
       
        int[] arr3 = {4, 2, 7, 1, 4, 6};
        System.out.println("Caso 3: " + encontrarKEsimo(arr3, 1)); // Debe devolver 1
       
        int[] arr4 = {9, 2, 7, 1, 7};
        System.out.println("Caso 4: " + encontrarKEsimo(arr4, 4)); // Debe devolver 7
    }
}

