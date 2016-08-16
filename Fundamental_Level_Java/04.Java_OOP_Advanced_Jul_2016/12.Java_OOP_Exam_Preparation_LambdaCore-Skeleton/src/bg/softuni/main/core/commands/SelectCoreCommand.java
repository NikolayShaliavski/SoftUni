package bg.softuni.main.core.commands;

import bg.softuni.main.contracts.coreContracts.Executable;
import bg.softuni.main.contracts.coreContracts.Writer;
import bg.softuni.main.contracts.modelContracts.LambdaCore;
import bg.softuni.main.messages.Messages;

import java.util.NoSuchElementException;

public class SelectCoreCommand extends AbstractCommand {

    private String[] commandParams;

    public SelectCoreCommand(LambdaCore lambdaCore, Writer writer) {
        super(lambdaCore, writer);
    }

    /**
     * @see Executable
     */
    @Override
    public void execute() {

        String coreToSelect = this.commandParams[1];
        try {
            super.getLambdaCore().selectCore(coreToSelect);
            super.getWriter().write(
                    String.format(Messages.SUCCESSFULLY_SELECTED_CORE, coreToSelect));
        } catch (NoSuchElementException nex) {
            super.getWriter().write(nex.getMessage());
        }
    }
}
