import contracts.Executable;
import contracts.Interpreter;

public class Engine {

    private int value;
    private String name;

    private Interpreter commandInterpreter;

    public Engine(Interpreter commandInterpreter, int value, String name) {
        this.commandInterpreter = commandInterpreter;
        this.value = value;
        this.name = name;
    }

    public void run() throws ReflectiveOperationException {
        String command = "Add";
        Executable currentCommand = this.commandInterpreter.interpretCommand(command);
        currentCommand.execute();
    }
}
