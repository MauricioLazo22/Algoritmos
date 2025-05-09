public class Main {
    public static void main(String[] args) {
        ListaEnlazada<String> listaDeTareas = new ListaEnlazada<>(null);
        GestorTareas<String> gestor = new GestorTareas<>(listaDeTareas);
        
        gestor.agregarTarea("Estudiar Java");
        gestor.agregarTarea("Hacer ejercicio");
        gestor.agregarTarea("Leer un libro");
        
        System.out.println("Tareas después de agregar:");
        gestor.mostrarTareas();
    }
}