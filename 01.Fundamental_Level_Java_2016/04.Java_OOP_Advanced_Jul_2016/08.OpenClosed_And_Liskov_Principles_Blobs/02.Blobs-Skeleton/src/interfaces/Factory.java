package interfaces;

public interface Factory {

    Blob createBlob(String[] params) throws ReflectiveOperationException;
}
