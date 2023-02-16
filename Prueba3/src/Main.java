import entities.Patient;
import entities.Vaccine;
import persistance.JSONConfigFile;
import persistance.Persistance;
import processes.ConfigVaccinate;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ConfigVaccinate config = new ConfigVaccinate();
        Persistance p = new JSONConfigFile();


        Patient p1 = new Patient("123","Carlos","12");
        Patient p2 = new Patient("456","Juan","34");
        Patient p3 = new Patient("789","Gabriel","56");

        /*config.addVaccinePatient(p2,new Vaccine("Pfizer","77","489"));
        config.addVaccinePatient(p2,new Vaccine("Pfizer","98","415"));


        config.addVaccinePatient(p1,new Vaccine("Moderna","16","415"));
        config.addVaccinePatient(p1,new Vaccine("Moderna","68","153"));
        config.addVaccinePatient(p1,new Vaccine("Pfizer","77","489"));

        config.addVaccinePatient(p3,new Vaccine("Pfizer","01","156"));

        config.createPatient(p1);
        config.createPatient(p2);
        config.createPatient(p3);
        p.saveConfig(config);
        */
        //p.loadConfig(config);
        //p.exportVaccines("vaccines.json",config);
        p.loadExportVaccines("vaccines.json",config);





    }
}