public class Main {
    public static void main(String[] args) {
        // Paso 1: Crear una instancia de GestorDeTareas
        ListaEnlazada<Tarea> listaDeTareas = new ListaEnlazada<>(null); 
        GestorDeTareas<Tarea> gestor = new GestorDeTareas<>(listaDeTareas);
        
    }
}
