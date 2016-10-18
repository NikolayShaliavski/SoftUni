import recycleStation.contracts.*;
import recycleStation.contracts.Runnable;
import recycleStation.core.CommandInterpreter;
import recycleStation.core.Engine;
import recycleStation.core.factories.GarbageFactoryImpl;
import recycleStation.core.RecycleStationImpl;
import recycleStation.core.factories.ManagementRequirementFactory;
import recycleStation.core.factories.StrategyFactoryImpl;
import recycleStation.io.ConsoleReader;
import recycleStation.io.ConsoleWriter;
import wasteDisposal.DefaultGarbageProcessor;
import wasteDisposal.contracts.GarbageProcessor;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ReflectiveOperationException {

        Reader reader = new ConsoleReader();
        Writer writer = new ConsoleWriter();
        GarbageProcessor garbageProcessor = new DefaultGarbageProcessor();
        StrategyFactory strategyFactory = new StrategyFactoryImpl();
        RecycleStation recycleStation = new RecycleStationImpl(garbageProcessor, strategyFactory);
        GarbageFactory garbageFactory = new GarbageFactoryImpl();
        RequirementFactory requirementFactory = new ManagementRequirementFactory();
        Interpreter commandInterpreter =
                new CommandInterpreter(recycleStation, garbageFactory, requirementFactory);
        Runnable engine = new Engine(reader, writer, commandInterpreter);
        engine.run();
    }
}
