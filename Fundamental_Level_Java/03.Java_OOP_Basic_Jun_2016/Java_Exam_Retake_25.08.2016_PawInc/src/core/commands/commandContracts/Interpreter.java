package core.commands.commandContracts;

import models.corporation.Corporation;

public interface Interpreter {

    Executable interpretCommand(String line) throws ReflectiveOperationException;

    Corporation getCorporation();
}
