package photography.io.parsers.fileParsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import photography.io.readers.Reader;
import photography.io.writers.Writer;

import java.io.IOException;

@Component(value = "JSONParser")
public class JSONParser implements Parser {

    private Gson gson;

    @Autowired
    private Reader fileReader;

    @Autowired
    @Qualifier(value = "FileWriter")
    private Writer fileWriter;

    public JSONParser() {
        this.setGson(new GsonBuilder().setPrettyPrinting().serializeNulls().create());
    }

    public Gson getGson() {
        return this.gson;
    }

    private void setGson(Gson gson) {
        this.gson = gson;
    }

    @Override
    public <T> T read(Class<T> classes, String file) throws IOException {
        String fileData = this.fileReader.read(file);
        T object = this.getGson().fromJson(fileData, classes);

        return object;
    }

    @Override
    public <T> void write(T object, String fileName) throws IOException {
        String json = this.getGson().toJson(object);
        this.fileWriter.write(json, fileName);
    }
}
