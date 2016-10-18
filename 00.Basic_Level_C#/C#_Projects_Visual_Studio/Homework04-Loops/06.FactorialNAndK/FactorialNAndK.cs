using System;

class FactorialNAndK
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        int k = int.Parse(Console.ReadLine());
        decimal factorialN = 1;
        decimal factorialK = 1;
        decimal result = 0;

        for (int i = 1; i <= n; i++)
        {
            factorialN *= i;
            if (i > k)
            {
                continue;
            }
            else
            {
                factorialK *= i;
            }
        }
        result = factorialN / factorialK;
        Console.WriteLine("{0:0.#####}", result);
    }
}