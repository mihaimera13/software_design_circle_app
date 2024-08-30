package server.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.user.Student;
import repo.StudentPersistence;

public class LoginService {

    private StudentPersistence studentPersistence;

    public LoginService() {
        studentPersistence = new StudentPersistence();
    }
    public String login(String username) {
        Student student = studentPersistence.findStudentByNickname(username);

        ObjectMapper objectMapper = new ObjectMapper();

        String result = "";

        try{
            result = objectMapper.writeValueAsString(student);
        } catch (Exception e){
            throw new RuntimeException(e);
        }

        return result;
    }
}
