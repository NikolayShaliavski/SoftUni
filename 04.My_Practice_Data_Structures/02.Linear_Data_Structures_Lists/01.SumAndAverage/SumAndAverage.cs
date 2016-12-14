using System;
using System.Collections.Generic;
using System.Linq;

class SumAndAverage
{
    static void Main(string[] args)
    {
        List<int> sequence = Console.ReadLine().Split().Select(int.Parse).ToList();
        var sum = 0;
        double average = 0;

        for (int i = 0; i < sequence.Count(); i++)
        {
            sum += sequence[i];
        }

        average = (double) sum / sequence.Count();
        Console.WriteLine("Sum = {0}; Average = {1}", sum, average);
    }
}

