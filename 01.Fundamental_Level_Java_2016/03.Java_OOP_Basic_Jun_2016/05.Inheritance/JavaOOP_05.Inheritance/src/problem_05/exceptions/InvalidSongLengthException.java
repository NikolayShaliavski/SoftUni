package problem_05.exceptions;

public class InvalidSongLengthException extends InvalidSongException {

    private static final String SONG_LENGTH_EXCEPTION = "Invalid song length.";

    public InvalidSongLengthException() {
        super(SONG_LENGTH_EXCEPTION);
    }

    public InvalidSongLengthException(String message) {
        super(message);
    }
}
