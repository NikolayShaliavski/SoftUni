using System;
using System.Collections.Generic;

class PrimesInRange
{
    static List<int> PrimesInRangeMethod (int startNumber, int endNumber)
    {
        List<int> primes = new List<int>();

        if (startNumber < endNumber)
        {
            for (int i = startNumber; i <= endNumber; i++)
            {
                bool prime = true;
                for (int j = 2; j <= Math.Sqrt(i); j++)
                {
                    if (i % j == 0)
                    {
                        prime = false;
                    }
                }
                if (prime && i != 0 && i != 1)
                {
                    primes.Add(i);
                }
            }
        }
        return primes;
    }
    static void Main()
    {
        int startNumber = int.Parse(Console.ReadLine());
        int endNumber = int.Parse(Console.ReadLine());

        List<int> primesReturned = PrimesInRangeMethod(startNumber, endNumber);

        for (int i = 0; i < primesReturned.Count - 1; i++)
        {
            Console.Write(primesReturned[i] + ", ");
        }
        if (primesReturned.Count > 0)
        {
            Console.Write(primesReturned[primesReturned.Count - 1]);
            Console.WriteLine();
        }
        else
        {
            Console.WriteLine("empty List");
        }
    }
}
