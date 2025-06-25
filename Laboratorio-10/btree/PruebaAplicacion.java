package Laboratorio-10.btree;

public class PruebaAplicacion {
    public static void main(String[] args) {
        BTree<RegistroEstudiante> arbolEstudiantes = new BTree<>(4);

        arbolEstudiantes.insert(new RegistroEstudiante(103, "Ana"));
        arbolEstudiantes.insert(new RegistroEstudiante(110, "Luis"));
        arbolEstudiantes.insert(new RegistroEstudiante(101, "Carlos"));
        arbolEstudiantes.insert(new RegistroEstudiante(120, "Lucía"));
        arbolEstudiantes.insert(new RegistroEstudiante(115, "David"));
        arbolEstudiantes.insert(new RegistroEstudiante(125, "Jorge"));
        arbolEstudiantes.insert(new RegistroEstudiante(140, "Camila"));
        arbolEstudiantes.insert(new RegistroEstudiante(108, "Rosa"));
        arbolEstudiantes.insert(new RegistroEstudiante(132, "Ernesto"));
        arbolEstudiantes.insert(new RegistroEstudiante(128, "Denis"));
        arbolEstudiantes.insert(new RegistroEstudiante(145, "Enrique"));
        arbolEstudiantes.insert(new RegistroEstudiante(122, "Karina"));
        arbolEstudiantes.insert(new RegistroEstudiante(108, "Juan")); // Intento de duplicado

        System.out.println(arbolEstudiantes);
    }
}
