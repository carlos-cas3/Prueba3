package processes;

import entities.Patient;
import entities.Vaccine;

import java.util.ArrayList;


public class ConfigVaccinate {
    private ArrayList<Patient> patients;

    public ConfigVaccinate() {
        this.patients = new ArrayList<>();
    }

    public int createPatient(Patient patient) {

        for (Patient p : this.patients) {
            if (p.getCodPatient().equals(patient.getCodPatient()))
                return 1;
        }
        this.patients.add(patient);
        return 0;
    }
    public void showPatients() {
        for (Patient patient : this.patients) {
            for (Vaccine vaccine : patient.getVaccinesPatient()) {
                System.out.println(patient + " " + vaccine.toString());
            }
        }
    }
    public void  addVaccinePatient(Patient patient, Vaccine vaccine) {
        patient.addVaccine(vaccine);
    }
    public Patient getPatientByCode(String code) {
        for (Patient patient : this.patients) {
            if (patient.getCodPatient().equals(code)) {
                return patient;
            }
        }
        return null;
    }
    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }
}


