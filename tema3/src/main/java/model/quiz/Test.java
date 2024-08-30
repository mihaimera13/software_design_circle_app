package model.quiz;

import repo.QuestionsPersistence;

import java.util.ArrayList;
import java.util.Collections;

public class Test {
    private ArrayList<Question> questions;
    private final int MAXIMUM_POINTS;
    private final ArrayList<Integer> correctAnswers;

    public Test(){
        questions = new ArrayList<>();
        correctAnswers = new ArrayList<>();
        QuestionsPersistence persistence = new QuestionsPersistence();
        ArrayList<Integer> qids = new ArrayList<>();
        for(int i=1;i<=50;i++)
            qids.add(i);
        Collections.shuffle(qids);
        int points = 0;
        int SIZE = 10;
        for(int i = 1; i<= SIZE; i++){
            Question q = persistence.findQuestionById(qids.get(i));
            points += q.getDifficulty().value();
            correctAnswers.add(q.randomizeOptions());
            questions.add(q);
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
