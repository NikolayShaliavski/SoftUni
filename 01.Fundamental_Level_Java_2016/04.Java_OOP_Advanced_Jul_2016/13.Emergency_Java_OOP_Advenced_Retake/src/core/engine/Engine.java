package core.engine;

import core.commands.Executable;
import core.interpreter.Interpreter;
import core.io.Reader;
import core.io.Writer;

import java.io.IOException;

public class Engine implements Runnable {

    private Reader consoleReader;
    private Writer consoleWriter;
    private Interpreter commandInterpreter;

    public Engine(Reader consoleReader,
                  Writer consoleWriter,
                  Interpreter commandInterpreter) {
        this.consoleReader = consoleReader;
        this.consoleWriter = consoleWriter;
        this.commandInterpreter = commandInterpreter;
    }

    @Override
    public void run() throws IOException, ReflectiveOperationException {
        String line = this.consoleReader.read();

        while (!line.equals("EmergencyBreak")) {
            Executable command = this.commandInterpreter.interpretCommand(line);
            this.consoleWriter.write(command.execute());
            line = this.consoleReader.read();
        }
    }
}
