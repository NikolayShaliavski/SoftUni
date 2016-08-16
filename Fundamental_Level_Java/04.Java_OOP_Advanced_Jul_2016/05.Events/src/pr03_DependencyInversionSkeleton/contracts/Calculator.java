package pr03_DependencyInversionSkeleton.contracts;

public interface Calculator {

    void changeStrategy(CalculatorStrategy strategy);

    int performCalculation(int firstOperand,int secondOperand);
}
