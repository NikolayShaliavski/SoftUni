using System;
using System.Collections.Generic;

class LongestAreaInArray
{
    static void Main()
    {
        int input = int.Parse(Console.ReadLine());

        string[] userInputs = new string[input];

        for (int i = 0; i < input; i++)
        {
            userInputs[i] = Console.ReadLine();
        }

        List<string> temp = new List<string>();
        List<string> seqence = new List<string>();

        for (int i = 0; i < userInputs.Length - 1; i++)
        {
            temp.Add(userInputs[i]);
            if (i == userInputs.Length - 2 && userInputs[i] == userInputs[i + 1])
            {
                temp.Add(userInputs[i + 1]);
                break;
            }
            if (userInputs[i] == userInputs[i + 1])
            {
                continue;
            }
            else if (temp.Count > seqence.Count)
            {
                seqence.Clear();
                seqence.AddRange(temp);
                temp.Clear();
            }
        }
        if (seqence.Count >= temp.Count)
        {
            Console.WriteLine(seqence.Count);

            foreach (string element in seqence)
            {
                Console.WriteLine(element);
            }
        }
        else
        {
            Console.WriteLine(temp.Count);
            foreach (string element in temp)
            {
                Console.WriteLine(element);
            }
        }
    }
}
