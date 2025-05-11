package Actividad1;

public class Test {
    public static void main(String[] args) {
        try {
            // Prueba con Integer
            Stack<Integer> intStack = new StackArray<>(3);
            System.out.println("=== Prueba con enteros ===");
            intStack.push(10);
            intStack.push(20);
            System.out.println("Contenido actual: " + intStack); // [10, 20]
            System.out.println("Top: " + intStack.top()); // 20
            System.out.println("Pop: " + intStack.pop()); // 20
            intStack.push(30);
            intStack.push(40); // Pila llena

            // Prueba con String
            Stack<String> strStack = new StackArray<>(2);
            System.out.println("\n=== Prueba con cadenas ===");
            strStack.push("Hola");
            strStack.push("Mundo");
            System.out.println(strStack.pop() + " " + strStack.pop()); // Mundo Hola
            
        } catch (ExceptionIsEmpty e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
