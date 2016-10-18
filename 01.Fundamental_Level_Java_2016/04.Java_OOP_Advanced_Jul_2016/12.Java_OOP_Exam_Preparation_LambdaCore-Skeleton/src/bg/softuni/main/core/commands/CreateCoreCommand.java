package bg.softuni.main.core.commands;

import bg.softuni.main.contracts.coreContracts.Executable;
import bg.softuni.main.contracts.coreContracts.Writer;
import bg.softuni.main.contracts.factoryContracts.CoreFactory;
import bg.softuni.main.contracts.modelContracts.Core;
import bg.softuni.main.contracts.modelContracts.LambdaCore;
import bg.softuni.main.messages.Messages;
import com.sun.javaws.exceptions.InvalidArgumentException;

public class CreateCoreCommand extends AbstractCommand {

    private CoreFactory coreFactory;
    private String[] commandParams;

    public CreateCoreCommand(LambdaCore lambdaCore, Writer writer) {
        super(lambdaCore, writer);
    }

    /**
     * @see Executable
     */
    @Override
    public void execute() {

        try {
            Core newCore = this.coreFactory.createCore(this.commandParams);
            this.getLambdaCore().addCore(newCore);
            super.getWriter().write(
                    String.format(Messages.SUCCESSFULLY_CREATE_CORE, newCore.getCoreName()));
        } catch (ReflectiveOperationException | InvalidArgumentException ex) {
            super.getWriter().write(Messages.FAIL_TO_CREATE_CORE);
        }
    }
}
