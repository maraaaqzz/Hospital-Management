package code.repository;

import code.domain.Identifiable;
import code.exceptions.DuplicateItemException;
import code.exceptions.ItemNotFound;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DataBaseRepository<ID, T extends Identifiable<ID>> extends MemoryRepo<ID,T>{
    protected final String URL = "jdbc:sqlite:D:\\Projects\\Java\\a4-maraaaqzz\\identifier.sqlite";

    protected String tableName;
    protected Connection conn = null;

    public DataBaseRepository(String tableName) {
        this.tableName = tableName;
    }
    protected void openConnection() {
        try {

            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(URL);
            if (conn == null || conn.isClosed()){
                conn = ds.getConnection();
                System.out.println("Connected!");
            }

        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
    }
    protected void closeConnection() {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public abstract Iterable<T> getAll();

    public abstract T getItem(Integer id);

    public abstract void add(T item) throws DuplicateItemException;

    public abstract void remove(Integer integer) throws ItemNotFound;

    public abstract void update(Integer integer,T newItem) throws ItemNotFound;
}
