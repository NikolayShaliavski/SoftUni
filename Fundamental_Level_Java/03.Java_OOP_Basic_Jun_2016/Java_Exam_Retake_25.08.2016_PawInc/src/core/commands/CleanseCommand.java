package core.commands;

import core.commands.commandContracts.Executable;
import models.corporation.Corporation;

public class CleanseCommand extends AbstractCommand implements Executable {

    private String[] params;

    public CleanseCommand(Corporation corporation,
                          String[] params) {
        super(corporation);
        this.params = params;
    }

    @Override
    public void execute() {
        String cleansingCenterName = this.params[1];
        super.getCorporation().cleanse(cleansingCenterName);
    }
}
