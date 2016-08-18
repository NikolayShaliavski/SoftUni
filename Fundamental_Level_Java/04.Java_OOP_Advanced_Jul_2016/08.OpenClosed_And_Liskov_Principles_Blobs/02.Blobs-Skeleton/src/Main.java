import core.BlobsRepository;
import core.Engine;
import core.factories.BlobFactory;
import core.io.ConsoleReader;
import core.io.ConsoleWriter;
import interfaces.*;
import interfaces.Runnable;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ReflectiveOperationException {

        Reader reader = new ConsoleReader();
        Writer writer = new ConsoleWriter();
        BlobsData blobsData = new BlobsRepository();
        Factory factory = new BlobFactory();

        Runnable engine = new Engine(blobsData, factory, reader, writer);
        engine.run();
    }
}
