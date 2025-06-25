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

        // Búsqueda 1
        RegistroEstudiante r = arbolEstudiantes.buscarPorCodigo(115);
        if (r != null)
            System.out.println(r.getNombre()); // Debe retornar: David
        else
            System.out.println("No encontrado");

        // Búsqueda 2
        r = arbolEstudiantes.buscarPorCodigo(132);
        if (r != null)
            System.out.println(r.getNombre()); // Debe retornar: Ernesto
        else
            System.out.println("No encontrado");

        // Búsqueda 3
        r = arbolEstudiantes.buscarPorCodigo(999);
        if (r != null)
            System.out.println(r.getNombre());
        else
            System.out.println("No encontrado"); // Debe retornar: No encontrado

        // Eliminar al estudiante con código 101
        arbolEstudiantes.remove(new RegistroEstudiante(101, "")); // Solo el código importa para comparar

        // Insertar nuevo estudiante: (106, “Sara”)
        arbolEstudiantes.insert(new RegistroEstudiante(106, "Sara"));

        // Buscar estudiante con código 106
        r = arbolEstudiantes.buscarPorCodigo(106);
        if (r != null)
            System.out.println(r.getNombre()); // Debe retornar: Sara
        else
            System.out.println("No encontrado");
    }

    // Método estático para buscar estudiante por código en un BTree
    public static RegistroEstudiante buscarPorCodigo(BTree<RegistroEstudiante> arbol, int codigo) {
        return buscarRec(arbol.root, codigo);
    }

    private static RegistroEstudiante buscarRec(BNode<RegistroEstudiante> node, int codigo) {
        if (node == null) return null;
        for (int i = 0; i < node.count; i++) {
            RegistroEstudiante est = node.keys.get(i);
            if (est != null && est.getCodigo() == codigo)
                return est;
            if (est != null && codigo < est.getCodigo())
                return buscarRec(node.childs.get(i), codigo);
        }
        return buscarRec(node.childs.get(node.count), codigo);
    }
}
