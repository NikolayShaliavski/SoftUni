package bg.softuni.contracts;

public interface FilteredTaker {

    void filterAndTake(String courseName, String filter, int studentsToTake);
    void filterAndTake(String courseName, String filter);
}
