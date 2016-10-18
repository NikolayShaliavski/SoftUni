package problem_04.exceptions;

/**
 * Created by Nick on 11.7.2016 г..
 */
public class InvalidPhoneException extends RuntimeException {

    public String getMessage() {
        return "Invalid number!";
    }
}
