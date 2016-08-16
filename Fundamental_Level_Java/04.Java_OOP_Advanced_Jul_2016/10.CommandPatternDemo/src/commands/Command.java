package commands;

import contracts.Executable;

//maybe we don't have need of abstract Command class,
//because we can pass params[] directly to execute() method
public abstract class Command implements Executable {

    private String[] params;

    protected Command(String[] params) {
        this.setParams(params);
    }

    protected String[] getParams() {
        return this.params;
    }

    private void setParams(String[] params) {
        this.params = params;
    }
}
