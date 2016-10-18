package problem_05.models;

import problem_05.exceptions.InvalidSongMinutesException;
import problem_05.exceptions.InvalidSongNameException;
import problem_05.exceptions.InvalidSongSecondsException;

public class Song {

    private String name;
    private Integer minutes;
    private Integer seconds;

    public Song(String name, Integer minutes, Integer seconds) {
        this.setName(name);
        this.setMinutes(minutes);
        this.setSeconds(seconds);
    }

    private void setName(String name) {
        if (name == null || name.trim().length() < 4 || name.trim().length() > 30) {
            throw new InvalidSongNameException();
        }
        this.name = name;
    }

    private void setMinutes(Integer minutes) {
        if (minutes < 0 || minutes > 14) {
            throw new InvalidSongMinutesException();
        }
        this.minutes = minutes;
    }

    private void setSeconds(Integer seconds) {
        if (seconds < 0 || seconds > 59) {
            throw new InvalidSongSecondsException();
        }
        this.seconds = seconds;
    }
}
