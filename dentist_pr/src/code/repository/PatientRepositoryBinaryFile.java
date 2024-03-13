package code.repository;

import code.domain.Patient;

import java.io.*;
import java.util.Map;

public class PatientRepositoryBinaryFile extends FileRepository<Integer, Patient<Integer>> {

    public PatientRepositoryBinaryFile(String filename) {
        super(filename);
    }

    @Override
    protected void readFromFile() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
            items = (Map<Integer, Patient<Integer>>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void writeToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(items);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}