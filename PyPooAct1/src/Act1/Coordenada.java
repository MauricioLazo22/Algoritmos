package Act1;

public class Coordenada {
    private double x;
    private double y;

    public Coordenada() {
        this.x = 0;
        this.y = 0;
    }

    // Constructor con parámetros
    public Coordenada(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Coordenada(Coordenada c) {
        this.x = c.x;
        this.y = c.y;
    }

    void setX(double x) {
        this.x = x;
    }

    void setY(double y) {
        this.y = y;
    }

    double getX() {
        return this.x;
    }

    double getY() {
        return this.y;
    }

    double distancia(Coordenada c) {
        return Math.sqrt(Math.pow(c.x - this.x, 2) + Math.pow(c.y - this.y, 2));
    }

    static double distancia(Coordenada c1, Coordenada c2) {
        return Math.sqrt(Math.pow(c2.x - c1.x, 2) + Math.pow(c2.y - c1.y, 2));
    }

    @Override
    public String toString() {
        return "Coordenada (" + x + ", " + y + ")";
    }
}