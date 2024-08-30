import client.Client;
import view.views.CanvasView;

import java.util.ArrayList;
import java.util.Collections;

public class App {
    public static void main(String[] args) {
        Client client = new Client();
        client.connectClient();
        CanvasView canvasView = new CanvasView(client);

    }
}
