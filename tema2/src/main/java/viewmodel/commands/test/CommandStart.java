package viewmodel.commands.test;

import model.quiz.Test;
import viewmodel.commands.ICommand;
import viewmodel.viewmodels.VMTest;

public class CommandStart implements ICommand {

    private VMTest vmTest;

    public CommandStart(VMTest vmTest) {
        this.vmTest = vmTest;
    }

    @Override
    public void execute() {
        vmTest.setIndex(0);
        vmTest.setPoints(0);
        vmTest.setTest(new Test());
        vmTest.next.execute();
    }
}
