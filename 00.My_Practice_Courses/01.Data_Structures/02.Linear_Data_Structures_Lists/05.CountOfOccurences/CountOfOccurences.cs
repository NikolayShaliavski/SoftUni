using System;
using System.Collections.Generic;
using System.Linq;

class CountOfOccurences
{
    static void Main(string[] args)
    {
        var sequence = Console.ReadLine().Split().ToList();
        var occurences = new SortedDictionary<string, int>();

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
        foreach (var element in occurences)
        {
            Console.WriteLine("{0} -> {1} times", element.Key, element.Value);
        }
    }
}
