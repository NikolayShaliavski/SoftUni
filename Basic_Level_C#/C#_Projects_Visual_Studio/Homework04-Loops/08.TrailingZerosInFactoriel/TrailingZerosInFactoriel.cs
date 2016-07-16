using System;

class TrailingZerosInFactoriel
{
    static void Main()
    {
        int factoriel = int.Parse(Console.ReadLine());
        int zeros = 0;

        for (int i = 5; i <= factoriel; i *= 5)
        {
            zeros += factoriel / i;
        }
        Console.WriteLine(zeros);
    }
}
