package viewmodel.viewmodels;

import model.quiz.Test;
import net.sds.mvvm.properties.Property;
import net.sds.mvvm.properties.PropertyFactory;
import view.views.TestView;
import viewmodel.commands.ICommand;
import viewmodel.commands.test.CommandEnd;
import viewmodel.commands.test.CommandNext;
import viewmodel.commands.test.CommandStart;

public class VMTest {

    private Test test;
    private int index;
    private int points;

    public ICommand start;
    public ICommand end;
    public ICommand next;

    private Property<Boolean> option1;
    private Property<Boolean> option2;
    private Property<Boolean> option3;
    private Property<Boolean> option4;

    private TestView testView;

    public VMTest(TestView testView) {
        this.testView = testView;
        option1 = PropertyFactory.createProperty("option1", this, Boolean.class);
        option2 = PropertyFactory.createProperty("option2", this, Boolean.class);
        option3 = PropertyFactory.createProperty("option3", this, Boolean.class);
        option4 = PropertyFactory.createProperty("option4", this, Boolean.class);
        start = new CommandStart(this);
        end = new CommandEnd(this);
        next = new CommandNext(this);
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Boolean getOption1() { return option1.get(); }

    public void setOption1(Boolean t){
        option1.set(t);
    }

    public Boolean getOption2() {
        return option2.get();
    }

    public void setOption2(Boolean t){
        option2.set(t);
    }

    public Boolean getOption3() {
        return option3.get();
    }

    public void setOption3(Boolean t){
        option3.set(t);
    }

    public Boolean getOption4() {
        return option4.get();
    }

    public void setOption4(Boolean t){
        option4.set(t);
    }

    public TestView getTestView() {
        return testView;
    }
}
