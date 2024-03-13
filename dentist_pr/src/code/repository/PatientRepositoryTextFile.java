package code.repository;

import code.domain.Patient;

import java.io.*;

public class PatientRepositoryTextFile extends FileRepository<Integer, Patient<Integer>> {
    public PatientRepositoryTextFile(String filename) { super(filename);}
    @Override
    protected void readFromFile() {
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = null;
            while ((line = reader.readLine())!= null) {
                String[] stringArray = line.split(";");
                if (stringArray.length != 4) {
                    continue;
                } else {
                    Patient<Integer> cake = new Patient<>(Integer.parseInt(stringArray[0].trim()), stringArray[1].trim(), stringArray[2].trim(), Integer.parseInt(stringArray[3].trim()));
                    this.items.put(cake.getId(), cake);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void writeToFile() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Patient<Integer> p: getALlItems())
            {
                writer.write(p.getId() + ";" +
                        p.getName() + ";" +
                        p.getIllness() + ";" +
                        p.getAge()+ "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
