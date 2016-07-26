package pr0304Barracks.core.commands;

import pr0304Barracks.annotations.CommandName;
import pr0304Barracks.annotations.Inject;
import pr0304Barracks.contracts.Repository;
import pr0304Barracks.contracts.Unit;
import pr0304Barracks.contracts.UnitFactory;

/**
 * Created by Nikolay Shalyavski on 25.7.2016 г..
 */
@CommandName(name = "add")
public class AddCommand extends Command {

    @Inject(name = "repository")
    private Repository repository;
    @Inject(name = "factory")
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
