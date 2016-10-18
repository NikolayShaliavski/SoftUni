package pr03Barracks.core;

import pr03Barracks.contracts.*;
import pr03Barracks.contracts.Runnable;
import pr03Barracks.core.commands.CommandInterpreterImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Engine implements Runnable {

    private Repository repository;
    private UnitFactory unitFactory;
    private CommandInterpreter commandInterpreter;

    public Engine(Repository repository,
                  UnitFactory unitFactory) {
        this.repository = repository;
        this.unitFactory = unitFactory;
        this.initCommandInterpreter();
    }

    public Repository getRepository() {
        return this.repository;
    }

    public UnitFactory getUnitFactory() {
        return this.unitFactory;
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        while (true) {
            try {
                String input = reader.readLine();
                String[] data = input.split("\\s+");
                String commandArg = data[0];

                Executable command = this.commandInterpreter.interpretCommand(data, commandArg);
                String result = command.execute();
                if (result.equals("fight")) {
                    break;
                }
                System.out.println(result);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }
        }
    }

    //init CommandInterpretter once when create Engine, not every iteration in the loop
    private void initCommandInterpreter() {
        this.commandInterpreter = new CommandInterpreterImpl(this);
    }
}
