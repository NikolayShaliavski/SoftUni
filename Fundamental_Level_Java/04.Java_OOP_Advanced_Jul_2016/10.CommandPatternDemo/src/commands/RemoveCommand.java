package commands;

import annotations.Inject;
import contracts.Executable;

public class RemoveCommand extends Command implements Executable {

    @Inject
    private String name;

    public RemoveCommand(String[] params) {
        super(params);
    }

    @Override
    public void execute() {
        String[] params = super.getParams();
        //write code for executing command here
    }
}
