package pr03_DependencyInversionSkeleton.contracts;

public interface Factory {

    CalculatorStrategy createStrategy(char operand);
}
