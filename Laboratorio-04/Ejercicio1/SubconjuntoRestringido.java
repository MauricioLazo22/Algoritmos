package Ejercicio1;

public class SubconjuntoRestringido {

    private static boolean esPotenciaDe2(int n) {
        if (n <= 1) {
            return false;
        }
        while (n % 2 == 0) {
            n = n / 2;
        }
        return n == 1;
    }
    
    public static boolean puedeFormarObjetivo(int[] arr, int target) {
        return backtrack(arr, 0, 0, target);
    }

}
