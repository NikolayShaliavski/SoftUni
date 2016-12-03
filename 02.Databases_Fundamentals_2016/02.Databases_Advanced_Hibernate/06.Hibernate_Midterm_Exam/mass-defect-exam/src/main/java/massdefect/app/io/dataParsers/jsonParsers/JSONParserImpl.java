package massdefect.app.io.dataParsers.jsonParsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import massdefect.app.io.readers.Reader;
import massdefect.app.io.writers.Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JSONParserImpl implements JSONParser {

    private Gson gson;

    @Autowired
    private Reader fileReader;

    @Autowired
    @Qualifier(value = "FileWriter")
    private Writer fileWriter;

    public JSONParserImpl() {
        this.setGson(new GsonBuilder().setPrettyPrinting().create());
    }

    public Gson getGson() {
        return this.gson;
    }

    private void setGson(Gson gson) {
        this.gson = gson;
    }

    @Override
    public <T> T readFromJSON(Class<T> classes, String file) throws IOException {
        String fileData = this.fileReader.read(file);
        T object = this.getGson().fromJson(fileData, classes);

        return object;
    }

    @Override
    public <T> void writeToJSON(T object, String fileName) throws IOException {
        String json = this.getGson().toJson(object);
        this.fileWriter.write(json, fileName);
    }
}
