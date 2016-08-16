package contracts;

public interface Interpreter {

    Executable interpretCommand(String commandName) throws ReflectiveOperationException;
}
