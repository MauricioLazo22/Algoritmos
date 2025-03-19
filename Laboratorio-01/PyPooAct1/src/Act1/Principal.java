package Act1;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese una esquina del 1er rectángulo: ");
        double x1 = sc.nextDouble();
        double y1 = sc.nextDouble();
        System.out.println("Ingrese la esquina opuesta del 1er rectángulo: ");
        double x2 = sc.nextDouble();
        double y2 = sc.nextDouble();
        Rectangulo rectA = new Rectangulo(new Coordenada(x1, y1), new Coordenada(x2, y2));

        System.out.println("Ingrese una esquina del 2do rectángulo: ");
        double x3 = sc.nextDouble();
        double y3 = sc.nextDouble();
        System.out.println("Ingrese la esquina opuesta del 2do rectángulo: ");
        double x4 = sc.nextDouble();
        double y4 = sc.nextDouble();
        Rectangulo rectB = new Rectangulo(new Coordenada(x3, y3), new Coordenada(x4, y4));

        System.out.println(rectA);
        System.out.println(rectB);

        if (Verificador.esSobrePos(rectA, rectB)) {
            System.out.println("Rectángulos A y B se sobreponen.");
            Rectangulo rectSobre = rectanguloSobre(rectA, rectB);
            System.out.println("Área de sobreposición = " + rectSobre.calculoArea());
        } else if (Verificador.esJunto(rectA, rectB)) {
            System.out.println("Rectángulos A y B están juntos.");
        } else {
            System.out.println("Rectángulos A y B son disjuntos.");
        }

        sc.close();
    }

    public static void imprimirRectangulo(String nombre, Rectangulo rect) {
        System.out.println("Rectángulo " + nombre + " = ([" + rect.getEsquina1().getX() + ", " + rect.getEsquina1().getY() +
                           "], [" + rect.getEsquina2().getX() + ", " + rect.getEsquina2().getY() + "])");
    }
    
    public static Rectangulo rectanguloSobre(Rectangulo r1, Rectangulo r2) {
        double xMin = Math.max(r1.getEsquina1().getX(), r2.getEsquina1().getX());
        double yMin = Math.max(r1.getEsquina1().getY(), r2.getEsquina1().getY());
        double xMax = Math.min(r1.getEsquina2().getX(), r2.getEsquina2().getX());
        double yMax = Math.min(r1.getEsquina2().getY(), r2.getEsquina2().getY());

        return new Rectangulo(new Coordenada(xMin, yMin), new Coordenada(xMax, yMax));
    }
}

