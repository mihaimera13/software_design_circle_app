package server.services;

import model.user.Student;
import repo.StudentPersistence;

public class NewAccountService {

    private StudentPersistence studentPersistence;

    public NewAccountService() {
        studentPersistence = new StudentPersistence();
    }

    public String newAccount(String nickname, String password,String name, String surname) {
        int id = -1;

        id = studentPersistence.findIdByNickname(nickname);

        if(id==-1){
            if(name != null && surname != null && !password.isEmpty()){
                studentPersistence.insertStudent(new Student(name,surname,nickname,password));
                return "OK";
            }
            else return "ERROR_EMPTY_FIELDS";
        }
        else{
            return "ERROR_NAME_USED";
        }
    }
}
