import repo.DatabaseCreator;
import view.views.CanvasView;


public class Main {
    public static void main(String[] args) {
        DatabaseCreator db = new DatabaseCreator();
        db.createTestsTable();
        db.createTableStudent();
        db.fillTableQuestions("questions.txt");
        new CanvasView();
    }
}
