package pr03Barracks.core.commands;

import pr03Barracks.annotations.CommandName;

/**
 * Created by Nikolay Shalyavski on 25.7.2016 г..
 */
@CommandName(name = "fight")
public class FightCommand extends Command {

    public FightCommand(String[] dataArg) {
        super(dataArg);
    }

    @Override
    public String execute() throws ReflectiveOperationException {
        return "fight";
    }
}
