using System;
using System.Collections.Generic;

class CountOfLetters
{
    static void Main()
    {
        string[] input = Console.ReadLine().Split();

        List<string> letters = new List<string>();

        foreach (string letter in input)
        {
            letters.Add(letter);
        }
        if (letters.Count == 1)
        {
            Console.WriteLine(letters[0] + " -> " + 1);
        }
        letters.Sort();
        int count = 1;

        for (int i = 0; i < letters.Count - 1; i++)
        {
            string letter = letters[i];
            if (i == letters.Count - 2 && letters[i] == letters[i + 1])
            {
                count++;
                Console.WriteLine(letter + " -> " + count);
            }
            if (letters[i] == letters[i + 1])
            {
                count++;
            }
            else if (i == letters.Count - 2 && letters[i] != letters[i + 1])
            {
                Console.WriteLine(letter + " -> " + count);
                letter = letters[i + 1];
                count = 1;
                Console.WriteLine(letter + " -> " + count);
            }
            else if (letters[i] != letters[i + 1])
            {
                Console.WriteLine(letter + " -> " + count);
                count = 1;
            }
        }
    }
}