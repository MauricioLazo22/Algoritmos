package Ejercicio7;
import java.util.Arrays;
public class OrdenamientoPorMezclaMejorado {

    private static final int UMBRAL_ORDENACION_INSERCION = 15;

    public static void ordenarPorMezcla(int[] arreglo) {
        if (arreglo == null || arreglo.length <= 1) {
            return;
        }
        int[] arregloTemporal = arreglo.clone();
        ordenarPorMezcla(arreglo, arregloTemporal, 0, arreglo.length - 1);
    }

    private static void ordenarPorMezcla(int[] arreglo, int[] arregloTemporal, int izquierda, int derecha) {
        if (derecha - izquierda <= UMBRAL_ORDENACION_INSERCION) {
            ordenarPorInsercion(arreglo, izquierda, derecha);
            return;
        }

        int medio = izquierda + (derecha - izquierda) / 2;
        
        ordenarPorMezcla(arregloTemporal, arreglo, izquierda, medio);
        ordenarPorMezcla(arregloTemporal, arreglo, medio + 1, derecha);

        if (arregloTemporal[medio] <= arregloTemporal[medio + 1]) {
            System.arraycopy(arregloTemporal, izquierda, arreglo, izquierda, derecha - izquierda + 1);
            return;
        }

        mezclar(arregloTemporal, arreglo, izquierda, medio, derecha);
    }

    private static void ordenarPorInsercion(int[] arreglo, int izquierda, int derecha) {
        for (int i = izquierda + 1; i <= derecha; i++) {
            int clave = arreglo[i];
            int j = i - 1;
            while (j >= izquierda && arreglo[j] > clave) {
                arreglo[j + 1] = arreglo[j];
                j--;
            }
            arreglo[j + 1] = clave;
        }
    }

    private static void mezclar(int[] fuente, int[] destino, int izquierda, int medio, int derecha) {
        int i = izquierda, j = medio + 1, k = izquierda;
        
        while (i <= medio && j <= derecha) {
            if (fuente[i] <= fuente[j]) {
                destino[k++] = fuente[i++];
            } else {
                destino[k++] = fuente[j++];
            }
        }

        while (i <= medio) {
            destino[k++] = fuente[i++];
        }
        while (j <= derecha) {
            destino[k++] = fuente[j++];
        }
    }

    public static void main(String[] args) {
        int[] datos = {12, 11, 13, 5, 6, 7, 1, 3, 2, 8, 9, 4, 10};
        System.out.println("Arreglo original: " + Arrays.toString(datos));

        ordenarPorMezcla(datos);
        System.out.println("Arreglo ordenado: " + Arrays.toString(datos));
    }
}