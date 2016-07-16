package app.IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Nick on 7.7.2016 г..
 */
public class Reader {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String read() throws IOException {
        return reader.readLine();
    }
}
