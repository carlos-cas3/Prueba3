package persistance;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import entities.Patient;
import entities.Vaccine;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import processes.ConfigVaccinate;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

public class JSONConfigFile implements Persistance{
    private String filename;
    private String exportFilename;
    //private boolean overwrite;

    public JSONConfigFile() {
        this.filename = "config.json";
      //  this.overwrite = overwrite;

    }
    @Override
    public void saveConfig(ConfigVaccinate config) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray patientArray = gson.toJsonTree(config.getPatients()).getAsJsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("patients", patientArray);

        try {
            FileWriter writer = new FileWriter(this.filename, false);
            gson.toJson(jsonObject, writer);
            writer.flush();
            writer.close();
            System.out.println("Configuración guardada.");
        } catch (IOException ex) {
            System.out.println("Exception" + ex);
            ex.printStackTrace();
        }
    }


    @Override
    public void loadConfig(ConfigVaccinate config) {
        try {
            Gson gson = new Gson();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.filename));
            JsonElement json = gson.fromJson(bufferedReader, JsonElement.class);
            JsonObject jsonObject = json.getAsJsonObject();
            JsonArray patientArray = jsonObject.getAsJsonArray("patients");
            Type patientListType = new TypeToken<ArrayList<Patient>>() {}.getType();
            ArrayList<Patient> loadedPatients = gson.fromJson(patientArray, patientListType);
            config.setPatients(loadedPatients);
            System.out.println("Configuración cargada:");
            for (Patient patient : loadedPatients) {
                System.out.print("{CodPatient:" + patient.getCodPatient() + " Nombre:" + patient.getNombre() + " NroDoc:" + patient.getNroDoc() + " {");
                ArrayList<Vaccine> vaccines = patient.getVaccinesPatient();
                for (Vaccine vaccine : vaccines) {
                    System.out.print("Vaccine name:" + vaccine.getName() + " Kit:" + vaccine.getKit()  + " Lote:" + vaccine.getLote());
                }
                System.out.println("}}");
            }
        } catch (FileNotFoundException ex){
            System.out.println("El archivo de configuración no existe.");
        } catch (JsonSyntaxException | JsonIOException ex){
            System.out.println("Error al leer el archivo de configuración.");
            ex.printStackTrace();
        }
    }
    public void exportVaccines(String exportFilename, ConfigVaccinate config) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray vaccineArray = new JsonArray();

        for (Patient patient : config.getPatients()) {
            for (Vaccine vaccine : patient.getVaccinesPatient()) {
                JsonObject vaccineObject = new JsonObject();
                vaccineObject.addProperty("patient", patient.getCodPatient());
                vaccineObject.addProperty("name", vaccine.getName());
                vaccineObject.addProperty("kit", vaccine.getKit());
                vaccineObject.addProperty("lote", vaccine.getLote());
                vaccineArray.add(vaccineObject);
            }
        }

        JsonObject jsonObject = new JsonObject();
        jsonObject.add("vaccines", vaccineArray);

        try {
            FileWriter writer = new FileWriter(exportFilename, true);
            gson.toJson(jsonObject, writer);
            writer.flush();
            writer.close();
            System.out.println("Vacunas exportadas correctamente.");
        } catch (IOException ex) {
            System.out.println("Exception" + ex);
            ex.printStackTrace();
        }
    }

    public void loadExportVaccines(String exportFilename, ConfigVaccinate config) {
        try {
            Gson gson = new Gson();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(exportFilename));
            JsonObject jsonObject = gson.fromJson(bufferedReader, JsonObject.class);
            JsonArray vaccineArray = jsonObject.getAsJsonArray("vaccines");
            System.out.println("Vacunas exportadas:");
            for (JsonElement vaccineElement : vaccineArray) {
                JsonObject vaccineObject = vaccineElement.getAsJsonObject();
                String patientCode = vaccineObject.get("patient").getAsString();
                String vaccineName = vaccineObject.get("name").getAsString();
                String kit = vaccineObject.get("kit").getAsString();
                String lote = vaccineObject.get("lote").getAsString();
                System.out.println("codPatient:" + patientCode + " nameVaccine:" + vaccineName + " kit:" + kit + " lote:" + lote);
                Patient patient = config.getPatientByCode(patientCode);
                if (patient != null) {
                    Vaccine vaccine = new Vaccine(vaccineName, kit, lote);
                    patient.addVaccine(vaccine);
                }
            }
        } catch (FileNotFoundException ex){
            System.out.println("El archivo de vacunas exportadas no existe.");
        } catch (JsonSyntaxException | JsonIOException ex){
            System.out.println("Error al leer el archivo de vacunas exportadas.");
            ex.printStackTrace();
        }
    }






}
