package viewmodel.viewmodels;

import net.sds.mvvm.properties.Property;
import net.sds.mvvm.properties.PropertyFactory;
import view.views.AccountView;
import viewmodel.commands.ICommand;
import viewmodel.commands.account.CommandGoBack;
import viewmodel.commands.account.CommandRetrieveTests;
import viewmodel.commands.account.CommandTakeTest;

import javax.swing.table.DefaultTableModel;

public class VMAccount {

    public ICommand goBack;
    public ICommand retrieveTests;
    public ICommand takeTest;

    private Property<String> nickname;
    private Property<DefaultTableModel> model;

    private AccountView accountView;

    public VMAccount(AccountView accountView) {
        this.accountView = accountView;
        nickname = PropertyFactory.createProperty("nickname", this, String.class);
        model = PropertyFactory.createProperty("model", this, new DefaultTableModel());
        goBack = new CommandGoBack(this);
        retrieveTests = new CommandRetrieveTests(this);
        takeTest = new CommandTakeTest(this);
    }

    public String getNickname() {
        return nickname.get();
    }

    public DefaultTableModel getModel() {
        return model.get();
    }

    public void setModel(DefaultTableModel model) {
        this.model.set(model);
    }

    public AccountView getAccountView() {
        return accountView;
    }
}
