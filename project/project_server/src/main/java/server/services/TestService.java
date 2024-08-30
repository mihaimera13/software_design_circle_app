package server.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import repo.QuestionsPersistence;
import repo.TestPersistence;

import java.sql.Timestamp;
import java.util.ArrayList;

public class TestService {

    private TestPersistence testPersistence;

    private QuestionsPersistence questionsPersistence;

    public TestService() {
        this.testPersistence = new TestPersistence();
        this.questionsPersistence = new QuestionsPersistence();
    }
    public void insertTest(String sid, String points, String timestamp) {
        System.out.println(sid + " " + points + " " + timestamp);
        testPersistence.insertTest(Integer.parseInt(sid), Integer.parseInt(points), Timestamp.valueOf(timestamp));
    }

    public String getQuestions(String qids) {
        ArrayList<Integer> qidsList = new ArrayList<>();
        qids = qids.replace("[", "").replace("]", "").replace(" ", "");
        for(String qid : qids.split(","))
            qidsList.add(Integer.parseInt(qid));

        System.out.println(qidsList);

        StringBuilder questions = new StringBuilder();

        questions.append("[");

        for (int i=0;i<10;i++){
            questionsPersistence.findQuestionById(qidsList.get(i));
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                questions.append(objectMapper.writeValueAsString(questionsPersistence.findQuestionById(qidsList.get(i))));
                if(i != 9)
                    questions.append(",");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        questions.append("]");

        return questions.toString();
    }
}
