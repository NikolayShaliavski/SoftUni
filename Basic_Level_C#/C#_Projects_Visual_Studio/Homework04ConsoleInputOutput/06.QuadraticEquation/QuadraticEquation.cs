using System;

class QuadraticEquation
{
    static void Main()
    {
        double firstCoefficient = double.Parse(Console.ReadLine());
        double secondCoefficient = double.Parse(Console.ReadLine());
        double thirdCoefficient = double.Parse(Console.ReadLine());

        secondCoefficient = -Math.Abs(secondCoefficient);

        if ((secondCoefficient * secondCoefficient - (4 * firstCoefficient * thirdCoefficient)) < 0)
        {
            Console.WriteLine("No real roots."); return;
        }
        if (((secondCoefficient * secondCoefficient - (4 * firstCoefficient * thirdCoefficient)) == 0))
        {
            double root = (-Math.Abs(secondCoefficient)) / (2 * firstCoefficient);
            Console.WriteLine("X1=X2={0:0.##}", root); return;
        }
        else
        {
            double sqrtCoefficient = Math.Sqrt((secondCoefficient * secondCoefficient) -
                (4 * firstCoefficient * thirdCoefficient));

            double firstRoot = (-Math.Abs(secondCoefficient) - sqrtCoefficient) / (2 * firstCoefficient);

            double secondRoot = (-Math.Abs(secondCoefficient) + sqrtCoefficient) / (2 * firstCoefficient);

            Console.WriteLine("X1={0:0.#}; X2={1:0.#}", firstRoot, secondRoot);
        }
    }
}

