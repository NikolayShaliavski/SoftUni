import core.engine.Engine;
import core.engine.Runnable;
import core.interpreter.CommandInterpreter;
import core.interpreter.Interpreter;
import core.io.ConsoleReader;
import core.io.ConsoleWriter;
import core.io.Reader;
import core.io.Writer;
import core.system.EmergencyManagementSystem;
import core.system.ManagementSystem;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ReflectiveOperationException {

        Reader consoleReader = new ConsoleReader();
        Writer consoleWriter = new ConsoleWriter();
        ManagementSystem managementSystem = new EmergencyManagementSystem();
        Interpreter commandInterpreter = new CommandInterpreter(managementSystem);
        Runnable engine = new Engine(consoleReader, consoleWriter, commandInterpreter);

        engine.run();
    }
}
