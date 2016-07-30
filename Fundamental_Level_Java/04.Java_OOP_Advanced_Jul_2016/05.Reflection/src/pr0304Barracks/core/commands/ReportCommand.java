package pr0304Barracks.core.commands;

import pr0304Barracks.annotations.CommandName;
import pr0304Barracks.annotations.Inject;
import pr0304Barracks.contracts.Repository;

/**
 * Created by Nikolay Shalyavski on 25.7.2016 г..
 */
@CommandName(name = "report")
public class ReportCommand extends Command {

    @Inject
    private Repository repository;

    public ReportCommand(String[] dataArg) {
        super(dataArg);
    }

    @Override
    public String execute() throws ReflectiveOperationException {
        String output = this.repository.getStatistics();
        return output;
    }
}
