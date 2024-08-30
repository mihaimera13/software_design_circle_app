package viewmodel.viewmodels;

import net.sds.mvvm.properties.Property;
import net.sds.mvvm.properties.PropertyFactory;
import view.views.NewAccountView;
import viewmodel.commands.ICommand;
import viewmodel.commands.newaccount.CommandRequest;
import viewmodel.commands.newaccount.CommandReset;

public class VMNewAccount {

    public ICommand request;
    public ICommand reset;

    private Property<String> name;
    private Property<String> surname;
    private Property<String> nickname;
    private Property<String> password;

    private NewAccountView newAccountView;

    public VMNewAccount(NewAccountView newAccountView) {
        this.newAccountView = newAccountView;
        request = new CommandRequest(this);
        reset = new CommandReset(this);
        name = PropertyFactory.createProperty("name",this, String.class);
        surname = PropertyFactory.createProperty("surname",this, String.class);
        nickname = PropertyFactory.createProperty("nickname",this, String.class);
        password = PropertyFactory.createProperty("password",this, String.class);
    }

    public String getName() {
        return name.get();
    }

    public String getSurname() {
        return surname.get();
    }

    public String getNickname() {
        return nickname.get();
    }

    public String getPassword() {
        return password.get();
    }

    public NewAccountView getNewAccountView() {
        return newAccountView;
    }
}
