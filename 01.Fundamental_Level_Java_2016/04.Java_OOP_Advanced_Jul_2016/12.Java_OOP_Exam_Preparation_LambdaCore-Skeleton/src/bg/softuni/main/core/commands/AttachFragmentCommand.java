package bg.softuni.main.core.commands;

import bg.softuni.main.contracts.coreContracts.Executable;
import bg.softuni.main.contracts.coreContracts.Writer;
import bg.softuni.main.contracts.factoryContracts.FragmentFactory;
import bg.softuni.main.contracts.modelContracts.Fragment;
import bg.softuni.main.contracts.modelContracts.LambdaCore;
import bg.softuni.main.messages.Messages;
import com.sun.javaws.exceptions.InvalidArgumentException;

public class AttachFragmentCommand extends AbstractCommand {

    private String[] commandParams;
    private FragmentFactory fragmentFactory;

    public AttachFragmentCommand(LambdaCore lambdaCore, Writer writer) {
        super(lambdaCore, writer);
    }

    /**
     * @see Executable
     */
    @Override
    public void execute() {
        String fragmentName = this.commandParams[2];
        try {
            Fragment fragment = this.fragmentFactory.createFragment(this.commandParams);
            this.getLambdaCore().attachFragmentToCore(fragment);
            super.getWriter().write(
                    String.format(Messages.SUCCESSFULLY_ATTACH_FRAGMENT,
                            fragmentName,
                            super.getLambdaCore().getCurrentCore().getCoreName()));
        } catch (ReflectiveOperationException | InvalidArgumentException |
                UnsupportedOperationException ex) {
            super.getWriter().write(String.format(Messages.FAIL_TO_ATTACH_FRAGMENT, fragmentName));
        }
    }
}
