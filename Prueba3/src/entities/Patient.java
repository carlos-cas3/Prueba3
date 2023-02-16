package entities;

import java.util.ArrayList;

public class Patient extends Person{
    private String codPatient;
    private ArrayList<Vaccine> vaccinesPatient;
    public Patient() { }
    public Patient(String codPatient, String name, String nroDoc) {
        super(name,nroDoc);
        this.codPatient = codPatient;
        this.vaccinesPatient = new ArrayList<>();
    }
    public String getCodPatient() {
        return codPatient;
    }
    public void setVaccinesPatient(ArrayList<Vaccine> vaccinesPatient) {
        this.vaccinesPatient = vaccinesPatient;
    }
    public ArrayList<Vaccine> getVaccinesPatient() {return vaccinesPatient;}

    public void addVaccine(Vaccine vaccine ) {
        this.vaccinesPatient.add(vaccine);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "codPatient='" + codPatient + '\'' +
                ", vaccinesPatient=" + vaccinesPatient +
                "} " + super.toString();
    }
}
