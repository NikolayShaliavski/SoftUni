package problem_10.IO;

import problem_10.contracts.Writer;

/**
 * Created by Nikolay Shalyavski on 24.7.2016 г..
 */
public class ConsoleWriter implements Writer {

    @Override
    public void write(String message) {
        System.out.println(message);
    }
}
