package repo;

import model.quiz.TestTableEntry;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestPersistenceTest {

    @Test
    void insertTest() {
        TestPersistence tp = new TestPersistence();
        boolean value = tp.insertTest(5000,50, new Timestamp(System.currentTimeMillis()));
        assertTrue(value);
    }

    @Test
    void deleteAllTestsOfStudent() {
        TestPersistence tp = new TestPersistence();
        boolean value = tp.deleteAllTestsOfStudent(5000);
        assertTrue(value);
    }

    @Test
    void findTestsByStudent() {
        TestPersistence tp = new TestPersistence();
        ArrayList<TestTableEntry> tte = tp.findTestsByStudent(1);
        assertEquals(0,tte.size());
    }
}