package pr03_DependencyInversionSkeleton.strategies;

import pr03_DependencyInversionSkeleton.contracts.CalculatorStrategy;

public class SubtractionStrategy implements CalculatorStrategy {

    @Override
    public int calculate(int firstOperand, int secondOperand){
        return firstOperand - secondOperand;
    }
}
