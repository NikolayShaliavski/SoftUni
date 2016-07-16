package problem_05.exceptions;

public class InvalidSongSecondsException extends InvalidSongLengthException {

    private static final String SONG_SECONDS_EXCEPTION = "Song seconds should be between 0 and 59.";

    public InvalidSongSecondsException() {
        super(SONG_SECONDS_EXCEPTION);
    }
}
