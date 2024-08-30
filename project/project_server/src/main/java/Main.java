import repo.DatabaseCreator;
import repo.QuestionsPersistence;
import server.Server;

public class Main {
    public static void main(String[] args) {
        DatabaseCreator databaseCreator = new DatabaseCreator();
        QuestionsPersistence questionsPersistence = new QuestionsPersistence();
        questionsPersistence.deleteAll();

        databaseCreator.fillTableQuestions("questions.txt");
        Server server = new Server();
        server.startServer();
    }
}
