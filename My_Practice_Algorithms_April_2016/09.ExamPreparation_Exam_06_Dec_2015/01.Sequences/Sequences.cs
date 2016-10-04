using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

class Sequences
{
    static int max;
    static List<int> numbers;
    static StringBuilder sequences;

    static void Main(string[] args)
    {
        max = int.Parse(Console.ReadLine());
        numbers = new List<int>();
        sequences = new StringBuilder();
        GenerateSequence(0);
        Console.WriteLine(sequences);
    }

    static void GenerateSequence(int sum)
    {
        for (int i = 1; i <= max; i++)
        {
            if (sum + i <= max)
            {
                numbers.Add(i);
                SaveSequence();
                GenerateSequence(sum + i);
                numbers.RemoveAt(numbers.Count() - 1);
            }
            else
            {
                break;
            }            
        }
    }

    static void SaveSequence()
    {
        sequences.Append(string.Join(" ", numbers));
        sequences.AppendLine();
    }
}

