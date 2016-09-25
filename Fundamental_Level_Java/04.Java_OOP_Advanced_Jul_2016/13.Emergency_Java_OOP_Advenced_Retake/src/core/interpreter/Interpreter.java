package core.interpreter;

import core.commands.Executable;

public interface Interpreter {

    Executable interpretCommand(String tokensLine) throws ReflectiveOperationException;
}
