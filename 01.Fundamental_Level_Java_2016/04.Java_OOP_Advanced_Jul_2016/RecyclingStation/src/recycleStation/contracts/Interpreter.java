package recycleStation.contracts;

public interface Interpreter {

    Executable interpretCommand(String line) throws ReflectiveOperationException;
}
