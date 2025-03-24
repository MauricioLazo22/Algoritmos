package InterfacesClasesGenericas;
import java.util.Scanner;

public class Principal {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        int tipoDato;
        
        do {
            System.out.println("Seleccione el tipo de dato: \n1. Integer\n2. Double");
            tipoDato = scanner.nextInt();
            
            if (tipoDato == 1 || tipoDato == 2) break;
            System.out.println("Opción inválida. Intente nuevamente.");
        } while (true);
        
        if (tipoDato == 1) {
            OperacionesMatBInteger operacionesInt = new OperacionesMatBInteger();
            OperacionesMat<Integer> operaciones = new OperacionesMat<>(operacionesInt);
            
            do {
                mostrarMenu();
                opcion = scanner.nextInt();
                if (opcion == 8) break;
                
                System.out.print("Ingrese el primer número: ");
                int num1 = scanner.nextInt();
                int num2 = 0;
                if (opcion != 6 && opcion != 7) {
                    System.out.print("Ingrese el segundo número: ");
                    num2 = scanner.nextInt();
                }
                ejecutarOperacion(opcion, operaciones, num1, num2);
                
            } while (true);
            
        } else {
            OperacionesMatDouble operacionesDouble = new OperacionesMatDouble();
            OperacionesMat<Double> operaciones = new OperacionesMat<>(operacionesDouble);
            
            do {
                mostrarMenu();
                opcion = scanner.nextInt();
                if (opcion == 8) break;
                
                System.out.print("Ingrese el primer número: ");
                double num1 = scanner.nextDouble();
                double num2 = 0;
                if (opcion != 6 && opcion != 7) {
                    System.out.print("Ingrese el segundo número: ");
                    num2 = scanner.nextDouble();
                }
                ejecutarOperacion(opcion, operaciones, num1, num2);
                
            } while (true);
        }
        
        scanner.close();
        System.out.println("Programa terminado.");
    }
    
    private static void mostrarMenu() {
        System.out.println("\nMenú de Operaciones Clases Genéricas:");
        System.out.println("1. Suma");
        System.out.println("2. Resta");
        System.out.println("3. Producto");
        System.out.println("4. División");
        System.out.println("5. Potencia");
        System.out.println("6. Raíz Cuadrada");
        System.out.println("7. Raíz Cúbica");
        System.out.println("8. Salir");
        System.out.print("Seleccione una opción: ");
    }
    
    private static <T extends Number> void ejecutarOperacion(int opcion, OperacionesMat<T> operaciones, T num1, T num2) {
        switch (opcion) {
            case 1: System.out.println("Resultado: " + operaciones.suma(num1, num2)); break;
            case 2: System.out.println("Resultado: " + operaciones.resta(num1, num2)); break;
            case 3: System.out.println("Resultado: " + operaciones.producto(num1, num2)); break;
            case 4: System.out.println("Resultado: " + operaciones.division(num1, num2)); break;
            case 5: System.out.println("Resultado: " + operaciones.potencia(num1, num2)); break;
            case 6: System.out.println("Resultado: " + operaciones.raizCuadrada(num1)); break;
            case 7: System.out.println("Resultado: " + operaciones.raizCubica(num1)); break;
            default: System.out.println("Opción inválida");
        }
    }
}