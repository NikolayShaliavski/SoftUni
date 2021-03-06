package bg.softuni.main.core.commands;

import bg.softuni.main.contracts.coreContracts.Executable;
import bg.softuni.main.contracts.coreContracts.Writer;
import bg.softuni.main.contracts.modelContracts.LambdaCore;
import bg.softuni.main.messages.Messages;

import java.util.NoSuchElementException;

public class RemoveCoreCommand extends AbstractCommand {

    private String[] commandParams;

    public RemoveCoreCommand(LambdaCore lambdaCore, Writer writer) {
        super(lambdaCore, writer);
    }

    /**
     * @see Executable
     */
    @Override
    public void execute() {
        String coreToRemove = this.commandParams[1];
        try {
            super.getLambdaCore().removeCore(coreToRemove);
            super.getWriter().write(
                    String.format(Messages.SUCCESSFULLY_REMOVE_CORE, coreToRemove));
        } catch (NoSuchElementException nex) {
            super.getWriter().write(nex.getMessage());
        }
    }
}
