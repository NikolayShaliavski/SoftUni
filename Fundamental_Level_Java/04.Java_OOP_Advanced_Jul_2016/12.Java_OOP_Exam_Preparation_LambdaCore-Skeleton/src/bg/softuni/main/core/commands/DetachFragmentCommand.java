package bg.softuni.main.core.commands;

import bg.softuni.main.contracts.coreContracts.Executable;
import bg.softuni.main.contracts.coreContracts.Writer;
import bg.softuni.main.contracts.modelContracts.Fragment;
import bg.softuni.main.contracts.modelContracts.LambdaCore;
import bg.softuni.main.messages.Messages;

public class DetachFragmentCommand extends AbstractCommand {

    public DetachFragmentCommand(LambdaCore lambdaCore, Writer writer) {
        super(lambdaCore, writer);
    }

    /**
     * @see Executable
     */
    @Override
    public void execute() {

        try {
            Fragment detached = super.getLambdaCore().detachFragment();
            super.getWriter().write(
                    String.format(Messages.SUCCESSFULLY_DETACH_FRAGMENT,
                    detached.getName(), super.getLambdaCore().getCurrentCore().getCoreName()));
        } catch (UnsupportedOperationException nsex) {
            super.getWriter().write(nsex.getMessage());
        }
    }
}
