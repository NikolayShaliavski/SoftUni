package problem_05;

import problem_05.exceptions.InvalidSongException;
import problem_05.exceptions.InvalidSongLengthException;
import problem_05.models.Artist;
import problem_05.models.Song;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P05_OnlineRadioDatabaseMain {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Integer numberOfLines = Integer.valueOf(reader.readLine());

        Integer allTimeInSeconds = 0;
        Integer allSongs = 0;

        for (int i = 0; i < numberOfLines; i++) {
            String[] line = reader.readLine().split(";");
            String artistName = line[0];
            try {
                Artist artist = new Artist(artistName);

                String songName = line[1];
                String[] songTime = line[2].split(":");

                Integer minutes = 0;
                Integer seconds = 0;
                try {
                    minutes = Integer.valueOf(songTime[0]);
                    seconds = Integer.valueOf(songTime[1]);
                } catch (NumberFormatException nfex) {
                    throw new InvalidSongLengthException();
                }

                Song song = new Song(songName, minutes, seconds);

                System.out.println("Song added.");
                allSongs++;
                allTimeInSeconds += minutes * 60;
                allTimeInSeconds += seconds;
            } catch (InvalidSongException isex) {
                System.out.println(isex.getMessage());
            }
        }
        int hours = allTimeInSeconds / 3600;
        allTimeInSeconds %= 3600;
        int minutes = allTimeInSeconds / 60;
        allTimeInSeconds %= 60;
        int seconds = allTimeInSeconds;

        System.out.printf("Songs added: %d%n", allSongs);
        System.out.printf("Playlist length: %dh %dm %ds%n", hours, minutes, seconds);
    }
}
