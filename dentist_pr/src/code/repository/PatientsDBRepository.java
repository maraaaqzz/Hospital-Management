package code.repository;

import code.domain.Patient;
import code.exceptions.DuplicateItemException;
import code.exceptions.ItemNotFound;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PatientsDBRepository extends DataBaseRepository<Integer, Patient<Integer>> {
    public PatientsDBRepository(String tableName) {
        super(tableName);
    }
    @Override
    public Iterable<Patient<Integer>> getAll()
    {
        ArrayList<Patient<Integer>> patients = new ArrayList<>();
        try
        {
            openConnection();
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tableName + ";"); ResultSet rs = ps.executeQuery();)
            {
                while(rs.next())
                {
                    Patient<Integer> p = new Patient<>(rs.getInt("Pid"), rs.getString("Name"),  rs.getString("Illness"), rs.getInt("Age"));
                    patients.add(p);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }
        return patients;
    }
    @Override
    public Patient<Integer> getItem(Integer id) {
        Patient<Integer> p = null;
        try {
            openConnection();
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE Pid=" + id); ResultSet rs = ps.executeQuery();) {
                p = new Patient<>(rs.getInt("Pid"), rs.getString("Name"), rs.getString("Illness"), rs.getInt("Age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return p;
    }
    @Override
    public void add(Patient<Integer> patient) throws DuplicateItemException{
        try{
            openConnection();
            String insertString = "INSERT INTO " + tableName + " VALUES (?, ?, ?, ?);";
            try (PreparedStatement statement = conn.prepareStatement(insertString)){
                statement.setInt(1, patient.getId());
                statement.setString(2, patient.getName());
                statement.setString(3, patient.getIllness());
                statement.setInt(4, patient.getAge());
                statement.executeUpdate();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
    @Override
    public void remove(Integer integer) throws ItemNotFound{
        try{
            openConnection();
            String deleteString = "DELETE FROM " + tableName + " WHERE Pid=" + integer;
            try (PreparedStatement ps = conn.prepareStatement(deleteString)) {
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
    @Override
    public void update(Integer integer, Patient<Integer> newPatient) throws ItemNotFound{
        try{
            openConnection();
            String insertString = "UPDATE " + tableName +
                    " SET Name = '" + newPatient.getName() + "'," +
                    " Illness = '" + newPatient.getIllness() + "'," +
                    " Age = '" + newPatient.getAge() +
                    "' WHERE Pid = " + newPatient.getId();
            try (PreparedStatement ps = conn.prepareStatement(insertString)) {
                ps.executeUpdate();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
}