package core.commands;

import core.commands.commandContracts.Executable;
import models.animals.Animal;
import models.animals.Cat;
import models.corporation.Corporation;

public class RegisterCatCommand extends AbstractCommand implements Executable {

    private Animal cat;

    public RegisterCatCommand(Corporation corporation,
                              String[] params) {
        super(corporation);
        this.createCat(params);
    }

    @Override
    public void execute() {
        super.getCorporation().registerAnimal(this.cat, this.cat.getCenterName());
    }

    private void createCat(String[] params) {
        String catName = params[1];
        int catAge = Integer.valueOf(params[2]);
        int intelligence = Integer.valueOf(params[3]);
        String adoptionCenterName = params[4];
        this.cat = new Cat(catName, adoptionCenterName, catAge, intelligence);
    }
}
