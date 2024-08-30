package viewmodel.viewmodels;

import net.sds.mvvm.properties.Property;
import net.sds.mvvm.properties.PropertyFactory;
import view.views.AdminView;
import viewmodel.commands.ICommand;
import viewmodel.commands.admin.*;

import javax.swing.table.DefaultTableModel;

public class VMAdmin {
    public ICommand approve;
    public ICommand delete;
    public ICommand seeAll;
    public ICommand seeRequests;
    public ICommand goBack;

    private Property<DefaultTableModel> model;
    private Property<Integer> row;

    private AdminView adminView;

    public VMAdmin(AdminView adminView){
        this.adminView = adminView;
        model = PropertyFactory.createProperty("model",this, new DefaultTableModel());
        row = PropertyFactory.createProperty("row",this, Integer.class);
        approve = new CommandApprove(this);
        delete = new CommandDelete(this);
        seeAll = new CommandSeeAll(this);
        seeRequests = new CommandSeeRequests(this);
        goBack = new CommandGoBack(this);
    }

    public Integer getRow() {
        return row.get();
    }

    public DefaultTableModel getModel() {
        return model.get();
    }

    public void setModel(DefaultTableModel model){
        this.model.set(model);
    }

    public AdminView getAdminView() {
        return adminView;
    }
}
