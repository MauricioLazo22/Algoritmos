public class Main {
    public static void main(String[] args) {
        ListaEnlazada<String> listaDeTareas = new ListaEnlazada<>(null);
        GestorTareas<String> gestor = new GestorTareas<>(listaDeTareas);
        
        gestor.agregarTarea("Estudiar Java");
        gestor.agregarTarea("Hacer ejercicio");
        gestor.agregarTarea("Leer un libro");
        
        System.out.println("Tareas después de agregar:");
        gestor.mostrarTareas();

        String tareaBuscada = "Hacer ejercicio";
        int posicion = gestor.buscarTarea(tareaBuscada);
        if (posicion != -1) {
            System.out.println("La tarea '" + tareaBuscada + "' se encuentra en la posición: " + posicion);
        } else {
            System.out.println("La tarea '" + tareaBuscada + "' no fue encontrada.");
        }

        gestor.eliminarTarea("Estudiar Java");
        System.out.println("\nTareas después de eliminar 'Estudiar Java':");
        gestor.mostrarTareas();
        
        System.out.println("\nNúmero de tareas restantes: " + gestor.contarTareas());
        
        System.out.println("\n¿Está la lista vacía? " + gestor.estaVacia());
    }
}