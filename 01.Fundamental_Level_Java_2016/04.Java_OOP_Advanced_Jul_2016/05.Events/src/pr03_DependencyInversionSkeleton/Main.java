package pr03_DependencyInversionSkeleton;

import pr03_DependencyInversionSkeleton.contracts.*;
import pr03_DependencyInversionSkeleton.contracts.Runnable;
import pr03_DependencyInversionSkeleton.core.Engine;
import pr03_DependencyInversionSkeleton.core.PrimitiveCalculator;
import pr03_DependencyInversionSkeleton.core.StrategyFactory;
import pr03_DependencyInversionSkeleton.io.ConsoleReader;
import pr03_DependencyInversionSkeleton.io.ConsoleWriter;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Reader reader = new ConsoleReader();
        Writer writer = new ConsoleWriter();
        Factory factory = new StrategyFactory();
        Calculator calculator = new PrimitiveCalculator();

        Runnable engine = new Engine(
                reader, writer, calculator, factory);
        engine.run();
    }
}
