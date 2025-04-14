package Actividad4;

public class moda {

    public static int modal(int array[]) {
        int first = 0;
        int end = array.length - 1;
        if (first == end)
            return array[first];
        
        int moda = array[first];
        int maxfree = frecuencia(array, first, end, moda);

        for (int i = first + 1; i <= end; i++) {
            int free = frecuencia(array, i, end, array[i]);
            if (free > maxfree) {
                maxfree = free;
                moda = array[i];
            }
        }
        return moda;
    }

}
