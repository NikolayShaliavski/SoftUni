package core.commands;

import core.commands.commandContracts.Executable;
import models.animals.Animal;
import models.animals.Dog;
import models.corporation.Corporation;

public class RegisterDogCommand extends AbstractCommand implements Executable {

    private Animal dog;

    public RegisterDogCommand(Corporation corporation,
                              String[] params) {
        super(corporation);
        this.createDog(params);
    }

    @Override
    public void execute() {
        super.getCorporation().registerAnimal(this.dog, this.dog.getCenterName());
    }

    private void createDog(String[] params) {
        String dogName = params[1];
        int dogAge = Integer.valueOf(params[2]);
        int learnedCommands = Integer.valueOf(params[3]);
        String adoptionCenterName = params[4];
        this.dog = new Dog(dogName, adoptionCenterName, dogAge, learnedCommands);
    }
}
