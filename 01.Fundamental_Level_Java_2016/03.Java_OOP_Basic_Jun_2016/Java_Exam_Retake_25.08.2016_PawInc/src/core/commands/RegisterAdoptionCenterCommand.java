package core.commands;

import core.commands.commandContracts.Executable;
import models.centers.AdoptionCenterImpl;
import models.centers.centersContracts.Center;
import models.corporation.Corporation;

public class RegisterAdoptionCenterCommand extends AbstractCommand implements Executable {

    private Center adoptionCenter;

    public RegisterAdoptionCenterCommand(Corporation corporation,
                                         String[] params) {
        super(corporation);
        this.createAdoptionCenter(params);
    }

    @Override
    public void execute() {
        super.getCorporation().registerCenter(this.adoptionCenter);
    }

    private void createAdoptionCenter(String[] params) {
        String centerName = params[1];
        this.adoptionCenter = new AdoptionCenterImpl(centerName);
    }
}
