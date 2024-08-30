package repo;

import model.quiz.Difficulty;
import model.quiz.Question;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionsPersistence {
    private final Persistence persistence = Persistence.getInstance();

    public QuestionsPersistence(){

    }

    public boolean insertQuestion(Question question){
        String query = "INSERT INTO QUESTIONS (QUESTION,DIFFICULTY,ANSWER_A,ANSWER_B,ANSWER_C,ANSWER_D,IMAGE_FILE) VALUES ('" +
                        question.getText() + "','" +
                        question.getDifficulty().toString() + "','" +
                        question.getOptions().get(0) + "','" +
                        question.getOptions().get(1) + "','" +
                        question.getOptions().get(2) + "','" +
                        question.getOptions().get(3) + "',";
        if(!question.getImageFile().isEmpty()){
            query += "'"+question.getImageFile()+"'";
        }
        else query += "null";
        query += ")";
        return this.persistence.executeQuery(query);
    }

    public Question findQuestionById(int id){
        Question question = null;
        String query = "SELECT * FROM QUESTIONS WHERE QID = " + id;

        ResultSet resultSet = this.persistence.getDataTable(query);

        try{
            if(resultSet.next()){
                Difficulty difficulty = Difficulty.valueOf(resultSet.getString("DIFFICULTY"));
                ArrayList<String> options = new ArrayList<>();
                options.add(resultSet.getString("ANSWER_A"));
                options.add(resultSet.getString("ANSWER_B"));
                options.add(resultSet.getString("ANSWER_C"));
                options.add(resultSet.getString("ANSWER_D"));
                question = new Question(resultSet.getString("QUESTION"),difficulty,options, resultSet.getString("IMAGE_FILE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return question;
    }

    public boolean deleteAll(){
        String query = "DELETE FROM QUESTIONS";
        return this.persistence.executeQuery(query);
    }
}

