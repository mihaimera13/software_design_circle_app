package server.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.quiz.TestTableEntry;
import repo.StudentPersistence;
import repo.TestPersistence;

import java.util.ArrayList;

public class AccountService {

    private StudentPersistence studentPersistence;
    private TestPersistence testPersistence;

    public AccountService() {
        this.studentPersistence = new StudentPersistence();
        this.testPersistence = new TestPersistence();
    }

    public String retrieveTests(String nickname) {
        int id = studentPersistence.findIdByNickname(nickname);
        ArrayList<TestTableEntry> entries = testPersistence.findTestsByStudent(id);

        String result = "";

        ObjectMapper objectMapper = new ObjectMapper();

        try{
            result = objectMapper.writeValueAsString(entries);
        } catch (Exception e){
            throw new RuntimeException(e);
        }

        return result;
    }

    public String getId(String nickname) {
        int id = studentPersistence.findIdByNickname(nickname);
        return String.valueOf(id);
    }

}
