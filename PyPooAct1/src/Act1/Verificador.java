package Act1;

public class Verificador {

	// Método para verificar si los rectángulos se sobreponen
	public static boolean esSobrePos(Rectangulo r1, Rectangulo r2) {
	    Coordenada c1A = r1.getEsquina1();
	    Coordenada c2A = r1.getEsquina2();
	    Coordenada c1B = r2.getEsquina1();
	    Coordenada c2B = r2.getEsquina2();

	    // Verificar si los rectángulos se sobreponen
	    return !(c1A.getX() > c2B.getX() || c2A.getX() < c1B.getX() ||
	             c1A.getY() > c2B.getY() || c2A.getY() < c1B.getY());
	}

	// Método para verificar si los rectángulos están juntos (tienen un borde en común)
	public static boolean esJunto(Rectangulo r1, Rectangulo r2) {
	    Coordenada c1A = r1.getEsquina1();
	    Coordenada c2A = r1.getEsquina2();
	    Coordenada c1B = r2.getEsquina1();
	    Coordenada c2B = r2.getEsquina2();

	    // Verificar si los rectángulos están tocando en el eje X (en un borde lateral)
	    boolean enElMismoEjeX = (c1A.getX() == c2B.getX() || c2A.getX() == c1B.getX()) &&
	                             (c1A.getY() <= c2B.getY() && c2A.getY() >= c1B.getY());

	    // Verificar si los rectángulos están tocando en el eje Y (en un borde superior/inferior)
	    boolean enElMismoEjeY = (c1A.getY() == c2B.getY() || c2A.getY() == c1B.getY()) &&
	                             (c1A.getX() <= c2B.getX() && c2A.getX() >= c1B.getX());

	    // Los rectángulos están juntos si se tocan en uno de los dos ejes, pero no se superponen
	    return (enElMismoEjeX || enElMismoEjeY) && !esSobrePos(r1, r2);
	}

    // Método para verificar si los rectángulos son disjuntos
    public static boolean esDisjunto(Rectangulo r1, Rectangulo r2) {
        return !esSobrePos(r1, r2) && !esJunto(r1, r2);
    }
}
