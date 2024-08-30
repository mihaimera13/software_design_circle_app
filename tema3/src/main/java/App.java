import repo.DatabaseCreator;
import view.views.*;

public class App {
    public static void main(String[] args) {
        DatabaseCreator databaseCreator = new DatabaseCreator();
        databaseCreator.fillTableQuestions("questions.txt");
        CanvasView canvasView = new CanvasView();
    }
}
