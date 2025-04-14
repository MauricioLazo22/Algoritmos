package Ejercicio2;

public class KEsimoElemento {
    public static int encontrarKEsimo(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            throw new IllegalArgumentException("k debe estar entre 1 y el tamaño del arreglo");
        }
        return quickSelect(arr.clone(), 0, arr.length - 1, k - 1);
    }
       
}
