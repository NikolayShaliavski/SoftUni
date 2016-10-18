package problem_05.exceptions;

public class InvalidSongMinutesException extends InvalidSongLengthException {

    private static final String SONG_MINUTES_EXCEPTION = "Song minutes should be between 0 and 14.";

    public InvalidSongMinutesException() {
        super(SONG_MINUTES_EXCEPTION);
    }
}
