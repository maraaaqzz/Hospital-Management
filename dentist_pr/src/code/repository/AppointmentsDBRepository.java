package code.repository;

import code.domain.Appointments;
import code.exceptions.DuplicateItemException;
import code.exceptions.ItemNotFound;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentsDBRepository extends DataBaseRepository<Integer, Appointments<Integer>> {
    public AppointmentsDBRepository(String tableName) {
        super(tableName);
    }
    @Override
    public List<Appointments<Integer>> getAll()
    {
        ArrayList appointments = new ArrayList<>();
        try
        {
            openConnection();
            try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM " + tableName + ";"); ResultSet rs = statement.executeQuery();)
            {
                while (rs.next())
                {
                    Appointments<Integer> d = new Appointments<>(rs.getInt("Aid"), rs.getInt("PatientId"), rs.getString("Date"));
                    appointments.add(d);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closeConnection();
        }
        return appointments;
    }

    @Override
    public Appointments<Integer> getItem(Integer id) {
        Appointments<Integer> a = null;
        try {
            openConnection();
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE Aid=" + id); ResultSet rs = ps.executeQuery();) {
                a = new Appointments<>(rs.getInt("Aid"), rs.getInt("patientID"), rs.getString("Date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return a;
    }

    @Override
    public void add(Appointments<Integer> item) throws DuplicateItemException {
        try
        {
            openConnection();
            String insertString = "INSERT INTO " + tableName + " VALUES (?, ?, ?);";
            try (PreparedStatement statement = conn.prepareStatement(insertString)) {
                statement.setInt(1, item.getId());
                statement.setInt(2, item.getPatientID());
                statement.setString(3, item.getDate());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public void remove(Integer integer) throws ItemNotFound {
        try
        {
            openConnection();
            String deleteString = "DELETE FROM " + tableName + " WHERE Aid=" + integer;
            try (PreparedStatement statement = conn.prepareStatement(deleteString)) {
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public void update(Integer integer, Appointments<Integer> newItem) throws ItemNotFound {
        try
        {
            openConnection();
            String insertString = "UPDATE " + tableName +
                    " SET PatientId = '" + newItem.getPatientID() + "'," +
                    " Date = '" + newItem.getDate() +
                    "' WHERE Aid = " + newItem.getId();
            try (PreparedStatement statement = conn.prepareStatement(insertString)) {
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
}
