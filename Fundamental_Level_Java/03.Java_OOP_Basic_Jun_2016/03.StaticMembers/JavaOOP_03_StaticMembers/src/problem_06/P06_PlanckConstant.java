package problem_06;

public class P06_PlanckConstant {

    public static void main(String[] args) {
        System.out.println(Calculation.reducePlanckConstant());
    }
}
class Calculation {
    private static double planckConstant = 6.62606896e-34;
    private static double piConstant = 3.14159;

    public static double reducePlanckConstant() {
        return planckConstant / (2 * piConstant);
    }
}