package bg.softuni.main.core.commands;

import bg.softuni.main.contracts.coreContracts.Executable;
import bg.softuni.main.contracts.coreContracts.Writer;
import bg.softuni.main.contracts.modelContracts.LambdaCore;

public abstract class AbstractCommand implements Executable {

    private LambdaCore lambdaCore;
    private Writer writer;

    protected AbstractCommand(LambdaCore lambdaCore,
                              Writer writer) {
        this.lambdaCore = lambdaCore;
        this.writer = writer;
    }

    protected LambdaCore getLambdaCore() {
        return this.lambdaCore;
    }

    protected Writer getWriter() {
        return this.writer;
    }
}
