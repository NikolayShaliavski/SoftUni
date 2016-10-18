package pr07_1984.contracts;

public interface Event {

    String getFieldName();

    String getMessage() throws IllegalAccessException;
}
