package recycleStation.core;

import recycleStation.contracts.*;
import recycleStation.contracts.Runnable;

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

        String lineParams = this.reader.read();
        while (!lineParams.equals("TimeToRecycle")) {

            Executable currentCommand = this.commandInterpreter.interpretCommand(lineParams);
            String result = currentCommand.execute();
            this.writer.write(result);
            lineParams = this.reader.read();
        }
    }
}
