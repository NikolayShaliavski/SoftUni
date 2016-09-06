package core.commands;

import core.commands.commandContracts.Executable;
import models.centers.CleansingCenterImpl;
import models.centers.centersContracts.Center;
import models.corporation.Corporation;

public class RegisterCleansingCenterCommand extends AbstractCommand implements Executable {

    private Center cleansingCenter;

    public RegisterCleansingCenterCommand(Corporation corporation,
                                          String[] params) {
        super(corporation);
        this.createCleansingCenter(params);
    }

    @Override
    public void execute() {
        super.getCorporation().registerCenter(this.cleansingCenter);
    }

    private void createCleansingCenter(String[] params) {
        String centerName = params[1];
        this.cleansingCenter = new CleansingCenterImpl(centerName);
    }
}
