package viewmodel.viewmodels;

import net.sds.mvvm.properties.Property;
import net.sds.mvvm.properties.PropertyFactory;
import view.views.LoginView;
import viewmodel.commands.ICommand;
import viewmodel.commands.login.CommandGoBack;
import viewmodel.commands.login.CommandLogin;
import viewmodel.commands.login.CommandReset;

public class VMLogin {

    public ICommand login;
    public ICommand reset;
    public ICommand goBack;

    private LoginView loginView;

    private Property<String> nickname;
    private Property<String> password;

    public VMLogin(LoginView loginView) {
        this.loginView = loginView;
        nickname = PropertyFactory.createProperty("nickname", this, String.class);
        password = PropertyFactory.createProperty("password", this, String.class);
        login = new CommandLogin(this);
        reset = new CommandReset(this);
        goBack = new CommandGoBack(this);
    }

    public String getNickname() {
        return nickname.get();
    }

    public String getPassword() {
        return password.get();
    }

    public LoginView getLoginView() {
        return loginView;
    }
}
