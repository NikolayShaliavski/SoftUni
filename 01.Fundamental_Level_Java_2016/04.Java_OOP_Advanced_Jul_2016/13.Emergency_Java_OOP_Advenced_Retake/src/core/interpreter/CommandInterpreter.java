package core.interpreter;

import core.annotations.InjectParams;
import core.annotations.InjectType;
import core.commands.Executable;
import core.system.ManagementSystem;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class CommandInterpreter implements Interpreter {

    private static final String COMMANDS_PACKAGE = "core.commands.";
    private static final String COMMAND_SUFFIX = "Command";

    private ManagementSystem managementSystem;

    public CommandInterpreter(ManagementSystem managementSystem) {
        this.managementSystem = managementSystem;
    }

    @Override
    public Executable interpretCommand(String tokensLine) throws ReflectiveOperationException {
        Executable executable = null;
        String[] params = tokensLine.split("[\\|]+");
        String commandName = params[0];
        Class<Executable> exeClass =
                (Class<Executable>) Class.forName(COMMANDS_PACKAGE + commandName + COMMAND_SUFFIX);
        Constructor exeCtor = exeClass.getDeclaredConstructor(ManagementSystem.class);
        executable = (Executable) exeCtor.newInstance(this.managementSystem);
        this.injectDependencies(executable, params);
        return executable;
    }

    private void injectDependencies(Executable executable, String[] params) throws IllegalAccessException {
        Field[] exeFields = executable.getClass().getDeclaredFields();
        for (Field exeField : exeFields) {
            exeField.setAccessible(true);
            if (exeField.isAnnotationPresent(InjectParams.class)) {
                exeField.set(executable, params);
                return;
            }
            if (exeField.isAnnotationPresent(InjectType.class)) {
                exeField.set(executable, params[1]);
                return;
            }
        }
    }
}
