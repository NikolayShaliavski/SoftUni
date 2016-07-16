using System;
using System.Collections.Generic;
using System.Text;

class WeirdCombinations
{
    static void Main()
    {
        string sequence = Console.ReadLine();
        int number = int.Parse(Console.ReadLine());
        List<string> combinations = new List<string>();
        StringBuilder builder = new StringBuilder();

        for (int a = 0; a < sequence.Length; a++)
        {
            for (int b = 0; b < sequence.Length; b++)
            {
                for (int c = 0; c < sequence.Length; c++)
                {
                    for (int d = 0; d < sequence.Length; d++)
                    {
                        for (int e = 0; e < sequence.Length; e++)
                        {
                            builder.Append(sequence[a]);
                            builder.Append(sequence[b]);
                            builder.Append(sequence[c]);
                            builder.Append(sequence[d]);
                            builder.Append(sequence[e]);

                            combinations.Add(builder.ToString());
                            builder.Clear();
                        }
                    }
                }
            }
        }
        if (number <= combinations.Count - 1)
        {
            Console.WriteLine(combinations[number]);
        }
        else
        {
            Console.WriteLine("No");
        }
    }
}
