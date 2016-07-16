package problem_07;

import problem_07.commandInterpreter.CommandInterpreter;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        CommandInterpreter commandInterpreter = new CommandInterpreter();
        commandInterpreter.executeCommands();
    }
}
