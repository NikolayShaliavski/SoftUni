package problem_01_Logger.core;

import problem_01_Logger.contracs.*;
import problem_01_Logger.contracs.Runnable;
import problem_01_Logger.core.loggers.MessageLogger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Engine implements Runnable {

    private Reader reader;
    private Writer writer;
    private Controller controller;
    private List<Appender> appenders;
    private Logger logger;
    private boolean isRunning;

    public Engine(Reader reader,
                  Writer writer,
                  Controller controller) {
        this.reader = reader;
        this.writer = writer;
        this.controller = controller;
        this.appenders = new ArrayList<>();
        this.isRunning = true;
    }

    @Override
    public void run() throws IOException, ReflectiveOperationException {

            int numberOfAppenders = Integer.valueOf(this.reader.read());

            for (int i = 0; i < numberOfAppenders; i++) {
                String appenderArgs = this.reader.read();
                this.appenders.add(this.controller.readAppender(appenderArgs));
            }
            this.setLogger();

        while (isRunning) {
            String inputLine = this.reader.read();

            if (inputLine.equals("END")) {
                this.isRunning = false;
            } else {
                Message message = this.controller.readMessage(inputLine);
                this.logger.logMessage(message);
            }
        }
        this.printAppendersInfo();
    }

    private void printAppendersInfo() {
        this.writer.write("Logger info");
        for (Appender appender : this.appenders) {
            this.writer.write(appender.toString());
        }
    }

    private void setLogger() {
        this.logger = new MessageLogger(this.appenders);
    }
}
