package problem_01_Logger.contracs;

public interface Controller {

    Appender readAppender(String appendersArgs) throws ReflectiveOperationException;
    Message readMessage(String messageArgs);
}
