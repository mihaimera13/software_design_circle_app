package repo;

import model.user.AccountStatus;
import model.user.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentPersistence {

    private Persistence persistence;

    public StudentPersistence(){
        this.persistence = new Persistence();
    }

    public boolean insertStudent(Student student){
        String query = "INSERT INTO STUDENTS (NAME, SURNAME, NICKNAME, PASSWORD,ACCOUNT_STATUS) VALUES ( '" +
                        student.getName() + "','" +
                        student.getSurname() + "','" +
                        student.getNickname() + "','" +
                        student.getPassword() + "','" +
                        student.getAccountStatus() + "')";
        return this.persistence.executeQuery(query);
    }

    public boolean deleteStudent(int id){
        String query = "DELETE FROM STUDENTS WHERE SID = " + id;
        return this.persistence.executeQuery(query);
    }

    public Student findStudentByNickname(String nickname){
        Student student = null;
        String query = "SELECT * FROM STUDENTS WHERE NICKNAME = '"+ nickname + "'";

        ResultSet resultSet = this.persistence.getDataTable(query);

        try{
            if(resultSet.next()){
                student = new Student(resultSet.getString("NAME"),
                        resultSet.getString("SURNAME"),
                        resultSet.getString("NICKNAME"),
                        resultSet.getString("PASSWORD"),
                        AccountStatus.valueOf(resultSet.getString("ACCOUNT_STATUS")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    public ArrayList<Student> findAllStudents(){
        ArrayList<Student> students= new ArrayList<>();
        String query = "SELECT * FROM STUDENTS";
        ResultSet resultSet = this.persistence.getDataTable(query);
        Student student;
        try{
            while(resultSet.next()){
                student = new Student(resultSet.getString("NAME"),
                        resultSet.getString("SURNAME"),
                        resultSet.getString("NICKNAME"),
                        resultSet.getString("PASSWORD"),
                        AccountStatus.valueOf(resultSet.getString("ACCOUNT_STATUS")));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    public ArrayList<Student> findStudentsByStatus(String status){
        ArrayList<Student> students= new ArrayList<>();
        String query = "SELECT * FROM STUDENTS WHERE ACCOUNT_STATUS = '" + status + "'";
        ResultSet resultSet = this.persistence.getDataTable(query);
        Student student;
        try{
            while(resultSet.next()){
                student = new Student(resultSet.getString("NAME"),
                        resultSet.getString("SURNAME"),
                        resultSet.getString("NICKNAME"),
                        resultSet.getString("PASSWORD"),
                        AccountStatus.valueOf(resultSet.getString("ACCOUNT_STATUS")));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public int findIdByNickname(String nickname){
        int id = -1;
        String query = "SELECT * FROM STUDENTS WHERE NICKNAME = '"+ nickname + "'";

        ResultSet resultSet = this.persistence.getDataTable(query);

        try{
            if(resultSet.next()){
                id = Integer.parseInt(resultSet.getString("SID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public boolean updateStudentStatus(int id){
        String query = "UPDATE STUDENTS SET ACCOUNT_STATUS = 'APPROVED' WHERE SID = " + id;
        System.out.println(id);
        return this.persistence.executeQuery(query);
    }
}
