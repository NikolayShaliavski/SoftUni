package pr0304Barracks.core.commands;

import pr0304Barracks.annotations.CommandName;
import pr0304Barracks.annotations.Inject;
import pr0304Barracks.contracts.CommandInterpreter;
import pr0304Barracks.contracts.Executable;
import pr0304Barracks.core.Engine;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * Created by Nikolay Shalyavski on 26.7.2016 г..
 */
public class CommandInterpreterImpl implements CommandInterpreter {

    private static final String COMMAND_PACKAGE = "pr0304Barracks.core.commands.";

    private Engine engine;

    public CommandInterpreterImpl(Engine engine) {
        this.engine = engine;
    }

    @Override
    public Executable interpretCommand(String[] data, String commandArg) throws ReflectiveOperationException {

        Executable executable = null;
        String commandDirectoryPath = System.getProperty("user.dir") + "\\src\\" +
                this.getClass().getPackage().getName().replace(".", "\\");

        //This does not work in Judge
        //you have to make first letter to upperCase and create command directly
        File file = new File(commandDirectoryPath);
        for (File currentFile : file.listFiles()) {
            if (currentFile.isFile() && currentFile.getName().endsWith(".java")) {
                String fullName = currentFile.getName();
                String fileName = fullName.substring(0, fullName.indexOf('.'));
                Class commandClass =
                        Class.forName(COMMAND_PACKAGE + fileName);
                if (commandClass.isAnnotationPresent(CommandName.class)) {
                    CommandName commandName = (CommandName) commandClass.getAnnotation(CommandName.class);

                    if (commandName.name().equals(commandArg)) {
                        Constructor ctor = commandClass.getDeclaredConstructor(String[].class);
                        ctor.setAccessible(true);
                        executable = (Executable) ctor.newInstance((Object) data);
                        break;
                    }
                }
            }
        }
        Field[] fields = executable.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(Inject.class)) {
                continue;
            }
            field.setAccessible(true);
            Field[] engineFields = this.engine.getClass().getDeclaredFields();
            for (Field engineField : engineFields) {
                if (!field.getType().equals(engineField.getType())) {
                    continue;
                }
                engineField.setAccessible(true);
                field.set(executable, engineField.get(this.engine));
            }
        }
        return executable;
    }
}
