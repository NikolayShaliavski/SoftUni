package pr07_1984;

import pr07_1984.contracts.Data;
import pr07_1984.contracts.Reader;
import pr07_1984.contracts.Runnable;
import pr07_1984.contracts.Writer;
import pr07_1984.core.DataRepository;
import pr07_1984.core.Engine;
import pr07_1984.io.ConsoleReader;
import pr07_1984.io.ConsoleWriter;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, IllegalAccessException {

        Reader reader = new ConsoleReader();
        Writer writer = new ConsoleWriter();
        Data dataRepository = new DataRepository();

        Runnable engine = new Engine(dataRepository, reader, writer);
        engine.run();
    }
}
