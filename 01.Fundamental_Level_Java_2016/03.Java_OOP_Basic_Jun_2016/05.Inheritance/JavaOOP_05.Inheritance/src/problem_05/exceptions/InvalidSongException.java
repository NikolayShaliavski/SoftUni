package problem_05.exceptions;

public class InvalidSongException extends RuntimeException {

    private static final String DEFAULT_SONG_EXCEPTION = "Invalid song.";

    public InvalidSongException() {
        super(DEFAULT_SONG_EXCEPTION);
    }

    public InvalidSongException(String message) {
        super(message);
    }

//    public String getMessage() {
//        return this.message;
//    }
//
//    private void setMessage(String message) {
//        this.message = message;
//    }
}
