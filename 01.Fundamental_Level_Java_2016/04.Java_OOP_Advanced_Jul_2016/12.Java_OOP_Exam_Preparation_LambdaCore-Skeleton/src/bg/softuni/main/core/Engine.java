package bg.softuni.main.core;

import bg.softuni.main.contracts.coreContracts.*;
import bg.softuni.main.contracts.coreContracts.Runnable;
import bg.softuni.main.contracts.modelContracts.LambdaCore;
import bg.softuni.main.annotations.Inject;

import java.io.IOException;
import java.lang.reflect.Field;

public class Engine implements Runnable {

    private static final String END_INPUT_LINE = "System Shutdown!";

    private Reader reader;
    private Writer writer;
    private LambdaCore lambdaCoreSystem;
    private Interpreter commandInterpreter;

    public Engine(Reader reader,
                  Writer writer,
                  LambdaCore lambdaCoreSystem,
                  Interpreter commandInterpreter) {
        this.reader = reader;
        this.writer = writer;
        this.lambdaCoreSystem = lambdaCoreSystem;
        this.commandInterpreter = commandInterpreter;
    }

    @Override
    public void run() throws IOException, ReflectiveOperationException {

        String commandLine = this.reader.read();
        while (!commandLine.equals(END_INPUT_LINE)) {

            String[] params = commandLine.split(":");
            String commandName = params[0];
            if (params.length > 1) {
                String[] commandArgs = params[1].split("@");
                this.injectInterpreter(commandArgs);
            }
            Executable executable = this.commandInterpreter.interpretCommand(commandName);
            executable.execute();
            commandLine = this.reader.read();
        }
    }

    private void injectInterpreter(String[] commandArgs) throws IllegalAccessException {
        Field[] interpreterFields =
                this.commandInterpreter.getClass().getDeclaredFields();
        for (Field interpreterField : interpreterFields) {
            interpreterField.setAccessible(true);
            if (!interpreterField.isAnnotationPresent(Inject.class)) {
                continue;
            }
            interpreterField.set(this.commandInterpreter, commandArgs);
        }
    }
}
