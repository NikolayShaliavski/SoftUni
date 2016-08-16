package bg.softuni.main.core.commands;

import bg.softuni.main.contracts.coreContracts.Executable;
import bg.softuni.main.contracts.coreContracts.Writer;
import bg.softuni.main.contracts.modelContracts.LambdaCore;

public class StatusCommand extends AbstractCommand {

    protected StatusCommand(LambdaCore lambdaCore, Writer writer) {
        super(lambdaCore, writer);
    }

    /**
     * @see Executable
     */
    @Override
    public void execute() {
        super.getWriter().write(super.getLambdaCore().reportStatus());
    }
}
