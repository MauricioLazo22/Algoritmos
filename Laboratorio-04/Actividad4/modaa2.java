package Actividad4;

public class modaa2 {
    public static int moda2(int array[]) {
        int first = 1;
        int p = 0;
        int end = array.length-1;
        int moda = array[0];
        int frec = 1;
        int maxfrec = 0;
        while (first <= end) {
            if (array[p] == array[first]) {
                frec ++; 
                first++; 
            }
            else {
                if (frec > maxfrec) {
                    maxfrec = frec; 
                    moda = array[0]; 
                }
                p = first; 
                first = p+1; 
                frec = 1; 
            }
        }
        return moda;
    }
    public static void main(String[] args) {
        int[] numerosOrdenados = {1, 2, 2, 2, 3, 4, 5}; // Debe estar ordenado
        System.out.println("La moda es: " + moda2(numerosOrdenados)); // Imprime 2
    }
}
