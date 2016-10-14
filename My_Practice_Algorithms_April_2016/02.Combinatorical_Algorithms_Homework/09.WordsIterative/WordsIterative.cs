using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

class WordsIterative
{
    //passes test for time in judge but memory tests doesn't pass
    static void Main(string[] args)
    {
        char[] letters = Console.ReadLine().ToArray();
        ISet<string> words = new HashSet<string>();
        int permutationsCounter = 0;

        if (IsPossible(letters) && !words.Contains(new string(letters)))
        {
            permutationsCounter++;
            words.Add(new string(letters));
        }

        int[] p = new int[letters.Length];
        int i = 1;

        while (i < letters.Length)
        {
            if (p[i] < i)
            {
                int j = (i % 2) == 0 ? 0 : p[i];
                Swap(letters, j, i);
                if (IsPossible(letters) && !words.Contains(new string(letters)))
                {
                    words.Add(new string(letters));
                    permutationsCounter++;
                }

                p[i]++;
                i = 1;
            }
            else
            {
                p[i] = 0;
                i++;
            }
        }
        Console.WriteLine(permutationsCounter);
    }

    private static bool IsPossible(char[] letters)
    {
        for (int i = 0; i < letters.Length - 1; i++)
        {
            if (letters[i] == letters[i + 1])
            {
                return false;
            }
        }
        return true;
    }

    private static void Swap(char[] letters, int i, int j)
    {
        if (i == j)
        {
            return;
        }
        char temp = letters[i];
        letters[i] = letters[j];
        letters[j] = temp;
    }
}

