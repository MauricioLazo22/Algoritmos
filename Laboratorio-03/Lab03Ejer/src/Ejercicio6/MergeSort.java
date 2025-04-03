package Ejercicio6;

import java.util.Arrays;

public class MergeSort {

    // Método principal que ordena un arreglo de enteros
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return; // Caso base: arreglo vacío o de tamaño 1 ya está ordenado
        }
        int[] temp = new int[arr.length]; // Arreglo temporal para fusionar
        mergeSort(arr, temp, 0, arr.length - 1);
    }

    // Método recursivo para dividir el arreglo
    private static void mergeSort(int[] arr, int[] temp, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2; // Punto medio para dividir
            mergeSort(arr, temp, left, mid);     // Ordenar mitad izquierda
            mergeSort(arr, temp, mid + 1, right); // Ordenar mitad derecha
            merge(arr, temp, left, mid, right);  // Fusionar las mitades ordenadas
        }
    }

    // Método para fusionar dos subarreglos ordenados
    private static void merge(int[] arr, int[] temp, int left, int mid, int right) {
        // Copiar datos al arreglo temporal
        System.arraycopy(arr, left, temp, left, right - left + 1);

        int i = left;    // Índice para la mitad izquierda
        int j = mid + 1; // Índice para la mitad derecha
        int k = left;    // Índice para el arreglo final

        // Comparar y fusionar
        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                arr[k++] = temp[i++];
            } else {
                arr[k++] = temp[j++];
            }
        }

        // Copiar elementos restantes de la mitad izquierda (si los hay)
        while (i <= mid) {
            arr[k++] = temp[i++];
        }
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};
        System.out.println("Arreglo original: " + Arrays.toString(arr));

        mergeSort(arr);
        System.out.println("Arreglo ordenado: " + Arrays.toString(arr));
    }
}