package Ejer1;

public class ContainerRect {
    private Rectangulo[] rectangulos; 
    private double[] distancias;       
    private double[] areas;         
    private int n;                     
    private static int numRec = 0;     

    // Constructor
    public ContainerRect(int capacidad) {
        this.n = capacidad;
        this.rectangulos = new Rectangulo[capacidad];
        this.distancias = new double[capacidad];
        this.areas = new double[capacidad];
    }

    public boolean addRectangulo(Rectangulo rectangulo) {
        if (numRec < n) {
            rectangulos[numRec] = rectangulo;

            double distancia = rectangulo.getEsquina1().distancia(rectangulo.getEsquina2());
            distancias[numRec] = distancia;

            double area = rectangulo.calculoArea();
            areas[numRec] = area;

            numRec++;
            return true;
        } else {
            System.out.println("No se puede agregar el rectángulo. El contenedor está lleno.");
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRec; i++) {
            sb.append(String.format("Rectángulo %d %s %.3f %.2f\n", 
                    i + 1, rectangulos[i].toString(), distancias[i], areas[i]));
        }
        return sb.toString();
    }

    public static int getNumRec() {
        return numRec;
    }
}
