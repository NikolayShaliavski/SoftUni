package pr03_DependencyInversionSkeleton.core;


import pr03_DependencyInversionSkeleton.contracts.Calculator;
import pr03_DependencyInversionSkeleton.contracts.CalculatorStrategy;
import pr03_DependencyInversionSkeleton.strategies.AdditionStrategy;

public class PrimitiveCalculator implements Calculator {

    private CalculatorStrategy calculatorStrategy;

    public PrimitiveCalculator() {
        this.calculatorStrategy = new AdditionStrategy();
    }

    @Override
    public void changeStrategy(CalculatorStrategy strategy) {
        this.calculatorStrategy = strategy;
    }

    @Override
    public int performCalculation(int firstOperand, int secondOperand) {
        return this.calculatorStrategy.calculate(firstOperand, secondOperand);
    }
}
