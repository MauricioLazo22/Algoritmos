package Act1;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);

	    System.out.println("Ingrese una esquina del 1er rectángulo:");
	    double x1A = sc.nextDouble();
	    double y1A = sc.nextDouble();
	    System.out.println("Ingrese la esquina opuesta del 1er rectángulo:");
	    double x2A = sc.nextDouble();
	    double y2A = sc.nextDouble();

	    Coordenada c1A = new Coordenada(Math.min(x1A, x2A), Math.min(y1A, y2A));
	    Coordenada c2A = new Coordenada(Math.max(x1A, x2A), Math.max(y1A, y2A));

	    Rectangulo A = new Rectangulo(c1A, c2A);

	    System.out.println("Ingrese una esquina del 2do rectángulo:");
	    double x1B = sc.nextDouble();
	    double y1B = sc.nextDouble();
	    System.out.println("Ingrese la esquina opuesta del 2do rectángulo:");
	    double x2B = sc.nextDouble();
	    double y2B = sc.nextDouble();

	    Coordenada c1B = new Coordenada(Math.min(x1B, x2B), Math.min(y1B, y2B));
	    Coordenada c2B = new Coordenada(Math.max(x1B, x2B), Math.max(y1B, y2B));

	    Rectangulo B = new Rectangulo(c1B, c2B);

	    System.out.println("Rectángulo A = " + formatoRectangulo(A));
	    System.out.println("Rectángulo B = " + formatoRectangulo(B));

	    // Primero verificamos si se sobreponen
	    if (Verificador.esSobrePos(A, B)) {
	        System.out.println("Rectángulos A y B se sobreponen.");
	        Rectangulo sobrePos = rectanguloSobre(A, B);
	        if (sobrePos != null) {
	            double areaSobresposicion = sobrePos.calculoArea();
	            System.out.println("Área de sobreposición = " + String.format("%.2f", areaSobresposicion));
	        }
	    } 
	    // Si no se sobreponen, verificamos si están juntos
	    else if (Verificador.esJunto(A, B)) {
	        System.out.println("Rectángulos A y B se juntan.");
	    } 
	    // Si no se sobreponen ni están juntos, entonces son disjuntos
	    else {
	        System.out.println("Rectángulos A y B son disjuntos.");
	    }

	    sc.close();
	}


    private static String formatoRectangulo(Rectangulo rectangulo) {
        Coordenada esquina1 = rectangulo.getEsquina1();
        Coordenada esquina2 = rectangulo.getEsquina2();
        return String.format("([%.1f, %.1f], [%.1f, %.1f])", 
                             esquina1.getX(), esquina1.getY(), 
                             esquina2.getX(), esquina2.getY());
    }

    private static Rectangulo rectanguloSobre(Rectangulo r1, Rectangulo r2) {
        Coordenada c1A = r1.getEsquina1();
        Coordenada c2A = r1.getEsquina2();
        Coordenada c1B = r2.getEsquina1();
        Coordenada c2B = r2.getEsquina2();

        double xInterseccion = Math.max(c1A.getX(), c1B.getX());
        double yInterseccion = Math.max(c1A.getY(), c1B.getY());
        double xInterseccion2 = Math.min(c2A.getX(), c2B.getX());
        double yInterseccion2 = Math.min(c2A.getY(), c2B.getY());

        if (xInterseccion < xInterseccion2 && yInterseccion < yInterseccion2) {
            Coordenada c1Interseccion = new Coordenada(xInterseccion, yInterseccion);
            Coordenada c2Interseccion = new Coordenada(xInterseccion2, yInterseccion2);
            return new Rectangulo(c1Interseccion, c2Interseccion);
        }
        return null;
    }
}
