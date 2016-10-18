using System;
using System.Numerics;

class Combinatorics
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        int k = int.Parse(Console.ReadLine());
        BigInteger factorialN = 1;
        BigInteger factorialK = 1;
        BigInteger factorialNK = 1;
        BigInteger result = 0;

        for (int i = 1; i <= n; i++)
        {
            factorialN *= i;
            if (i > k)
            {
                factorialK *=1;
            }
            else
            {
                factorialK *= i;
            }
            if (i > (n - k))
            {
                factorialNK *= 1;
            }
            else
            {
                factorialNK *= i;
            }
        }
        result = factorialN / (factorialK * factorialNK);
        Console.WriteLine("{0:0.#####}", result);
    }
}