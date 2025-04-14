package Ejercicio3;

public class ViajeMasBarato {
    public static int[][] calcularCostosMinimos(int[][] T) {
        int n = T.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    C[i][j] = 0;
                } else if (i < j) {
                    C[i][j] = T[i][j];
                } else {
                    C[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                for (int k = i + 1; k < j; k++) {
                    if (T[i][k] != Integer.MAX_VALUE && C[k][j] != Integer.MAX_VALUE) {
                        int costoConEscala = T[i][k] + C[k][j];
                        C[i][j] = Math.min(C[i][j], costoConEscala);
                    }
                }
            }
        }
        return C;
    }

    public static void mostrarMatriz(int[][] M) {
        for (int[] fila : M) {
            for (int valor : fila) {
                if (valor == Integer.MAX_VALUE) {
                    System.out.print("INF ");
                } else {
                    System.out.print(valor + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int INF = Integer.MAX_VALUE;
        int[][] T = {
            { 0, 3, 1, 6, INF },
            { 0, 0, 1, 2, INF },
            { 0, 0, 0, 1, 4 },
            { 0, 0, 0, 0, 2 },
            { 0, 0, 0, 0, 0 }
        };

        int[][] C = calcularCostosMinimos(T);
        System.out.println("Matriz de Costos Mínimos:");
        mostrarMatriz(C);
    }
}
