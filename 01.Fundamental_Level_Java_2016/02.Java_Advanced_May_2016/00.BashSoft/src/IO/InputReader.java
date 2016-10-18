package IO;

import StaticData.SessionData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputReader {

    //written in fifth LAB
    private static final String END_COMMAND = "quit";

    public static void readCommands() {

        OutputWriter.writeMessage(String.format("%s > ", SessionData.currentPath));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        try {
            input = reader.readLine().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (!input.equals(END_COMMAND)) {
            CommandInterpreter.interpretCommand(input);
            OutputWriter.writeMessage(String.format("%s > ", SessionData.currentPath));
            try {
                input = reader.readLine().trim();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Thread[] threads = new Thread[Thread.activeCount()];
        Thread.enumerate(threads);
        for (Thread thread : threads) {
            if (!thread.getName().equals("main") && !thread.isDaemon()) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
