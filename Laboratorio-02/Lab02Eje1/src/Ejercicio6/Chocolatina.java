package Ejercicio6;
import java.util.Objects;

public class Chocolatina {
    private String marca;

    public Chocolatina(String marca) {
        this.marca = marca;
    }

    public String getMarca() {
        return marca;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Chocolatina that = (Chocolatina) obj;
        return Objects.equals(marca, that.marca);
    }

    @Override
    public int hashCode() {
        return Objects.hash(marca);
    }

    @Override
    public String toString() {
        return marca;
    }
}