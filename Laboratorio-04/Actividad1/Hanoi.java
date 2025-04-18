package Actividad1;

public class Hanoi {

    public static void main(String[] args) {
        torresHanoi(3,2,1,3);
    }

    //METODO RECURSIVO PARA LA RESOLUCIÓN DEL PROBLEMA DE LAS TORRES DE HANOI
    public static void torresHanoi(int discos, int torre1, int torre2, int torre3){
        if (discos == 1){                                                                   // Caso Base
            System.out.println("Mover disco de torre " + torre1 + " a torre " + torre3);
        } else{                                                                             // Dominio (problema – 1)
            torresHanoi(discos -1, torre1, torre3,torre2);
            System.out.println("mover disco de torre "+ torre1 + " a torre " + torre3);
            torresHanoi(discos -1, torre2, torre1, torre3);
        }
    }
}
