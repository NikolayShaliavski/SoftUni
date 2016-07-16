using System;

class PrimeCheckerMethod
{
    static bool IsPrime(ulong checkedNumber)
    {
        bool isPrime = true;
        if (checkedNumber == 0 || checkedNumber == 1)
        {
            isPrime = false;
        }
        for (ulong i = 2; i <= Math.Sqrt(checkedNumber); i++)
        {
            if (checkedNumber % i == 0)
            {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }
    static void Main()
    {
        ulong checkedNumber = ulong.Parse(Console.ReadLine());
        Console.WriteLine(IsPrime(checkedNumber));
    }
}
