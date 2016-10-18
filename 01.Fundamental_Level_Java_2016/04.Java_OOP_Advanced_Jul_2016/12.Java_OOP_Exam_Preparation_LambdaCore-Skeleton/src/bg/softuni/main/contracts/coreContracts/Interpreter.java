package bg.softuni.main.contracts.coreContracts;

/**
 * This interface describes behaviour of CommandInterpreter class
 */
public interface Interpreter {

    /**
     * Creates command which type depends on commandName argument
     *
     * @param commandName argument
     * @return Executable command
     * @throws ReflectiveOperationException
     */
    Executable interpretCommand(String commandName) throws ReflectiveOperationException;
}
