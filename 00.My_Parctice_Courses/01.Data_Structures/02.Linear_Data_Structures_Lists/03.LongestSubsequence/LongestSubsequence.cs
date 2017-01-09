using System;
using System.Collections.Generic;
using System.Linq;

class LongestSubsequence
{
    static void Main(string[] args)
    {
        List<int> sequence = Console.ReadLine().Split().Select(int.Parse).ToList();
        var bestIndex = 0;
        var bestlength = 0;
        var currIndex = 0;
        var currLength = 1;

        for (int i = 0; i < sequence.Count() - 1; i++)
        {
            if (sequence[i] == sequence[i + 1])
            {
                currLength++;
                continue;
            }
            if (currLength > bestlength)
            {
                bestIndex = currIndex;
                bestlength = currLength;
            }
            currIndex = i + 1;
            currLength = 1;
        }
        if (currLength > bestlength)
        {
            bestlength = currLength;
            bestIndex = currIndex;
        }
        for (int i = bestIndex; i < bestIndex + bestlength; i++)
        {
            Console.Write(sequence[i] + " ");
        }
        Console.WriteLine();
    }
}

