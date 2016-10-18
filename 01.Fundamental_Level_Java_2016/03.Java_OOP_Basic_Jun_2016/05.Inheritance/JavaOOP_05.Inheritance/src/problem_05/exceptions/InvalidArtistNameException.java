package problem_05.exceptions;

public class InvalidArtistNameException extends InvalidSongException {

    private static final String ARTIST_NAME_EXCEPTION = "Artist name should be between 3 and 20 symbols.";

    public InvalidArtistNameException() {
        super(ARTIST_NAME_EXCEPTION);
    }
}
