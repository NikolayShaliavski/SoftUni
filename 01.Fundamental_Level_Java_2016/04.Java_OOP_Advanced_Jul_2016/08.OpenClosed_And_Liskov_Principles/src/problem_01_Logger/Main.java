package problem_01_Logger;

import problem_01_Logger.contracs.Controller;
import problem_01_Logger.contracs.Reader;
import problem_01_Logger.contracs.Runnable;
import problem_01_Logger.contracs.Writer;
import problem_01_Logger.core.ControllerImpl;
import problem_01_Logger.core.Engine;
import problem_01_Logger.core.IO.ConsoleReader;
import problem_01_Logger.core.IO.ConsoleWriter;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ReflectiveOperationException {

        Reader reader = new ConsoleReader();
        Writer writer = new ConsoleWriter();
        Controller controller = new ControllerImpl();
        Runnable engine = new Engine(reader, writer, controller);
        engine.run();
    }
}
