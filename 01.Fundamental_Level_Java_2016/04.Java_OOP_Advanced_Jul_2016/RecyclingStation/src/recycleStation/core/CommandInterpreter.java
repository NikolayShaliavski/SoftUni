package recycleStation.core;

import recycleStation.contracts.*;
import recycleStation.modelsAnnotations.Inject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class CommandInterpreter implements Interpreter {

    private static final String COMMANDS_PACKAGE = "recycleStation.core.commands.";
    private static final String COMMAND_SUFFIX = "Command";

    private RecycleStation recycleStation;
    private GarbageFactory garbageFactory;
    private RequirementFactory managementFactory;

    public CommandInterpreter(RecycleStation recycleStation,
                              GarbageFactory garbageFactory,
                              RequirementFactory managementFactory) {
        this.recycleStation = recycleStation;
        this.garbageFactory = garbageFactory;
        this.managementFactory = managementFactory;
    }

    @Override
    public Executable interpretCommand(String line) throws ReflectiveOperationException {
        Executable executable = null;
        String[] params = line.split("[\\s]+");
        String commandName = params[0];
        Class<Executable> exeClass =
                (Class<Executable>) Class.forName(COMMANDS_PACKAGE + commandName + COMMAND_SUFFIX);
        Constructor exeCtor = exeClass.getDeclaredConstructor(RecycleStation.class);
        executable = (Executable) exeCtor.newInstance(this.recycleStation);
        this.injectDependencies(executable, params);
        return executable;
    }

    private void injectDependencies(Executable executable, String[] params)
            throws IllegalAccessException {
        Field[] exeFields = executable.getClass().getDeclaredFields();
        for (Field exeField : exeFields) {
            exeField.setAccessible(true);
            if (exeField.isAnnotationPresent(Inject.class)) {
                exeField.set(executable, params[1]);
                continue;
            }
            Field[] interpreterFields = this.getClass().getDeclaredFields();
            for (Field interpreterField : interpreterFields) {
                interpreterField.setAccessible(true);
                if (!exeField.getType().equals(interpreterField.getType())) {
                    continue;
                }
                exeField.set(executable, interpreterField.get(this));
            }
        }
    }
}
