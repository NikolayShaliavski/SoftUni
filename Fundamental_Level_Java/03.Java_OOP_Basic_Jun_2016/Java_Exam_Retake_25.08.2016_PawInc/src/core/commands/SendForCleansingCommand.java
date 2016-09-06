package core.commands;

import core.commands.commandContracts.Executable;
import models.corporation.Corporation;

public class SendForCleansingCommand extends AbstractCommand implements Executable {

    private String[] params;

    public SendForCleansingCommand(Corporation corporation,
                                   String[] params) {
        super(corporation);
        this.params = params;
    }

    @Override
    public void execute() {
        String adoptionCenterName = params[1];
        String cleansingCenterName = params[2];
        super.getCorporation().sendForCleansing(adoptionCenterName, cleansingCenterName);
    }
}
