package entities;

import java.util.HashSet;
import java.util.Set;

public class Vaccine {
    private String name;
    private String kit;
    private String lote;
    private static Set<Vaccine> vacunasUnicas = new HashSet<Vaccine>();
    public Vaccine() { }
    public Vaccine(String name, String kit, String lote) {
        this.name = name;
        this.kit = kit;
        this.lote = lote;
        vacunasUnicas.add(this);
    }
    public String getName() {
        return name;
    }
    public String getKit() {
        return kit;
    }
    public String getLote() {
        return lote;
    }

    public static Set<Vaccine> getVacunasUnicas() {
        return vacunasUnicas;
    }
    @Override
    public String toString() {
        return "Vaccine{" +
                "name='" + name + '\'' +
                ", kit='" + kit + '\'' +
                ", lote='" + lote + '\'' +
                '}';
    }
}
