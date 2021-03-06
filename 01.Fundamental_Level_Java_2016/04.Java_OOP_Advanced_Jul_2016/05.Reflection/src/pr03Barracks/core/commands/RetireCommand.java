package pr03Barracks.core.commands;

import pr03Barracks.annotations.CommandName;
import pr03Barracks.annotations.Inject;
import pr03Barracks.contracts.Repository;

import java.util.NoSuchElementException;

/**
 * Created by Nikolay Shalyavski on 26.7.2016 г..
 */
@CommandName(name = "retire")
public class RetireCommand extends Command {

    @Inject
    private Repository repository;

    public RetireCommand(String[] dataArg) {
        super(dataArg);
    }

    @Override
    public String execute() throws ReflectiveOperationException {
        String unitType = super.getData()[1];
        try {
            this.repository.removeUnit(unitType);
        } catch (NoSuchElementException nsex) {
            return nsex.getMessage();
        }
        return unitType + " retired!";
    }
}
