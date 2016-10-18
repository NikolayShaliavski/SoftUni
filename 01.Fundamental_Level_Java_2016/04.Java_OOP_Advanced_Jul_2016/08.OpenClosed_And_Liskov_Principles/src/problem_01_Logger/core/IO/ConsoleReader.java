package problem_01_Logger.core.IO;

import problem_01_Logger.contracs.Reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Nikolay Shalyavski on 30.7.2016 г..
 */
public class ConsoleReader implements Reader {

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public String read() throws IOException {
        return this.reader.readLine();
    }
}
