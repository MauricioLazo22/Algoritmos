package Ejer1;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Crear un contenedor con capacidad de 5 rectángulos
        ContainerRect contenedor = new ContainerRect(5);

        // Crear los rectángulos y agregarlos al contenedor
        for (int i = 0; i < 3; i++) {  // Crear 3 rectángulos como ejemplo
            System.out.println("Ingrese una esquina del " + (i + 1) + "er rectángulo:");
            double x1A = sc.nextDouble();
            double y1A = sc.nextDouble();
            System.out.println("Ingrese la esquina opuesta del " + (i + 1) + "er rectángulo:");
            double x2A = sc.nextDouble();
            double y2A = sc.nextDouble();

            Coordenada c1A = new Coordenada(Math.min(x1A, x2A), Math.min(y1A, y2A));
            Coordenada c2A = new Coordenada(Math.max(x1A, x2A), Math.max(y1A, y2A));

            Rectangulo A = new Rectangulo(c1A, c2A);

            // Agregar el rectángulo al contenedor
            if (contenedor.addRectangulo(A)) {
                System.out.println("Rectángulo agregado al contenedor.");
            }
        }

        // Mostrar el contenido del contenedor de rectángulos
        System.out.println("\nContenido del contenedor de rectángulos:");
        System.out.println(contenedor.toString());

        sc.close();
    }
}
