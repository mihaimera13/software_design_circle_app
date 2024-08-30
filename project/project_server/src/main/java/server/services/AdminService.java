package server.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.user.AccountStatus;
import model.user.Student;
import repo.StudentPersistence;
import repo.TestPersistence;

import java.util.ArrayList;

public class AdminService {

    private StudentPersistence studentPersistence;
    private TestPersistence testPersistence;

    public AdminService(){
        studentPersistence = new StudentPersistence();
        testPersistence = new TestPersistence();
    }

    public String deleteStudent(String nickname){
        int id = studentPersistence.findIdByNickname(nickname);
        testPersistence.deleteAllTestsOfStudent(id);
        if(studentPersistence.deleteStudent(id))
            return "SUCCESS";
        else return "FAIL";
    }

    public String approveStudent(String nickname){
        int id = studentPersistence.findIdByNickname(nickname);
        if(studentPersistence.updateStudentStatus(id))
            return "SUCCESS";
        else return "FAIL";
    }

    public String seeAllStudents(){
        ArrayList<Student> students = studentPersistence.findAllStudents();

        ObjectMapper mapper = new ObjectMapper();

        String result = "";

        try {
            result = mapper.writeValueAsString(students);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public String seeRequests() {
        ArrayList<Student> requests = studentPersistence.findStudentsByStatus(String.valueOf(AccountStatus.REQUESTED));

        ObjectMapper mapper = new ObjectMapper();

        String result = "";

        try {
            result = mapper.writeValueAsString(requests);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
