package Act1;

public class Rectangulo {
    private Coordenada esquina1;
    private Coordenada esquina2;

    public Rectangulo(Coordenada c1, Coordenada c2) {
        setEsquina1(c1);
        setEsquina2(c2);
    }

    public void setEsquina1(Coordenada coo) {
        this.esquina1 = coo;
    }

    public void setEsquina2(Coordenada coo) {
        this.esquina2 = coo;
    }

    public Coordenada getEsquina1() {
        return this.esquina1;
    }

    public Coordenada getEsquina2() {
        return this.esquina2;
    }

    // Método para calcular el área de un rectángulo
    public double calculoArea() {
        double ancho = Math.abs(esquina2.getX() - esquina1.getX());
        double alto = Math.abs(esquina2.getY() - esquina1.getY());
        return ancho * alto;
    }

    @Override
    public String toString() {
        return "Rectángulo [Esquina 1: " + esquina1.toString() + ", Esquina 2: " + esquina2.toString() + "]";
    }
}
