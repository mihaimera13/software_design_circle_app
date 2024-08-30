package repo;

import model.user.AccountStatus;
import model.user.Student;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StudentPersistenceTest {

    @Test
    void insertStudent() {
        StudentPersistence sp = new StudentPersistence();
        boolean value1 = sp.insertStudent(new Student("name","surname","nickname","password"));
        boolean value2 = sp.insertStudent(new Student("name","surname","nickname","password"));
        assertTrue(value1);
        assertFalse(value2);
    }

    @Test
    void deleteStudent() {
        StudentPersistence sp = new StudentPersistence();
        boolean value1 = sp.deleteStudent(5);
        boolean value2 = sp.deleteStudent(5);
        assertTrue(value1);
        assertFalse(value2);
    }

    @Test
    void findStudentByNickname() {
        StudentPersistence sp = new StudentPersistence();
        Student s1 = sp.findStudentByNickname("mihaimera");
        Student s2 = sp.findStudentByNickname("asdfgh");
        assertNull(s2);
        assertEquals("mera",s1.getName());
        assertEquals("mihai",s1.getSurname());
    }

    @Test
    void findAllStudents() {
        StudentPersistence sp = new StudentPersistence();
        ArrayList<Student> students = sp.findAllStudents();
        assertTrue(students.size()>0);
    }

    @Test
    void findStudentsByStatus() {
        StudentPersistence sp = new StudentPersistence();
        ArrayList<Student> admins = sp.findStudentsByStatus(String.valueOf(AccountStatus.ADMIN));
        assertTrue(admins.size()>0);
    }

    @Test
    void findIdByNickname() {
        StudentPersistence sp = new StudentPersistence();
        int id1 = sp.findIdByNickname("mihaimera");
        int id2 = sp.findIdByNickname("mihaimera13");
        assertEquals(7,id1);
        assertEquals(-1,id2);
    }

    @Test
    void updateStudentStatus() {
        StudentPersistence sp = new StudentPersistence();
        boolean value = sp.updateStudentStatus(8);
        Student s = sp.findStudentByNickname("nickname");
        assertTrue(value);
        assertEquals(AccountStatus.APPROVED,s.getAccountStatus());
    }
}