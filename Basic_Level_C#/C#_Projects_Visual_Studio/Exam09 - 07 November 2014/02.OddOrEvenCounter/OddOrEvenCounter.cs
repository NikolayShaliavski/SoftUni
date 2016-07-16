using System;
using System.Collections.Generic;

class OddOrEvenCounter
{
    static void Main()
    {
        int sets = int.Parse(Console.ReadLine());
        int length = int.Parse(Console.ReadLine());
        string command = Console.ReadLine();
        int biggestCount = 0;
        int count = 0;
        int longestSet = 0;
        List<int> sequence = new List<int>();

        for (int a = 0; a < sets; a++)
        {
            for (int b = 0; b < length; b++)
            {
                int number = int.Parse(Console.ReadLine());
                sequence.Add(number);
            }

            for (int i = 0; i < sequence.Count; i++)
            {
                switch (command)
                {
                    case "odd":
                        if (sequence[i] % 2 != 0)
                        {
                            count++;
                        }
                        break;
                    case "even":
                        if (sequence[i] % 2 == 0)
                        {
                            count++;
                        }
                        break;
                    default:
                        break;
                }
            }

            if (count > biggestCount)
            {
                biggestCount = count;
                longestSet = a;
            }
            count = 0;
            sequence.Clear();
        }

        string[] ordinals = { "First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eighth", "Ninth", "Tenth" };

        if (biggestCount == 0)
        {
            Console.WriteLine("No");
        }
        else
        {
            Console.WriteLine("{0} set has the most {1} numbers: {2}", ordinals[longestSet], command, biggestCount);
        }
    }
}
