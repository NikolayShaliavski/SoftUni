using System;
using System.Collections.Generic;
using System.Linq;

class RemoveOddOccurences
{
    static void Main(string[] args)
    {
        var sequence = Console.ReadLine().Split().ToList();
        var occurences = new Dictionary<string, int>();

        for (int i = 0; i < sequence.Count(); i++)
        {
            if (!occurences.ContainsKey(sequence[i]))
            {
                occurences[sequence[i]] = 1;
            }
            else
            {
                occurences[sequence[i]]++;
            }
        }
        for (int i = 0; i < sequence.Count(); i++)
        {
            if (occurences[sequence[i]] % 2 == 0)
            {
                Console.Write(sequence[i] + " ");
            }
        }
        Console.WriteLine();
    }
}

