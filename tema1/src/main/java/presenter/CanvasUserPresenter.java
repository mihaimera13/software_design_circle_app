package presenter;

import view.views.CanvasView;
import view.views.LoginView;
import view.views.NewAccountView;

public class CanvasUserPresenter {

    private CanvasView canvasView;

    public CanvasUserPresenter(CanvasView cv){
        canvasView = cv;
    }

    public void openLogin(){
        canvasView.setVisible(false);
        new LoginView(canvasView);
    }

    public void openNewAccount(){
        canvasView.setVisible(false);
        new NewAccountView(canvasView);
    }
}
