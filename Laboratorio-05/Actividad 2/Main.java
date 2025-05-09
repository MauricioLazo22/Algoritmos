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

        // Paso 3: Eliminar alguna tarea
        gestor.eliminarTarea(tarea2); // Elimina la tarea "Estudiar Java"

        // Paso 4: Imprimir todas las tareas actuales
        System.out.println("Tareas actuales:");
        gestor.imprimirTareas(); // Muestra todas las tareas restantes

        // Paso 5: Verificar si cierta tarea existe
        boolean existeTarea = gestor.contieneTarea(tarea1);
        System.out.println("¿La tarea 'Comprar leche' existe? " + existeTarea);

        // Paso 6: Invertir la lista
        gestor.invertirTareas();
        System.out.println("Lista invertida:");
        gestor.imprimirTareas();

        // Paso 7: Transferir una tarea a una lista de “tareas completadas”
        List<Tarea> tareasCompletadas = new ArrayList<>();
        Tarea tareaCompletada = new Tarea("Comprar leche", 2); // O cualquier tarea que queramos transferir

        if (gestor.eliminarTarea(tareaCompletada)) {
            tareasCompletadas.add(tareaCompletada); // La agregamos a la lista de tareas completadas
        }

        
    }
}
