package repo;

import org.junit.jupiter.api.Test;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseCreatorTest {


    @Test
    void createTableStudent() {
        DatabaseCreator db = new DatabaseCreator();
        boolean value = db.createTableStudent();

        Persistence p = new Persistence();
        p.connect();

        boolean ok = true;

        try {
            DatabaseMetaData dmd = p.connection.getMetaData();
            ResultSet rs = dmd.getTables(null, null, "%", null);
            while (rs.next()) {
                if(rs.getString(3).equals("students"))
                    ok = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            p.close();
        }

        assertFalse(value);
        assertFalse(ok);
    }

    @Test
    void fillTableQuestions() {
        DatabaseCreator db = new DatabaseCreator();
        boolean value = db.fillTableQuestions("questions.txt");

        Persistence p = new Persistence();
        p.connect();

        boolean ok = true;

        try {
            DatabaseMetaData dmd = p.connection.getMetaData();
            ResultSet rs = dmd.getTables(null, null, "%", null);
            while (rs.next()) {
                if(rs.getString(3).equals("questions"))
                    ok = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            p.close();
        }

        assertFalse(value);
        assertFalse(ok);
    }

    @Test
    void createTestsTable() {
        DatabaseCreator db = new DatabaseCreator();
        boolean value = db.createTestsTable();

        Persistence p = new Persistence();
        p.connect();

        boolean ok = true;

        try {
            DatabaseMetaData dmd = p.connection.getMetaData();
            ResultSet rs = dmd.getTables(null, null, "%", null);
            while (rs.next()) {
                if(rs.getString(3).equals("tests"))
                    ok = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            p.close();
        }

        assertFalse(value);
        assertFalse(ok);
    }

    @Test
    void insertAdmin() {
        DatabaseCreator db = new DatabaseCreator();
        boolean value = db.insertAdmin();
        assertFalse(value);
    }
}