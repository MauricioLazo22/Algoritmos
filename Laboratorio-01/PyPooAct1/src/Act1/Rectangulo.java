package Act1;

public class Rectangulo {
    private Coordenada esquina1;
    private Coordenada esquina2;

    public Rectangulo(Coordenada c1, Coordenada c2) {
        double xMin = Math.min(c1.getX(), c2.getX());
        double yMin = Math.min(c1.getY(), c2.getY());
        double xMax = Math.max(c1.getX(), c2.getX());
        double yMax = Math.max(c1.getY(), c2.getY());

        this.esquina1 = new Coordenada(xMin, yMin);
        this.esquina2 = new Coordenada(xMax, yMax);
    }
    
    public void setEsquina1(Coordenada esquina1) {
		this.esquina1 = esquina1;
	}

	public void setEsquina2(Coordenada esquina2) {
		this.esquina2 = esquina2;
	}
	
	public Coordenada getEsquina1() {
        return this.esquina1;
    }

    public Coordenada getEsquina2() {
        return this.esquina2;
    }

    public double calculoArea() {
        double base = esquina2.getX() - esquina1.getX();
        double altura = esquina2.getY() - esquina1.getY();
        return base * altura;
    }

    @Override
    public String toString() {
        return "Rectángulo A = ([" + esquina1.getX() + ", " + esquina1.getY() + "], [" +
                esquina2.getX() + ", " + esquina2.getY() + "])";
    }
}
