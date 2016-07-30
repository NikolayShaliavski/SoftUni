package problem_01_Logger.core.IO;

import problem_01_Logger.contracs.Writer;

public class ConsoleWriter implements Writer {

    @Override
    public void write(String text) {
        System.out.println(text);
    }
}
