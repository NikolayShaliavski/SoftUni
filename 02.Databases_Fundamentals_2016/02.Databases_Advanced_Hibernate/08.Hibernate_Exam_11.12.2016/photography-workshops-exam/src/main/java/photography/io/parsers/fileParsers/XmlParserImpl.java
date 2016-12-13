package photography.io.parsers.fileParsers;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

@Component(value = "XmlParser")
public class XmlParserImpl implements Parser {

    @Override
    public <T> T read(Class<T> classes, String fileName) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(classes);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        File file = new File(fileName);
        T object = (T) unmarshaller.unmarshal(file);
        return object;
    }

    @Override
    public <T> void write(T object, String fileName) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        File file = new File(fileName);
        marshaller.marshal(object, file);
    }
}
