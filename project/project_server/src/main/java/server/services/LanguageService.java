package server.services;

import repo.DatabaseCreator;
import repo.QuestionsPersistence;

public class LanguageService {

    private QuestionsPersistence questionsPersistence;

    public LanguageService() {
        this.questionsPersistence = new QuestionsPersistence();
    }

    public void updateLang(int index) {
        questionsPersistence.deleteAll();

        DatabaseCreator databaseCreator = new DatabaseCreator();

        switch (index){
            case 0 -> databaseCreator.fillTableQuestions("questions.txt");
            case 1 -> databaseCreator.fillTableQuestions("questionsIT.txt");
            case 2 -> databaseCreator.fillTableQuestions("questionsES.txt");
        }
    }
}
