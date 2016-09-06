import core.Engine;
import core.Runnable;
import core.commands.CommandInterpreter;
import core.commands.commandContracts.Interpreter;
import io.ConsoleReader;
import io.ConsoleWriter;
import io.Reader;
import io.Writer;
import models.corporation.Corporation;
import models.corporation.PawCorporation;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ReflectiveOperationException {

        Reader reader = new ConsoleReader();
        Writer writer = new ConsoleWriter();
        Corporation corporation = new PawCorporation();
        Interpreter commandInterpreter = new CommandInterpreter(corporation);
        Runnable engine = new Engine(reader, writer, commandInterpreter);

        engine.run();
    }
}
