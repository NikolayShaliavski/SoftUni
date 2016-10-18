package core.commands;

import core.commands.commandContracts.Executable;
import models.corporation.Corporation;

public class AdoptCommand extends AbstractCommand implements Executable {

    private String[] params;

    public AdoptCommand(Corporation corporation,
                        String[] params) {
        super(corporation);
        this.params = params;
    }

    @Override
    public void execute() {
        String adoptionCenterName = this.params[1];
        super.getCorporation().adopt(adoptionCenterName);
    }
}
