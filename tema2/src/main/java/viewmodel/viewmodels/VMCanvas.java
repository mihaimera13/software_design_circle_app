package viewmodel.viewmodels;

import view.views.CanvasView;
import viewmodel.commands.ICommand;
import viewmodel.commands.canvas.*;

public class VMCanvas {

    public ICommand changeColor;
    public ICommand changePattern;
    public ICommand changeStroke;
    public ICommand displayInformation;
    public ICommand drawCircumscribed;
    public ICommand drawInscribed;
    public ICommand openAccount;
    public ICommand openLogin;
    public ICommand uploadXML;
    public ICommand downloadXML;

    private CanvasView canvas;

    public VMCanvas(CanvasView canvas){
        this.canvas = canvas;
        changeColor = new CommandChangeColor(this);
        changePattern = new CommandChangePattern(this);
        changeStroke = new CommandChangeStroke(this);
        displayInformation = new CommandDisplayInformation(this);
        drawCircumscribed = new CommandDrawCircumscribed(this);
        drawInscribed = new CommandDrawInscribed(this);
        openAccount = new CommandOpenAccount(this);
        openLogin = new CommandOpenLogin(this);
        uploadXML = new CommandUploadXML(this);
        downloadXML = new CommandDownloadXML(this);
    }

    public CanvasView getCanvas() {
        return canvas;
    }
}
