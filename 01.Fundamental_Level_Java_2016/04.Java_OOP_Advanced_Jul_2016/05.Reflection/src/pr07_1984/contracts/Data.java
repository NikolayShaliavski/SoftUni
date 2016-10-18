package pr07_1984.contracts;

public interface Data {

    void addInstitution(Observer observer);

    void addInstitutionToSubjects();

    void addSubject(Observable subject);

    void createChangeEvent(
            String id, String fieldName, String newValue) throws IllegalAccessException;

    String reportInfo() throws IllegalAccessException;
}
