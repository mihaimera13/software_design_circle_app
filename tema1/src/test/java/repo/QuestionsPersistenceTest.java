package repo;

import model.quiz.Difficulty;
import model.quiz.Question;
import org.junit.jupiter.api.Test;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class QuestionsPersistenceTest {

    @Test
    void insertQuestion() {
        QuestionsPersistence qp = new QuestionsPersistence();
        String text = "QUESTION?";
        String opt1 = "1";
        String opt2 = "2";
        String opt3 = "3";
        String opt4 = "4";
        ArrayList<String> opts = new ArrayList<>();
        opts.add(opt1);
        opts.add(opt2);
        opts.add(opt3);
        opts.add(opt4);
        Question q = new Question(text, Difficulty.EASY,opts,"");
        boolean value = qp.insertQuestion(q);
        assertTrue(value);
    }

    @Test
    void findQuestionById() {
        int id = 10;
        QuestionsPersistence qp = new QuestionsPersistence();
        Question q = qp.findQuestionById(id);
        assertEquals(q.getDifficulty(),Difficulty.MEDIUM);
        assertNull(q.getImageFile());
    }
}