package pr03_DependencyInversionSkeleton.core;

import pr03_DependencyInversionSkeleton.contracts.CalculatorStrategy;
import pr03_DependencyInversionSkeleton.contracts.Factory;
import pr03_DependencyInversionSkeleton.strategies.AdditionStrategy;
import pr03_DependencyInversionSkeleton.strategies.DivisionStrategy;
import pr03_DependencyInversionSkeleton.strategies.MultiplicationStrategy;
import pr03_DependencyInversionSkeleton.strategies.SubtractionStrategy;

public class StrategyFactory implements Factory {

    @Override
    public CalculatorStrategy createStrategy(char operand) {
        switch (operand) {
            case '+': return new AdditionStrategy();
            case '-': return new SubtractionStrategy();
            case '*': return new MultiplicationStrategy();
            case '/': return new DivisionStrategy();
            default: return null;
        }
    }
}
