package problem_08.contracts;

public interface Clinic extends Printable {

    boolean add(Pet pet);
    boolean release();
    boolean hasEmptyRooms();
    String getClinicName();
}
