package bg.softuni.exceptions;

public class InvalidNumberOfScores extends RuntimeException {

    public static final String INVALID_NUMBER_OF_SCORES =
            "The number of scores for the given course is greater than the possible.";

    public InvalidNumberOfScores() {
        super(INVALID_NUMBER_OF_SCORES);
    }

    public InvalidNumberOfScores(String message) {
        super(message);
    }
}
