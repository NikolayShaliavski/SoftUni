package core;

import core.commands.commandContracts.Executable;
import core.commands.commandContracts.Interpreter;
import io.Reader;
import io.Writer;

import java.io.IOException;

public class Engine implements Runnable {

    private Reader reader;
    private Writer writer;
    private Interpreter commandInterpreter;

    public Engine(Reader reader,
            Writer writer,
            Interpreter commandInterpreter) {
        this.reader = reader;
        this.writer = writer;
        this.commandInterpreter = commandInterpreter;
    }

    @Override
    public void run() throws IOException, ReflectiveOperationException {

        String line = this.reader.read();
        while (!line.equals("Paw Paw Pawah")) {
            Executable command = this.commandInterpreter.interpretCommand(line);
            command.execute();
            line = this.reader.read();
        }
        String outPut = this.commandInterpreter.getCorporation().reportStatus();
        this.writer.write(outPut);
    }
}
