package massdefect.app.io.dataParsers.jsonParsers;

import java.io.IOException;

public interface JSONParser {

    <T> T readFromJSON(Class<T> classes, String file) throws IOException;

    <T> void writeToJSON(T object, String file) throws IOException;
}
