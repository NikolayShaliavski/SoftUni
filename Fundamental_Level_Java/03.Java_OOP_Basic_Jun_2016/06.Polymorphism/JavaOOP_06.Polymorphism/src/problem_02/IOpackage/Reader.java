package problem_02.IOpackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Reader {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public String read() throws IOException {
        return reader.readLine();
    }
}
