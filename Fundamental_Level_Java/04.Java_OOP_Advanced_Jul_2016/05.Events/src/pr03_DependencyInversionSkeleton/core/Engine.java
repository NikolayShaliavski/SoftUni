package pr03_DependencyInversionSkeleton.core;

import pr03_DependencyInversionSkeleton.contracts.*;
import pr03_DependencyInversionSkeleton.contracts.Runnable;

import java.io.IOException;

public class Engine implements Runnable {

    private Reader reader;
    private Writer writer;
    private Calculator calculator;
    private Factory factory;

    public Engine(Reader reader,
                  Writer writer,
                  Calculator calculator,
                  Factory factory) {
        this.reader = reader;
        this.writer = writer;
        this.calculator = calculator;
        this.factory = factory;
    }

    @Override
    public void run() throws IOException {

        String command = this.reader.read();
        while (!command.equals("End")) {

            this.parseInput(command);

            command = reader.read();
        }
    }

    private void parseInput(String command) {

        String[] params = command.split(" ");
        if (params[0].equals("mode")) {
            char operand = params[1].charAt(0);
            CalculatorStrategy strategy = this.factory.createStrategy(operand);
            this.calculator.changeStrategy(strategy);
        } else {
            int firstOperand = Integer.valueOf(params[0]);
            int secondOperand = Integer.valueOf(params[1]);
            int result = this.calculator.performCalculation(
                    firstOperand, secondOperand);
            this.writer.write(String.valueOf(result));
        }
    }
}
