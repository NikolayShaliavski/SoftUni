package pr07_1984.contracts;

public interface Observer {

    void handleChangeEvent(Event changeEvent);

    String reportSavedChanges() throws IllegalAccessException;
}
