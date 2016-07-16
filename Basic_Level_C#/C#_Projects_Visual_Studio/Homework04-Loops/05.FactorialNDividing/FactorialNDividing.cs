using System;

class FactorialNDividing
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        int x = int.Parse(Console.ReadLine());
        decimal resultX = 1;
        decimal factorialN = 1;
        decimal sum = 0;

        for (int i = 1; i <= n; i++)
        {
            factorialN *= i;
            resultX *= x;
            sum += (factorialN / resultX);
        }
        decimal result = sum + 1;
        Console.WriteLine("{0:F5}", result);
    }
}
