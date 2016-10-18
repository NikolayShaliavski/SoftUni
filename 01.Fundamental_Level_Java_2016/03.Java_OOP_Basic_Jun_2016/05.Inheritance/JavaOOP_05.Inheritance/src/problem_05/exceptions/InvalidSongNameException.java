package problem_05.exceptions;

public class InvalidSongNameException extends InvalidSongException {

    private static final String SONG_NAME_EXCEPTION = "Song name should be between 3 and 30 symbols.";

    public InvalidSongNameException() {
        super(SONG_NAME_EXCEPTION);
    }

}
