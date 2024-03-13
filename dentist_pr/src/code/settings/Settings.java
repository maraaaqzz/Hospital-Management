package code.settings;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class Settings {
    private Properties properties;
    private final String filename = "dentist_pr/java.settings.properties";

    public Settings() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getRepositoryType() {
        return properties.getProperty("Repository"); // returns what is after = in java.settings.property
    }

    public String getPatientsFile() {
        return properties.getProperty("Patients");
    }

    public String getAppointmentsFile() {
        return properties.getProperty("Appointments");
    }

    public void updateRepositoryType(String repositoryType) {
        properties.setProperty("Repository", repositoryType);
        savePropertiesToFile();
    }

    public void updatePatientsFile(String patientFile) {
        properties.setProperty("Patients", patientFile);
        savePropertiesToFile();
    }

    public void updateAppointmentsFile(String appFile) {
        properties.setProperty("Appointments", appFile);
        savePropertiesToFile();
    }

    private void savePropertiesToFile() {
        try (OutputStream outputStream = new FileOutputStream(filename)) {
            properties.store(outputStream, "Updated java.settings");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}