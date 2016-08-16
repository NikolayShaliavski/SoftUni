package commands;

import annotations.Inject;
import contracts.Executable;
import contracts.Interpreter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class CommandInterpreter implements Interpreter {

    private int value;
    private String name;

    public CommandInterpreter(int value, String name) {
        this.value = value;
        this.name = name;
    }

    //example - package where are all command classes
    private static final String COMMAND_PACKAGE = "commands.";

    @Override
    public Executable interpretCommand(String commandName) throws ReflectiveOperationException {

        Executable executable = null;

        //make commandName exactly like name of command class we need to create

        Class commandClass = Class.forName(COMMAND_PACKAGE + commandName);
        Constructor commandCtor = commandClass.getDeclaredConstructor(String[].class);
        //here add what params command constructor receive -> like String.class, String[].class etc.
        //if we pass params[] directly to execute()  method, command constructor won't receive any params

        commandCtor.setAccessible(true);//if ctor access is not public
        executable = (Executable) commandCtor.newInstance();
        //if ctor wants any params, we pass them to constructor
        //and if we pass one param to ctor we have to cast it to (Object)

        Field[] exeFields = commandClass.getDeclaredFields();
        for (Field exeField : exeFields) {
            if (!exeField.isAnnotationPresent(Inject.class)) {
                continue;
            }
            exeField.setAccessible(true);
            Field[] interpreterFields = this.getClass().getDeclaredFields();
            for (Field interpreterField : interpreterFields) {
                if (!exeField.getType().equals(interpreterField.getType())) {
                    continue;
                }
                interpreterField.setAccessible(true);
                exeField.set(executable, interpreterField.get(this));
            }
        }
        return executable;
    }
}
