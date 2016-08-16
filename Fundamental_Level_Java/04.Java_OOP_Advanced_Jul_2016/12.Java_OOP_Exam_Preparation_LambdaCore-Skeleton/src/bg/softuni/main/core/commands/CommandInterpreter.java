package bg.softuni.main.core.commands;

import bg.softuni.main.contracts.coreContracts.Executable;
import bg.softuni.main.contracts.coreContracts.Interpreter;
import bg.softuni.main.contracts.coreContracts.Writer;
import bg.softuni.main.contracts.factoryContracts.CoreFactory;
import bg.softuni.main.contracts.factoryContracts.FragmentFactory;
import bg.softuni.main.contracts.modelContracts.LambdaCore;
import bg.softuni.main.annotations.Inject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class CommandInterpreter implements Interpreter {

    private static final String COMMAND_PACKAGE = "bg.softuni.main.core.commands.";
    private static final String COMMAND_SUFFIX = "Command";

    private LambdaCore lambdaCore;
    private Writer writer;
    private CoreFactory coreFactory;
    private FragmentFactory fragmentFactory;
    @Inject
    private String[] commandParams;

    public CommandInterpreter(LambdaCore lambdaCore,
                              Writer writer,
                              CoreFactory coreFactory,
                              FragmentFactory fragmentFactory) {
        this.lambdaCore = lambdaCore;
        this.writer = writer;
        this.coreFactory = coreFactory;
        this.fragmentFactory = fragmentFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Executable interpretCommand(String commandName) throws ReflectiveOperationException {
        Executable executable = null;

        Class<Executable> exeClass = (Class<Executable>) Class.forName(
                COMMAND_PACKAGE + commandName + COMMAND_SUFFIX);
        Constructor exeCtor = exeClass.getDeclaredConstructor(
                LambdaCore.class, Writer.class);
        executable = (Executable) exeCtor.newInstance(this.lambdaCore, this.writer);

        Field[] exeFields = exeClass.getDeclaredFields();
        for (Field exeField : exeFields) {
            exeField.setAccessible(true);
            for (Field interpretField : this.getClass().getDeclaredFields()) {
                interpretField.setAccessible(true);
                if (!exeField.getType().equals(interpretField.getType())) {
                    continue;
                }
                exeField.set(executable, interpretField.get(this));
            }
        }
        return executable;
    }
}
