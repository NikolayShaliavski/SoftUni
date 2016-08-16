package pr03Barracks.core.commands;

import pr03Barracks.annotations.CommandName;
import pr03Barracks.annotations.Inject;
import pr03Barracks.contracts.Repository;
import pr03Barracks.contracts.Unit;
import pr03Barracks.contracts.UnitFactory;

/**
 * Created by Nikolay Shalyavski on 25.7.2016 г..
 */
@CommandName(name = "add")
public class AddCommand extends Command {

    @Inject
    private Repository repository;
    @Inject
    private UnitFactory unitFactory;

    public AddCommand(String[] dataArg) {
        super(dataArg);
    }

    @Override
    public String execute() throws ReflectiveOperationException {
        String unitType = super.getData()[1];
        Unit unit = this.unitFactory.createUnit(unitType);
        this.repository.addUnit(unit);
        String result = unitType + " added!";
        return result;
    }
}
