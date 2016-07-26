package pr0304Barracks.core.commands;

import pr0304Barracks.contracts.Executable;

/**
 * Created by Nikolay Shalyavski on 25.7.2016 г..
 */
public abstract class Command implements Executable {

    private String[] data;

    protected Command(String[] dataArg) {
        this.setData(dataArg);
    }

    protected String[] getData() {
        return this.data;
    }

    private void setData(String[] data) {
        this.data = data;
    }
}
