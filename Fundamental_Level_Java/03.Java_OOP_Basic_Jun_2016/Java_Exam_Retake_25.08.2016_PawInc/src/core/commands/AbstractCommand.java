package core.commands;

import core.commands.commandContracts.Executable;
import models.corporation.Corporation;

public abstract class AbstractCommand implements Executable {

    private Corporation corporation;

    public AbstractCommand(Corporation corporation) {
        this.corporation = corporation;
    }

    protected Corporation getCorporation() {
        return this.corporation;
    }
}
