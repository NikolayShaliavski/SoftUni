package bg.softuni.main;

import bg.softuni.main.contracts.coreContracts.Interpreter;
import bg.softuni.main.contracts.coreContracts.Reader;
import bg.softuni.main.contracts.coreContracts.Runnable;
import bg.softuni.main.contracts.coreContracts.Writer;
import bg.softuni.main.contracts.factoryContracts.CoreFactory;
import bg.softuni.main.contracts.factoryContracts.FragmentFactory;
import bg.softuni.main.contracts.modelContracts.LambdaCore;
import bg.softuni.main.core.Engine;
import bg.softuni.main.core.commands.CommandInterpreter;
import bg.softuni.main.core.factories.CoreFactoryImpl;
import bg.softuni.main.core.factories.FragmentFactoryImpl;
import bg.softuni.main.core.io.ConsoleReader;
import bg.softuni.main.core.io.ConsoleWriter;
import bg.softuni.main.models.lambdaSystem.LambdaSystem;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ReflectiveOperationException {

        Reader reader = new ConsoleReader();
        Writer writer = new ConsoleWriter();
        CoreFactory coreFactory = new CoreFactoryImpl();
        FragmentFactory fragmentFactory = new FragmentFactoryImpl();
        LambdaCore lambdaCoreSystem = new LambdaSystem();
        Interpreter commandInterpreter =
                new CommandInterpreter(lambdaCoreSystem, writer, coreFactory, fragmentFactory);
        Runnable engine = new Engine(reader, writer, lambdaCoreSystem, commandInterpreter);
        engine.run();
    }
}
