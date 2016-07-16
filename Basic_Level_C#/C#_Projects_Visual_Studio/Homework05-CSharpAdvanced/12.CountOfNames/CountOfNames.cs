using System;
using System.Collections.Generic;

class CountOfNames
{
    static void Main()
    {
        string[] input = Console.ReadLine().Split();

        List<string> names = new List<string>();

        foreach (string name in input)
        {
            names.Add(name);
        }
        if (names.Count == 1)
        {
            Console.WriteLine(names[0] + " -> " + 1);
        }
        names.Sort();
        int count = 1;

        for (int i = 0; i < names.Count - 1; i++)
        {
            string name = names[i];
            if (i == names.Count - 2 && names[i] == names[i + 1])
            {
                count++;
                Console.WriteLine(name + " -> " + count);
            }
            if (names[i] == names[i + 1])
            {
                count++;
            }           
            else if (i == names.Count - 2 && names[i] != names[i + 1])
            {
                Console.WriteLine(name + " -> " + count);
                name = names[i + 1];
                count = 1;
                Console.WriteLine(name + " -> " + count);
            }
            else if (names[i] != names[i + 1])
            {
                Console.WriteLine(name + " -> " + count);
                count = 1;
            }
        }
    }
}
