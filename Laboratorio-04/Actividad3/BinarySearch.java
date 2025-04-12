package Actividad3;

public class BinarySearch {
    public static void main(String[] args) {
        
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
