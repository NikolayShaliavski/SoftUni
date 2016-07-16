using System;
using System.Numerics;

class CatalanNumbers
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        BigInteger factorialN = 1;
        BigInteger factorial2N = 1;
        BigInteger factorialN1 = 1;
        BigInteger result = 0;

        for (int i = 1; i <= 2 * n; i++)
        {
            factorial2N *= i;
            if (i > n)
            {
                factorialN *= 1;
            }
            else
            {
                factorialN *= i;
            }
            if (i > (n + 1))
            {
                factorialN1 *= 1;
            }
            else
            {
                factorialN1 *= i;
            }
        }
        result = factorial2N / (factorialN * factorialN1);
        Console.WriteLine("{0:0.#####}", result);
    }
}
