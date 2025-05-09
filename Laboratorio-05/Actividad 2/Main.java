public class Main {
    public static void main(String[] args) {
        // Paso 1: Crear una instancia de GestorDeTareas
        ListaEnlazada<Tarea> listaDeTareas = new ListaEnlazada<>(null); 
        GestorDeTareas<Tarea> gestor = new GestorDeTareas<>(listaDeTareas);

        // Paso 2: Agregar tareas
        Tarea tarea1 = new Tarea("Comprar leche", 2);
        Tarea tarea2 = new Tarea("Estudiar Java", 1);
        Tarea tarea3 = new Tarea("Hacer ejercicio", 3);

        gestor.agregarTarea(tarea1);
        gestor.agregarTarea(tarea2);
        gestor.agregarTarea(tarea3);

        
    }
}
