package model.quiz;

import client.Client;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Collections;

public class Test {
    private ArrayList<Question> questions;
    private final int MAXIMUM_POINTS;
    private final ArrayList<Integer> correctAnswers;

    public Test(Client client){
        questions = new ArrayList<>();
        correctAnswers = new ArrayList<>();
        ArrayList<Integer> qids = new ArrayList<>();
        for(int i=1;i<=50;i++)
            qids.add(i);
        Collections.shuffle(qids);

        client.sendMessage("GET_QUESTIONS " + qids.subList(0, 10));
        String questions = client.receiveMessage();

        Question[] questionsArray = new Question[10];

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            questionsArray = objectMapper.readValue(questions, Question[].class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int points = 0;
        int SIZE = 10;
        for(Question q : questionsArray){
            this.questions.add(q);
            points += q.getDifficulty().value();
            correctAnswers.add(q.randomizeOptions());
        }
        MAXIMUM_POINTS = points;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public int getMAXIMUM_POINTS() {
        return MAXIMUM_POINTS;
    }

    public ArrayList<Integer> getCorrectAnswers() {
        return correctAnswers;
    }
}
