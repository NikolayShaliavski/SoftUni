package collection;

public interface Register<T> {

    void enqueueEmergency(T emergency);

    T dequeueEmergency();

    T peekEmergency();

    Integer count();

    Boolean isEmpty();
}
