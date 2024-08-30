package repo;

import model.quiz.TestTableEntry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class TestPersistence {
    private Persistence persistence;

    public TestPersistence(){
        this.persistence = new Persistence();
    }

    public boolean insertTest(int sid, int points, Timestamp timestamp){
        String query = "INSERT INTO TESTS (SID, POINTS, TEST_TIME) VALUES (" + sid + "," + points + ",'" + timestamp + "')";
        return this.persistence.executeQuery(query);
    }

    public boolean deleteAllTestsOfStudent(int sid){
        String query = "DELETE FROM TESTS WHERE SID = "+ sid;
        return this.persistence.executeQuery(query);
    }

    public ArrayList<TestTableEntry> findTestsByStudent(int sid){
        ArrayList<TestTableEntry> testTableEntries = new ArrayList<>();

        String query = "SELECT STUDENTS.NAME, STUDENTS.SURNAME, TESTS.POINTS, TESTS.TEST_TIME FROM " +
                "STUDENTS INNER JOIN TESTS ON (STUDENTS.SID = TESTS.SID AND STUDENTS.SID = " + sid + ")";

        ResultSet resultSet = this.persistence.getDataTable(query);

        TestTableEntry tte;
        int index = 1;

        if(resultSet == null)
            return testTableEntries;

        try{
            while(resultSet.next()){
                tte = new TestTableEntry(index, resultSet.getString("NAME"),
                        resultSet.getString("SURNAME"),
                        Integer.parseInt(resultSet.getString("POINTS")), resultSet.getTimestamp("TEST_TIME"));
                testTableEntries.add(tte);
                index++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return testTableEntries;
    }
}
