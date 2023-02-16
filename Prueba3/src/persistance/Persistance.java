package persistance;

import processes.ConfigVaccinate;

public interface Persistance {
    void saveConfig(ConfigVaccinate config);
    void loadConfig(ConfigVaccinate config);
    void exportVaccines(String exportFilename,ConfigVaccinate config);
    void loadExportVaccines(String exportFilename,ConfigVaccinate config);

}
