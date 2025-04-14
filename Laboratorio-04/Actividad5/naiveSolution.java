package Actividad5;

public class naiveSolution {

    static int getValue(int[] values, int length){
        if (length <= 0) return 0;
        int tmpMax = -1;
        for (int i = 0; i < length; i++) {
            tmpMax = Math.max(tmpMax, values[i] + getValue(values, length - i - 1));
        }
        return tmpMax;
    }
    
}