package core.commands;

import core.commands.commandContracts.Executable;
import core.commands.commandContracts.Interpreter;
import models.corporation.Corporation;

import java.lang.reflect.Constructor;

public class CommandInterpreter implements Interpreter {

    private static final String COMMANDS_PACKAGE = "core.commands.";
    private static final String COMMAND_SUFFIX = "Command";

    private Corporation corporation;

    public CommandInterpreter(Corporation corporation) {
        this.corporation = corporation;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Executable interpretCommand(String line) throws ReflectiveOperationException {
        Executable executable = null;
        String[] params = line.split(" \\| ");
        String commandName = params[0];

        Class<Executable> executableClass =
                (Class<Executable>) Class.forName(COMMANDS_PACKAGE + commandName + COMMAND_SUFFIX);
        Constructor executableCtor =
                executableClass.getConstructor(Corporation.class, String[].class);
        executable = (Executable) executableCtor.newInstance(this.corporation, params);
        return executable;
    }

    @Override
    public Corporation getCorporation() {
        return this.corporation;
    }
}
