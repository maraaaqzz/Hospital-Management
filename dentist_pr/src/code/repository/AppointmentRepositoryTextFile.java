package code.repository;

import code.domain.Appointments;

import java.io.*;

public class AppointmentRepositoryTextFile extends FileRepository<Integer, Appointments<Integer>>{
    public AppointmentRepositoryTextFile(String filename) {
        super(filename);
    }

    @Override
    protected void readFromFile() {
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = null;
            while ((line = reader.readLine())!= null) {
                String[] stringArray = line.split(";");
                if (stringArray.length != 3) {
                    continue;
                } else {
                    String text = stringArray[1].trim();
                    text = text.replace("[", "");
                    text = text.replace("]", "");
                    text = text.replace(" ", "");

                    Appointments<Integer> app = new Appointments<>(Integer.parseInt(stringArray[0].trim()),Integer.parseInt(stringArray[1].trim()),stringArray[3].trim());
                    this.items.put(app.getId(), app);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void writeToFile() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Appointments<Integer> a: getALlItems())
            {
                writer.write(a.getId() + ";" +
                        a.getPatientID() + ";" +
                        a.getDate()+ "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}