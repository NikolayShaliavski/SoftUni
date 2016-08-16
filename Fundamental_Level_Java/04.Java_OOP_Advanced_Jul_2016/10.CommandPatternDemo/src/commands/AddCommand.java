package commands;

import annotations.Inject;
import contracts.Executable;

public class AddCommand extends Command implements Executable {

    //some fields which will be injected
    @Inject
    private int value;
    @Inject
    private String name;

    public AddCommand(String[] params) {
        super(params);
    }

    @Override
    public void execute() {
        String[] params = super.getParams();
        //write code for executing command here
    }
}
