package problem_10.interfaces;

public interface GameObject<T> {

    String getUserName();
    Integer getLevel();
    T getHashedPassword();
    void setHashedPassword(T element);
}
