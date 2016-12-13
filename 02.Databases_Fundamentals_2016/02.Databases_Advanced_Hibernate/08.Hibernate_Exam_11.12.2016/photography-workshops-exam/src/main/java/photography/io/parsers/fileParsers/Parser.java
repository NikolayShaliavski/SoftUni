package photography.io.parsers.fileParsers;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface Parser {

    <T> T read(Class<T> classes, String file) throws IOException, JAXBException;

    <T> void write(T object, String file) throws IOException, JAXBException;
}
